package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Archer extends Unit {

    public Archer(Turn team) {
        this.team = team;
        name = "Archer";
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-archer.png").getImage();
        maxHP = 2;
        HP = maxHP;
        walkRange = 3;
        attackRange = 2;
    }
    
}
