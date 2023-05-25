package gui;

import java.awt.*;

public class Button {

    /* Attributes */
    private int x, y, width, height;
    private String text;
    private Color defaultColor, selectedColor;
    private boolean isSelected;

    /* Constructors */
    public Button(String text, int x, int y, int width, int height) {
        this(x, y, width, height);
        this.text = text;
    }
    public Button(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        isSelected = false;
        defaultColor = Color.BLACK;
        selectedColor = Color.BLUE;
    }

    /* Setters */
    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public void setDefaultColor(Color color) {
        defaultColor = color;
    }
    public void setSelectedColor(Color color) {
        selectedColor = color;
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        g2D.setFont(MainPanel.mainFont);
        if (!isSelected) {
            g2D.setColor(defaultColor);
        } else {
            g2D.setColor(selectedColor);
        }
        g2D.fillRect(x, y, width, height);
        if (text != null) {
            drawCenteredString(text, g2D);
        }
    }

    /* Draw string with origin at center of given Rectangle */
    private void drawCenteredString(String text, Graphics2D g2D) {
        FontMetrics metrics = g2D.getFontMetrics();
        Rectangle rect = new Rectangle(x, y, width, height);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2D.setColor(Color.RED);
        g2D.drawString(text, x, y);
    }
    
}
