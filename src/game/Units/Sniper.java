package game.Units;

import game.State.Turn;

public class Sniper extends Archer {

    public Sniper(Turn team) {
        super(team);
        type = UnitType.SNIPER;
        setImages();
        maxHP = 4;
        HP = maxHP;
        DMG = 2;
        attackRange = 4;
        promoted = true;
        desc = "Ranged unit with moderate damage and health. \nVery high attack range.";
    }
    
}
