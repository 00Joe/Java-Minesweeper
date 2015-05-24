/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Joseph
 */
public class GamePanel extends javax.swing.JPanel {

    private int width;
    private int length;
    private final JPanel game = new JPanel();
    private JButton[][] grid;
    private MinesweeperController controller;
    @SuppressWarnings("Convert2Diamond")
    private final HashMap<JButton, String> buttonMap = new HashMap<JButton, String>();

    public GamePanel() {

    }

    public void setGameSize(int w, int l) {
        this.length = l;
        this.width = w;
        //this.setLayout(new BorderLayout());
        game.setLayout(new GridLayout(width, length));
        this.add(game, BorderLayout.CENTER);

        grid = new JButton[width][length]; //allocate the size of grid
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y] = new JButton(); //creates new button
                grid[x][y].setPreferredSize(new Dimension(50, 50));
                grid[x][y].addActionListener(listener);
                game.add(grid[x][y]); //adds button to grid
                buttonMap.put(grid[x][y], Integer.toString(x) + " " + Integer.toString(y));
            }
        }
    }

    private final ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton pressed = (JButton) e.getSource();
            makeMove(pressed);
        }
    };

    public void setController(MinesweeperController c) {
        controller = c;
    }

    public void viewBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                grid[i][j].setText(controller.getCell(i, j));
            }
        }
    }
    
    public void showCell(JButton pressed)
    {
        String[] coordinates = buttonMap.get(pressed).split(" ");
        int i = Integer.parseInt(coordinates[0]);
        int j = Integer.parseInt(coordinates[1]);
        
        pressed.setText(controller.getCell(i, j));
    }

    public void makeMove(JButton pressed) {
        String[] coordinates = buttonMap.get(pressed).split(" ");
        int i = Integer.parseInt(coordinates[0]);
        int j = Integer.parseInt(coordinates[1]);
        if (pressed.isEnabled()) {
            if (controller.getCell(i, j).equals("0")){
                pressed.setEnabled(false);

                if (i - 1 < 9 && i - 1 > -1 && j < 9 && j > -1) // above
                {

                    makeMove(grid[i - 1][j]);
                }
                if (i + 1 < 9 && i + 1 > -1 && j < 9 && j > -1) // below
                {

                    makeMove(grid[i + 1][j]);
                }
                if (i < 9 && i > -1 && j + 1 < 9 && j + 1 > -1) // right
                {

                    makeMove(grid[i][j + 1]);
                }
                if (i < 9 && i > -1 && j - 1 < 9 && j - 1 > -1) // left
                {

                    makeMove(grid[i][j - 1]);
                }
                if (i - 1 < 9 && i - 1 > -1 && j - 1 < 9 && j - 1 > -1) // diagonal up left
                {

                    makeMove(grid[i - 1][j - 1]);
                }
                if (i - 1 < 9 && i - 1 > -1 && j + 1 < 9 && j + 1 > -1) // diagonal up right
                {

                    makeMove(grid[i - 1][j + 1]);
                }
                if (i + 1 < 9 && i + 1 > -1 && j - 1 < 9 && j - 1 > -1) // diagonal below left
                {

                    makeMove(grid[i + 1][j - 1]);
                }
                if (i - 1 < 9 && i - 1 > -1 && j - 1 < 9 && j - 1 > -1) // diagonal below right
                {

                    makeMove(grid[i - 1][j - 1]);
                }
            }
            else if(controller.getCell(i, j).equals("1") ||
                    controller.getCell(i, j).equals("2") ||
                    controller.getCell(i, j).equals("3") ||
                    controller.getCell(i, j).equals("4") ||
                    controller.getCell(i, j).equals("5") ||
                    controller.getCell(i, j).equals("6") ||
                    controller.getCell(i, j).equals("7") ||
                    controller.getCell(i, j).equals("8") )
            {
                pressed.setEnabled(false);
                showCell(pressed);
            }
            else
            {
                infoBox("You Lost!", "");
                viewBoard();
                
            }
        }
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}
