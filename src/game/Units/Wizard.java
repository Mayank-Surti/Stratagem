package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Wizard extends Mage {
	
	public Wizard(Turn team) {
		super(team);
		type = UnitType.WIZARD;
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-mage.png").getImage();
        maxHP = 5;
        HP = maxHP;
        DMG = 3;
        attackRange = 3;
	}

}
