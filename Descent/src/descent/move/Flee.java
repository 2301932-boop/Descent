package descent.move;

import descent.Player;
import descent.Enemy;

public class Flee extends Move {
	public Flee() {
		super("Flee", "Take your chances and run away", 1);
	}

	@Override
	public String execute(Player player, Enemy enemy) {
		if (!player.hasEnergy(energyCost)) {
			return "[FAIL]: not enough energy!";
		}
		player.useEnergy(energyCost);
		return "You try to run away!";
	}
}
