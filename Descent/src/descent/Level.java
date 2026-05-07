package descent;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Level {
	private Player player;
	private int depth;
	private int layer;
	private List<Enemy> enemies;
	private Boss boss;
	private int minEnemies;
	private int enemiesDefeated;

	private boolean bossDefeated = false;
	private boolean bossAvailable = false;;
	private boolean levelComplete = false;

	private Random random = new Random();

	// Do I want to keep this?
	private enum layerEffect {
		NONE, TOXIC_AIR
	}

	public Level(Player player) {
		this.player = player;
	}

	public void start() {

		enemiesDefeated = 0;

		while (player.getHealth() > 0 && !levelComplete) {

			int depth = player.getDepth();
			int layer = getLayer(depth);

			displayStatus(depth, layer);

			int choice = getPlayerChoice();

			switch (choice) {
			case 1:
				explore(layer);
				break;
			case 2:
				player.getInventory().manageInventory(player);
				break;
			case 3:
				player.displayStats();
				break;
			case 4:
				// TODO possible 4th option (shop?)
				break;
			case 5:
				if (bossAvailable && !bossDefeated) {
					fightBoss(layer);
				} else if (bossDefeated) {
					descend();
				}
				break;
			}

		}
	}

	private void descend() {
		Console.println(Console.CYAN, "You descend deeper into the abyss...");
		Console.sleep(800);

		levelComplete = true;

	}

	private List<Enemy> generateEnemies(int layer) {

		List<Enemy> enemies = new ArrayList<>();

		// List of possible enemy types
		Enemy.Type[] possibleEnemies;

		if (layer == 1) {
			possibleEnemies = new Enemy.Type[] { 
					Enemy.Type.VALLEY_CROAKER, 
					Enemy.Type.ROCK_LICKER,
					Enemy.Type.HAMMERBEAK };
		}

		else if (layer == 2) {
			possibleEnemies = new Enemy.Type[] { 
					Enemy.Type.HAMMERBEAK, 
					Enemy.Type.OTTOBAS, 
					Enemy.Type.SILKFANG };
		}

		else {
			possibleEnemies = new Enemy.Type[] { 
					Enemy.Type.SHROOMBEAR, 
					Enemy.Type.DRAGON, 
					Enemy.Type.SILKFANG };

		}

		int enemiesInLayer = 3 + random.nextInt(3);

		for (int i = 0; i < enemiesInLayer; i++) {
			Enemy.Type type = possibleEnemies[random.nextInt(possibleEnemies.length)];
			enemies.add(new Enemy(type, layer));
		}

		return enemies;
	}

	private Boss generateBoss(int layer) {

		Boss.Ability ability;
		Enemy.Type type;

		if (layer == 1) {
			type = Enemy.Type.HAMMERBEAK;
			ability = Boss.Ability.ENRAGE;
		} else if (layer == 2) {
			type = Enemy.Type.CRIMSON_SPLITJAW;
			ability = Boss.Ability.REGENERATE;
		} else {
			type = Enemy.Type.SILKFANG;
			ability = Boss.Ability.POISON;
		}

		return new Boss(type, layer * 2, ability); // boss is twice the level of the depth

	}

	private void startCombat(int layer) {

		if (enemies == null || enemies.isEmpty()) {
			enemies = generateEnemies(layer);
		}

		Enemy enemy = enemies.remove(0);

		Combat combat = new Combat(player, enemy);
		boolean won = combat.startBattle();

		if (won && !bossAvailable) {
			int gain = 350 + random.nextInt(151);
			player.increaseDepth(gain);
			Console.println(Console.CYAN, "You descend " + gain + "m...");
			enemiesDefeated++;

			if (enemies.isEmpty()) {
				Console.println(Console.WHITE, "The area grows silent...");
				Console.sleep(800);

				bossAvailable = true;
			}

		} else {
			return;
		}

	}

	private void explore(int layer) {

		int roll = random.nextInt(100);

		if (roll < 100) {
			startCombat(layer);
		} else if (roll < 100) { // 100 = no chance of encounterEvent();
			findLoot();
		} else {
			encounterEvent();
		}
	}

	private void fightBoss(int layer) {
		Boss boss = generateBoss(layer);

		Combat bossFight = new Combat(player, boss);
		boolean won = bossFight.startBattle();

		if (won) {
			Console.println(Console.BOLD_YELLOW, "You've defeated the ruler of this layer.");
			bossDefeated = true;
		}
	}

	private void findLoot() {
		// TODO add loot tables
	}

	private void encounterEvent() {
		// TODO add an event encounter
	}

	private int getLayer(int depth) {
		if (depth <= 1350)
			return 1;
		else if (depth <= 2600)
			return 2;
		else if (depth <= 7000)
			return 3;
		else if (depth <= 12000)
			return 4;
		else if (depth <= 13000)
			return 5;
		else
			return 0;
	}

	private int getPlayerChoice() {
		System.out.println("\nWhat would you like to do?");
		System.out.println("[1] Explore");
		System.out.println("[2] Inventory");
		System.out.println("[3] Stats");
		System.out.println("[4] Rest");

		if (bossDefeated) {
			System.out.println("[5] Descend into layer " + getLayer(player.getDepth()) + 1 + ".");
			return Console.errCheckInt(">>> ", 1, 5);
		}
		else if (bossAvailable) {
			System.out.println("[5] Investigate mysterious presence");
			return Console.errCheckInt(">>> ", 1, 5);
		}

		return Console.errCheckInt(">>> ", 1, 4);
	}

	private void displayStatus(int depth, int layer) {
		Console.println(Console.BOLD_WHITE, "\n==== DESCENT ====");
		Console.println(Console.WHITE, "Depth: " + depth + "m");
		Console.println(Console.WHITE, "Layer: " + layer);
	}
}
