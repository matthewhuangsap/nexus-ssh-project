package it.nexus.core.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 9:56:30
 * To change this template use File | Settings | File Templates.
 */
public final class HibernateDAO {
    
    @Resource(name="sessionFactory")    //为父类HibernateDaoSupport注入sessionFactory的值
    public static ThreadLocal<SessionFactory> sessionFactory;
    
}
