package game.Units;

import game.State.Turn;

public class Mage extends Unit {

    public Mage(Turn team) {
        this.team = team;
        type = UnitType.MAGE;
        setImages();
        maxHP = 3;
        HP = maxHP;
        DMG = 2;
        walkRange = 3;
        attackRange = 2;
        promoted = false;
        desc = "Ranged unit with moderate health and \ndamage. Decent attack range.";
    }
    
}
