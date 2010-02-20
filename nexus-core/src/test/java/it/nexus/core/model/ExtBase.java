package it.nexus.core.model;

import it.nexus.core.models.Base;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-2-20
 * Time: 3:49:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class ExtBase extends Base {
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
