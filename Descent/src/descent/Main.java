package descent;

public class Main {

	public static void main(String[] args) {
		Player test = new Player("Gerbert", 10, 85, 10, 10);
		Enemy sb = new Enemy(Enemy.Type.SHROOMBEAR, 5);
		Enemy hb = new Enemy(Enemy.Type.HAMMERBEAK, 3);
		CombatHandler combat1 = new CombatHandler(test, sb);
		combat1.startBattle();
		
	}

} 
