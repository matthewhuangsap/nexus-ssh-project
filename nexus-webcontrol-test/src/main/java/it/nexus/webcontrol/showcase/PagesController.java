package it.nexus.webcontrol.showcase;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-27
 * Time: 15:38:19
 * To change this template use File | Settings | File Templates.
 */
public class PagesController extends ActionSupport {
    private String cpage;
    private String total;
    private String url;

    public String getCpage() {
        return cpage;
    }

    public void setCpage(String cpage) {
        this.cpage = cpage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String list()throws Exception{
        cpage="2";
        total="5";
        url="list";
        return super.execute();
    }
}
