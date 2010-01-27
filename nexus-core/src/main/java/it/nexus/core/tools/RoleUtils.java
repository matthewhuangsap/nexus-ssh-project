package it.nexus.core.tools;



public final class RoleUtils {
	public static boolean checkRole(long userPurview, long optPurview) {
		long purviewValue = (long) Math.pow(2, optPurview);
		return (userPurview & purviewValue) == purviewValue;
	}

	/**
	 *
	 * @param
	 * @param
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
