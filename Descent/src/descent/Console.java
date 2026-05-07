package descent;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Console {
	// String colours/formats
	// ==============================================================
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[91m";
	public static final String GREEN = "\u001B[92m";
	public static final String YELLOW = "\u001B[93m";
	public static final String BLUE = "\u001B[94m";
	public static final String PURPLE = "\u001B[95m";
	public static final String CYAN = "\u001B[96m";
	public static final String GREY = "\u001B[90m";
	public static final String WHITE = "\u001B[97m";
	public static final String BOLD_RED = "\u001B[1;91m";
	public static final String BOLD_GREEN = "\u001B[1;92m";
	public static final String BOLD_YELLOW = "\u001B[1;93m";
	public static final String BOLD_BLUE = "\u001B[1;94m";
	public static final String BOLD_PURPLE = "\u001B[1;95m";
	public static final String BOLD_CYAN = "\u001B[1;96m";
	public static final String BOLD_GREY = "\u001B[1;90m";
	public static final String BOLD_WHITE = "\u001B[1;97m";

	public static final String ITALIC = "\u001B[3m";
	// ===========================================================================================

	private static final Scanner in = new Scanner(System.in);

	// basic error checking Int
	public static int errCheckInt(String prompt, int min, int max) {
		while (true) {
			System.out.print(prompt);
			if (in.hasNextInt()) {
				int num = in.nextInt();
				in.nextLine();
				if (num >= min && num <= max) {
					return num;
				}
				println(RED, "Please enter a number between " + min + " and " + max + ".");
			} else {
				println(RED, "Invalid input. Please enter a number.");
				in.nextLine();
			}
		}
	}

	// For open ended strings where anything can go (ex. names)
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

	public static void pressEnter() {
		System.out.println(Console.WHITE + "\n[ Press Enter to continue... ]" + Console.RESET);
		in.nextLine(); 
	}

	public static void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// Coloured strings method
	public static void println(String colour, String message) {
		System.out.println(colour + message + RESET);
	}

	public static void printSlow(String msg, int time) {
		printSlowClr(WHITE, msg, time);
	}

	public static void printSlow(String msg) {
		printSlowClr(WHITE, msg, 25);
	}

	public static void printSlowClr(String colour, String msg, int time) {
		try {
			//Set the color once before the typing starts
			System.out.print(colour);

			char[] array = msg.toCharArray();
			for (int i = 0; i < array.length; i++) {
				System.out.print(array[i]);
				TimeUnit.MILLISECONDS.sleep(time);
			}

			System.out.println(RESET);

		} catch (Exception e) {
			System.out.println(colour + msg + RESET);
		}

	}
	
	public static void showColours() {
		println(RED, "This is a show of colours!");
		println(GREEN, "This is a show of colours!");
		println(YELLOW, "This is a show of colours!");
		println(BLUE, "This is a show of colours!");
		println(PURPLE, "This is a show of colours!");
		println(CYAN, "This is a show of colours!");
		println(GREY, "This is a show of colours!");
		println(WHITE, "This is a show of colours!");
		
		println(BOLD_RED, "This is a show of colours!");
		println(BOLD_GREEN, "This is a show of colours!");
		println(BOLD_YELLOW, "This is a show of colours!");
		println(BOLD_BLUE, "This is a show of colours!");
		println(BOLD_PURPLE, "This is a show of colours!");
		println(BOLD_CYAN, "This is a show of colours!");
		println(BOLD_GREY, "This is a show of colours!");
		println(BOLD_WHITE, "This is a show of colours!");

		
	}
	
	//stop time in milliseconds
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}