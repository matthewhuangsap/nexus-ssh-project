package it.nexus.component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-21
 * Time: 14:00:08
 * To change this template use File | Settings | File Templates.
 */
public class UIComponent extends UIComponentBase  {
    protected List<UIComponent> controls;
    public UIComponent add(UIComponent uiComponent){
        if(controls==null)
            controls=new LinkedList<UIComponent>();
        controls.add(uiComponent);
        return this;
    }

    public int size(){
        if(controls!=null)
            return controls.size();
        return 0; 
    }

    public String renderBegin(){
        return "<tag >";
    }

    public String renderEnd(){
        return "</tag>";
    }

    StringBuilder render_string=  new StringBuilder();
    public String render(){
        if(this.controls.size()>0)
        {
            Iterator it = this.controls.iterator();
            while(it.hasNext()){

            }
        }
        return "";
    }
}