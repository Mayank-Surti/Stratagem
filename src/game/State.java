package game;

public abstract class State {
	
	public enum MainState {
		PREP, BATTLE;
	}
	public enum Turn {
		BLUE, RED;
	}
	public enum PrepState {
		UNIT, GRID;
	}
	public enum BattleState {
		GRID, ACTION;
	}
	
	/* Attributes */
	public static MainState main;
	public static Turn turn;
	public static PrepState prep;
	public static BattleState battle;

}
