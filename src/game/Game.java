package game;

import game.State.Turn;
import game.Units.Unit;

public class Game {

	/* Attributes */
	public static final int GRID_LENGTH = 10;
	public static final int NUM_UNITS = 1;
	public Unit[][] blueUnits, redUnits;
	public Map map;

	/* Constructor */
	public Game() {
		//blueTeam = new Team();
		blueUnits = new Unit[Game.GRID_LENGTH][Game.GRID_LENGTH];
		redUnits = new Unit[Game.GRID_LENGTH][Game.GRID_LENGTH];
		map = new Map();
	}

	public Unit getUnit(Turn team, int x, int y) {
		switch (team) {
			default:
			case BLUE: return blueUnits[x][y];
			case RED: return redUnits[x][y];
		}
	}

	public void addUnit(Turn team, Unit unit, int x, int y) {
		//System.out.println(unit.getName());
		switch (team) {
			case BLUE:
				blueUnits[x][y] = unit;
				break;
			case RED:
				redUnits[x][y] = unit;
				break;
		}
	}
	
	public void moveUnit(Turn team, int oldX, int oldY, int newX, int newY) {
		Unit newUnit;
		switch (team) {
		case BLUE:
			newUnit = new Unit(blueUnits[oldX][oldY]);
			blueUnits[oldX][oldY] = null;
			blueUnits[newX][newY] = newUnit;
			break;
		case RED:
			newUnit = new Unit(redUnits[oldX][oldY]);
			redUnits[oldX][oldY] = null;
			redUnits[newX][newY] = newUnit;
			break;
		}
	}
	
	public void checkDeadUnit(Turn team, int x, int y) {
		switch (team) {
		case BLUE:
			if (blueUnits[x][y].getHP() <= 0) {
				blueUnits[x][y] = null;
			}
			break;
		case RED:
			if (redUnits[x][y].getHP() <= 0) {
				redUnits[x][y] = null;
			}
			break;
		}
	}
	
	public boolean isTeamDead(Turn team) {
		switch (team) {
		case BLUE:
			for (int i = 0; i < blueUnits.length; i++) {
				for (int j = 0; j < blueUnits[0].length; j++) {
					if (blueUnits[i][j] != null){
						return false;
					}
				}
			}
			break;
		case RED:
			for (int i = 0; i < redUnits.length; i++) {
				for (int j = 0; j < redUnits[0].length; j++) {
					if (redUnits[i][j] != null){
						return false;
					}
				}
			}
			break;
		}
		return true;
	}

}
