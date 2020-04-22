package com.ss.training.datetimeapi.fridaythirteenth;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author Justin O'Brien
 *
 */
public class FridayThirteenthFinder {
	public static boolean isFridayThirteenth(LocalDate date) {
		return DayOfWeek.FRIDAY.equals(date.getDayOfWeek()) && date.getDayOfMonth() == 13;
	}
}
