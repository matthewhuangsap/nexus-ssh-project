package it.nexus.enterprise.system.authorize.controller;

import it.nexus.core.SettingClass;
import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.controller.BaseAction;
import it.nexus.core.tools.RoleUtils;
import it.nexus.core.tools.converters.ConverterUtils;
import it.nexus.enterprise.system.authorize.model.Role;
import it.nexus.enterprise.system.authorize.model.RoleAction;
import it.nexus.enterprise.system.authorize.service.AuthorizeService;
import it.nexus.enterprise.system.framework.services.SystemAssistService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import plugin.system.PluginClass;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@ParentPackage(value = "coolie-default")
@Namespace("/permission")
@AccessGroup(name = "鏉冮檺绠＄悊", pluginClass = PluginClass.class)
public class RoleController extends BaseAction<Role> {
	private static final long	serialVersionUID	= -7004668560727425352L;

	@Resource
	AuthorizeService			authorizeService;
	@Resource
	SettingClass				settingClass;
	@Resource
	SystemAssistService systemAssistService;
	
	
	@Access("璁块棶")
	@Action(value = "/permission/list", results = { @Result(type = "freemarker", location = "/it/coolie/system/authorize/view/role_list.ftl", name = "success") })
	public String list() throws Exception {
		lists = authorizeService.getAll();
		return super.execute();
	}

	@SuppressWarnings("unchecked")
	@Action(value = "/permission/create", results = { @Result(type = "freemarker", location = "/it/coolie/system/authorize/view/role_edit.ftl", name = "success") })
	public String create() throws Exception {
		System.out.println(valueList.size());
		this.initAccessGroupMap();
		return super.execute();
	}

	@SuppressWarnings("unchecked")
	private void initAccessGroupMap() {
		access_group_map = (Map<String, Map<String, Map<Long, String>>>) ActionContext
				.getContext().getSession().get("access_group_map");
		plugin_info = (Map<String, String>) ActionContext.getContext()
				.getSession().get("plugin_info");
		if (access_group_map == null || plugin_info == null) {
			ActionContext.getContext().getSession().put("access_group_map",
					systemAssistService.getAccess_group_map());
			ActionContext.getContext().getSession().put("plugin_info",
					systemAssistService.getPlugin_info());
		}
	}

	@Access("缂栬緫")
	@Action(value = "/permission/save", results = { @Result(type = "freemarker", location = "/it/coolie/system/authorize/view/role_edit.ftl", name = "success") })
	public String save() throws Exception {
		// 浣跨敤缂撳瓨 灏嗗彇寰楃殑鏉冮檺淇℃伅鏀惧叆session
		this.initAccessGroupMap();

		Object[] objs = valueList.toArray();
		if (!dmo.isNew())
			authorizeService.removeAction(dmo);
		for (int i = 0; i < valueList.size(); i++) {

			RoleAction ra = new RoleAction();
			ra.setName(keyList.get(i));

			ra.setPlugin(findPluginDispName(access_group_map, keyList.get(i)));

			String[] arr_role = (String[]) objs[i];
			long[] arr_role_long = ConverterUtils.ArrStringToArrLong(arr_role);
			long rolebit = RoleUtils.createPurview(arr_role_long);
			ra.setRolebits(rolebit);
			dmo.addRoleAction(ra);
		}
		authorizeService.save(dmo);

		return super.execute();
	}

	@Action(value = "/permission/remove", results = { @Result(type = "freemarker", location = "/it/coolie/system/authorize/view/role_list.ftl", name = "success") })
	public String remove() throws Exception {
		dmo = authorizeService.load(dmo.getId());
		authorizeService.delete(dmo);
		lists = authorizeService.getAll();
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
	@Access("缂栬緫")
	@Action(value = "/permission/edit", results = { @Result(type = "freemarker", location = "/it/coolie/system/authorize/view/role_edit.ftl", name = "success") })
	public String edit() throws Exception {

		// 浣跨敤缂撳瓨 灏嗗彇寰楃殑鏉冮檺淇℃伅鏀惧叆session
		this.initAccessGroupMap();
		dmo = authorizeService.load(dmo.getId());
		List<RoleAction> actions = dmo.getRoleactions();
		Map<String, RoleAction> action_maps = new HashMap<String, RoleAction>();
		// 灏唋ist 杞垚 map
		for (RoleAction roleAction : actions) {
			action_maps.put(roleAction.getName(), roleAction);
		}
		// 寰幆浠庨厤缃枃浠惰鍙栫殑鏁版嵁 瀵逛负浠栧鎵炬暟鎹簱鏄惁鏈夊畠鐨勮缃暟鎹�
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
				String[] result = new String[] {};
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (action_maps.containsKey(key)) {
					// 鍦ㄦ暟鎹簱涓壘鍒颁簡閰嶇疆鏁版嵁
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
			return new String[] {};
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

	/*浠ヤ笅鏄痝eter seter灞炴��*/
	private Role								dmo;
	private List<Role>							lists				= new ArrayList<Role>();
	// 鏀剧疆value
	private List<String>						keyList				= new ArrayList<String>();
	// 鏀剧疆key
	private List<String[]>						valueList			= new ArrayList<String[]>();
	// 瀛樻斁鎵�鏈夎AccessGroup鏍囪杩囩殑绫�
	List<Class<?>>								action_class_list	= new ArrayList<Class<?>>();
	/* 瀛樻斁 plugin_name
	// |----------access_group_name
	// |---------------- 1 = 鏂板缓
	// |---------------- 2 = 淇濆瓨*/
	Map<String, Map<String, Map<Long, String>>>	access_group_map	= new TreeMap<String, Map<String, Map<Long, String>>>();

	Map<String, String>							plugin_info			= new HashMap<String, String>();

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

	/*浠ヤ笂鏄痝eter seter灞炴��*/
}
