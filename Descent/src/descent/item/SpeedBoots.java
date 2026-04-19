package descent.item;

import descent.Console;
import descent.Player;

public class SpeedBoots extends Equipment{

	public SpeedBoots() {
		super("Speed Boots", "Boots that make you run faster (+3 agility", Item.Rarity.UNCOMMON);
	}
	
	@Override
	public void use(Player player) {
		player.increaseAgility(3);
	}
	
	public void unequip(Player player) {
		player.increaseAgility(-3);
	}

	@Override
	public void equip(Player player) {
		player.increaseAgility(-3);
		
	}
}
