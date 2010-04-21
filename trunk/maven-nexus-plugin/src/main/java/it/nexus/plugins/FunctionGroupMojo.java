package it.nexus.plugins;

import freemarker.template.TemplateException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-20
 * Time: 9:07:04
 *
 * @goal create
 * @requiresProject true
 * @requiresReports true
 */
public class FunctionGroupMojo extends AbstractMojo {
  
  
  /**
   * @parameter expression="${basedir}"
   */
  private File basedir;

  /**
   * 取得function group 的名称
   * @parameter expression="${fgName}"
   */
  private String fgName;

  /**
   * The project artifact, which should have the LATEST metadata added to it.
   *
   * @parameter expression="${project}"
   * @required
   * @readonly
   */
  private MavenProject project;

  public void execute() throws MojoExecutionException, MojoFailureException {
    getLog().info("====================================================");
    getLog().info("=====Begin Generate Nexus Function Group Moduel=====");
    getLog().info("====================================================");


    getLog().info("project dir is:" + basedir.getAbsolutePath());
    getLog().info("group id is :" + project.getGroupId());
    getLog().info("artifact id is :" + project.getArtifactId());

    /** 创建 所需要的目录 **/
    FunctionGroup fg = new FunctionGroup();
    fg.setFgName(fgName);
    fg.setProject(project);

    try {
      //创建FunctionGroup
      fg.create();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TemplateException e) {
      e.printStackTrace();  
    }

    getLog().info("====================================================");
    getLog().info("===== End Generate Nexus Function Group Moduel =====");
    getLog().info("====================================================");
  }

  
}
