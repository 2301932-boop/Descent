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
			this.description = "A decent strike with your hands.";
			break;

		case QUICK_ATTACK:
			this.name = "Quick Attack";
			this.energyCost = 4;
			this.description = "A quick jab.";
			break;

		case FLEE:
			this.name = "Flee";
			this.energyCost = 0;
			this.description = "Take your chances and run away.";
			break;

		}
	}

	public String execute(Player player, Enemy enemy) {
		switch (type) {
		case STRIKE:
			if (player.useEnergy(energyCost) == false) {
				return "Not enough energy";
			} 
			else {

				int damage = Math.max(1, player.getStrength() + random.nextInt(2) - enemy.getDefense());
				enemy.takeDamage(damage);
				return "Dealt " +  damage + "damage!";
			}
		}
	}
}
