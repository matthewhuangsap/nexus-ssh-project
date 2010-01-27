package it.nexus.core.tools;

import it.nexus.core.BasePlugin;

public class PluginClassUtil {
	/**
	 * Jan 7, 2010 PluginClassUtil.java Administrator
	 */

	public static String getPluginName(Class<?> plugin_clazz) {
		try {
			BasePlugin bp= (BasePlugin) plugin_clazz.newInstance();
			return bp.getName();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getPluginDispName(Class<?> plugin_clazz) {
		try {
			BasePlugin bp= (BasePlugin) plugin_clazz.newInstance();
			return bp.getDisplayName();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
