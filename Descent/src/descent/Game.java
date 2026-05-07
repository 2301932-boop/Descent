package descent;

import item.HealthPotion;
import item.SpeedBoots;

public class Game {

	public static void main(String[] args) {
		Player tim = new Player("Timmy", 5, 5, 5, 5);
		Boss hb = new Boss(Enemy.Type.SILKFANG, 15, Boss.Ability.REGENERATE);

		tim.getInventory().addItem(new SpeedBoots());
		tim.getInventory().addItem(new HealthPotion());
		tim.getInventory().addItem(new HealthPotion());
		tim.getInventory().addItem(new HealthPotion());

		//tim.getInventory().manageInventory(tim);
		
		Level main = new Level(tim);
		
		main.start();
	}

}
