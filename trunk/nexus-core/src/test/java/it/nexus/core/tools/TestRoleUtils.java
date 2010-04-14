package it.nexus.core.tools;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-4-14
 * Time: 13:28:45
 * 测试指数权限检测类的三个功能。
 */
public class TestRoleUtils {
  @Test
  public void testRole() {
    long[] arr_long = new long[]{0, 1, 2, 3, 4, 8};
    long result = RoleUtils.createPurview(arr_long);
    Assert.assertEquals(result, 287);

    Assert.assertTrue(RoleUtils.checkRole(287, 0));
    Assert.assertTrue(RoleUtils.checkRole(287, 1));
    Assert.assertTrue(RoleUtils.checkRole(287, 2));
    Assert.assertTrue(RoleUtils.checkRole(287, 3));
    Assert.assertTrue(RoleUtils.checkRole(287, 4));
    Assert.assertTrue(RoleUtils.checkRole(287, 8));
    Assert.assertFalse(RoleUtils.checkRole(31,5));
    Assert.assertFalse(RoleUtils.checkRole(31,6));
    Assert.assertFalse(RoleUtils.checkRole(31,7));

    long[] roles1 = new long[]{0,3,8};
    long[] roles2 = new long[]{1,4,9};
    Assert.assertTrue(RoleUtils.checkRoleMulti(287,roles1));
    Assert.assertFalse(RoleUtils.checkRoleMulti(287,roles2));

    Assert.assertFalse(RoleUtils.checkRole(0,0));
    
  }
}
