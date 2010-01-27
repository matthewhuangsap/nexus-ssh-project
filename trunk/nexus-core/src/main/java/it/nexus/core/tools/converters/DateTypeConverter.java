package it.nexus.core.tools.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateTypeConverter extends StrutsTypeConverter {
//	private static final Logger log = Logger.getLogger(DateTypeConverter.class);
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	// 暂时只考虑这几种日期格式
	public static final DateFormat[] ACCEPT_DATE_FORMATS = {
			new SimpleDateFormat(DEFAULT_DATE_FORMAT),
			new SimpleDateFormat("MM/dd/yyyy"),
			new SimpleDateFormat("yyyy/MM/dd") 
			};

	/**
	 * 
	 */
	public DateTypeConverter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.util.StrutsTypeConverterconvertFromString(java.util.Map,
	 *      java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values[0] == null || values[0].trim().equals(""))
			return null;
		for (DateFormat format : ACCEPT_DATE_FORMATS) {
			try {
				return format.parse(values[0]);
			} catch (ParseException e) {
				continue;
			} catch (RuntimeException e) {
				continue;
			}
		}
//		log.debug("can not format date string:" + values[0]);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts2.util.StrutsTypeConverterconvertToString(java.util.Map,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			try {
				return format.format((Date) o);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}

}
