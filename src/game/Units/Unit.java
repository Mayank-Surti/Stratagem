package game.Units;

import game.State.Turn;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Unit {
	
	public enum UnitType {
		MERCENARY, HERO,
		KNIGHT, PALADIN,
		MYRMIDON, SWORDMASTER,
		ARCHER, SNIPER,
		MAGE, WIZARD
	}
	
	/* Attributes */
	protected Turn team;
	protected UnitType type;
	protected Image colorSprite, graySprite, card;
	protected int maxHP, HP, DMG, walkRange, attackRange, numAttacks;
	protected boolean promoted;
	protected String desc;
	protected int turnsOnFort;
	public static final int TURNS_TO_HEAL = 2;
	public static final int ATTACKS_TO_PRO = 2;
	
	/* Constructors */
	public Unit() {}
	public Unit(Unit unit) {
		this.team = unit.team;
		this.type = unit.type;
		this.colorSprite = unit.colorSprite;
		this.graySprite = unit.graySprite;
		this.card = unit.card;
		this.maxHP = unit.maxHP;
		this.HP = unit.HP;
		this.DMG = unit.DMG;
		this.walkRange = unit.walkRange;
		this.attackRange = unit.attackRange;
		this.numAttacks = unit.numAttacks;
		this.promoted = unit.promoted;
		this.turnsOnFort = unit.turnsOnFort;
	}
	
	/* Getters */
	public String getName() {
		return type.name().substring(0, 1) + type.name().substring(1).toLowerCase();
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
	public Image getColorSprite() {
		return colorSprite;
	}
	public Image getGraySprite() {
		return graySprite;
	}
	public Image getCard() {
		return card;
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
	public String getDesc() {
		return desc;
	}
	public int getTurnsOnFort() {
		return turnsOnFort;
	}
	public Turn getTeam() {
		return team;
	}

	/* Heal 1 HP if the unit is on the fort */
	public void incrementTurnsOnFort() {
		turnsOnFort++;
		if (turnsOnFort >= TURNS_TO_HEAL) {
			if (HP < maxHP) {
				HP++;
			}
			turnsOnFort = 0;
		}
	}
	
	/* Setter */
	public void setPromoted(boolean promoted) {
		this.promoted = promoted;
	}
	protected void setImages() {
		colorSprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-" + type.name().toLowerCase() + ".png").getImage();
		graySprite = new ImageIcon("sprites/units/gray-" + type.name().toLowerCase() + ".png").getImage();
        card = new ImageIcon("sprites/cards/"+ type.name().toLowerCase() + ".png").getImage();
	}

	/* Attack other unit and decrease their HP */
	public void attackUnit(Unit unit) {
		unit.HP -= DMG;
		numAttacks++;
		if (unit.HP <= 0) {
			unit = null;
		}
	}

}
