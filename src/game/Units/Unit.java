package game.Units;

import game.State.Turn;

import java.awt.Image;

public class Unit {
	
	public enum UnitType {
		KNIGHT, ARCHER,
		PALADIN, SNIPER
	}
	
	/* Attributes */
	protected Turn team;
	protected UnitType type;
	protected Image sprite;
	protected int maxHP, HP, DMG, walkRange, attackRange, numAttacks;
	protected boolean promoted;
	public static final int ATTACKS_REQ = 2;
	
	/* Constructors */
	public Unit() {}
	public Unit(Unit unit) {
		this.team = unit.team;
		//this.name = unit.name;
		this.type = unit.type;
		this.sprite = unit.sprite;
		this.maxHP = unit.maxHP;
		this.HP = unit.HP;
		this.DMG = unit.DMG;
		this.walkRange = unit.walkRange;
		this.attackRange = unit.attackRange;
		this.numAttacks = unit.numAttacks;
		this.promoted = unit.promoted;
	}
	
	/* Getters */
	public String getName() {
		return type.name();
	}
	public UnitType getType() {
		return type;
	}
	public int getHP() {
		return HP;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public int getDMG() {
		return DMG;
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
	public int getNumAttacks() {
		return numAttacks;
	}
	public boolean wasPromoted() {
		return promoted;
	}
	public Turn getTeam() {
		return team;
	}
	
	/* Setter */
	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}

	/* Attack other unit */
	public void attackUnit(Unit unit) {
		unit.HP--;
		numAttacks++;
		if (unit.HP <= 0) {
			unit = null;
		}
	}

}
