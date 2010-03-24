package it.nexus.core.web;

import com.opensymphony.xwork2.util.ClassLoaderUtil;
import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.core.SettingClass;
import it.nexus.core.tools.ActionUtils;
import it.nexus.core.tools.FileUtils;
import it.nexus.core.tools.xml.XmlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 1:10:29
 * To change this template use File | Settings | File Templates.
 */
public class InitWebHostListener implements  ServletContextListener {
    private static Log logger = LogFactory.getLog(InitWebHostListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //TODO 这里要加入各种启动初始化代码，比如ChoiceBoxSettings
        //TODO init choice box setting
        //TODO init plugin menus
        //TODO init plugin security
        URL base = this.getClass().getResource("/");
        logger.info("输出当前类的根路径："+base.getFile());
        
        logger.info("Init InitWebHostListener....");
        String lib_path = sce.getServletContext().getRealPath("/WEB-INF/lib");

        List<String> jar_list = new ArrayList<String>();
        // TODO: 一个约定，目前只是临时的，只搜索以［-SNAPSHOT］结尾的jar包，把这种jar包做为plugin
        List<String> plugin_list = FileUtils.searchFileFromFolder(lib_path, "[a-zA-Z]*.*-SNAPSHOT\\.jar");
        for (String jar_path : plugin_list) {
            jar_list.add(jar_path);
            
        }

        PluginManager.init(jar_list);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //TODO Destoryed 初始化的设置
        logger.info("Destroy InitWebHostListener......");
    }
}
