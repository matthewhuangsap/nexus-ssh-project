package it.nexus.core.web;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-9
 * Time: 13:21:34
 * To change this template use File | Settings | File Templates.
 */
public class InitPackage implements IInitStep {
    @Override
    public void Execute(InitContext context) {
        PluginManager.init(context.getConfigFolder());
    }

    @Override
    public String getName() {
        return "Package";  
    }
}
