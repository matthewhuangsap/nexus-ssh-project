package it.nexus.enterprise.system;

import org.springframework.beans.factory.InitializingBean;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-8
 * Time: 16:54:52
 * To change this template use File | Settings | File Templates.
 */
public class InitAccessBean implements InitializingBean{
    @Override
    public void afterPropertiesSet() throws Exception {
         System.out.println(">>>>>>>>>>>>>>>>>init access bean>>>>>>>>>>>>>>>>>");
    }
}
