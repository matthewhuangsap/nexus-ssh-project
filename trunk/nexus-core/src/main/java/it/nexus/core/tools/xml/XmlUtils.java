package it.nexus.core.tools.xml;

import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class XmlUtils {
	/**
	 * 载入XML数据，通过文件路径的方式
	 * @param xml_path
	 * @return
	 */
	public static Document loadDocument(String xml_path) {
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(new File(xml_path));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 载入XML数据，通过InputStream的方式
	 * @param is_xml
	 * @return
	 */
	public static Document loadDocument(InputStream is_xml){
		Document document = null;
		SAXReader reader = new SAXReader();
		try {
			document = reader.read(is_xml);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 获取document中某一个Element
	 * @param document
	 * @param element_name
	 * @return
	 */
	public static Element getElement(Document document, String element_name) {
		Element root = null;
		root = document.getRootElement();
		Element element = root.element(element_name);
		return element;
	}
	
	/**
	 * 通过xpath的方式，获取Attribute的值
	 * @param document
	 * @param xpath
	 * @return
	 */
	public static String findValue(Document document, String xpath) {
		String result = "";
		List<?> list = document.selectNodes(xpath);
		Iterator<?> iterator = list.iterator();
		if (iterator.hasNext()) {
			Attribute attribute = (Attribute) iterator.next();
			result = attribute.getValue();
		}
		return result;
	}
	
	//这个有问题，准备放弃
	public static String getNamedQueryValue(Document document,
			String datakind_name) {
		String result = "";
		List<?> list = document.selectNodes("/datakinds/datakind[@name=\""
				+ datakind_name + "\"]/@query");
		Iterator<?> iterator = list.iterator();
		if (iterator.hasNext()) {
			Attribute attribute = (Attribute) iterator.next();
			result = attribute.getValue();
		}
		return result;
	}

	/**
	 * 通过xpath的方式，取得document中某一种结点的集合
	 * @param document
	 * @param xpath
	 * @return
	 */
	public static List<?> getElementList(Document document, String xpath) {
		return document.selectNodes(xpath);
	}
	
	
	public static List<?> getElementList(Document document,String xpath,String rule){
		return document.selectNodes(xpath,rule);
	}
	
	/**
	 * 检查Element是否存在子结点
	 * @param element
	 * @return
	 */
	public static boolean elementIsExistChild(Element element) {
		List<?> childs = element.elements();
		if (childs.size() > 0)
			return true;
		return false;
	}

	/**
	 * 检查当前Element中是否存在某一属性名称
	 * @param element
	 * @param attr_name
	 * @return
	 */
	public static boolean elementIsExistAttribute(Element element,
			String attr_name) {
		Attribute attribute = element.attribute(attr_name);
		if(attribute!=null)
			return true;
		return false;
	}
	
	/**
	 * 检查某一Element上是否有子结点
	 * @param element
	 * @return
	 */
	public static List<?> getElementChilds(Element element){
		
		return null;
	}
	
}
