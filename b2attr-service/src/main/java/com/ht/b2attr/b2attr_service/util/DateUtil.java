package com.ht.b2attr.b2attr_service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	private static String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
	private static Locale locale = Locale.US;

	/**
	 * Parse date string to java.util.date which format is "EEE MMM dd HH:mm:ss zzz yyyy". Default Locale is locale.US
	 * @throws ParseException 
	 */
	public static Date parse(String str) throws ParseException {
		if (str == null) {
			return null;
		}
		return new SimpleDateFormat(pattern, locale).parse(str);
	}
}
