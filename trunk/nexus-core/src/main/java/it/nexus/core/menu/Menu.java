package it.nexus.core.menu;

import it.nexus.core.NexusException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-18
 * Time: 13:58:47
 * To change this template use File | Settings | File Templates.
 */
public class Menu {
    public Menu(){}
    public Menu(String name,String url,Boolean isFolder){
        this.name = name;
        this.url = url;
        this.isFolder = isFolder;
    }

    public void add(Menu menu) throws NexusException {
        if(this.childs.contains(menu))
            throw new NexusException("［ERROR］不能重复添加菜单");
        this.childs.add(menu);

    }
    
    private String id;
    private List<Menu> childs = new LinkedList<Menu>();
    private Menu parent;
    private Boolean isFolder;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Menu> getChilds() {
        return childs;
    }

    public void setChilds(List<Menu> childs) {
        this.childs = childs;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public Boolean isFolder() {
        return isFolder;
    }

    public void setFolder(Boolean folder) {
        isFolder = folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object obj) {
        Menu menu = (Menu)obj;
        if(menu.getName() == this.name && menu.getUrl() == this.url && menu.isFolder() == this.isFolder)
            return true;
        return super.equals(obj); 
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Menu:");
        printMenu(this,sb);
        return sb.toString();
    }

    private void printMenu(Menu menu, StringBuilder sb){
        sb.append("Name:"+menu.getName()+"  Url:" + menu.getUrl()+ " IsFolder:" + menu.isFolder() +" \n ");
        if(menu.getChilds().size()>0)
        {
            for(Menu m  : menu.getChilds()){
                printMenu(m,sb);
            }
        }
    }
}
