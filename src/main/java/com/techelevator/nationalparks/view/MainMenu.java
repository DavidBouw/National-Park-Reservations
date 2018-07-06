package com.techelevator.nationalparks.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Scanner;

import com.techelevator.nationalparks.model.Park;
import com.techelevator.nationalparks.model.ParkData;

import java.io.PrintWriter;

public class MainMenu extends Menu{
	private Scanner in;
	private PrintWriter out;
	
	public MainMenu() {
		super();
	}
	
	@Override
	public Object getChoiceFromOptions(List<ParkData> options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	@Override
	protected Object getChoiceFromUserInput(List<ParkData> options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.size()) {
				choice = options.get(selectedOption - 1);
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	protected void displayMenuOptions(List<ParkData> options) {
		out.println();
		for(int i = 0; i < options.size(); i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options.get(i));
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	
}
