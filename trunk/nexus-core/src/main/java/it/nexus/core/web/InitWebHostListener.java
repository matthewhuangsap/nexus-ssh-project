package it.nexus.core.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
        
        logger.info("Init InitWebHostListener....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //TODO Destoryed 初始化的设置
        logger.info("Destroy InitWebHostListener....");
    }
}
