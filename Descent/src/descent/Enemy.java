package descent;

public class Enemy {

	private String name;
	private int level;
	private int maxHealth;
	private int health;
	private int attack;
	private int defense;
	private int expReward;

	public Enemy(String type, int level) {

		this.name = type;

		switch (type) {

		case "Shroombear":
			this.maxHealth = level * 15;
			this.health = maxHealth;
			this.attack = level * 6;
			this.defense = 0;
			this.expReward = (int) (level * 2.5);
			break;

		case "Hammerbeak":
			this.maxHealth = level * 6;
			this.health = maxHealth;
			this.attack = level * 3;
			this.defense = 0;
			this.expReward = (int) (level * 2.5);
		}
	}

	public boolean isDefeated() {
		return health <= 0;
	}

	public void takeDamage(int damage) {
		health = Math.max(health - damage, 0);
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}
	
}
