package descent;

public class Main {

	

	private static void handleCombat(Enemy enemy) {
		Console.println(Console.BOLD_WHITE, "You've encountered a " + enemy.getName() + "!");

	}

	public static void main(String[] args) {

		Enemy sb = new Enemy("Shroombear", 5);
		handleCombat(sb);
	}

}
