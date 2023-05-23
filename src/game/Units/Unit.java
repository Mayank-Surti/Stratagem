package game.Units;

import game.State.Turn;

import java.awt.Image;

public class Unit {
	
	/* Attributes */
	protected Turn team;
	protected String name;
	protected Image sprite;
	protected int maxHP, HP, walkRange, attackRange;
	
	/* Constructors */
	public Unit() {}
	public Unit(Unit unit) {
		this.team = unit.team;
		this.name = unit.name;
		this.sprite = unit.sprite;
		this.maxHP = unit.maxHP;
		this.HP = unit.HP;
		this.walkRange = unit.walkRange;
		this.attackRange = unit.attackRange;
	}
	
	/* Getters */
	public String getName() {
		return name;
	}
	public int getHP() {
		return HP;
	}
	public int getMaxHP() {
		return maxHP;
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
	public Turn getTeam() {
		return team;
	}

	/* Attack other unit */
	public void attackUnit(Unit unit) {
		unit.HP--;
		if (unit.HP <= 0) {
			unit = null;
		}
	}

}
