/* Mayank Surti
 * Scene Class - Superclass handling rendering and input for a particular sections of the game
 * 5/31
 */

package gui;

import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public abstract class Scene {

    // Anchor points
    protected enum Anchor {
        TOP, BOTTOM, LEFT, RIGHT,
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT;
    }

    // Attributes
    protected int selectionX, selectionY;
    protected int maxSelectionX, maxSelectionY;
    protected int lastX, lastY;

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

        // Space
        Action spaceAction = new SpaceAction();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "space");
        actionMap.put("space", spaceAction);

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
            System.exit(0);
        }
    }

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

    // Space
    protected class SpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            spacePressed();
        }
    }
    protected void spacePressed() {}

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


    /* Increment/decrement selections */
    protected void incrementSelectionY() {
        selectionY = increment(selectionY, maxSelectionY);
    }
    protected void decrementSelectionY() {
    	selectionY = decrement(selectionY, maxSelectionY);
    }
    protected void incrementSelectionX() {
        selectionX = increment(selectionX, maxSelectionX);
    }
    protected void decrementSelectionX() {
    	selectionX = decrement(selectionX, maxSelectionX);
    }
    /* Increment and decrement number with proper looping */
    private int increment(int num, int max) {
    	if ((num + 1) > max) {
            num = 1;
        } else {
            num++;
        }
    	return num;
    }
    private int decrement(int num, int max) {
    	if ((num - 1) < 1) {
            num = max;
        } else {
            num--;
        }
    	return num;
    }
    /* Revert selection to usable state */
    protected void resetSelection(int newMaxX, int newMaxY) {
        if (lastX <= newMaxX && lastX > 0) {
            selectionX = lastX;
        } else {
            selectionX = 1;
        }
    	if (lastY <= newMaxY && lastY > 0) {
            selectionY = lastY;
        } else {
            selectionY = 1;
        }
    	maxSelectionX = newMaxX;
    	maxSelectionY = newMaxY;
    }

    /* Render */
    protected abstract void draw(Graphics2D g2D);

    /* Get current selection indexes */
    public int getSelectionX() {
    	return selectionX;
    }
    public int getSelectionY() {
    	return selectionY;
    }

}