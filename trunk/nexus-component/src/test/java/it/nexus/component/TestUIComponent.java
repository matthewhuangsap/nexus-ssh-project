package it.nexus.component;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-21
 * Time: 14:03:44
 * To change this template use File | Settings | File Templates.
 */
public class TestUIComponent {
    UIComponent form=new UIComponent();
    @Before
    public void createComponent(){

        UIComponent div1 =new UIComponent();
        UIComponent div2 =new UIComponent();
        UIComponent div3 =new UIComponent();
        UIComponent div4 =new UIComponent();
        UIComponent div5 =new UIComponent();

        form.add(div1);
        form.add(div2);
        form.add(div3);
        form.add(div4);
        form.add(div5);

        UIComponent text1 =new UIComponent();
        UIComponent text2 =new UIComponent();
        UIComponent text3 =new UIComponent();
        UIComponent text4 =new UIComponent();
        UIComponent text5 =new UIComponent();

        div1.add(text1);
        div1.add(text2);
        div1.add(text3);
        div1.add(text4);
        div1.add(text5);


    }
    @Test
    public void TestAdd(){
        Assert.assertEquals(form.size(),5);
        
    }
}
