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
	Map<String, Map<String, Map<Long, String>>> accessGroupMap = new TreeMap<String, Map<String, Map<Long, String>>>();
	Map<String, String> pluginInfo = new HashMap<String, String>();
	List<Class<?>> actionClassList = new ArrayList<Class<?>>();

	@Resource
	SettingClass settingClass;

    @SuppressWarnings("unused")
	private void createPluginInfo() {
		for (String str_package : settingClass.getAccessToScan()) {
//			System.out.println("你配置了：" + str_package);
			List<Class<?>> classFiles = ClassUtils.getAccessGroup(str_package);

			for (Class<?> clazz : classFiles) {
				actionClassList.add(clazz);
			}
		}
		for (Class<?> clazz : actionClassList) {
			findAccessGroup(clazz);
		}
	}

	@SuppressWarnings("unused")
	private void findAccessGroup(Class<?> action_clazz) {
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
     * @description 取得一个Action类的权限Annotation配置
     * @param actionClazz
     * @return
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

	private Map<String, Map<Long, String>> findAccessGroupMap(String tree_name) {
		if (accessGroupMap == null)
			accessGroupMap = new TreeMap<String, Map<String, Map<Long, String>>>();
		if (!accessGroupMap.containsKey(tree_name))
			accessGroupMap.put(tree_name,
					new HashMap<String, Map<Long, String>>());
		return accessGroupMap.get(tree_name);
	}

    /**
     * @desc  存放简单plugin信息
     * @param pluginName
     * @param pluginDispname
     */
	@SuppressWarnings("unused")
	private void createPluginInfo(final String pluginName,
			final String pluginDispname) {
		if (pluginInfo == null)
			pluginInfo = new HashMap<String, String>();
		if (!pluginInfo.containsKey(pluginName)) {
			pluginInfo.put(pluginName, pluginDispname);
		}
	}

	public Map<String, Map<String, Map<Long, String>>> getAccessGroupMap() {
		if(accessGroupMap ==null || accessGroupMap.size()<=0)
			this.createPluginInfo();
		return accessGroupMap;
	}

	public void setAccessGroupMap(
			Map<String, Map<String, Map<Long, String>>> accessGroupMap) {
		this.accessGroupMap = accessGroupMap;
	}

	public Map<String, String> getPluginInfo() {
		if(pluginInfo ==null || pluginInfo.size()<=0)
			this.createPluginInfo();
		return pluginInfo;
	}

	public void setPluginInfo(Map<String, String> pluginInfo) {
		this.pluginInfo = pluginInfo;
	}

	public List<Class<?>> getActionClassList() {
		return actionClassList;
	}

	public void setActionClassList(List<Class<?>> actionClassList) {
		this.actionClassList = actionClassList;
	}

	public SettingClass getSettingClass() {
		return settingClass;
	}

	public void setSettingClass(SettingClass settingClass) {
		this.settingClass = settingClass;
	}
}
