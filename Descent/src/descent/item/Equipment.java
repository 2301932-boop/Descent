package descent.item;

import descent.Player;

public abstract class Equipment extends Item {

	public Equipment(String name, String description, Rarity rarity) {
		super(name, description, rarity);
	}

	public void use(Player player) {
		equip(player);
	}

	public abstract void equip(Player player);

	@Override
	public abstract void unequip(Player player);
}
