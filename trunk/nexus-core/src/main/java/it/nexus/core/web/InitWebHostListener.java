package it.nexus.core.web;

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
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //TODO 这里要加入各种启动初始化代码，比如ChoiceBoxSettings
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //TODO Destoryed 初始化的设置
    }
}
