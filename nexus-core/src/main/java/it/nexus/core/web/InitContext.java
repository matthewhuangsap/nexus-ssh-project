package it.nexus.core.web;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-9
 * Time: 10:40:10
 * To change this template use File | Settings | File Templates.
 */
public class InitContext {
    private String webFolder;
    private StartUpType startUpType;
    private String configFolder;
    private List<IInitStep> stepList = new ArrayList<IInitStep>();

    public void AddStep(IInitStep step){
        stepList.add(step);
    }


    public String getConfigFolder() {
        return configFolder;
    }

    public InitContext(String webFolder, StartUpType startUpType)
    {
        this.webFolder = webFolder;
        this.webFolder = webFolder;
        this.configFolder = webFolder + "\\config";
    }

    public String getWebFolder() {
        return webFolder;
    }

    public void setWebFolder(String webFolder) {
        this.webFolder = webFolder;
    }
    
    public StartUpType getStartUpType() {
        return startUpType;
    }

    public void setStartUpType(StartUpType startUpType) {
        this.startUpType = startUpType;
    }
}
