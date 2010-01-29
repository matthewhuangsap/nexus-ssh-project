package plugin.psi;

import it.nexus.core.BasePlugin;

public class PluginClass extends BasePlugin {
	/**
	 * Dec 24, 2009 PluginClass.java Administrator
	 */
	protected final String name="psi";
	protected final String displayName = "进销存";

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
