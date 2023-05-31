package game.Units;

import game.State.Turn;

public class Archer extends Unit {

    public Archer(Turn team) {
        this.team = team;
        type = UnitType.ARCHER;
        setImages();
        maxHP = 2;
        HP = maxHP;
        DMG = 1;
        walkRange = 2;
        attackRange = 2;
        promoted = false;
        desc = "Ranged unit with low damage and health. \nModerate attack range.";
    }
    
}
