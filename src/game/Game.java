/* Mayank Surti
 * Game Class - Handles game related data including unit positions, statuses, levels, and more
 * 5/31
 */

package game;

import java.util.ArrayList;

import game.Map.Tile;
import game.State.BattleState;
import game.State.Turn;
import game.Units.*;
import game.Units.Unit.UnitType;

public class Game {

	/* Attributes */
	public static final int GRID_LENGTH = 7;
	public static final int NUM_UNITS = 5, MAX_PLACE_SPACE = 2;
	public int moves;
	public Unit[][] blueUnits, redUnits;
	public ArrayList<String> blueUnitsDone = new ArrayList<String>(), redUnitsDone = new ArrayList<String>();
	public Map map;

	/* Constructor */
	public Game(Map map) {
		blueUnits = new Unit[Game.GRID_LENGTH][Game.GRID_LENGTH];
		redUnits = new Unit[Game.GRID_LENGTH][Game.GRID_LENGTH];
		this.map = map;
		moves = 0;
	}
	
	/* Returns true if the map tile at the given x and y is traversable (is a floor) */
	public boolean isValidTile(int x, int y) {
		if (map.getTile(x, y) == Tile.FLOOR || map.getTile(x, y) == Tile.FORT) {
			return true;
		} else {
			return false;
		}
	}

	/* Increments the number of moves a team has made. Change turns once all moves have been made */
	public void incrementMoves(Turn team) {
		moves++;
		if (moves >= getTeamSize(team)) {
			resetMoves();
			blueUnitsDone.clear(); 
			redUnitsDone.clear();
			switch (State.turn) {
				case BLUE: State.turn = Turn.RED; break;
				case RED: State.turn = Turn.BLUE; break;
			}
			State.battle = BattleState.UNIT;
		}
	}

	/* Reset moves to zero */
	public void resetMoves() {
		moves = 0;
	}

	/* Returns the number of units in a team */
	public int getTeamSize(Turn team) {
		int counter = 0;
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				switch (team) {
					case BLUE:
					if (blueUnits[i][j] != null) {
						counter++;
					}
					break;
					case RED:
					if (redUnits[i][j] != null) {
						counter++;
					}
					break;
				}
			}
		}
		return counter;
	}

	/* Returns the unit in the given team and x and y coordinate */
	public Unit getUnit(Turn team, int x, int y) {
		switch (team) {
			default:
			case BLUE: return blueUnits[x][y];
			case RED: return redUnits[x][y];
		}
	}

	/* Add given unit to team in x and y coordinates */
	public void addUnit(Turn team, Unit unit, int x, int y) {
		switch (team) {
		case BLUE:
			blueUnits[x][y] = unit;
			break;
		case RED:
			redUnits[x][y] = unit;
			break;
		}
	}
	
	/* Move unit from given team from one coordinate to another */
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
 
	/* Level up given unit */
	public void promoteUnit(Turn team, UnitType type, int x, int y) {
		switch (team) {
			case BLUE: 
				if (!blueUnits[x][y].wasPromoted()) {
					blueUnits[x][y] = findPromotion(Turn.BLUE, type); 
				}
				break;
			case RED: 
				if (!redUnits[x][y].wasPromoted()) {
					redUnits[x][y] = findPromotion(Turn.RED, type); 
				}
				break;
		}
	}
	/* Returns the appropriate promotion for given unit */
	private Unit findPromotion(Turn team, UnitType origType) {
		Unit unit;
		switch (origType) {
		default:
		case MERCENARY:	unit = new Hero(team);			break;
		case KNIGHT: 	unit = new Paladin(team); 		break;
		case MYRMIDON:	unit = new Swordmaster(team);	break;
		case ARCHER:	unit = new Sniper(team); 		break;
		case MAGE:		unit = new Wizard(team);		break;
		}
		unit.setPromoted(true);
		return unit;
	}

	/* Check if any units are on a fort. If so, increment turns on fort */
	public void checkForts() {
		for (int i = 0; i < GRID_LENGTH; i++) {
			for (int j = 0; j < GRID_LENGTH; j++) {
				if (map.tiles[i][j] == Tile.FORT) {
					if (blueUnits[i][j] != null) {
						blueUnits[i][j].incrementTurnsOnFort();
					} else if (redUnits[i][j] != null) {
						redUnits[i][j].incrementTurnsOnFort();
					}
				}
			}
		}
	}
	
	/* Check if a unit is dead on given coordinate. If so, set it to null */
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
	
	/* Returns true if all the units in a team are null/dead */
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
