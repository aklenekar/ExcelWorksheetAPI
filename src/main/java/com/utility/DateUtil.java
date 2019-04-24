package com.utility;

import java.time.LocalDateTime;

public class DateUtil {

	private static final LocalDateTime DATE_TIME = LocalDateTime.now();

	public static int currentMonthDays() {
		return DATE_TIME.getMonth().maxLength();
	}

}
