package game;

import game.State.Turn;
import game.Units.Unit;

public class Game {

	/* Attributes */
	public Unit[][] blueUnits;
	public Map map;

	/* Constructor */
	public Game() {
		//blueTeam = new Team();
		blueUnits = new Unit[8][8];
		map = new Map();
	}

	public Unit getUnit(Turn team, int x, int y) {
		switch (team) {
			default:
			case BLUE: return blueUnits[x][y];
			case RED: return blueUnits[x][y];
		}
	}

	public void addUnit(Turn team, Unit unit, int x, int y) {
		//System.out.println(unit.getName());
		switch (team) {
			case BLUE:
			blueUnits[x][y] = unit;
			break;
			case RED:
			break;
		}
	}
	
	public boolean isTeamFull(Turn team) {
		boolean isFull = true;
		switch (team) {
			case BLUE:
			for (int i = 0; i < blueUnits.length; i++) {
				if (blueUnits[i] == null) {
					isFull = false;
					break;
				}
			}
			break;
			case RED:
			break;
		}
		return isFull;
	}

}
