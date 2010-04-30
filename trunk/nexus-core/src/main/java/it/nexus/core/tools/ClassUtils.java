package it.nexus.core.tools;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * tools class
 *
 * @author <a href="mailto:dcriori@gmail.com">dcriori</a>
 */
public class ClassUtils {
    private static final Log log = LogFactory.getLog(ClassUtils.class);

    /*
      * get all class name
      */

    public static String[] getPackageAllClassName(String classLocation,
                                                  String packageName) {
        // 将packageName分解
        String[] packagePathSplit = packageName.split("[.]");
        String realClassLocation = classLocation;
        int packageLength = packagePathSplit.length;
        for (int i = 0; i < packageLength; i++) {
            realClassLocation = realClassLocation + File.separator
                    + packagePathSplit[i];
        }
        File packeageDir = new File(realClassLocation);
        if (packeageDir.isDirectory()) {
            String[] allClassName = packeageDir.list();
            return allClassName;
        }
        return null;
    }

    /**
     * 从包package中获取所有的Class
     */
    public static List<Class<?>> getClasses(String packageName) {

        // 第一个class类的集合
        List<Class<?>> classes = new ArrayList<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        //  get package name and replace
//		String packageName = pack.getName();
        System.out.println(packageName);
        String packageDirName = packageName.replace('.', '/');
        System.out.println(packageDirName);
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // get next element
                URL url = dirs.nextElement();
                // get agree on name
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

                    findAndAddClassesInPackageByFile(packageName, filePath,
                            recursive, classes);
                } else if ("jar".equals(protocol)) {
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection())
                                .getJarFile();
                        Enumeration<JarEntry> entries = jar.entries();

                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            if (name.charAt(0) == '/') {
                                name = name.substring(1);
                            }
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                if (idx != -1) {
                                    packageName = name.substring(0, idx)
                                            .replace('/', '.');
                                }
                                if ((idx != -1) || recursive) {
                                    if (name.endsWith(".class")
                                            && !entry.isDirectory()) {
                                        String className = name.substring(
                                                packageName.length() + 1, name
                                                        .length() - 6);
                                        try {
                                            classes.add(Class
                                                    .forName(packageName + '.'
                                                    + className));
                                        } catch (ClassNotFoundException e) {
                                            log
                                                    .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        log.error("在扫描用户定义的视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName
     * @param packagePath
     * @param recursive
     * @param classes
     */
    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath, final boolean recursive, List<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                        + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    classes.add(Class.forName(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }

    public static Class<?> getGenericType(Class<?> clazz, int index) {
        return (Class<?>) ((ParameterizedType) clazz.getGenericSuperclass())
                .getActualTypeArguments()[index];
    }

    public static List<Class<?>> getControllers(String package_name) {
        List<Class<?>> clazz_list = new ArrayList<Class<?>>();
        List<Class<?>> clazzes = ClassUtils.getClasses(package_name);
        for (Class<?> clazz : clazzes) {
            String clazz_name = clazz.getName();
            if (!clazz_name.endsWith("Action")
                    && !clazz_name.endsWith("Controller"))
                continue;
            try {
                Class<?> clazz_instance = Class.forName(clazz_name);
                clazz_list.add(clazz_instance);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return clazz_list;
    }

//    /**
//     * 得到菜单资源
//     */
//
//    public static List<Class<?>> getAccessGroup(String package_name) {
//        List<Class<?>> result_list = new ArrayList<Class<?>>();
//        List<Class<?>> clazz_list = ClassUtils.getClasses(package_name);
//        for (Class<?> clazz : clazz_list) {
//            AccessGroup aGroup = clazz.getAnnotation(AccessGroup.class);
//            if (aGroup != null) {
//                System.out.println("ClassUtils GetAccessGroup:" + clazz.getName());
//                result_list.add(clazz);
//            }
//        }
//        return result_list;
//    }

    /**
     * 根据文件所在类路径返回对应的绝对路径的文件对象。
     * 该文件的URL是类装载器在类路径上找到的第一个该路径上的文件的URL
     *
     * @param classPath 文件类路径
     * @return 文件URL绝对路径对应的File对象
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     *
     * @throws java.net.URISyntaxException
     */
    public static File getClassPathFile(String classPath) throws FileNotFoundException {
        URL url = getClassPathFileURL(classPath);
        if (url != null && "file".equals(url.getProtocol()))
            try {
                return new File(url.toURI());

                //toURI如果有空格，可能会抛出错误，所以需要进行另外的处理
            } catch (URISyntaxException e) {
                log.warn("url to URI syntax exception, maybe jdk bug");
                log.warn(e);

                String p = url.getFile();
                if (p.charAt(0) == '/' && p.charAt(2) == ':') p = p.substring(1);
                return new File(p);
            }
        else if (url != null)
            throw new FileNotFoundException("this file [" + url + "] in class path:[" + classPath + "] not a filesystem file");
        else
            throw new FileNotFoundException("this file not in class path:[" + classPath + "]");
    }

    /**
     * 根据文件所在类路径返回该文件对应的绝对URLs。
     * 该URL数组是类装载器在类路径上找到的所有该路径文件的URL的集合
     * URL格式可能是file:/D:/foo/bar/classes/com/guanghua/brick/aaa.class
     * 也有可能是jar:file:/D:/foo/bar/brick.jar!com/guanghua/brick/aaa.class等等
     *
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
            set.add((URL) o);
            log.debug("find file [" + classPath + "] from class path, and url is [" + o + "]");
        }
        return (URL[]) set.toArray(new URL[0]);
    }

    /**
     * 根据文件所在类路径返回该文件对应的绝对URL。
     * 该URL是类装载器在类路径上找到的第一个该路径上的文件的URL
     * URL格式可能是file:/D:/foo/bar/classes/com/guanghua/brick/aaa.class
     * 也有可能是jar:file:/D:/foo/bar/brick.jar!com/guanghua/brick/aaa.class等等
     *
     * @param classPath 文件类路径
     * @return 文件所在绝对路径URL
     * @throws Exception
     */
    public static URL getClassPathFileURL(String classPath) {
        return Thread.currentThread().getContextClassLoader().getResource(classPath);
    }

    /**
     * 根据文件所在类路径返回对应文件的输入流。
     *
     * @param classPath 文件类路径
     * @return 该文件的输入流
     */
    public static InputStream getClassPathFileByInputStream(String classPath) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(classPath);
    }

    public static String convertClassNameToResourcePath(String className) {
        return className.replace('.', '/');
    }

    public static String convertResourcePathToClassName(String resourcePath) {
        return resourcePath.replace('/', '.');
    }

    /**
     * 使用Spring的框架获取匹配的package下的类
     *
     * @param matchPattern 例：it.nexus.**.model
     * @return
     * @throws IOException
     */
    public static List<Class> listMatchingClasses(String matchPattern) throws IOException {
        List<Class> classes = new LinkedList<Class>();
        PathMatchingResourcePatternResolver scanner = new PathMatchingResourcePatternResolver();
        Resource[] resources = scanner.getResources(matchPattern);

        for (Resource resource : resources) {
            Class<?> clazz = getClassFromResource(resource);
            classes.add(clazz);
        }

        return classes;
    }

    /**
     * 将Spring Resource 转成Class
     * TODO:这里有些问题，因为得到的resourceUri一般是绝对路径
     * URL base = this.getClass().getResource("/");
     *
     * @param resource
     * @return
     */
    public static Class getClassFromResource(Resource resource) {
        try {
            String resourceUri = resource.getURI().toString();
            resourceUri = resourceUri.replace(resourceUri.substring(resourceUri.indexOf(".class")), "").replace("/", ".");
            // try printing the resourceUri before calling forName, to see if it is OK.
            return Class.forName(resourceUri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 在文件系统的某个目录中搜索符合pattern要求的文件，filePath就是搜索的目录的全路径
     * 比如filePath路径是c:/foo/bar，那么就是搜索c:/foo/bar下的所有符合patter格式的文件
     * 如果filePath的不是目录或者不存在，那么返回一个空列表。
     *
     * @param filePath 需要搜索的目录路径
     * @param pattern  文件名(不包括文件路径)符合的模版，正则表达式的模版
     * @return 返回搜索到的文件全路径列表
     */
    public static List<String> searchFileFromFolder(String filePath,
                                                    String pattern) {
        return searchFileFromFolder(getFileResource(filePath), pattern, null);
    }

    /**
     * 检查文件系统某个目录下所有名字(非路径,只包括文件名)符合正则表达式pattern要求的文件 返回的结果是一个String的列表，可以有两种结果
     * 如果prefix为null，列表里的字符串就是符合要求的文件的绝对路径
     * 如果prefix不为null，列表里的字符串就是类路径格式的字符串，比如在c:/foo/bar下搜索,prefix为foo1/bar1
     * 搜索到一个文件是c:/foo/bar/test/Test.class,那么返回的list中的字符串就是foo1/bar1/test/Test.class
     * 如果prefix传的是"",那么返回的字符串就是/test/Test.class
     *
     * @param folder,  开始搜索的文件夹，如果文件夹为空，或者不存在，或者不是文件夹，那么返回空列表
     * @param pattern, 搜索用的正则表达式
     * @param prefix,  返回类路径或者是文件绝对路径的标志，如果是null，返回文件绝对路径
     * @return 返回list,符合list要求的每一个对象是该文件的绝对路径
     */
    public static List<String> searchFileFromFolder(File folder,
                                                    final String pattern, String prefix) {
        List<String> re = new ArrayList<String>();

        // 给prefix加上/，以便于递归
        if (prefix != null && prefix.length() != 0)
            prefix = prefix + "/";

        // 当文件夹存在，并且是文件夹时，才进行搜索
        if (folder.exists() && folder.isDirectory()) {

            // 获取符合pattern条件的文件列表
            File[] files = folder.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    // 如果该文件是隐藏的,不继续寻找
                    if (file.isHidden())
                        return false;
                    else {
                        // 如果该文件是目录那么继续寻找他的儿子
                        if (file.isDirectory())
                            return true;
                        else {
                            // 如果不是目录,则检验其文件名是否满足要求
                            return Pattern.matches(pattern, file.getName());
                        }
                    }
                }
            });

            // 遍历文件列表，看是否是目录或者文件，是目录就递归，是文件就根据prefix返回
            for (int i = 0; files != null && i < files.length; i++) {
                if (files[i].isDirectory()) {
                    re
                            .addAll(searchFileFromFolder(files[i], pattern,
                                    prefix == null ? null : prefix
                                            + files[i].getName()));
                } else {
                    // 需要返回文件路径
                    if (prefix == null)
                        re.add(files[i].getAbsolutePath());
                        // 需要返回类路径
                    else
                        re.add(prefix + files[i].getName());
                }
            }
        }
        return re;
    }

    /**
     * 根据文件路径（绝对路径）直接返回文件对象。 相当于new File(filePath)
     *
     * @param filePath 文件的绝对路径
     * @return 文件对象
     */
    public static File getFileResource(String filePath) {
		return new File(filePath);
	}
    
}