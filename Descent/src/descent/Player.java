package descent;

public class Player {
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
    private int intelligence;
    private Inventory inventory;

    private static final int START_LEVEL = 1;
    private static final int START_EXP_TO_NEXT_LEVEL = 30;
    private static final int START_MAX_HEALTH = 100;
    private static final int START_LIVES = 3;
    private static final int START_STAT_POINTS = 8;

    public Player(String name, int strength, int agility, int vitality, int intelligence) {
        this.name = name;
        this.level = START_LEVEL;
        this.expToNextLevel = START_EXP_TO_NEXT_LEVEL;
        this.maxHealth = START_MAX_HEALTH;
        this.health = maxHealth;
        this.livesRemaining = START_LIVES;
        this.statPoints = START_STAT_POINTS;
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.inventory = new Inventory();
    }

    // Getters
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
    public Inventory getInventory() { return inventory; }

    public boolean isGameOver() {
        return livesRemaining <= 0;
    }
    
    /**
     * 
     * @param amount, amount to heal
     * taking the lower value of max health or amount to heal when healing to prevent 
     * going past max
     */
    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            livesRemaining--;
            if (isGameOver() == false) {
                health = maxHealth; //restore health on life loss, not game over
            }
        }
    }

    public void gainExperience(int expGained) {
        exp += expGained;
        while (exp >= expToNextLevel) {
            levelUp();
        }
    }

    private void levelUp() {
        exp -= expToNextLevel;
        level++;
        expToNextLevel = (int) (expToNextLevel * 1.3);
        maxHealth += 10;
        health = maxHealth; // restore health on level up
        statPoints += 3;    // reward points to allocate
        System.out.println(name + " reached level " + level + "! You have " + statPoints + " stat points to allocate.");
    }

    public void allocateStats(String stat, int points) {
        if (points <= 0) return;
        if (points > statPoints) points = statPoints;

        switch (stat.toLowerCase()) {
            case "strength":     strength += points;     break;
            
            case "agility":      agility += points;      break;
            
            case "vitality":     vitality += points;
                                 maxHealth += points * 5; 
                                 health += points * 5;
                                 break;
                                 
            case "intelligence": intelligence += points; break;
            
            default:
                System.out.println("Unknown stat: " + stat);
                return;
        }
        statPoints -= points;
        System.out.println("Allocated " + points + " points to " + stat + ". Points remaining: " + statPoints);
    }

    public void viewStats() {
        System.out.println("<==== " + name + " ====>");
        System.out.println("Level: " + level + " | EXP: " + exp + "/" + expToNextLevel);
        System.out.println("HP: " + health + "/" + maxHealth + " | Lives: " + livesRemaining);
        System.out.println("Strength: " + strength + " | Agility: " + agility);
        System.out.println("Vitality: " + vitality + " | Intelligence: " + intelligence);
        System.out.println("Unspent stat points: " + statPoints);
    }
}