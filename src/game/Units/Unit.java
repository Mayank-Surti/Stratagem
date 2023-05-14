package game.Units;

import java.awt.Image;

public class Unit {
	
	/* Attributes */
	protected String name;
	protected Image sprite;
	protected int HP, walkRange, attackRange;
	protected int x, y;
	
	/* Constructor */
	public Unit(String name, Image sprite, int HP, int walkRange, int attackRange) {
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
	public int getY() {
		return y;
	}
	public String getName() {
		return name;
	}

}
