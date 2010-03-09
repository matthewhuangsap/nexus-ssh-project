package it.nexus.core.datakind;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 9:44:18
 * To change this template use File | Settings | File Templates.
 */
public class Argument {
    private String code;
    private String input;
    private String kind;
    
    public Argument(String kind,String code ,String input){
        this.code = code;
        this.input = input;
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
