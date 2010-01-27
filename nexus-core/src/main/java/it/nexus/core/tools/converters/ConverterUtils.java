package it.nexus.core.tools.converters;

public class ConverterUtils {
	/**Dec 16, 2009
	 *ConverterUtils.java
	 * dcriori
	 */
	public static long[] ArrStringToArrLong(String[] arr_string){
		if(arr_string==null)
			return null;
		int i=0;
		long[] result= new long[arr_string.length];
		for (String string : arr_string) {
			result[i]= new Long(string);
			i++;
		}
		if(result.length>0)
			return result;
		return null;
	}
	
	public static Long toLong(String number){
		return new Long(number);
	}
}
