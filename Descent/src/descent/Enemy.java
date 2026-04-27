package descent;

public class Enemy {

	private String name;
	private int level;
	private int maxHealth;
	private int health;
	private int attack;
	private int expReward;
	private Type type;
	
	
	public enum Type{
		SHROOMBEAR, 
		HAMMERBEAK,
		SILKFANG,
		DRAGON
	}
	
	
	//Constructor=================================================================
	public Enemy(Type type, int level) {

		this.type = type;

		switch (type) {

		case SHROOMBEAR:
			this.name = "Shroombear";
			this.maxHealth = level * 6;
			this.attack = level * 5;
			this.expReward = (int) (level * 2.5);
			break;

		case HAMMERBEAK:
			this.name = "Hammerbeak";
			this.maxHealth = level * 5;
			this.attack = level * 3;
			this.expReward = (int) (level * 2);
			break;
		
		case SILKFANG:
			this.name = "Silkfang";
			this.maxHealth = level * 5;
			this.attack = level * 3;
			this.expReward = (int) (level * 2.75);
			
		case DRAGON:
			this.name = "Dragon";
			this.maxHealth = level * 15;
			this.attack = level * 5;
			this.expReward = (int) (level * 6);
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
		health = Math.max(health - damage, 0);
	}
	
	public void heal() {
		health = maxHealth;
	}
	
	public void heal (int amount) {
		health = Math.min(health + amount, maxHealth);
	}
	
	
	

	
	//Getters=====================================================================
	public String getName() { return name; }
	public int getLevel() { return level; }
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public int getAttack() { return attack; } 
	public int getExpReward() { return expReward; }
	public Type getType() { return type; }
	
	
	
	
}
