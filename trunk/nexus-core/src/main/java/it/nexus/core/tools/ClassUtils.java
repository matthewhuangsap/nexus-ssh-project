package it.nexus.core.tools;

import it.nexus.core.annotation.AccessGroup;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * tools class 
 * @author <a href="mailto:ohergal@gmail.com">ohergal</a>
 * 
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
	 * 
	 *
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
	
	/**
	 *  得到菜单资源
	 */
	
	public static List<Class<?>> getAccessGroup(String package_name){
		List<Class<?>> result_list= new ArrayList<Class<?>>(); 
		List<Class<?>> clazz_list = ClassUtils.getClasses(package_name);
		for (Class<?> clazz : clazz_list) {
			AccessGroup aGroup = clazz.getAnnotation(AccessGroup.class);
			if(aGroup!=null){
				System.out.println("ClassUtils GetAccessGroup:"+clazz.getName());
				result_list.add(clazz);
			}
		}
		return result_list;
	}

     /**
     * 根据文件所在类路径返回对应的绝对路径的文件对象。
     * 该文件的URL是类装载器在类路径上找到的第一个该路径上的文件的URL
     * @param classPath 文件类路径
     * @return 文件URL绝对路径对应的File对象
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
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
            log.debug("find file [" + classPath +"] from class path, and url is [" + o + "]");
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