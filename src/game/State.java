/* Mayank Surti
 * State Class - Handles "State" of the game, determining where in the game the players are
 * 5/31
 */

package game;

public abstract class State {
	
	public enum MainState {
		MENU, PREP, BATTLE;
	}
	public enum Turn {
		BLUE, RED;
	}
	public enum MenuState {
		OPTIONS, RULES, MAPS
	}
	public enum PrepState {
		UNIT, GRID;
	}
	public enum BattleState {
		UNIT, MOVE, ATTACK, END;
	}
	
	/* Attributes */
	public static MainState main;
	public static Turn turn;
	public static MenuState menu;
	public static PrepState prep;
	public static BattleState battle;

}
