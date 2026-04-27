package descent;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Level {
	private int depth;
	private List<Enemy> enemies;
	private Enemy boss;

	//Do I want to keep this?
	private enum layerEffect {
		NONE, TOXIC_AIR,
	}

	public Level(int depth) {
		this.depth = depth;
	}

	private List<Enemy> generateEnemies() {
		Random random = new Random();
		List<Enemy> enemies = new ArrayList<>();

		// List of possible enemy types
		Enemy.Type[] possibleEnemies;

		
		//TEMPORARY: need to make depth input meters and then get a range of possible enemy levels.
		if (depth <= 3) {
			possibleEnemies = new Enemy.Type[] { 
					Enemy.Type.SHROOMBEAR, 
					Enemy.Type.HAMMERBEAK };
		}
		
		else if (depth <= 6) {
			possibleEnemies = new Enemy.Type[] {
					Enemy.Type.HAMMERBEAK,
					Enemy.Type.SILKFANG };
		}
		
		else {
			possibleEnemies = new Enemy.Type[] { 
					Enemy.Type.SHROOMBEAR, 
					Enemy.Type.HAMMERBEAK,
					Enemy.Type.SILKFANG };
			
		}
		
		
		int enemiesInLayer = 3 + random.nextInt(3);
		
		for (int i = 0; i < enemiesInLayer; i++) {
			Enemy.Type type = possibleEnemies[random.nextInt(possibleEnemies.length)];
			enemies.add(new Enemy(type, depth));
		}
		
		return enemies;
	}
	
	private Boss generateBoss(int depth) {
		Random random = new Random();
		
		Boss.Ability ability;
	    Enemy.Type type;
	    
	    if (depth <= 3) {
	        type = Enemy.Type.SHROOMBEAR;
	        ability = Boss.Ability.ENRAGE;
	    } else if (depth <= 6) {
	        type = Enemy.Type.HAMMERBEAK;
	        ability = Boss.Ability.REGENERATE;
	    } else {
	        type = Enemy.Type.SILKFANG;
	        ability = Boss.Ability.POISON;
	    }
	    
	    return new Boss(type, depth * 2, ability); // boss is twice the level of the depth
			
		
	}
}
