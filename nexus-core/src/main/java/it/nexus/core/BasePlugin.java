package it.nexus.core;

import it.nexus.core.menu.Menu;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;
import java.net.URL;

public abstract class BasePlugin {
	/**
	 * Jan 7, 2010 BasePlugin.java dcriori
	 */
	protected String pluginName;
	protected String displayName;

	public abstract String getName();
    
	public abstract String getDisplayName();

    public void validate(){
        
    }
    
    public void Init() throws NexusException{
        URL url =  getClass().getResource("/plugin.xml");
        System.out.println("得到Plugin.xml路径："+url.getFile());
        String ur = url.getFile();
        File file=new File(ur.substring(6,ur.length()));
        SAXReader saxReader = new SAXReader();
        try{
           Document document = saxReader.read(file);
           System.out.println(">>>>>>>>>>>"+document.toString());
        }  catch (DocumentException de){
           System.out.println(de.getMessage());
        }     
    }

    public Menu getMenu(){
        Menu menu = new Menu(getName(),null,true);
//        URL url =  getClass().getResource("/plugin.xml");
//        System.out.println("得到Plugin.xml路径："+url.getFile());
//        String ur = url.getFile();
//        File file=new File(ur.substring(6,ur.length()));
//        SAXReader saxReader = new SAXReader();
//        try{
//           Document document = saxReader.read(file);
//           System.out.println(">>>>>>>>>>>"+document.toString());
//        }  catch (DocumentException de){
//           System.out.println(de.getMessage());
//        }
        return menu;     
    }
}
