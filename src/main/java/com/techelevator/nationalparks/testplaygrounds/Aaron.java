package com.techelevator.nationalparks.testplaygrounds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Aaron {

	//Need to make sure reservations are equal to or past current date
	//
	
	public static void main(String[] args) throws ParseException {

		String userDateString = getValidDate("What is the arrival date?");

		System.out.println(userDateString);

		LocalDate formattedDate = formatDate(userDateString);
		System.out.println(formattedDate);

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

}
