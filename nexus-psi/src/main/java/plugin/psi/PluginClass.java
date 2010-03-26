package plugin.psi;

import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.core.menu.Menu;

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
    public void Init() throws NexusException {
        
    }
    
    @Override
	public String getName() {
		return this.name;
	}
}
