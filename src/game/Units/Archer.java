package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Archer extends Unit {

    public Archer(Turn team) {
        this.team = team;
        type = UnitType.ARCHER;
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-archer.png").getImage();
        maxHP = 2;
        HP = maxHP;
        DMG = 1;
        walkRange = 3;
        attackRange = 2;
        promoted = false;
    }
    
}
