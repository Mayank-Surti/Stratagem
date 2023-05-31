package game.Units;

import game.State.Turn;

public class Knight extends Unit {

    public Knight(Turn team) {
        this.team = team;
        type = UnitType.KNIGHT;
        setImages();
        maxHP = 5;
        HP = maxHP;
        DMG = 2;
        walkRange = 1;
        attackRange = 1;
        promoted = false;
        desc = "Melee unit with low damage and high health. \nLow movement range.";
    }
    
}
