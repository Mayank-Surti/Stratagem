package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Archer extends Unit {

    public Archer(Turn team) {
        super(team, "Archer", new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-archer.png").getImage(), 1, 3, 2);
    }
    
}
