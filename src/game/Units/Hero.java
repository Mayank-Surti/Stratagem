package game.Units;

import game.State.Turn;

public class Hero extends Mercenary {

    public Hero(Turn team) {
        super(team);
        type = UnitType.HERO;
        setImages();
        maxHP = 4;
        HP = maxHP;
        DMG = 3;
        promoted = true;
        desc = "Melee unit with good damage, health, and \nmovement range.";
    }
    
}
