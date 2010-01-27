package it.nexus.core.tools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
  
/**  
 * @author leeon  
 * 对类路径上文件操作的一些工具类  
 */  
public class BeanUtil {   
  
    private static Log logger = LogFactory.getLog(BeanUtil.class);   
       
       
    /**  
     * 根据文件所在类路径返回对应的绝对路径的文件对象。  
     * 该文件的URL是类装载器在类路径上找到的第一个该路径上的文件的URL  
     * @param classPath 文件类路径  
     * @return 文件URL绝对路径对应的File对象  
     * @throws FileNotFoundException  
     * @throws UnsupportedEncodingException  
     * @throws URISyntaxException   
     */  
    public static File getClassPathFile(String classPath) throws FileNotFoundException {   
        URL url = getClassPathFileURL(classPath);   
        if (url != null && "file".equals(url.getProtocol()))   
        try {   
            return new File(url.toURI());   
           
        //toURI如果有空格，可能会抛出错误，所以需要进行另外的处理   
        } catch (URISyntaxException e) {   
            logger.warn("url to URI syntax exception, maybe jdk bug");   
            logger.warn(e);   
               
            String p = url.getFile();   
            if (p.charAt(0) == '/' && p.charAt(2) == ':') p = p.substring(1);   
            return new File(p);   
        }   
        else if (url != null)   
            throw new FileNotFoundException("this file [" + url + "] in class path:[" + classPath + "] not a filesystem file");   
        else  
            throw new FileNotFoundException("this file not in class path:["+ classPath + "]");   
    }   
  
    /**  
     * 根据文件所在类路径返回该文件对应的绝对URLs。  
     * 该URL数组是类装载器在类路径上找到的所有该路径文件的URL的集合  
     * URL格式可能是file:/D:/foo/bar/classes/com/guanghua/brick/aaa.class  
     * 也有可能是jar:file:/D:/foo/bar/brick.jar!com/guanghua/brick/aaa.class等等  
     * @param classPath 文件类路径  
     * @return 文件所在绝对路径URL的数组  
     * @throws IOException   
     */  
    @SuppressWarnings("unchecked")
	public static URL[] getClassPathFileURLs(String classPath) throws IOException {   
        Enumeration e = Thread.currentThread().getContextClassLoader().getResources(classPath);   
           
        //将url放入hashset以去掉重复的url   
        HashSet<URL> set = new HashSet<URL>();   
        while (e.hasMoreElements()) {   
            Object o = e.nextElement();   
            set.add((URL)o);   
            logger.debug("find file [" + classPath +"] from class path, and url is [" + o + "]");   
        }   
           
        return (URL[]) set.toArray(new URL[0]);   
    }   
  
    /**  
     * 根据文件所在类路径返回该文件对应的绝对URL。  
     * 该URL是类装载器在类路径上找到的第一个该路径上的文件的URL  
     * URL格式可能是file:/D:/foo/bar/classes/com/guanghua/brick/aaa.class  
     * 也有可能是jar:file:/D:/foo/bar/brick.jar!com/guanghua/brick/aaa.class等等  
     * @param classPath 文件类路径  
     * @return 文件所在绝对路径URL  
     * @throws Exception  
     */  
    public static URL getClassPathFileURL(String classPath) {   
        return Thread.currentThread().getContextClassLoader().getResource(classPath);   
    }   
  
    /**  
     * 根据文件所在类路径返回对应文件的输入流。  
     * @param classPath 文件类路径  
     * @return 该文件的输入流  
     */  
    public static InputStream getClassPathFileByInputStream(String classPath) {   
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(classPath);   
    }   
    
    
}  