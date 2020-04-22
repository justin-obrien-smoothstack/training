package com.ss.training.datetimeapi.mondays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

/**
 * @author Justin O'Brien
 *
 */
public class MondayFinder {
	public static ArrayList<LocalDate> getMondaysInMonthOfCurrentYear(Month month) {
		YearMonth yearMonth = Year.now().atMonth(month);
		DayOfWeek dayOne = yearMonth.atDay(1).getDayOfWeek();
		LocalDate monday;
		ArrayList<LocalDate> mondays = new ArrayList<LocalDate>();
		byte firstMonday = (byte) ((DayOfWeek.MONDAY.getValue() - dayOne.getValue() + 7) % 7 + 1);
		System.out.println("Month: " + yearMonth);
		for (byte i = firstMonday; i <= yearMonth.atEndOfMonth().getDayOfMonth(); i += 7) {
			System.out.println(monday = yearMonth.atDay(i));
			mondays.add(monday);
		}
		return mondays;
	}
}
