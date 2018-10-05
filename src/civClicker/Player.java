// Player class. Keeps track of resources, buildings, and population
// Todo: Clean up unnecessary methods

package civClicker;

public class Player {
	private int food;
	private int wood;
	private int stone;
	
	private int population;
	private int military;
	private int controlPct;
	
	private int houses;
	private int barracks;
	private int farms;
	private int camps;
	private int mines;
	
	
	
	public Player() {
		food = 0;
		wood = 0;
		stone = 0;
		
		houses = 0;
		barracks = 0;
		farms = 1;
		camps = 1;
		mines = 1;
		
		population = 100;
		military = 0;
		controlPct = 20;
	}
	
	public int getFood() {
		return food;
	}
	
	public void addFood(int addedFood) {
		food = food + addedFood * farms;
	}
	
	public boolean payFood(int payment) {
		if (payment > food) {
			return false;
		}
		food = food - payment;
		return true;
	}
	
	public int getWood() {
		return wood;
	}
	
	public void addWood(int addedWood) {
		wood = wood + addedWood * camps;
	}
	
	public boolean payWood(int payment) {
		if (payment > wood) {
			return false;
		}
		wood = wood - payment;
		return true;
	}
	
	public int getStone() {
		return stone;
	}
	
	public void addStone(int addedStone) {
		stone = stone + addedStone * mines;
	}
	
	public boolean payStone(int payment) {
		if (payment > stone) {
			return false;
		}
		stone = stone - payment;
		return true;
	}
	
	public boolean build(String building) {
		if (building == "House") {
			if (payWood(10)){
				if (payFood(10)) {
					houses++;
					return true;
				}
				else {
					addWood(10);
				}
				
			}
		}
		if (building == "Farm") {
			if (payWood(10)) {
				farms++;
				return true;
			}
		}
		if (building == "Mine") {
			if (payWood(10)) {
				if (payStone(10)) {
					mines++;
					return true;
				}
				else {
					addWood(10);
				}
			}
		}
		if (building == "Camp") {
			if (payWood(10)) {
				if (payStone(10)) {
					camps++;
					return true;
				}
				else {
					addWood(10);
				}
			}
		}
		if (building == "Barracks") {
			if (payWood(100)) {
				barracks++;
				return true;
			}
		}
		
		return false;
	}
	
	public int getHouses() {
		return houses;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public void addPopulation(int amount) {
		population = population + amount;
	}
	
	public int getMilitary() {
		return military;
	}
	
	public void setMilitary(int amount) {
		military = amount;
	}
	
	public int getBarracks() {
		return barracks;
	}
	
	public int getFarms() {
		return farms;
	}
	
	public int getCamps() {
		return camps;
	}
	
	public int getMines() {
		return mines;
	}
	
	public int getControlPct() {
		return controlPct;
	}
	
	public void addControlPct(int amount) {
		controlPct = controlPct + amount;
	}
	
	public boolean subControlPct(int amount) {
		controlPct = controlPct - amount;
		if (controlPct < 0) {
			controlPct = 0;
			return true;
		}
		return true;
	}
}
