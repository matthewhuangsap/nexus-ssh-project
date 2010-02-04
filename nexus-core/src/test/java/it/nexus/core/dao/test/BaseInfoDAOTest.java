package it.nexus.core.dao.test;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-4
 * Time: 7:58:33
 * To change this template use File | Settings | File Templates.
 */

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseInfoDAOTest {
    @Test
    public void testCRUD(){
        
    }
}
