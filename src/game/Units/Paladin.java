package game.Units;

import game.State.Turn;

public class Paladin extends Knight {

    public Paladin(Turn team) {
        super(team);
        type = UnitType.PALADIN;
        setImages();
        maxHP = 6;
        HP = maxHP;
        DMG = 3;
        promoted = true;
        desc = "Melee unit with moderate damage and very high \nhealth. Low movement range.";
    }
    
}
