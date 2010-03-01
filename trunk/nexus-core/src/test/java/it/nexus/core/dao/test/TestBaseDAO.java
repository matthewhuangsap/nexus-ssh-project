package it.nexus.core.dao.test;

import it.nexus.core.dao.ExtBaseDAO;
import it.nexus.core.model.ExtBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-20
 * Time: 2:05:07
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test2.xml"})
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
public class TestBaseDAO extends AbstractTransactionalJUnit4SpringContextTests{       //

    @Resource
    ExtBaseDAO extBaseDAO;
    @Before
    public void before(){
        System.out.println("before class");    
    }

    @After
    public void after(){
        System.out.println("after class");
    }

    @Test
    @Repeat(10)
    public void check_test(){
        System.out.println("OKOKOOKK!!!");
        ExtBase eb=new ExtBase();
        eb.setName("dcriori");
        eb.setCreateDate(new Date());
        eb.setRemark("this is a sagaris test pojo!!");
        extBaseDAO.save(eb);
    }
}
