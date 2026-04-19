package descent;

public class Main {

	public static void main(String[] args) {
		
		Player player = new Player("Buddy", 10, 10, 10, 10);
		Enemy hb = new Enemy(Enemy.Type.HAMMERBEAK, 10);
		
		player.getInventory().displayInventory();
		/**
		Combat c = new Combat(player, hb);
		c.startBattle(player, hb);
		*/
		
		
	}

}
