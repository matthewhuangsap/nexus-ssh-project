package it.nexus.core.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-4
 * Time: 6:50:10
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})//②
public class BaseDAOTest {
    @Test
    public void testEnvironment(){
        System.out.println("==test spring test environment success!!!!!!==");
    }

    
}
