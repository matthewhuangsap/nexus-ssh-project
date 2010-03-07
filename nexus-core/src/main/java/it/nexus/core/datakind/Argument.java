package it.nexus.core.datakind;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 9:44:18
 * To change this template use File | Settings | File Templates.
 */
public class Argument {
    private String datakind;
    private String beforedrop;
    private String parameter;

    public String getDatakind() {
        return datakind;
    }

    public void setDatakind(String datakind) {
        this.datakind = datakind;
    }

    public String getBeforedrop() {
        return beforedrop;
    }

    public void setBeforedrop(String beforedrop) {
        this.beforedrop = beforedrop;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
