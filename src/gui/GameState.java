package gui;

public class GameState {
	
	public enum MainState {
		PREP, BATTLE;
	}
	public enum Turn {
		BLUE, RED;
	}
	public enum PrepState {
		UNIT, LOCATION;
	}
	public enum BattleState {
		UNIT, LOCATION, ACTION;
	}
	
	/* Attributes */
	private MainState main;
	private Turn turn;
	private PrepState prep;
	private BattleState battle;
	
	/* Constructor */
	public GameState() {
		setMain(MainState.PREP);
		setTurn(Turn.BLUE);
	}

	/* Getters and Setters for States */
	public MainState getMain() {
		return main;
	}
	public void setMain(MainState main) {
		this.main = main;
	}

	public Turn getTurn() {
		return turn;
	}
	public void setTurn(Turn turn) {
		this.turn = turn;
	}

	public PrepState getPrep() {
		return prep;
	}
	public void setPrep(PrepState prep) {
		this.prep = prep;
	}

	public BattleState getBattle() {
		return battle;
	}
	public void setBattle(BattleState battle) {
		this.battle = battle;
	}
	

}
