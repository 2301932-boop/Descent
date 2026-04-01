package descent;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Console {

	// String colours/formats ==============================================================
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	public static final String BOLD_RED = "\u001B[1;31m";
	public static final String BOLD_GREEN = "\u001B[1;32m";
	public static final String BOLD_YELLOW = "\u001B[1;33m";
	public static final String BOLD_WHITE = "\u001B[1;37m";
	
	public static final String ITALIC = "\u001B[3m";
	//===========================================================================================
	
	
	private static final Scanner in = new Scanner(System.in);

	// basic error checking Int
	public static int errCheckInt(String prompt, int min, int max) {
		while (true) {
			System.out.print(prompt);
			if (in.hasNextInt()) {
				int num = in.nextInt();

				in.nextLine(); //
				if (num >= min && num <= max) {
					return num;
				}
				println(RED, "Please enter a number between " + min + " and " + max + ".");
			} else {
				println(RED, "Invalid input. Please enter a number.");
				in.next();
			}
		}
	}

	// For open ended strings where anything can go
	public static String errCheckString(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = in.nextLine().trim(); 
			if (!input.isEmpty()) {
				return input;
			}
			println(RED, "Input cannot be empty. Please try again.");
		}
	}

	// For string inputs where 1 or more valid options
	public static String errCheckString(String prompt, String... validOptions) {
		while (true) {
			System.out.print(prompt);
			String input = in.nextLine().trim().toLowerCase();
			for (String option : validOptions) {
				if (input.equals(option.toLowerCase())) {
					return input;
				}
			}
			println(RED, "Invalid option.");
			System.out.println("Choose from: " + String.join(", ", validOptions));
		}
	}

	// Coloured strings method
	public static void println(String colour, String message) {
		System.out.println(colour + message + RESET);
	}
	
	public static void printSlow(String msg, int time) {
		try {
			char[] array = msg.toCharArray();
			for(int i = 0; i < array.length; i++	) {
				System.out.print(array[i]);
				TimeUnit.MILLISECONDS.sleep(time);	
			}
		} catch (Exception e) {
			
		}
	}

}
