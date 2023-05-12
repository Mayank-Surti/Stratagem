package gui;

import java.awt.Image;

public class Unit {
	
	/* Attributes */
	private String name;
	private Image sprite;
	private int HP, walkRange, attackRange;
	private int x, y;
	
	/* Constructor */
	public Unit(String name, Image sprite, int HP, int walkRange, int attackRange) {
		this.name = name;
		this.sprite = sprite;
		this.HP = HP;
		this.walkRange = walkRange;
		this.attackRange = attackRange;
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
