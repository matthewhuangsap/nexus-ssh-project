package it.nexus.core.service.test; /**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-4
 * Time: 8:18:05
 * To change this template use File | Settings | File Templates.
 */

import it.nexus.core.dao.BaseInfoEntityDAO;
import it.nexus.core.model.BaseInfoEntity;
import it.nexus.core.service.BaseInfoEntityService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class BIEntityServiceTest {
    @Resource
    BaseInfoEntityService baseinfoEntityService;
    @Resource
    BaseInfoEntityDAO baseinfoEntityDAO;

    @Resource
    SessionFactory sessionFactory;

    @Test
    public void testCRUD() {
        BaseInfoEntity bi_entity= new BaseInfoEntity();
        bi_entity.setName("sagaris");
        bi_entity.setCreateDate(new Date());
      /*  Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();
        session.save(bi_entity);
        t.commit();
        session.close();*/
          baseinfoEntityService.setDao(baseinfoEntityDAO);
          baseinfoEntityService.save(bi_entity);
        
    }
}
