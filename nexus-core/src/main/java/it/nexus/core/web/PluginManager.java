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
 */
public final class PluginManager {
  private static Map<String, BasePlugin> plugins = new HashMap<String, BasePlugin>();
  private static Map<String, Map<String, Map<Long, String>>> pluginMap = new TreeMap<String, Map<String, Map<Long, String>>>();
  private static Map<String, String> pluginSimpleInfo = new HashMap<String, String>();
  private static List<Class<?>> actionClassList = new ArrayList<Class<?>>();

  public static Map<String, BasePlugin> getPlugins() {
    return plugins;
  }

  public static Map<String, Map<String, Map<Long, String>>> getPluginMap() {
    return pluginMap;
  }

  public static Map<String, String> getPluginSimpleInfo() {
    return pluginSimpleInfo;
  }

  public static List<Class<?>> getActionClassList() {
    return actionClassList;
  }


  public static void init(List<String> jar_list) {
    for (String jar_path : jar_list) {
      initPlugin(jar_path);
    }
  }

  private static void initFuncGroup(List<?> securityList,String pluginName) {
    Map<String, Map<Long, String>> module_map = findAccessGroupMap(pluginName);
    Iterator<?> iterator = securityList.iterator();
    while (iterator.hasNext()) {
      Element element = (Element) iterator.next();
      System.out.println(">>>>>>>>>"+element.asXML());
      if (XmlUtils.elementIsExistChild(element)) {
        List<?> funcList = element.elements();//
        Map<Long,String> function_map = createFunctionRole(funcList);
        module_map.put(element.attributeValue("name"),function_map);
      }
    }
  }

  /**
   * @param pluginName
   * @param pluginDispname
   * @desc 存放简单plugin信息
   */
  private static void createPluginInfo(final String pluginName,
                                       final String pluginDispname) {
    if (pluginSimpleInfo == null)
      pluginSimpleInfo = new HashMap<String, String>();
    if (!pluginSimpleInfo.containsKey(pluginName)) {
      pluginSimpleInfo.put(pluginName, pluginDispname);
    }
  }

  private static Map<String, Map<Long, String>> findAccessGroupMap(String pluginName) {
    if (pluginMap == null)
      pluginMap = new TreeMap<String, Map<String, Map<Long, String>>>();
    if (!pluginMap.containsKey(pluginName))
      pluginMap.put(pluginName,
          new HashMap<String, Map<Long, String>>());
    return pluginMap.get(pluginName);
  }

  private static Map<Long,String> createFunctionRole(List<?> funcList){
    Map<Long, String> result = new HashMap<Long, String>();
    Iterator<?> iterator = funcList.iterator();
    while (iterator.hasNext()) {
      Element element = (Element) iterator.next();
      result.put(new Long(element.attributeValue("index")),
          (String)element.attributeValue("name"));

    }
    return result;
  }

  public static void initPlugin(String jar_path) {
    JarFile jarFile = null;
    Menu menu = new Menu();
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

          Element element = document.getRootElement();
          String plugin_class = element.attributeValue("class");
          String plugin_name = element.attributeValue("name");
          String plugin_dispname = element.attributeValue("displayName");          
          createPluginInfo(plugin_name, plugin_dispname);
          //检查
          checkRequire(document);

          //取得菜单
          List<?> menu_list = XmlUtils.getElementList(document, "/Plugin/Menus/Menu", "@id");
          initMenus(menu_list, menu);
          //取得权限
          List<?> security_list = XmlUtils.getElementList(document, "/Plugin/Security/FunctionGroup");
          initFuncGroup(security_list,plugin_name);

          //创建Plugin缓存
          BasePlugin bp = (BasePlugin) Class.forName(plugin_class).newInstance();
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

  private static void checkRequire(Document document) {
    
  }

  protected static void initMenus(List<?> list, Menu menu) throws NexusException {

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
        initMenus(childList,childMenu);
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

  /**
   * 初始化模块依赖关系
   * @param doc
   * @return
   */
  private static List<String> initRequire(Document doc){
    //TODO 完善这里获取模块的依赖关系
    return null;
  }
}
