/* Mayank Surti
 * Button Class - Superclass that displays a button with appropriate graphics
 * 5/31
 */

package gui;

import java.awt.BasicStroke;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {

    /* Attributes */
    private int x, y, width, height, arc;
    private String text;
    private boolean disabled, selected;

    /* Constructors */
    public Button(String text, int x, int y, int width, int height, int arc) {
        this(x, y, width, height, arc);
        this.text = text;
    }
    public Button(int x, int y, int width, int height, int arc) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.arc = arc;
        selected = false;
    }

    /* Getters and Setters */
    public boolean isDisabled() {
        return disabled;
    }
    public void setDisabled(boolean isDisabled) {
        this.disabled = isDisabled;
    }
    public void setSelected(boolean isSelected) {
        this.selected = isSelected;
    }

    /* Render */
    public void draw(Graphics2D g2D) {
        // set button color and border
        if (selected) {
            g2D.setColor(MainPanel.BROWN2);
            g2D.fillRoundRect(x, y, width, height, arc, arc);
            g2D.setColor(MainPanel.BROWN3);
            g2D.setStroke(new BasicStroke(6));
            g2D.drawRoundRect(x, y, width, height, arc, arc);
            if (!disabled) {
                g2D.setColor(MainPanel.BROWN2);
                g2D.fillRoundRect(x, y, width, height, arc, arc);
            }
        } else {
            g2D.setColor(MainPanel.BROWN1);
            g2D.fillRoundRect(x, y, width, height, arc, arc);
        }
        if (disabled) {
            g2D.setColor(MainPanel.BLUE_BROWN);
            g2D.fillRoundRect(x, y, width, height, arc, arc);
        }
        // text
        g2D.setFont(MainPanel.mainFont);
        g2D.setColor(MainPanel.BROWN4);
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
        g2D.drawString(text, x, y);
    }

}
