package it.nexus.core.tools;

import org.apache.struts2.StrutsSpringTestCase;
import org.apache.struts2.StrutsTestCase;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-8
 * Time: 14:01:56
 * To change this template use File | Settings | File Templates.
 */
public class TestActionUtils extends StrutsSpringTestCase {
    public void testGetRequest() throws ServletException, UnsupportedEncodingException {
        executeAction("/system/dept/save"); 
    }
}
