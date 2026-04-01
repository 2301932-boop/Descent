package descent;

import java.util.Random;

public class Move {

	private String name;
	private int energyCost;
	private String description;
	private Type type;
	private static final Random random = new Random();

	public enum Type {
		STRIKE, QUICK_ATTACK, FLEE
	}

	public Move(Type type) {
		this.type = type;
		switch (type) {

		case STRIKE:
			this.name = "Strike";
			this.energyCost = 10;
			this.description = "A decent strike with your hands. (STR) ";
			break;

		case QUICK_ATTACK:
			this.name = "Quick Attack";
			this.energyCost = 4;
			this.description = "A quick jab. (AGL)";
			break;

		case FLEE:
			this.name = "Flee";
			this.energyCost = 0;
			this.description = "Take your chances and run away. (AGL)";
			break;

		}
	}

	public String execute(Player player, Enemy enemy) {
		switch (type) {
		case STRIKE: {
			if (player.useEnergy(energyCost) == false) {
				return "FAIL: Not enough energy";
			} else {

				int damage = Math.max(1, player.getStrength() + random.nextInt(2) - enemy.getDefense());
				enemy.takeDamage(damage);
				return "HIT: Dealt " + damage + " damage!";
			}
		}
		case QUICK_ATTACK: {
			if (player.useEnergy(energyCost) == false) {
				return "FAIL: Not enough energy!";
			}
			else {
				int damage = Math.max(1, player.getAgility() - enemy.getDefense());
				enemy.takeDamage(damage);
				return "HIT: Dealt " + damage + " damage!";
			}
		}
		
		case FLEE: {
			int fleeChance = Math.min(player.getAgility() * 2, 80);
			if (fleeChance < random.nextInt(100)) {
				return "FAIL: You failed to run away!";
			}
			else {
				return "FLED: You escaped!";
			}
		}
		default:
			return "Move not found.";
		}
	}
	
	public String getName() { return name; }
	public String getDescription() { return description; }
	public int getEnergyCost() { return energyCost; }
	public Type getType() { return type; }
	
}
