package it.nexus.core;

import java.util.ArrayList;
import java.util.List;

public final class SettingClass {
    /**
     * Jan 5, 2010 SettingClass.java dcriori
     */
    private List<String> accessToScan;

    public List<String> getAccessToScan() {
        return accessToScan;
    }

    public void setAccessToScan(List<String> accessToScan) {
        if (this.accessToScan == null)
            this.accessToScan = new ArrayList<String>();
        this.accessToScan = accessToScan;
    }

    private List<String> pluginMatching;

    public List<String> getPluginMatching() {
        return pluginMatching;
    }

    public void setPluginMatching(List<String> pluginMatching) {
        this.pluginMatching = pluginMatching;
    }

    private Boolean debugModule;

    public Boolean getDebugModule() {
        return debugModule;
    }

    public void setDebugModule(Boolean debugModule) {
        this.debugModule = debugModule;
    }


}
