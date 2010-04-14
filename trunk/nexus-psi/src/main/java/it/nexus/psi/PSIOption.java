package it.nexus.psi;

import it.nexus.core.models.enums.BaseOperation;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-14
 * Time: 15:29:50
 */
public class PSIOption extends BaseOperation{
  protected PSIOption(String name, int value) {
    super(name, value); 
  }
  public static final PSIOption 检查单价 = new PSIOption("检查单价", 15);
}
