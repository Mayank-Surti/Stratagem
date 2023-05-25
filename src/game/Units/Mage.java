package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Mage extends Unit {

    public Mage(Turn team) {
        this.team = team;
        type = UnitType.MAGE;
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-mage.png").getImage();
        maxHP = 4;
        HP = maxHP;
        DMG = 2;
        walkRange = 3;
        attackRange = 2;
        promoted = false;
    }
    
}
