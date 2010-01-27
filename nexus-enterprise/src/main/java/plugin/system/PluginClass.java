package plugin.system;

import it.nexus.core.BasePlugin;

public class PluginClass extends BasePlugin{
	/**
	 * Dec 24, 2009 PluginClass.java Administrator
	 */
	protected final String name="system";
	protected final String displayName = "系统模块";

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
