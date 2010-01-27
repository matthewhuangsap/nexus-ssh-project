package it.nexus.core.models.enums;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.EnumUtils;

public final class EnumUtil {
	
	/**
	 * Dec 25, 2009 EnumUtils.java Administrator
	 */
	public static <T extends Enum<T> & IEnum<V>, V> T parse(Class<T> clazz,
			V key) {

		for (T t : clazz.getEnumConstants()) {
			if (isEquals(t.key(), key)) {
				return t;
			}
		}
		return null;
	}

	private static <T> boolean isEquals(T lhs, T rhs) {

		if (lhs == null && rhs == null) {
			return true;
		} else if (lhs == null || rhs == null) {
			return false;
		} else {
			return lhs.equals(rhs);
		}
	}

	/**
	 * 得到枚举的集合
	 * 
	 * @param enumClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<org.apache.commons.lang.enums.Enum> s(Class enumClass) {
		return EnumUtils.getEnumList(enumClass);
	}

	/**
	 * 得到一种枚举类型的各种信息
	 * 
	 * @param clazz
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static org.apache.commons.lang.enums.Enum getValuedEnum(Class clazz,
			int code) {
		try {
			Class.forName(clazz.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EnumUtils.getEnum(clazz, code);
	}

	/**
	 * 得到一种枚举所有枚举信息
	 * 
	 * @param enumClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getEnumMap(Class enumClass) {
		try {
			Class.forName(enumClass.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EnumUtils.getEnumMap(enumClass);
	}

	/**
	 * 获取枚举对象（注意此处的枚举类型为Apache Enum 非JDK 的枚举對象）
	 * 
	 * @param enumClass
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static org.apache.commons.lang.enums.Enum getEnum(Class enumClass,
			String name) {
		try {
			Class.forName(enumClass.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return EnumUtils.getEnum(enumClass, name);
	}
}
