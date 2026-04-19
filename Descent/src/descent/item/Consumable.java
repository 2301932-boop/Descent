package descent.item;

import descent.Player;

public abstract class Consumable extends Item{

	
	public Consumable(String name, String description, Rarity rarity) {
		super(name, description, rarity);
	}

	public abstract void use(Player player);

}
