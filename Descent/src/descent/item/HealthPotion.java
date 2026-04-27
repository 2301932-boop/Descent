package item;

import descent.Console;
import descent.Player;

public class HealthPotion extends Consumable{

	public HealthPotion() {
		super("Health potion", "Heals you for 20 HP", Rarity.CONSUMABLE);
	}

	@Override
	public void use(Player player){
		player.heal(20);
		Console.println(Console.GREEN, "You used a health potion!");
	}

	@Override
	public void unequip(Player player) {
		//consumable cant be unequipped
	}
}