package it.nexus.enterprise.system.authorize.controller;

import it.nexus.core.NexusException;
import it.nexus.core.SettingClass;
import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.dao.Page;
import it.nexus.core.tools.RoleUtils;
import it.nexus.core.tools.converters.ConverterUtils;
import it.nexus.core.web.PluginManager;
import it.nexus.enterprise.system.authorize.model.Role;
import it.nexus.enterprise.system.authorize.model.RoleAction;
import it.nexus.enterprise.system.authorize.service.AuthorizeService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import plugin.system.PluginClass;

import javax.annotation.Resource;
import java.util.*;

@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
@Namespace("/permission")
@AccessGroup(name = "权限管理", pluginClass = PluginClass.class)
public class RoleController extends BaseAction<Role> {
  private static final long serialVersionUID = -7004668560727425352L;
  @Resource
  AuthorizeService authorizeService;
  @Resource
  SettingClass settingClass;


  @Access("访问")
  @Action(value = "/permission/list", results = {@Result(type = "freemarker", location = "role_list.ftl", name = "success")})
  public String list() throws Exception {
    Page page = new Page();
    lists = authorizeService.getAll(page);
    return super.execute();
  }

  @SuppressWarnings("unchecked")
  @Action(value = "/permission/create", results = {@Result(type = "freemarker", location = "role_edit.ftl", name = "success")})
  public String create() throws Exception {
    System.out.println(valueList.size());
    this.initAccessGroupMap();
    return super.execute();
  }

  @SuppressWarnings("unchecked")
  private void initAccessGroupMap() throws NexusException {
    access_group_map = PluginManager.getPluginMap();
    plugin_info = PluginManager.getPluginSimpleInfo();
    if (access_group_map == null || plugin_info == null) {
      throw new NexusException("模块加载错误！请检查模块是否正确安装");
    }
  }

  @Access("保存")
  @Action(value = "/permission/save",
      results = {
          @Result(type = "freemarker", location = "role_edit.ftl", name = "success"),
          @Result(type = "freemarker", location = "role_edit.ftl", name = "input")
      })
  public String save() throws Exception {
    this.initAccessGroupMap();

    Object[] objs = valueList.toArray();
    if (!dmo.isNew())
      authorizeService.removeAction(dmo);
    for (int i = 0; i < valueList.size(); i++) {

      RoleAction ra = new RoleAction();
      ra.setName(keyList.get(i));

      ra.setPlugin(findPluginDispName(access_group_map, keyList.get(i)));

      String[] arr_role = (String[]) objs[i];
      long[] arr_role_long = ConverterUtils.StringToLong(arr_role);
      long rolebit = RoleUtils.createPurview(arr_role_long);
      ra.setRolebits(rolebit);
      dmo.addRoleAction(ra);
    }
    authorizeService.save(dmo);

    return super.execute();
  }

  @Action(value = "/permission/remove", results = {@Result(type = "freemarker", location = "role_list.ftl", name = "success")})
  public String remove() throws Exception {
    dmo = authorizeService.load(dmo.getId());
    authorizeService.delete(dmo);
    Page page = new Page();
    lists = authorizeService.getAll(page);
    return super.execute();
  }

  @SuppressWarnings("unchecked")
  private String findPluginDispName(
      Map<String, Map<String, Map<Long, String>>> access_group_map,
      String access_name) {
    Iterator it = access_group_map.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry entry = (Map.Entry) it.next();
      String key = (String) entry.getKey();
      Map<String, Map<Long, String>> value = (Map<String, Map<Long, String>>) entry
          .getValue();
      if (value.containsKey(access_name))
        return key;
    }
    return "system";
  }

  @SuppressWarnings("unchecked")
  @Access("编辑")
  @Action(value = "/permission/edit", results = {@Result(type = "freemarker", location = "role_edit.ftl", name = "success")})
  public String edit() throws Exception {
    this.initAccessGroupMap();
    dmo = authorizeService.load(dmo.getId());
    List<RoleAction> actions = dmo.getRoleactions();
    Map<String, RoleAction> action_maps = new HashMap<String, RoleAction>();
    for (RoleAction roleAction : actions) {
      action_maps.put(roleAction.getName(), roleAction);
    }
    fillCheckedValueList(action_maps, access_group_map);
    return super.execute();
  }

  @SuppressWarnings("unchecked")
  private void fillCheckedValueList(Map<String, RoleAction> action_maps,
                                    Map<String, Map<String, Map<Long, String>>> accessGroupMap) {
    Iterator listMapIter = accessGroupMap.entrySet().iterator();
    while (listMapIter.hasNext()) {

      Map.Entry access_entry = (Map.Entry) listMapIter.next();
      Map<String, Map<Long, String>> access_maps = (Map<String, Map<Long, String>>) access_entry
          .getValue();
      Iterator it = access_maps.entrySet().iterator();
      while (it.hasNext()) {
        String[] result = new String[]{};
        Map.Entry entry = (Map.Entry) it.next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (action_maps.containsKey(key)) {
          RoleAction ra = action_maps.get(key);
          result = createArrRole(ra.getRolebits(),
              (Map<Long, String>) value);
        }
        valueList.add(result);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private String[] createArrRole(long rolebits, Map<Long, String> roles) {
    List<String> access_roles = new ArrayList<String>();
    if (rolebits == -1)
      return new String[]{};
    Iterator iter = roles.entrySet().iterator();
    while (iter.hasNext()) {
      Map.Entry entry = (Map.Entry) iter.next();
      Object key = entry.getKey();
      Long intKey = new Long(key.toString());
      if (RoleUtils.checkRole(rolebits, intKey)) {
        access_roles.add(intKey.toString());
      }
    }
    String[] result = new String[access_roles.size()];
    access_roles.toArray(result);
    return result;
  }

  /*geter seter*/
  private Role dmo;
  private List<Role> lists = new ArrayList<Role>();
  private List<String> keyList = new ArrayList<String>();
  private List<String[]> valueList = new ArrayList<String[]>();
  List<Class<?>> action_class_list = new ArrayList<Class<?>>();
  /* plugin_name
   // |----------access_group_name
   // |---------------- 1 = key   1
   // |---------------- 2 = value 新建 */
  Map<String, Map<String, Map<Long, String>>> access_group_map = new TreeMap<String, Map<String, Map<Long, String>>>();
  Map<String, String> plugin_info = new HashMap<String, String>();

  public Role getDmo() {
    return dmo;
  }

  public void setDmo(Role dmo) {
    this.dmo = dmo;
  }

  public List<Role> getLists() {
    return lists;
  }

  public void setLists(List<Role> lists) {
    this.lists = lists;
  }

  public List<String[]> getValueList() {
    return valueList;
  }

  public void setValueList(List<String[]> valueList) {
    this.valueList = valueList;
  }

  public List<String> getKeyList() {
    return keyList;
  }

  public void setKeyList(List<String> keyList) {
    this.keyList = keyList;
  }

  public Map<String, Map<String, Map<Long, String>>> getAccess_group_map() {
    return access_group_map;
  }

  public void setAccess_group_map(
      Map<String, Map<String, Map<Long, String>>> access_group_map) {
    this.access_group_map = access_group_map;
  }

  public Map<String, String> getPlugin_info() {
    return plugin_info;
  }

  public void setPlugin_info(Map<String, String> plugin_info) {
    this.plugin_info = plugin_info;
  }

  /*geter seter*/
}
