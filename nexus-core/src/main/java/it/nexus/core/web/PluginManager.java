package it.nexus.core.web;

import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.menu.Menu;
import it.nexus.core.models.enums.EnumUtil;
import it.nexus.core.tools.PluginClassUtil;
import it.nexus.core.tools.xml.XmlUtils;
import org.apache.commons.lang.enums.ValuedEnum;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
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
  private static Map<String, Map<String, Map<Long, String>>> accessGroupMap = new TreeMap<String, Map<String, Map<Long, String>>>();
  private static Map<String, String> pluginSimpleInfo = new HashMap<String, String>();
  private static List<Class<?>> actionClassList = new ArrayList<Class<?>>();

  public static Map<String, BasePlugin> getPlugins() {
    return plugins;
  }

  public static Map<String, Map<String, Map<Long, String>>> getAccessGroupMap() {
    return accessGroupMap;
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
      initAccessInfo(jar_path);
    }
  }

  /**
   * 将Controller上的功能函数上的权限收集起来
   *
   * @param jar_path
   */
  private static void initAccessInfo(String jar_path) {
    JarFile jarFile = null;
    try {
      jarFile = new JarFile(jar_path);
      Enumeration<?> enu = jarFile.entries();
      while (enu.hasMoreElements()) {
        JarEntry entry = (JarEntry) enu.nextElement();
        String name = entry.getName();
        if (name.endsWith("Controller.class") || name.endsWith("Action.class")) {
          String className = name.replace(".class", "").replace("/", ".");
          Class clazz = Class.forName(className);
          AccessGroup ag = (AccessGroup) clazz.getAnnotation(AccessGroup.class);
          if (ag != null) {
            actionClassList.add(clazz);
            findAccessGroup(clazz);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

  }

  private static void findAccessGroup(Class<?> action_clazz) {
    AccessGroup group = action_clazz.getAnnotation(AccessGroup.class);
    Map<Long, String> access_actions = createAccessAction(action_clazz);
    String plugin_name = PluginClassUtil
        .getPluginName(group.pluginClass());
    String plugin_dispname = PluginClassUtil.getPluginDispName(group
        .pluginClass());
    Map<String, Map<Long, String>> access_map = findAccessGroupMap(plugin_name);
    createPluginInfo(plugin_name, plugin_dispname);
    PluginClassUtil.getPluginDispName(group.pluginClass());

    access_map.put(group.name(), access_actions);
  }

  /**
   * @param pluginName
   * @param pluginDispname
   * @desc 存放简单plugin信息
   */
  @SuppressWarnings("unused")
  private static void createPluginInfo(final String pluginName,
                                       final String pluginDispname) {
    if (pluginSimpleInfo == null)
      pluginSimpleInfo = new HashMap<String, String>();
    if (!pluginSimpleInfo.containsKey(pluginName)) {
      pluginSimpleInfo.put(pluginName, pluginDispname);
    }
  }

  private static Map<String, Map<Long, String>> findAccessGroupMap(String tree_name) {
    if (accessGroupMap == null)
      accessGroupMap = new TreeMap<String, Map<String, Map<Long, String>>>();
    if (!accessGroupMap.containsKey(tree_name))
      accessGroupMap.put(tree_name,
          new HashMap<String, Map<Long, String>>());
    return accessGroupMap.get(tree_name);
  }

  /**
   * @param actionClazz
   * @return
   * @description 取得一个Action类的权限Annotation配置
   */
  private static Map<Long, String> createAccessAction(Class<?> actionClazz) {
    Map<Long, String> result = new HashMap<Long, String>();
    Method[] methods = actionClazz.getMethods();
    for (Method method : methods) {
      Access access = method.getAnnotation(Access.class);
      if (access != null) {
        ValuedEnum operation = (ValuedEnum) EnumUtil.getEnum(access
            .enumClass(), access.value());
        if (operation == null)
          throw new Error(String.format(
              "当前Action：%s中的方法：%s注释有误,找不到权限:[%s];", actionClazz
                  .getSimpleName(), method.getName(), access
                  .value()));
        String name = access.value().toString();
        result.put(new Long(operation.getValue()), name);
      }
    }
    return result;
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
