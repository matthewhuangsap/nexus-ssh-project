package it.nexus.core.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 9:56:30
 * To change this template use File | Settings | File Templates.
 */
public class HibernateDAO extends HibernateDaoSupport {
    @Resource(name="sessionFactory")    //为父类HibernateDaoSupport注入sessionFactory的值
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

}
