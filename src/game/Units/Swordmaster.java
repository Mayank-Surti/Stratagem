package game.Units;

import game.State.Turn;

public class Swordmaster extends Myrmidon {

    public Swordmaster(Turn team) {
        super(team);
        type = UnitType.SWORDMASTER;
        setImages();
        maxHP = 3;
        HP = maxHP;
        DMG = 4;
        promoted = true;
        desc = "Melee unit with low health and very high \ndamage. High movement range.";
    }
    
}
