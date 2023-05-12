package gui;

import java.awt.*;
import javax.swing.*;

public class PreparationScene extends Scene {

	public PreparationScene(JPanel mainPane) {
		super(mainPane);
	}

	@Override
	protected void update() {
		
	}

	@Override
	protected void draw(Graphics2D g2D) {
		Image img = new ImageIcon("sprites/blue-knight.png").getImage();
		g2D.drawImage(img, 20, 20, 100, 100, null);
	}
	
	

}
