package descent;

public class Enemy {

	private String name;
	private int level;
	private int maxHealth;
	private int health;
	private int attack;
	private int defense;
	private int expReward;
	private Type type;
	
	
	public enum Type{
		SHROOMBEAR, 
		HAMMERBEAK,
	}
	
	
	//Constructor=================================================================
	public Enemy(Type type, int level) {

		this.type = type;

		switch (type) {

		case SHROOMBEAR:
			this.name = "Shroombear";
			this.maxHealth = level * 15;
			this.attack = level * 6;
			this.expReward = (int) (level * 2.5);

			this.defense = 0;
			
			break;

		case HAMMERBEAK:
			this.name = "Hammerbeak";
			this.maxHealth = level * 6;
			this.attack = level * 3;
			this.expReward = (int) (level * 2);
			this.defense = 0;
			break;
		
		}
		
		this.health = maxHealth;
		this.level = level;
		
	}
//Combat methods==================================================================
	public boolean isDefeated() {
		return health <= 0;
	}

	public void takeDamage(int damage) {
		int reducedDamage =  Math.max(damage - defense, 0);
		health = Math.max(health - reducedDamage, 0);
	}

	
	//Getters=====================================================================
	public String getName() { return name; }
	public int getLevel() { return level; }
	public int getDefense() { return defense; }
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getAttack() { return attack; } 
	public int getExpReward() { return expReward; }
	public Type getType() { return type; }
	
	
	
	
}
