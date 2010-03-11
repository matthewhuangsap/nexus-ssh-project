package it.nexus.core.tools;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-11
 * Time: 14:59:56
 * To change this template use File | Settings | File Templates.
 */

public class TestRegexUtils {
    private PathMatcher pathMatcher = new AntPathMatcher();
   

    @Test
    public void testStringRegex(){
        Assert.assertTrue(pathMatcher.isPattern("it.nexus.*.model"));
        Assert.assertTrue(RegexUtils.match("it.nexus.system.model","it.nexus.*.model"));
        Assert.assertTrue(RegexUtils.match("it.nexus.ok.system.model","it.nexus.*.model"));
        Assert.assertTrue(RegexUtils.match("it.nexus.model","it.nexus.*.model"));
    }

   
}
