package game.Units;

import game.State.Turn;

public class Myrmidon extends Unit {

    public Myrmidon(Turn team) {
        this.team = team;
        type = UnitType.MYRMIDON;
        setImages();
        maxHP = 2;
        HP = maxHP;
        DMG = 3;
        walkRange = 3;
        attackRange = 1;
        promoted = false;
        desc = "Melee unit with low health and high damage. \nHigh movement range.";
    }
    
}
