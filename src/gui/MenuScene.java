/* Mayank Surti
 * MenuScene Class - Handles Main Menu functionality, including viewing rules, starting a new game, selecting maps, and quitting
 * 5/31
 */

package gui;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;

import game.Map;
import game.State;
import game.State.MenuState;

public class MenuScene extends Scene {

    /* Attributes */
    private MainPanel mainPane;
    private final int BTN_WIDTH = 200, BTN_HEIGHT = 50, BTN_ARC = 25, BTN_OFFSET = 12;
    private final int MAP_SIZE = 280, STROKE = 10, ARC = 20;
    private final int NEW_LINE_HEIGHT = 50, TXT_OFFSET = 20;
    private Button[] optionsButtons, mapButtons;

    /* Constructor */
    public MenuScene(JPanel mainPane) {
        super(mainPane);
        this.mainPane = (MainPanel) mainPane;
        optionsButtons = new Button[3];
        mapButtons = new Button[3];
        resetSelection(mapButtons.length, optionsButtons.length);
        initButtons();
    }

    /* Initialize options and map buttons with appropriate locations and sizes */
    private void initButtons() {
        optionsButtons[0] = new Button("Play", MainFrame.WIDTH / 2 - BTN_WIDTH / 2, MainFrame.HEIGHT / 2 + BTN_OFFSET,
                BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        optionsButtons[1] = new Button("Rules", MainFrame.WIDTH / 2 - BTN_WIDTH / 2,
                MainFrame.HEIGHT / 2 + BTN_HEIGHT + BTN_OFFSET * 2,
                BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        optionsButtons[2] = new Button("Quit", MainFrame.WIDTH / 2 - BTN_WIDTH / 2,
                MainFrame.HEIGHT / 2 + BTN_HEIGHT * 2 + BTN_OFFSET * 3,
                BTN_WIDTH, BTN_HEIGHT, BTN_ARC);

        mapButtons[0] = new Button("Map 1", MainFrame.WIDTH / 4 - BTN_WIDTH / 2,
                MainFrame.HEIGHT - BTN_HEIGHT - NEW_LINE_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        mapButtons[1] = new Button("Map 2", MainFrame.WIDTH / 4 * 2 - BTN_WIDTH / 2,
                MainFrame.HEIGHT - BTN_HEIGHT - NEW_LINE_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
        mapButtons[2] = new Button("Map 3", MainFrame.WIDTH / 4 * 3 - BTN_WIDTH / 2,
                MainFrame.HEIGHT - BTN_HEIGHT - NEW_LINE_HEIGHT * 3, BTN_WIDTH, BTN_HEIGHT, BTN_ARC);
    }

    /* Input */
    @Override
    protected void upPressed() {
        decrementSelectionY();
    }
    @Override
    protected void downPressed() {
        incrementSelectionY();
    }
    protected void leftPressed() {
        decrementSelectionX();
    }
    @Override
    protected void rightPressed() {
        incrementSelectionX();
    }
    @Override
    protected void enterPressed() {
        if (State.menu == MenuState.OPTIONS) { // going from options to maps or rules
            switch (selectionY) {
                case 1:
                    State.menu = MenuState.MAPS;
                    break;
                case 2:
                    State.menu = MenuState.RULES;
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        } else if (State.menu == MenuState.MAPS) { // going from maps to new game
            mainPane.initPrep(new Map(new File("map-data/map" + selectionX + ".txt")));
        }
        resetSelection(mapButtons.length, optionsButtons.length);
    }

    @Override
    protected void backPressed() {
        if (State.menu == MenuState.RULES) { // return from rules to options
            State.menu = MenuState.OPTIONS;
        } else if (State.menu == MenuState.MAPS) { // return from maps to options
            State.menu = MenuState.OPTIONS;
        }
        resetSelection(mapButtons.length, optionsButtons.length);
    }

    /* Draw string with origin at given x and y */
    protected void drawCenteredString(String text, int x, int y, Graphics2D g2D) {
        FontMetrics metrics = g2D.getFontMetrics();
        int newX = x - metrics.stringWidth(text) / 2;
        int newY = y - metrics.getHeight() / 2 + metrics.getAscent();
        g2D.drawString(text, newX, newY);
    }

    /* Render */
    @Override
    protected void draw(Graphics2D g2D) {
        // background
        g2D.setColor(MainPanel.DARK_BLUE);
        g2D.fillRect(0, 0, MainFrame.WIDTH, MainFrame.HEIGHT);
        g2D.setColor(MainPanel.BROWN1);
        switch (State.menu) {
            case OPTIONS:
                // title
                g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.TITLE_SIZE));
                drawCenteredString(MainFrame.GAME_NAME, MainFrame.WIDTH / 2, MainFrame.HEIGHT / 2 - 100, g2D);
                // buttons
                for (int i = 0; i < optionsButtons.length; i++) {
                    if (selectionY == i + 1) {
                        optionsButtons[i].setSelected(true);
                    } else {
                        optionsButtons[i].setSelected(false);
                    }
                    optionsButtons[i].draw(g2D);
                }
                break;
            case RULES:
                // display rules
                g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER_SIZE));
                g2D.drawString("How to Play", NEW_LINE_HEIGHT, NEW_LINE_HEIGHT);
                g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.NORMAL_SIZE));
                g2D.drawString("This is a two-player, chess-like strategy game.", NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 2);
                g2D.drawString(
                        "There are two teams; BLUE and RED. The goal for each team is to wipe out all units in the opponent's team.",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 3);
                g2D.drawString("There are two phases; the preparation phase and the battle phase.", NEW_LINE_HEIGHT,
                        NEW_LINE_HEIGHT * 4);
                MainPanel.drawFormattedString(
                        "During the preparation phase, each team takes turns selecting which units to have on their team and where they should be \nplaced on the grid. Each unit is distinct and has varying properties (different HP, damage, movement/attack range, etc). ",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 5, TXT_OFFSET, g2D);
                MainPanel.drawFormattedString(
                        "There are two types of units; Good units and Great units. Great units are better versions of their respective Good units. \nEach team must start off with 3 Good units and 2 Great units. You cannot select two of the same unit for your team.",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 6 + TXT_OFFSET, TXT_OFFSET, g2D);
                MainPanel.drawFormattedString(
                        "During the battle phase, each team takes turns moving their units on the grid and performing attacks on the enemy's team.",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 7 + TXT_OFFSET * 2, TXT_OFFSET, g2D);
                MainPanel.drawFormattedString(
                        "Good units can be \"leveled-up\" to a Great unit. This happens when the Good unit successfully attacks an enemy unit 2 times \nduring the game. When a unit is \"leveled-up,\" its properties become better and its HP is reset to the max value.",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 8 + TXT_OFFSET * 2, TXT_OFFSET, g2D);
                MainPanel.drawFormattedString(
                        "The grid contains various different tiles. These include grass/bridges (units can freely move and attack on), lakes (units \ncannot move on but ranged units can attack over), and forts (units heal 1 HP for every turn they remain on the fort).",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 9 + TXT_OFFSET * 3, TXT_OFFSET, g2D);
                g2D.drawString(
                        "If you're unsure as to who's turn it is or what move to make next, look at the top-left corner to see instructions.",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 10 + TXT_OFFSET * 4);
                g2D.drawString(
                        "Deploy different units for each game and try out different strategies to defeat your opponent!",
                        NEW_LINE_HEIGHT, NEW_LINE_HEIGHT * 11 + TXT_OFFSET * 4);
                break;
            case MAPS:
                // display map buttons and preview images
                g2D.setFont(MainPanel.mainFont.deriveFont(MainPanel.HEADER2_SIZE));
                drawCenteredString("Select Map", MainFrame.WIDTH / 2, 80, g2D);
                for (int i = 0; i < mapButtons.length; i++) {
                    if (selectionX == i + 1) {
                        mapButtons[i].setSelected(true);
                    } else {
                        mapButtons[i].setSelected(false);
                    }
                    mapButtons[i].draw(g2D);
                    g2D.setColor(MainPanel.BROWN1);
                    g2D.fillRoundRect(MainFrame.WIDTH / 4 * (i + 1) - MAP_SIZE / 2 - STROKE, 
                            MainFrame.HEIGHT / 4 - STROKE, MAP_SIZE + STROKE * 2, MAP_SIZE + STROKE * 2, ARC, ARC);
                    g2D.drawImage(new ImageIcon("sprites/map-previews/map" + (i + 1) + ".png").getImage(),
                            MainFrame.WIDTH / 4 * (i + 1) - MAP_SIZE / 2, MainFrame.HEIGHT / 4, MAP_SIZE, MAP_SIZE,
                            null);
                }
                break;
        }
    }

}
