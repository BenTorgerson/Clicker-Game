// Faction class. Keeps track of their strength, and allows them to be attacked
// todo: allow user to purchase upgrades which increases their chance in battle, or decrease opponents base strength

package civClicker;

import java.util.concurrent.ThreadLocalRandom;

public class Faction {
	private int controlPct;
	private int strength;
	
	public Faction() {
		controlPct = 20;
		strength = 20;
	}
	
	public int getControlPct() {
		return controlPct;
	}
	
	public void addControlPct(int value) {
		controlPct = controlPct + value;
	}
	
	public boolean subControlPct(int value) {
		controlPct = controlPct - value;
		if (controlPct < 0) {
			controlPct = 0;
			return false;
		}
		return true;
	}
	
	public int attack(Player player) {
		int min = 0;
		int max = controlPct * strength;
		int defense = ThreadLocalRandom.current().nextInt(min, max + 1);
		
		// if player wins
		if (player.getMilitary() > defense) {
			int troopsLeft = player.getMilitary() - defense;
			player.setMilitary(troopsLeft);
			return troopsLeft;
		}
		player.setMilitary(0);
		return 0;
		
		
	}
}
