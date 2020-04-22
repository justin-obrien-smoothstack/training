package com.ss.training.datetimeapi.monthlengths;

import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.util.HashMap;

/**
 * @author Justin O'Brien
 *
 */
public class GetMonthLengths {
	public static HashMap<Month, Period> getMonthLengths(Year year) {
		HashMap<Month, Period> periods = new HashMap<Month, Period>();
		YearMonth yearMonth;
		System.out.println("Year: " + year);
		for (Month month : Month.values()) {
			yearMonth = year.atMonth(month);
			periods.put(month, Period.between(yearMonth.atDay(1), yearMonth.atEndOfMonth()).plusDays(1));
			System.out.println(month + ": " + periods.get(month).getDays() + " days");
		}
		return periods;
	}
}
