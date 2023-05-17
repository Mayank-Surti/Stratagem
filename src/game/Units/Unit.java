package game.Units;

import game.State.Turn;

import java.awt.Image;

public class Unit {
	
	/* Attributes */
	protected Turn team;
	protected String name;
	protected Image sprite;
	protected int HP, walkRange, attackRange;
	protected int x, y;
	
	/* Constructor */
	public Unit(Turn team, String name, Image sprite, int HP, int walkRange, int attackRange) {
		this.team = team;
		this.name = name;
		this.sprite = sprite;
		this.HP = HP;
		this.walkRange = walkRange;
		this.attackRange = attackRange;
	}
	
	/* Getters */
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public Image getSprite() {
		return sprite;
	}
	public int getWalkRange() {
		return walkRange;
	}

}
