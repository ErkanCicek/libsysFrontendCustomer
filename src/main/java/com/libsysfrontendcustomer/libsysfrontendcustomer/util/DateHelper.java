package com.libsysfrontendcustomer.libsysfrontendcustomer.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * class with static methods to get current date and return date which returns date value in 2 weeks
 * @deprecated
 */
public class DateHelper {
	/**
	 * @since 2022-05-19
	 * @implNote This method return a string value of a date from two weeks forward in time.
	 * @return String value of date
	 */
	public static String getReturnDate(){
		return LocalDateTime.now().plusWeeks(2).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	/**
	 * @since 2022-05-19
	 * @implNote use this method to get the currentdate of your local time zone
	 * @return string value of the CURRENT DATE
	 */
	public static String getCurrentDate(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public static String getNextDay(){
		return LocalDateTime.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
