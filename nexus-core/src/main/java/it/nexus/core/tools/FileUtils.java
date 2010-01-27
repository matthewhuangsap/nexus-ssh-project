package it.nexus.core.tools;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author leeon 对类路径上的文件或者文件系统上的文件进行搜索的工具类
 */
public class FileUtils {

	private static Log logger = LogFactory.getLog(FileUtils.class);

	/**
	 * 在文件系统的某个目录中搜索符合pattern要求的文件，filePath就是搜索的目录的全路径
	 * 比如filePath路径是c:/foo/bar，那么就是搜索c:/foo/bar下的所有符合patter格式的文件
	 * 如果filePath的不是目录或者不存在，那么返回一个空列表。
	 * 
	 * @param filePath
	 *            需要搜索的目录路径
	 * @param pattern
	 *            文件名(不包括文件路径)符合的模版，正则表达式的模版
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
	 * @param folder, 开始搜索的文件夹，如果文件夹为空，或者不存在，或者不是文件夹，那么返回空列表
	 * @param pattern, 搜索用的正则表达式
	 * @param prefix, 返回类路径或者是文件绝对路径的标志，如果是null，返回文件绝对路径
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
	 * 检查zip包中某个目录下的所有文件名(不包括文件路径)符合正则表达式pattern要求的文件 返回一个文件类路径的列表.
	 * folderPath是搜索的zip包中的类路径文件夹,prefix是一个其实搜索和返回路径的前缀，主要用于搜索war包时
	 * 比如一个war包，foo.war，那么zipFile时foo.war的ZipFile，要搜索pattern为*.hbm.xml，搜索的类路径就是com/guanghua/domain，而搜索的前缀就应该是/WEB-INF/classes
	 * 这时候，真正开始搜索的zip包路径是/WEB-INF/classes/com/guanghua/domain，而返回的文件类路径却是com/guanghua/domain/Test.hbm.xml的形式，返回结果会自动截取前缀
	 * 同样在搜索ear包时，也同理可以这样做。搜索jar包的化，prefix传入""即可
	 * 
	 * @param zipFile 被搜索的zip文件
	 * @param pattern 文件名必须符合的正则表达式的pattern
	 * @param folderPath
	 * @param prefix
	 * @return zip文件夹中所有符合条件的文件的类路径列表
	 */
	@SuppressWarnings("unchecked")
	public static List<String> searchFileFromZip(ZipFile zipFile,
			String pattern, String folderPath, String prefix) {
		List<String> list = new ArrayList<String>();

		// 拿到zip中所有的entry
		Enumeration e = zipFile.entries();
		folderPath = folderPath.startsWith("/") ? prefix + folderPath : prefix
				+ "/" + folderPath;
		while (e.hasMoreElements()) {
			ZipEntry zipEntry = (ZipEntry) e.nextElement();

			// 获取zip中文件的路径
			String zip = zipEntry.getName();
			zip = zip.startsWith("/") ? zip : "/" + zip;

			// 如果zipEntry的开头是prefix，那么说明这是prefix目录下的一个文件
			if (!zipEntry.isDirectory() && zip.startsWith(folderPath)) {
				// 获取真实的文件名
				String jarFileName = zip.substring(zip.lastIndexOf("/") + 1);
				// 文件名符合pattern的要求
				if (Pattern.matches(pattern, jarFileName))
					list.add(zip.substring(prefix.length() + 1));
			}
		}

		return list;
	}

	/**
	 * 在指定的类路径中搜索文件名符合pattern要求的文件
	 * 比如要搜索com/guanghua/brick下所有符合.hbm.xml结尾的文件就可以使用该方法
	 * 返回的结果是符合要求的文件的类路径,比如com/guanghua/brick/domain/foo.hbm.xml
	 * 
	 * @param classPath 指定的类路径
	 * @param pattern 文件名必须符合的pattern
	 * @return 返回在指定类路径中文件名符合要求的类路径列表
	 * @throws Exception
	 */
	public static List<String> searchFileFromClassPath(String classPath,
			String pattern) throws IOException {
		// 获取指定的类路径的定位URL
		URL[] urls = BeanUtil.getClassPathFileURLs(classPath);

		// 返回的是list，但是利用set来保证没有重复的url
		List<String> list = new ArrayList<String>();
		Set<String> set = new HashSet<String>();

		// 没有找到对应的url list，返回空
		if (urls == null)
			return list;
		for (int i = 0; i < urls.length; i++) {
			// 将搜索到的结果放入set
			set.addAll(searchFileFromClassPath(urls[i], classPath, pattern));
		}
		list.addAll(set);
		return list;
	}

