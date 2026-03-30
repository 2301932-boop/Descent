package descent;
import java.util.Scanner;

public class Console {
	
	 public static final String RESET  = "\u001B[0m";
	 public static final String RED    = "\u001B[31m";
	 public static final String GREEN  = "\u001B[32m";
	 public static final String YELLOW = "\u001B[33m";
	 public static final String BLUE   = "\u001B[34m";
	 public static final String PURPLE = "\u001B[35m";
	 public static final String CYAN   = "\u001B[36m";
	 public static final String WHITE  = "\u001B[37m";

	    // Bold variants
    public static final String BOLD_RED    = "\u001B[1;31m";
	public static final String BOLD_GREEN  = "\u001B[1;32m";
	public static final String BOLD_YELLOW = "\u001B[1;33m";
	public static final String BOLD_WHITE  = "\u001B[1;37m";
	
    private static final Scanner in = new Scanner(System.in);

    public static int errCheckInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) {
                int num = in.nextInt();
                in.nextLine(); // flush leftover newline
                if (num >= min && num <= max) {
                    return num;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } else {
                System.out.println("Invalid input. Please enter a number.");
                in.next();
            }
        }
    }

    //For open ended strings where anything can go
    public static String errCheckString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = in.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    // For string input restricted to a set of valid options (e.g. stat names)
    public static String errCheckString(String prompt, String... validOptions) {
        while (true) {
            System.out.print(prompt);
            String input = in.nextLine().trim().toLowerCase();
            for (String option : validOptions) {
                if (input.equals(option.toLowerCase())) {
                    return input;
                }
            }
            System.out.print("Invalid option. Choose from: ");
            System.out.println(String.join(", ", validOptions));
        }
    }
}