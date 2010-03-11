package it.nexus.core.tools;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-11
 * Time: 17:09:10
 * To change this template use File | Settings | File Templates.
 */
public class TestClassUtils {
    private final String RESOURCE_PATTERN ="/**/*.class";
    private final String RESOURCE_XML_PATTERN ="/**/*.xml";
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
     @Test
    public void testGetResource() throws IOException {

        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
							ClassUtils.convertClassNameToResourcePath("it.**.resource") + RESOURCE_PATTERN;
        URL base = this.getClass().getResource("/");
        System.out.println(base.getFile());
        try {

            Resource[] resources = this.resourcePatternResolver.getResources(pattern);
            Assert.assertEquals(resources.length,4);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    String resourceUri = resource.getURL().getFile();
                    resourceUri  = resourceUri.replace(base.getFile(),"");
                    System.out.println(resourceUri);
                    resourceUri = resourceUri.replace(resourceUri.substring(resourceUri.indexOf(".class")),"").replace("/", ".");
                    System.out.println(resourceUri);
                    Object obj = Class.forName(resourceUri);
                    System.out.println(obj.getClass().getName());

                }
		     }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void testGetResourceFile(){
        String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
							ClassUtils.convertClassNameToResourcePath("it.**.resource") + RESOURCE_XML_PATTERN;
        try {

            Resource[] resources = this.resourcePatternResolver.getResources(pattern);
            for (Resource resource : resources) {
                System.out.println(resource.getFile());
		     }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
