package game.Units;

import javax.swing.ImageIcon;

import game.State.Turn;

public class Sniper extends Archer {

    public Sniper(Turn team) {
        super(team);
        type = UnitType.SNIPER;
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-sniper.png").getImage();
        maxHP = 4;
        HP = maxHP;
        DMG = 2;
        attackRange = 4;
        promoted = true;
    }
    
}
