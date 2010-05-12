package it.nexus.core.test.spring;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.util.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-5-7
 * Time: 14:37:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(
    {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class})
public class SpringContextTestCase extends Assert implements ApplicationContextAware {
  protected Logger logger = LoggerFactory.getLogger(getClass());

  protected ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

  }
}
