package descent;

import java.util.ArrayList;
import java.util.List;

import descent.move.*;
import descent.item.*;


import descent.Inventory;

public class Player {

	//Stats and values
	private String name;
	private int level;
	private int exp;
	private int expToNextLevel;
	private int statPoints;
	private int health;
	private int maxHealth;
	private int livesRemaining;
	private int strength;
	private int agility;
	private int vitality;
	private int energy;
	private int maxEnergy;
	private int intelligence;

	//objects
	private Inventory inventory;
	private Whistle whistle;
	private List<Move> moves;
	
	private Item head;
	private Item chest;
	private Item boots;

	// DEFAULTS
	private static final int START_LEVEL = 1;
	private static final int START_EXP_TO_NEXT_LEVEL = 100;
	private static final int START_MAX_HEALTH = 100;
	private static final int START_LIVES = 3;
	private static final int START_STAT_POINTS = 8;
	private static final int START_MAX_ENERGY = 11;

	public Player(String name, int agility, int intelligence, int strength, int vitality) {

		this.name = name;
		this.maxHealth = START_MAX_HEALTH;
		this.health = maxHealth;
		this.level = START_LEVEL;
		this.expToNextLevel = START_EXP_TO_NEXT_LEVEL;
		this.livesRemaining = START_LIVES;
		this.maxEnergy = START_MAX_ENERGY;
		this.energy = maxEnergy;
		this.agility = agility;
		this.intelligence = intelligence;
		this.strength = strength;
		this.vitality = vitality;
		this.statPoints = START_STAT_POINTS;
		this.whistle = Whistle.WHITE;
		this.moves = new ArrayList<>();
		this.inventory = new Inventory();

		// TEMPORARY MOVES
		addMove(new BasicAttack());
		addMove(new QuickAttack());
		addMove(new Flee());
		
		inventory.addItem(new HealthPotion(), 2);
		inventory.addItem(new EnergyPotion());


	}

	// rank
	public enum Whistle {
		BELL(1.0), RED(1.2), BLUE(1.6), MOON(2.1), BLACK(2.7), WHITE(3.5);

		private final double expMult;

		Whistle(double expMult) {
			this.expMult = expMult;
		}

		public double getExpMult() {
			return expMult;
		}

	}

	// GETTERS
	public String getName() { return name; }
    public int getLevel() { return level; }
    public int getExp() { return exp; }
    public int getExpToNextLevel() { return expToNextLevel; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getLivesRemaining() { return livesRemaining; }
    public int getStatPoints() { return statPoints; }
    public int getStrength() { return strength; }
    public int getAgility() { return agility; }
    public int getVitality() { return vitality; }
    public int getIntelligence() { return intelligence; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }
    public Inventory getInventory() { return inventory; }
    public Whistle getWhistle() { return whistle; }
    public List<Move> getMoves() { return moves; }
    
    public int getExpReward(int baseExp) {
		int scaledExp = (int) Math.round(baseExp * whistle.getExpMult());
		return scaledExp;
	}
//======================================================

	public void allocateStat(String stat, int points) {
		if (statPoints <= 0)
			return;

		if (points > statPoints)
			points = statPoints;

		switch (stat.toLowerCase()) {
		case "strength":
			strength += points;
			energy += points * 5;
			maxEnergy += points * 5;
			break;

		case "agility":
			agility += points;
			break;

		case "vitality":
			vitality += points;
			maxHealth += points * 5;
			health += points * 5;
			break;

		case "intelligence":
			intelligence += points;
			break;

		default:
			System.out.println("Unknown stat: " + stat);
			return;
		}
		statPoints -= points;
		System.out.println("Allocated " + points + " points to " + stat + ". Points remaining: " + statPoints);

	}

	public void increaseStrength(int amount) {
		strength += amount;
	}

	public void increaseIntelligence(int amount) {
		intelligence += amount;
	}

	public void increaseAgility(int amount) {
		agility += amount;
	}

	public void increaseVitality(int amount) {
		vitality += amount;
	}

	public void increaseMaxHealth(int amount) {
		maxHealth += amount;
	}

	public void increaseMaxEnergy(int amount) {
		maxEnergy += amount;
	}

	public void takeDamage(int damage) {
		health -= damage;
		if (health <= 0) {
			health = 0;
			livesRemaining--;
		}

	}

	// check if player is alive
	public boolean isAlive() {
		if (livesRemaining > 0)
			return true;
		else
			return false;
	}

	// heal the user, catch overhealing
	public void heal(int amount) {
		health = Math.min(maxHealth, health + amount);
	}

	public void heal() {
		health = maxHealth;
	}

	public boolean hasEnergy(int amount) {
		if (energy < amount)
			return false;
		else {
			return true;
		}
	}

	public void useEnergy(int amount) {
		if (energy < amount) {
			return;
		}
		energy -= amount;
	}

	public void addEnergy(int amount) {
		energy = Math.min(energy + amount, maxEnergy);
	}

	public void addEnergy() {
		energy = maxEnergy;
	}

	public void gainExperience(int amount) {
		int scaledExp = (int) Math.round(amount * whistle.getExpMult());
		exp += scaledExp;
		while (exp >= expToNextLevel) {
			levelUp();
		}
	}

	
	public void levelUp() {
		exp -= expToNextLevel;
		level++;

		expToNextLevel = (int) Math.round(expToNextLevel * 1.12);
		maxHealth += 10;
		health = maxHealth;
		statPoints += 3;
		
		

		Console.println(Console.BOLD_YELLOW,
				"LEVELED UP! You are now level " + level + ". You have " + statPoints + " stat points to allocate.");
	}

	public void addMove(Move move) {
		for (Move learnedMoves : moves)
			if (learnedMoves.getName().equalsIgnoreCase(move.getName())) {
				Console.println(Console.YELLOW, "You already know " + move.getName());
				return;
			}
		moves.add(move);

	}

}