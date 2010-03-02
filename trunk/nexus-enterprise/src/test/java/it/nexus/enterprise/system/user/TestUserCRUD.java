package it.nexus.enterprise.system.user;

import it.nexus.enterprise.system.user.model.User;
import it.nexus.enterprise.system.user.service.UserService;
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
 * Time: 16:41:13
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager",defaultRollback = false)
public class TestUserCRUD  extends AbstractTransactionalJUnit4SpringContextTests {                //
    @Resource
    UserService userService;

    @Test
    public void TestSave(){
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");

        userService.save(user);
        System.out.println("out put test string!!!");
    }

}
