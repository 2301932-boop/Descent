package descent.move;

import descent.Player;
import descent.Enemy;

public class BasicAttack extends Move{
	public BasicAttack() {
		super("Basic Attack", "A heavy strike", 5);
	}
	
	@Override
	public String execute(Player player, Enemy enemy) {
		if(!player.hasEnergy(energyCost)) {
			return "[FAIL]: not enough energy!";
		}
		
		int damage = player.getStrength() * 2;
		player.useEnergy(energyCost);
		enemy.takeDamage(damage);
		
		return "[HIT]: You strike for " + damage + " damage!";
		
	}
}
