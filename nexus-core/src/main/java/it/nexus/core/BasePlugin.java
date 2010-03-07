package it.nexus.core;

public abstract class BasePlugin {
	/**
	 * Jan 7, 2010 BasePlugin.java Administrator
	 */
	protected String pluginName;
	protected String displayName;

	public abstract String getName();

	public abstract String getDisplayName();

    public abstract void Init() throws NexusException;
}
