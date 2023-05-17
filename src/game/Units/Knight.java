package game.Units;

import game.State.Turn;

import javax.swing.ImageIcon;

public class Knight extends Unit {

    public Knight(Turn team) {
        super(team, "Knight", new ImageIcon("sprites/units/" + team.name().toLowerCase() + "-knight.png").getImage(), 5, 3, 1);
    }
    
}
