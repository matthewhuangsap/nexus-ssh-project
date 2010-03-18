package it.nexus.core.menu;

import it.nexus.core.NexusException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-18
 * Time: 14:04:07
 * To change this template use File | Settings | File Templates.
 */
public class TestMenu {
     Menu main = new Menu("system","http://www.google.com",true);
    @Before
    public void before()  throws NexusException {
        Menu menu1 = new Menu("基础信息1","",true);
        Menu menu11 = new Menu("部门","/system/dept/create",false);
        Menu menu12 = new Menu("部门","/system/dept/list",false);
        menu1.add(menu11);
        menu1.add(menu12);

        Menu menu2 = new Menu("基础信息2","",true);
        Menu menu21 = new Menu("员工","",true);
        Menu menu211 = new Menu("新建","/system/employee/create",false);
        Menu menu212 = new Menu("列表","/system/employee/list",false);
        menu21.add(menu211);
        menu21.add(menu212);

        Menu menu22 = new Menu("会计单位","",true);

        menu2.add(menu21);
        menu2.add(menu22);

        Menu menu3 = new Menu("基础信息3","",true);
        Menu menu4 = new Menu("基础信息4","",true);

        main.add(menu1);
        main.add(menu2);
        main.add(menu3);
        main.add(menu4);
    }
    @Test
    public void createMenu(){
        create(main);   
    }

    private void create(Menu menu){
        for(Menu m : menu.getChilds()){
            System.out.println("Name:"+m.getName() + "URL:"+ m.getUrl());
            if(m.isFolder())
                create(m);
        }
    }
}
