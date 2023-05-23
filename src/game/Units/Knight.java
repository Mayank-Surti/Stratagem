package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Knight extends Unit {

    public Knight(Turn team) {
        this.team = team;
        type = UnitType.KNIGHT;
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-knight.png").getImage();
        maxHP = 5;
        HP = maxHP;
        DMG = 2;
        walkRange = 3;
        attackRange = 1;
        promoted = false;
    }
    
}
