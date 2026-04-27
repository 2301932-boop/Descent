package descent;

import item.*;

//import descent.moves.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {

		// Console.showColours();

		Player tim = new Player("Timmy", 5, 5, 5, 5);
		Boss hb = new Boss(Enemy.Type.DRAGON, 10, Boss.Ability.ENRAGE);

		tim.getInventory().addItem(new HealthPotion(), 3);
		tim.getInventory().addItem(new SpeedBoots());

		Combat c = new Combat(tim, hb);

		c.startBattle(tim, hb);

	}
}
