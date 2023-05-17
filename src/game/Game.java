package game;

import game.State.Turn;
import game.Units.Unit;

public class Game {

	/* Attributes */
	public static final int GRID_LENGTH = 10;
	public static final int NUM_UNITS = 2;
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
		switch (team) {
		case BLUE:
			blueUnits[newX][newY] = blueUnits[oldX][oldY];
			break;
		case RED:
			break;
		}
	}
	
//	public boolean isTeamFull(Turn team) {
//		boolean isFull = true;
//		switch (team) {
//			case BLUE:
//			for (int i = 0; i < blueUnits.length; i++) {
//				for (int j = 0; j < blueUnits.length; j++) {
//					if (blueUnits[i][j] == null) {
//						isFull = false;
//						break;
//					}
//				}
//			}
//			break;
//			case RED:
//			break;
//		}
//		return isFull;
//	}

}
