package it.nexus.enterprise.system;

import it.nexus.core.SettingClass;
import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.models.enums.EnumUtil;
import it.nexus.core.tools.ClassUtils;
import it.nexus.core.tools.PluginClassUtil;
import org.apache.commons.lang.enums.ValuedEnum;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-9
 * Time: 14:52:23
 * To change this template use File | Settings | File Templates.
 */
public class InitSystemBean implements InitializingBean {
  @Resource
  SettingClass settingClass;

  @Override
  public void afterPropertiesSet() throws Exception {
//        initPlugin();
    initActionAccessInfo();
  }

  /**
   * get action access info for role validate
   */
  private void initActionAccessInfo() {

  }

  List<Class<?>> actionClassList = new ArrayList<Class<?>>();
  Map<String, Map<String, Map<Long, String>>> accessGroupMap = new TreeMap<String, Map<String, Map<Long, String>>>();

  private void createPluginInfo() {
    for (String str_package : settingClass.getAccessToScan()) {
      List<Class<?>> classFiles = ClassUtils.getAccessGroup(str_package);

      for (Class<?> clazz : classFiles) {
        actionClassList.add(clazz);
      }
    }
    for (Class<?> clazz : actionClassList) {
      findAccessGroup(clazz);
    }
  }

  private void findAccessGroup(Class<?> action_clazz) {
    AccessGroup group = action_clazz.getAnnotation(AccessGroup.class);
    Map<Long, String> access_actions = createAccessAction(action_clazz);
    String plugin_name = PluginClassUtil
        .getPluginName(group.pluginClass());
    String plugin_dispname = PluginClassUtil.getPluginDispName(group.pluginClass());
    Map<String, Map<Long, String>> access_map = findAccessGroupMap(plugin_name);
    PluginClassUtil.getPluginDispName(group.pluginClass());

    access_map.put(group.name(), access_actions);
  }

  private Map<String, Map<Long, String>> findAccessGroupMap(String tree_name) {
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
  private Map<Long, String> createAccessAction(Class<?> actionClazz) {
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

  @Deprecated
  private void initPlugin() throws Exception {
    for (String s : settingClass.getPluginMatching()) {
      String filePath = this.getClass().getResource("").getPath();
      System.out.println("current class file path :" + filePath);
      List<String> plugin_list = ClassUtils.searchFileFromFolder(filePath, s);
      for (String plugin_jar : plugin_list) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>:" + plugin_jar);
      }
    }
  }
}
