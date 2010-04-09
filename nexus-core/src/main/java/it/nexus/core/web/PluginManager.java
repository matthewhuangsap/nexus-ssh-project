package it.nexus.core.web;

import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.core.menu.Menu;
import it.nexus.core.tools.xml.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-9
 * Time: 13:24:54
 * To change this template use File | Settings | File Templates.
 */
public final class PluginManager {
  private static Map<String, BasePlugin> plugins = new HashMap<String, BasePlugin>();

  public static void init(List<String> jar_list) {
    for (String jar_path : jar_list) {
      initPlugin(jar_path);
      initAccessInfo(jar_path);
    }
  }

  private static void initAccessInfo(String jar_path) {
    JarFile jarFile = null;
    try {
      jarFile = new JarFile(jar_path);
      Enumeration<?> enu = jarFile.entries();
      while (enu.hasMoreElements()) {
        JarEntry entry = (JarEntry) enu.nextElement();
        String name = entry.getName();
        if (name.endsWith("Controller.class")||name.endsWith("Action.class")) {
//          System.out.println("###########class name :" + name);
          String className = name.replace(".class","").replace("/",".");
          Class clazz = Class.forName(className);
          
        }
      }
    } catch (IOException e) {
      e.printStackTrace(); 
    } catch (ClassNotFoundException e) {
      e.printStackTrace();  
    }

  }

  public static void initPlugin(String jar_path) {
    JarFile jarFile = null;
    try {
      jarFile = new JarFile(jar_path);
      Enumeration<?> enu = jarFile.entries();
      while (enu.hasMoreElements()) {
        JarEntry entry = (JarEntry) enu.nextElement();
        String name = entry.getName();
        if (name.equals("plugin.xml")) {
          InputStream input = null;
          input = jarFile.getInputStream(entry);
          Document document = XmlUtils.loadDocument(input);
          //取得菜单
          Menu menu = new Menu();
          List<?> menu_list = XmlUtils.getElementList(document, "/Plugin/Menus/Menu", "@id");
          initMenus(menu_list, document, menu);
          System.out.println(">>>>>>>>>>>>>>:" + menu.toString());
          //取得依赖 plugin list
          List<String> requires = initRequire(document);

          Element element = document.getRootElement();
          String clazz_name = element.attributeValue("class");
          String plugin_name = element.attributeValue("name");
          BasePlugin bp = (BasePlugin) Class.forName(clazz_name).newInstance();
          bp.Init();  //初始化DataKind
          bp.setMenus(menu);
          plugins.put(plugin_name, bp);
        }
      }
      jarFile.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NexusException e) {
      e.printStackTrace();
    }
  }

  protected static void initMenus(List<?> list, Document doc, Menu menu) throws NexusException {

    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
      Element element = (Element) iterator.next();
      if (XmlUtils.elementIsExistChild(element)) {
        Menu childMenu = new Menu(
            element.attributeValue("id"),
            element.attributeValue("name"),
            "",
            true);
        menu.add(childMenu);
        List<?> childList = element.elements();
        initMenus(childList, doc, childMenu);
      } else {
        if (!XmlUtils.elementIsExistAttribute(element, "url")) {
          continue;
        }
        Menu linkMenu = new Menu(
            element.attributeValue("id"),
            element.attributeValue("name"),
            element.attributeValue("url"),
            element.attributeValue("roles"),
            false);
        menu.add(linkMenu);
      }
    }
  }

  protected static List<String> initRequire(Document doc) {
    return null;
  }

  public static Map<String, BasePlugin> getPlugins() {
    return plugins;
  }
}
