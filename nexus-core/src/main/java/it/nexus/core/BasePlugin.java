package it.nexus.core;

import it.nexus.core.menu.Menu;

import java.net.URL;

public abstract class BasePlugin {
	/**
	 * Jan 7, 2010 BasePlugin.java Administrator
	 */
	protected String pluginName;
	protected String displayName;

	public abstract String getName();

	public abstract String getDisplayName();

    public void Init() throws NexusException{
        URL url =  getClass().getResource("/plugin.xml");
        System.out.println("得到Plugin.xml路径："+url.getFile());
    }

    public abstract Menu getMenu();
}
