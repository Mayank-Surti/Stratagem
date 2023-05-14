package game;

import game.Units.Unit;

public class Game {

	/* Attributes */
	public Team blueTeam;
	public Team redTeam;
	public Map map;

	/* Constructor */
	public Game() {
		blueTeam = new Team();
		redTeam = new Team();
		map = new Map();
	}
	

}
