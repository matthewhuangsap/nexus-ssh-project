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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BIEntityServiceTest {
    @Resource
    BaseInfoEntityService baseinfoEntityService;
    @Resource
    BaseInfoEntityDAO baseinfoEntityDAO;
    @Test
    public void testCRUD() {
        BaseInfoEntity bi_entity= new BaseInfoEntity();
        bi_entity.setName("sagaris");
        bi_entity.setCreateDate(new Date());
//        baseinfoEntityService.setDao(baseinfoEntityDAO);

        baseinfoEntityDAO.save(bi_entity);
        
      
    }
}
