package it.nexus.core.service.test;

import it.nexus.core.model.ExtBase;
import it.nexus.core.service.ExtBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-1
 * Time: 10:39:14
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test2.xml"})
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
public class TestExtBaseService extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    ExtBaseService extBaseService;
    @Test
    public void test_service(){
        ExtBase eb = new ExtBase();
        eb.setName("sagaris");
        eb.setIsLock(true);
        eb.setRemark("okokookk!!!");
        extBaseService.save(eb);
    }
}
