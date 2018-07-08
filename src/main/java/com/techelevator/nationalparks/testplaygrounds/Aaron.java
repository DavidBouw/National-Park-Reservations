package com.techelevator.nationalparks.testplaygrounds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Aaron {

	public static void main(String[] args) throws ParseException {

		String userFromDateString = getValidDate("What is the arrival date?");

		LocalDate formattedFromDate = formatDate(userFromDateString);
		formattedFromDate = validFromDateChecker(formattedFromDate);

		String userToDateString = getValidDate("What is the departure date?");
		LocalDate formattedToDate = formatDate(userToDateString);
		formattedToDate = validToDateChecker(formattedFromDate, formattedToDate);

		long stayLength = getStayLength(formattedFromDate, formattedToDate);

		System.out.println("You've booked a stay at Mars National Park from " + formattedFromDate + " to "
				+ formattedToDate + "." + " Which is " + stayLength + " day(s).");

	}

	@SuppressWarnings("resource")
	private static String getValidDate(String prompt) {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat myDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		myDateFormat.setLenient(false);
		while (true) {
			System.out.print("\t" + prompt + ": ");
			String input = sc.next();
			try {
				String result = myDateFormat.format(myDateFormat.parse(input));
				return result;
			} catch (ParseException e) {
				System.out.print("\t Invalid date entry, please try again (MM/DD/YYYY).");

			}
		}
	}

	private static LocalDate formatDate(String userDateString) {
		DateTimeFormatter dTF = DateTimeFormatter.ofPattern("MM/dd/uuuu");
		LocalDate lds = LocalDate.parse(userDateString, dTF);
		return lds;
	}

	private static LocalDate validFromDateChecker(LocalDate userFromDate) {
		LocalDate createDate = LocalDate.now();
		while (userFromDate.isBefore(createDate)) {
			String userStringDate = getValidDate("You cannot book a date in the past. Please try again.");
			userFromDate = formatDate(userStringDate);
		}
		return userFromDate;
	}

	private static LocalDate validToDateChecker(LocalDate userFromDate, LocalDate userToDate) {
		while (userToDate.isBefore(userFromDate)) {
			String userStringDate = getValidDate(
					"Your departure date must be after your arrival date. Please try again.");
			userToDate = formatDate(userStringDate);
		}
		return userToDate;
	}

	private static long getStayLength(LocalDate userFromDate, LocalDate userToDate) {
		long stayLength = ChronoUnit.DAYS.between(userFromDate, userToDate);
		return stayLength;
	}

}
