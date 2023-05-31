package game.Units;

import game.State.Turn;

public class Mercenary extends Unit {

    public Mercenary(Turn team) {
        this.team = team;
        type = UnitType.MERCENARY;
        setImages();
        maxHP = 3;
        HP = maxHP;
        DMG = 2;
        walkRange = 2;
        attackRange = 1;
        promoted = false;
        desc = "Melee unit with moderate health, damage, and \nmovement range.";
    }
    
}
