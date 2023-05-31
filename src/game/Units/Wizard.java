package game.Units;

import game.State.Turn;

public class Wizard extends Mage {
	
	public Wizard(Turn team) {
		super(team);
		type = UnitType.WIZARD;
        setImages();
        maxHP = 4;
        HP = maxHP;
        DMG = 3;
        attackRange = 3;
        promoted = true;
        desc = "Ranged unit with moderate health and high \ndamage. High attack range.";
	}

}
