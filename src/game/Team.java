package game;

import game.Units.Unit;

public class Team {

    /* Attributes */
    Unit[] units;

    /* Constructor */
    public Team() {
        units = new Unit[8];
    }

    /* Get unit of index */
    public Unit getUnit(int index) {
        return units[index];
    }

    /* Check if team is full */
	public boolean isFull() {
		boolean isFull = true;
		for (int i = 0; i < units.length; i++) {
			if (units[i] == null) {
				isFull = false;
				break;
			}
		}
		return isFull;
	}

	/* Add units to teams */
	public void addUnit(Unit unit) {
		for (int i = 0; i < units.length; i++) {
			if (units[i] == null) {
				units[i] = unit;
				break;
			}
		}
	}
    
}
