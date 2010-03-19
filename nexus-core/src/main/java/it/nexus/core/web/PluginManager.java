package it.nexus.core.web;

import it.nexus.core.BasePlugin;
import it.nexus.core.NexusException;
import it.nexus.core.tools.ClassUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-9
 * Time: 13:24:54
 * To change this template use File | Settings | File Templates.
 */
public final class PluginManager {
    private static Map<String, BasePlugin> plugins = new HashMap<String,BasePlugin>();
    public static void init(String configFolder)  {
        List<Class<?>> list = ClassUtils.getClasses("plugin");
        for (Class cls : list){
            System.out.println(">>>>>>>>>>>>>>>>>>"+cls.getName());
            if(cls.getGenericSuperclass()==BasePlugin.class){
                try{
                    BasePlugin bp = (BasePlugin) cls.newInstance();
                    System.out.println(String.format("正在初始化模块%s......",bp.getDisplayName()));
                    bp.Init();
                    System.out.println(String.format("模块%s初始化完毕",bp.getDisplayName()));
                    bp.getMenu();                    
                    plugins.put(bp.getName(),bp);
                    System.out.println("%%%%%%%%%%%%%%%%"+ bp.getDisplayName()+"%%%%%%%%%%%%%%%%%%");
                } catch (Exception e){
                    e.getStackTrace();
                }
            }
        }
    }

    public static Map<String, BasePlugin> getPlugins() {
        return plugins;
    }
}
