package game.Units;

import javax.swing.ImageIcon;

import game.State.Turn;

public class Paladin extends Knight {

    public Paladin(Turn team) {
        super(team);
        name = "Paladin";
        sprite = new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-paladin.png").getImage();
        maxHP = 6;
        HP = maxHP;
    }
    
}
