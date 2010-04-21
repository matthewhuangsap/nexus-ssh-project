package it.nexus.plugins;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.maven.project.MavenProject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-21
 * Time: 11:19:01
 */
public final class FunctionGroup {
  private final String JAVA_DIR = "src\\main\\java";
  private final String RESOURCE_DIR = "src\\main\\resources";

  private String fgName;

  public String getFgName() {
    return fgName;
  }

  public void setFgName(String fgName) {
    this.fgName = fgName;
  }

  private MavenProject project;

  public MavenProject getProject() {
    return project;
  }

  public void setProject(MavenProject project) {
    this.project = project;
  }


  Map<String, String> dirs = new HashMap<String, String>();

  private void createDirs() {
    String groupid_dir = project.getGroupId() + "." + fgName;
    String fg_dir = groupid_dir.replace(".", "\\");

    dirs.put("controller", JAVA_DIR + "\\" + fg_dir + "\\controller");
    dirs.put("dao", JAVA_DIR + "\\" + fg_dir + "\\dao");
    dirs.put("service", JAVA_DIR + "\\" + fg_dir + "\\service");
    dirs.put("model", JAVA_DIR + "\\" + fg_dir + "\\model");
    dirs.put("view", RESOURCE_DIR + "\\" + fgName.replace(".", "\\"));

    for (Map.Entry<String, String> kvPair : dirs.entrySet()) {
      File file = new File(kvPair.getValue());
      if (!file.exists())
        file.mkdirs();
    }
  }

  public void create() throws IOException, TemplateException {
    Configuration cfg = new Configuration();
    File template_dir = new File("templates");
    if (!template_dir.exists())
      template_dir.mkdirs();
    /*2.设置模板路径*/
    cfg.setClassForTemplateLoading(this.getClass(),"/");
    createDirs();

    createController(cfg);
    createDao(cfg);
    createService(cfg);
    createModel(cfg);
    createView(cfg);
  }

  private String getFunctionGroupName(){
    String[] arrs = fgName.split("[.]");
    return arrs[arrs.length-1];
  }

  /**
   * 创建页面文件
   */
  private void createView(Configuration cfg) throws IOException, TemplateException {
    Template edit = cfg.getTemplate("templates/view_edit.ftl");
    Template list = cfg.getTemplate("templates/view_list.ftl");

    String path = dirs.get("view");
    String all_edit_path = path+"\\" +getFunctionGroupName()+"_edit.ftl";
    String all_list_path = path+"\\" +getFunctionGroupName()+"_list.ftl";
    OutputStream op_stream =
        new PrintStream(new FileOutputStream(all_edit_path));

    op_stream.flush();
    op_stream.close();
    
    /*创建一个数据模型*/
    Map root = new HashMap();
    root.put("user", "sgaris");
    Map latest = new HashMap();
    root.put("latestProduct", latest);
    latest.put("url", "products/greenmouse.html");
    latest.put("name", "green mouse");

//    /* 合并数据模型和模版*/
//    Writer out = new OutputStreamWriter(System.out);
//    edit.process(root, out);
//    list.process(root, out);
//    out.flush();
  }


  /**
   * 创建Struts2的action
   */
  private void createController(Configuration cfg) {

  }

  /**
   * 创建DAO类
   */
  private void createDao(Configuration cfg) {

  }

  /**
   * 创建 Service
   */
  private void createService(Configuration cfg) {

  }

  /**
   * 创建 Entity 类
   */
  private void createModel(Configuration cfg) {

  }
}