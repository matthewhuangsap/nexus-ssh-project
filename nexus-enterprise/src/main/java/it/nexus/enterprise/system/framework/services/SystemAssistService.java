package it.nexus.enterprise.system.framework.services;

import it.nexus.core.SettingClass;
import it.nexus.core.annotation.Access;
import it.nexus.core.annotation.AccessGroup;
import it.nexus.core.models.enums.EnumUtil;
import it.nexus.core.tools.ClassUtils;
import it.nexus.core.tools.PluginClassUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.enums.ValuedEnum;
import org.springframework.stereotype.Service;

@Service
public class SystemAssistService {
	Map<String, Map<String, Map<Long, String>>> access_group_map = new TreeMap<String, Map<String, Map<Long, String>>>();
	Map<String, String> plugin_info = new HashMap<String, String>();
	List<Class<?>> action_class_list = new ArrayList<Class<?>>();

	@Resource
	SettingClass settingClass;

	@SuppressWarnings("unused")
	private void createPluginInfo() {
		for (String str_package : settingClass.getAccessToScan()) {
			System.out.println("你配置了：" + str_package);
			List<Class<?>> classFiles = ClassUtils.getAccessGroup(str_package);

			for (Class<?> clazz : classFiles) {
				action_class_list.add(clazz);
			}
		}
		for (Class<?> clazz : action_class_list) {
			findAccessGroup(clazz);
		}
	}

	@SuppressWarnings("unused")
	private void findAccessGroup(Class<?> action_clazz) {
		AccessGroup aGroup = action_clazz.getAnnotation(AccessGroup.class);
		Map<Long, String> access_actions = createAccessAction(action_clazz);
		String plugin_name = PluginClassUtil
				.getPluginName(aGroup.pluginClass());
		String plugin_dispname = PluginClassUtil.getPluginDispName(aGroup
				.pluginClass());
		Map<String, Map<Long, String>> access_map = genAccessGroupMap(plugin_name);
		createPluginInfo(plugin_name, plugin_dispname);
		PluginClassUtil.getPluginDispName(aGroup.pluginClass());

		access_map.put(aGroup.name(), access_actions);
	}

	private Map<Long, String> createAccessAction(Class<?> action_clazz) {
		Map<Long, String> result = new HashMap<Long, String>();
		Method[] methods = action_clazz.getMethods();
		for (Method method : methods) {
			Access access = method.getAnnotation(Access.class);
			if (access != null) {
				ValuedEnum operation = (ValuedEnum) EnumUtil.getEnum(access
						.enumClass(), access.value());
				if (operation == null)
					throw new Error(String.format(
							"当前Action：%s中的方法：%s注释有误,找不到权限:[%s];", action_clazz
									.getSimpleName(), method.getName(), access
									.value()));
				String name = access.value().toString();
				result.put(new Long(operation.getValue()), name);
			}
		}
		return result;
	}

	private Map<String, Map<Long, String>> genAccessGroupMap(String tree_name) {
		if (access_group_map == null)
			access_group_map = new TreeMap<String, Map<String, Map<Long, String>>>();
		if (!access_group_map.containsKey(tree_name))
			access_group_map.put(tree_name,
					new HashMap<String, Map<Long, String>>());
		return access_group_map.get(tree_name);
	}

	@SuppressWarnings("unused")
	private void createPluginInfo(final String plugin_name,
			final String plugin_dispname) {
		if (plugin_info == null)
			plugin_info = new HashMap<String, String>();
		if (!plugin_info.containsKey(plugin_name)) {
			plugin_info.put(plugin_name, plugin_dispname);
		}
	}

	public Map<String, Map<String, Map<Long, String>>> getAccess_group_map() {
		if(access_group_map==null || access_group_map.size()<=0)
			this.createPluginInfo();
		return access_group_map;
	}

	public void setAccess_group_map(
			Map<String, Map<String, Map<Long, String>>> access_group_map) {
		this.access_group_map = access_group_map;
	}

	public Map<String, String> getPlugin_info() {
		if(plugin_info==null || plugin_info.size()<=0)
			this.createPluginInfo();
		return plugin_info;
	}

	public void setPlugin_info(Map<String, String> plugin_info) {
		this.plugin_info = plugin_info;
	}

	public List<Class<?>> getAction_class_list() {
		return action_class_list;
	}

	public void setAction_class_list(List<Class<?>> action_class_list) {
		this.action_class_list = action_class_list;
	}

	public SettingClass getSettingClass() {
		return settingClass;
	}

	public void setSettingClass(SettingClass settingClass) {
		this.settingClass = settingClass;
	}
}
