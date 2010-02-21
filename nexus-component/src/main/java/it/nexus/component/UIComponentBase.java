package it.nexus.component;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-21
 * Time: 14:45:32
 * To change this template use File | Settings | File Templates.
 */
public abstract class UIComponentBase {
    protected String id;
    protected String name;
    protected String type;
    protected String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
