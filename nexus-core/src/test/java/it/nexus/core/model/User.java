package it.nexus.core.model;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-5-7
 * Time: 11:31:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class User extends it.nexus.core.models.Entity {
  public User(){}

  public User(String name,String pwd){
    this.name = name;
    this.pwd = pwd;
  }
  
  private String name;
  private String pwd;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

}
