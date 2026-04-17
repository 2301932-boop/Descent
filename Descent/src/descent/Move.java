package descent;

import java.util.Random;

import descent.moves.*;


public class Enemy {
	
	Random random = new Random();
	
	private String name;
	private int level;
	private int maxHealth;
	private int health;
	private int defense;
	private int attack;
	private int expReward;
	private Type type;
	// Item itemDrop; //to do: make an array of possible drops and randomize

	// enum to handle the different types of enemies
	public enum Type {
		SHROOMBEAR, HAMMERBEAK
	}

	// type of enemy and level
	public Enemy(Type type, int level) {
		switch (type) {
		case SHROOMBEAR:
			name = "Shroombear";
			maxHealth = (int) (50 + level * 1.5);
			this.defense = 0;
			this.attack = level * 6;
			break;

		case HAMMERBEAK:
			name = "Hammerbeak";
			maxHealth = (int) (40 + level * 1.5);
			this.defense = 0;
			this.attack = level * 3;
			break;

		}
		health = maxHealth;
		this.level = level;
		expReward = (int) (5 + level * 3.5);
	}
	
	public boolean isDead() {
		return (health <= 0);
	}

	public void takeDamage(int amount) {
		int reducedDamage = Math.max(amount - defense, 0);
		health = Math.max(health - reducedDamage, 0);	

	}
	
	
	//Getters
	
	public String getName() { return name; }
	public int getLevel() { return level; }
	public int getDefense() { return defense; }
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getExpReward() { return expReward; }
	public int getAttack() { return attack; }
	public Type getType() { return type; }
	

}
