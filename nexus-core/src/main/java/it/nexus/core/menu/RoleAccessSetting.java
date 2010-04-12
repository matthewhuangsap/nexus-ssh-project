package it.nexus.core.menu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-12
 * Time: 8:53:17
 * To change this template use File | Settings | File Templates.
 */
public final class RoleAccessSetting {
  static Map<String,Map<String,Map<Long,String>>> plugin_access =
      new HashMap<String, Map<String, Map<Long,String>>>();
  
  public static Map<String, Map<String, Map<Long, String>>> getPlugin_access() {
    return plugin_access;
  }
  
  public static Map<String,Map<Long,String>> findActionInfo(String plugin){
    if(!plugin_access.containsKey(plugin))
      plugin_access.put(plugin,new HashMap<String,Map<Long,String>>());
    return plugin_access.get(plugin);
  }

  
}
