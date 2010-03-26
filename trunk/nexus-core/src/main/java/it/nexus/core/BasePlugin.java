package it.nexus.core;

import it.nexus.core.menu.Menu;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.net.URL;
import java.util.List;

public abstract class BasePlugin {
	/**
	 * Jan 7, 2010 BasePlugin.java dcriori
	 */
	protected String pluginName;
	protected String displayName;
    protected Menu menus;
    protected List<String> requires;

    
    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }

    public Menu getMenus() {
        return menus;
    }

    public void setMenus(Menu menus) {
        this.menus = menus;
    }

    public abstract String getName();
    
	public abstract String getDisplayName();

    public abstract void Init() throws NexusException;
}