	/**
	 * 根据URL确定搜索文件的路径，然后根据classPath确定搜索的类路径，再搜索文件名符合pattern格式的文件
	 * classPath所在的位置可能是jar，可能是war，可能是folder，但必须和url的位置一致。 在类路径中搜索符合pattern要求的文件
	 * 
	 * @param url 搜索的jar包或者文件夹所在的url路径，
	 * @param classPath 搜索的类路径
	 * @param pattern 文件名符合的模版
	 * @return 返回在指定类路径中文件名符合要求的类路径列表
	 * @throws IOException
	 */
	public static List<String> searchFileFromClassPath(URL url,
			String classPath, String pattern) throws IOException {

		logger.debug("search file type: [" + pattern + "]");
		logger.debug("search file in classpath: [" + classPath + "]");
		logger.debug("the real filepath is : [" + url + "]");

		if (url == null)
			return new ArrayList<String>();

		// 处理文件名
		String file = url.getFile();
		int i = file.indexOf("!");
		file = (i != -1) ? file.substring(0, i) : file;

		// 获取协议
		String protocol = url.getProtocol();
		// 如果类所在的文件路径是jar包
		if ("jar".equals(protocol)) {
			JarURLConnection jc = (JarURLConnection) url.openConnection();
			logger.debug("search jar file from :[" + url + "]");
			return searchFileFromZip(jc.getJarFile(), pattern, classPath, "");

			// 如果类所在的文件路径是wsjar包
		} else if ("wsjar".equals(protocol)) {
			// wsjar需要去掉file:/才能在ws下识别
			if (file.startsWith("file:/"))
				file = file.substring(6);
			logger.debug("search wsjar file from :[" + file + "]");
			JarFile jarFile = new JarFile(new File(URLDecoder.decode(file,
					"UTF-8")));
			return searchFileFromZip(jarFile, pattern, classPath, "");

			// 如果类所在的文件路径是zip包
		} else if ("zip".equals(protocol)) {
			// 如果类所在的文件路径是war包
			if (file.endsWith("war")) {
				logger.debug("search war file from :[" + file + "]");
				ZipFile zipFile = new ZipFile(new File(URLDecoder.decode(file,
						"UTF-8")));
				return searchFileFromZip(zipFile, pattern, classPath,
						"/WEB-INF/classes");

				// 如果类所在的文件路径是普通zip包
			} else {
				logger.debug("search zip file from :[" + file + "]");
				ZipFile zipFile = new ZipFile(new File(URLDecoder.decode(file,
						"UTF-8")));
				return searchFileFromZip(zipFile, pattern, classPath, "");
			}

			// 如果是普通的文件协议
		} else if ("file".equals(protocol)) {
			logger.debug("search filesystem folder from :[" + url + "]");
			return searchFileFromFolder(new File(URLDecoder.decode(url
					.getFile(), "UTF-8")), pattern, classPath);

			// 其余情况返回空列表
		} else
			return new ArrayList<String>();
	}

	/**
	 * 根据传入文件完整路径，获取文件所在的文件夹路径 原理是截取最后一个"/"之前的字符串作为文件夹名称
	 * 
	 * @param filePath 文件完整路径
	 * 
	 * @return 文件夹名称
	 */
	public static String getFloderName(String filePath) {
		return filePath.substring(0, filePath.lastIndexOf("/"));
	}

	/**
	 * 根据传入的文件完整路径，获取文件的名称 原理是截取最后一个"/"之后的字符串作为文件名
	 * 
	 * @param filePath 文件完整路径
	 * @return 文件名
	 */
	public static String getFileName(String filePath) {
		return filePath.substring(filePath.lastIndexOf("/") + 1);
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
