package descent.move;

import descent.Enemy;
import descent.Player;

public abstract class Move {
	protected String name;
	protected String description;
	protected int energyCost;
	
	
	public Move(String name, String description, int energyCost) {
		this.name = name;
        this.description = description;
        this.energyCost = energyCost;
	}
	public abstract String execute(Player player, Enemy enemy);
	
	public String getName() { return name; }
    public String getDescription() { return description; }
    public int getEnergyCost() { return energyCost; }
	
}
