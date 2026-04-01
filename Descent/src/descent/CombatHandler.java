package descent;

import java.util.List;
import java.util.ArrayList;

public class CombatHandler {
	private Player player;
	private Enemy enemy;

	public CombatHandler(Player player, Enemy enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	public void startBattle() {

		Console.println(Console.CYAN, "A " + enemy.getName() + " appears!");

		// main combat loop

		while (!enemy.isDefeated() && player.getHealth() > 0) {
			display();
			playerTurn();

			if (enemy.isDefeated()) {
				victory();
				return;
			}
			
			

			enemyTurn();
			
			if(player.getHealth() <= 0) {
				defeat();
				return;
			}
			
		}
	}

	private void display() {
		System.out.println(Console.WHITE + "================================================" + Console.RESET);

		// PLAYER ROW
		// Note: We use %s for Name and Energy because they contain hidden Color Codes
		// (Strings)
		System.out.printf("%-24s | HP: %3d/%-3d | Energy: %s%2d%s/%-2d %n",
				Console.BOLD_WHITE + player.getName() + Console.RESET, player.getHealth(), player.getMaxHealth(),
				Console.YELLOW, player.getEnergy(), Console.RESET, // Colored energy
				player.getMaxEnergy());

		// ENEMY ROW
		System.out.printf("%-24s | HP: %3d/%-3d %n", Console.RED + enemy.getName() + Console.RESET, enemy.getHealth(),
				enemy.getMaxHealth());

		System.out.println(Console.WHITE + "================================================" + Console.RESET);
	}

	private void playerTurn() {
		List<Move> moves = player.getMoves();

		Console.println(Console.BOLD_WHITE, "\n--- AVAILABLE MOVES ---");

		for (int i = 0; i < moves.size(); i++) {
			Move m = moves.get(i);

			String energyText = String.format("[%d E]", m.getEnergyCost());

			// 2. The Format String:
			// %-3d -> The number (1., 2., etc)
			// %-15s -> The Move Name
			// %-10s -> The Energy Cost
			// %s -> The Description

			System.out.print((i + 1) + ". "); 
			System.out.printf("%s%-15s%s ", Console.BOLD_WHITE, m.getName(), Console.RESET);
			System.out.printf("%s%-10s%s | ", Console.YELLOW, energyText, Console.RESET);
			System.out.printf("%s%s%s%n", Console.ITALIC, m.getDescription(), Console.RESET);
		}
		System.out.println("-----------------------");

		int choice = Console.errCheckInt(">>> ", 1, moves.size());
		Move selectedMove = moves.get(choice - 1);

		String result = selectedMove.execute(player, enemy);
		Console.println(Console.CYAN, result);
	}

	private void enemyTurn() {
		Console.println(Console.RED, "\n" + enemy.getName() + " attacks!");

		int damage = Math.max(1, enemy.getAttack());
		player.takeDamage(damage);

		Console.println(Console.RED, "You took " + damage + " damage!");

	}

	private void victory() {
		Console.println(Console.CYAN, "\nVictory! " + enemy.getName() + " has been defeated.");
		player.gainExperience(enemy.getExpReward());
		player.restoreEnergy();
	}
	
	private void defeat() {
		Console.println(Console.BOLD_RED, "\n[ DEFEATED ]");
	    Console.println(Console.RED, "You collapsed in battle...");
	    
	    if (player.isGameOver()) {
	        Console.println(Console.BOLD_RED, "GAME OVER: You have no lives remaining.");
	        //IMPORTANT: player is fully dead right here.
	    } else {
	        Console.println(Console.YELLOW, "You lost a life! Lives remaining: " + player.getLivesRemaining());
	        Console.println(Console.WHITE, "You retreat to safety to recover...");
	        player.heal(player.getMaxHealth());
	    }
	}

}
