package com.techelevator.nationalparks.view;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.nationalparks.model.ParkData;

abstract class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu() {
		this.out = new PrintWriter(System.out);
		this.in = new Scanner(System.in);
	}
	

	abstract Object getChoiceFromOptions(List<ParkData> options);
	
	/*
	 {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	*/
	
	protected abstract Object getChoiceFromUserInput(List<ParkData> options);
	/*
	{
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
	 */
	
	protected abstract void displayMenuOptions(List<ParkData> options);
	/*
	{
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	*/
}
