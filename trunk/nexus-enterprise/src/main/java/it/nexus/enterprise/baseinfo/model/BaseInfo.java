package it.nexus.enterprise.baseinfo.model;

import it.nexus.core.annotation.LogicName;
import it.nexus.core.models.Entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseInfo extends Entity {
  @LogicName("启用")
  private boolean available;
  @LogicName("名称")
  private String name;
  @LogicName("拼音")
  private String spell;

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpell() {
    return spell;
  }

  public void setSpell(String spell) {
    this.spell = spell;
  }
}
