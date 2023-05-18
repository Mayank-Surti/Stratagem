package game.Units;

import game.State.Turn;

import java.awt.Image;

public class Unit {
	
	/* Attributes */
	protected Turn team;
	protected String name;
	protected Image sprite;
	protected int HP, walkRange, attackRange;
	
	/* Constructor */
	public Unit(Turn team, String name, Image sprite, int HP, int walkRange, int attackRange) {
		this.team = team;
		this.name = name;
		this.sprite = sprite;
		this.HP = HP;
		this.walkRange = walkRange;
		this.attackRange = attackRange;
	}
	public Unit(Unit unit) {
		this.team = unit.team;
		this.name = unit.name;
		this.sprite = unit.sprite;
		this.HP = unit.HP;
		this.walkRange = unit.walkRange;
		this.attackRange = unit.attackRange;
	}
	
	/* Getters */
	public String getName() {
		return name;
	}
	public Image getSprite() {
		return sprite;
	}
	public int getWalkRange() {
		return walkRange;
	}
	public int getAttackRange() {
		return attackRange;
	}

	/* Attack other unit */
	public void attackUnit(Unit unit) {
		unit.HP--;
	}

}
