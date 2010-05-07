package it.nexus.core.dao;

import it.nexus.core.model.User;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-5-7
 * Time: 10:30:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class HibernateDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
  private static final String DEFAULT_LOGIN_NAME = "sagaris";
  private HibernateDao<User, String> dao;
  @Resource
  private SessionFactory sessionFactory;

  @Resource
  private JdbcTemplate jdbcTemplate;

  @Before
  public void before() {
    dao = new HibernateDao(sessionFactory, User.class);
  }

  @Test
  public void createData() {
    User user1 = new User("sagaris", "123456");
    User user2 = new User("dcriori", "790906");
    User user3 = new User("ll", "810923");
    User user4 = new User("system", "1");
    User user5 = new User("admin", "admin");
    User user6 = new User("ddweill", "hahahah");

    dao.save(user1);
    dao.save(user2);
    dao.save(user3);
    dao.save(user4);
    dao.save(user5);
    dao.save(user6);
  }


  @Test
  public void crud() {
    User user = new User();
    user.setName("admin");
    user.setPwd("123456");
    user.setRemark("sagaris test data!");


    dao.save(user);
    user.setName("dcriori");
    dao.save(user);
    dao.delete(user);
  }


  @Test
  public void getAll(){
    List<User> users = dao.getAll("id",true);
    assertEquals(6,users.size());
    assertEquals(DEFAULT_LOGIN_NAME, users.get(0).getName());
  }

}
