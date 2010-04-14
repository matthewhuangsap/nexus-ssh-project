package it.nexus.core.tools;



public final class RoleUtils {
  /**
   * @param userPurview 指数运算后的数字 （大）
   * @param optPurview  指数运算前的数字 （小）
   * @return
   */
	public static boolean checkRole(long userPurview, long optPurview) {
		long purviewValue = (long) Math.pow(2, optPurview);
		return (userPurview & purviewValue) == purviewValue;
	}

  /**
   * @param userPurview 指数运算后的数字         （大）
   * @param optPurviews 指数运算前的数字数组 （小）
   * @return
   */
	public static boolean checkRoleMulti(long userPurview, long[] optPurviews) {
		long purviewValue = createPurview(optPurviews);
		return (userPurview & purviewValue) == purviewValue;
	}

	/**
	 * 
	 * @param the <code>int[]</code> to be Roles
	 * @return int value this purview
	 */
	public static long createPurview(long[] roles) {
		if(roles==null)
			return -1;
		long purview = 0;
		for (int i = 0; i < roles.length; i++) {
			purview += Math.pow(2, roles[i]);
		}
		return purview;
	}
	
	
}
