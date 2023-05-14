package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

public abstract class Scene {

    // Anchor points
    protected enum Anchor {
        TOP, BOTTOM, LEFT, RIGHT,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
    }

    // Selection variables
    public int selectionIndex;
    public boolean[][] selectionArr;
    protected int maxSelectionIndex;

    public Scene(JPanel mainPane) {
        
        InputMap inputMap = mainPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainPane.getActionMap();

        // Escape
        Action escapeAction = new EscapeAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "exit");
        actionMap.put("exit", escapeAction);

        // Enter
        Action enterAction = new EnterAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "enter");
        actionMap.put("enter", enterAction);

        // Back
        Action backAction = new BackAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0, false), "back");
        actionMap.put("back", backAction);

        // Up
        Action upAction = new UpAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "up");
        actionMap.put("up", upAction);
        Action upReleasedAction = new UpReleasedAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "upReleased");
        actionMap.put("upReleased", upReleasedAction);

        // Down
        Action downAction = new DownAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "down");
        actionMap.put("down", downAction);
        Action downReleasedAction = new DownReleasedAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "downReleased");
        actionMap.put("downReleased", downReleasedAction);

        // Left
        Action leftAction = new LeftAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "left");
        actionMap.put("left", leftAction);
        Action leftReleasedAction = new LeftReleasedAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "leftReleased");
        actionMap.put("leftReleased", leftReleasedAction);

        // Right
        Action rightAction = new RightAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "right");
        actionMap.put("right", rightAction);
        Action rightReleasedAction = new RightReleasedAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "rightReleased");
        actionMap.put("rightReleased", rightReleasedAction);

    }

    // Escape
    protected class EscapeAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            escapePressed();
        }
    }
    protected void escapePressed() {}

    // Enter
    protected class EnterAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            enterPressed();
        }
    }
    protected void enterPressed() {}

    // Back
    protected class BackAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            backPressed();
        }
    }
    protected void backPressed() {}

    // Up
    protected class UpAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            upPressed();
        }
    }
    protected void upPressed() {}
    protected class UpReleasedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            upReleased();
        }
    }
    protected void upReleased() {}

    // Down
    protected class DownAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            downPressed();
        }
    }
    protected void downPressed() {}
    protected class DownReleasedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            downReleased();
        }
    }
    protected void downReleased() {}

    // Left
    protected class LeftAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            leftPressed();
        }
    }
    protected void leftPressed() {}
    protected class LeftReleasedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            leftReleased();
        }
    }
    protected void leftReleased() {}

    // Right
    protected class RightAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rightPressed();
        }
    }
    protected void rightPressed() {}
    protected class RightReleasedAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            rightReleased();
        }
    }
    protected void rightReleased() {}


    /* Increment selection with proper looping */
    protected void incrementSelection() {
        if ((selectionIndex + 1) > maxSelectionIndex) {
            selectionIndex = 1;
        } else {
            selectionIndex++;
        }
    }
    protected void moveSelectionUp() {
        
    }
    /* Decrement selection with proper looping */
    protected void decrementSelection() {
        if ((selectionIndex - 1) < 1) {
            selectionIndex = maxSelectionIndex;
        } else {
            selectionIndex--;
        }
    }

    
    /* Update and render */
    protected abstract void update();
    protected abstract void draw(Graphics2D g2D);


    /* Draw string with origin at given x and y */
	protected void drawCenteredString(String text, int x, int y, Graphics2D g2D) {
		FontMetrics metrics = g2D.getFontMetrics();
		int newX = x - metrics.stringWidth(text) / 2;
		int newY = y - metrics.getHeight() / 2 + metrics.getAscent();
		g2D.drawString(text, newX, newY);
	}

    /* Draw string with origin at center of given Rectangle */
    protected void drawCenteredString(String text, Rectangle rect, Graphics2D g2D) {
        FontMetrics metrics = g2D.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g2D.drawString(text, x, y);
    }

    /* Fill rect with given width, height, offset and anchor */
    private Rectangle currentRect;
    protected void fillAnchoredRect(int width, int height, Anchor anchor, int offset, Graphics2D g2D) {
        int x = 0, y = 0;
        switch (anchor) {  
            case TOP:
                if (width != 0) {
                    x = MainFrame.WIDTH - width - (MainFrame.WIDTH - width) / 2;
                } else {
                    width = MainFrame.WIDTH - (offset * 2);
                    x = offset;
                }
                y = offset;
                break;
            case BOTTOM:
                if (width != 0) {
                    x = MainFrame.WIDTH - width - (MainFrame.WIDTH - width) / 2;
                } else {
                    width = MainFrame.WIDTH - (offset * 2);
                    x = offset;
                }
                y = MainFrame.HEIGHT - height - offset;
                break;
            case LEFT:
                if (height != 0) {
                    y = MainFrame.HEIGHT - height - (MainFrame.HEIGHT - height) / 2;
                } else {
                    height = MainFrame.HEIGHT - (offset * 2);
                    y = offset;
                }
                x = offset;
                break;
            case TOP_LEFT:
                x = offset;
                y = offset;
                break;
            case BOTTOM_RIGHT:
                x = MainFrame.WIDTH - width - offset;
                y = MainFrame.HEIGHT - height - offset;
                break;
        }
        currentRect = new Rectangle(x, y, width, height);
        g2D.fillRect(x, y, width, height);
    }

    /* Fill rect with given Rectangle, offset, and anchor AND draw centered string */
    protected void drawStringWithRect(String text, int width, int height, Anchor anchor, int offset, Graphics2D g2D) {
        g2D.setColor(Color.DARK_GRAY);
        fillAnchoredRect(width, height, anchor, offset, g2D);  

        Font font = new Font("Plain", Font.PLAIN, 20);
        g2D.setFont(font);
        g2D.setColor(Color.BLUE);
        drawCenteredString(text, currentRect, g2D);
    } 


    
}