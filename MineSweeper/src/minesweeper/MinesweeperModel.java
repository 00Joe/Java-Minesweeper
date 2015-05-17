/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minesweeper;

/**
 *
 * @author Joseph
 */
public class MinesweeperModel {
    private String[][] board;
    private MinesweeperController controller;
    
    public MinesweeperModel(int width, int height)
    {
        board = new String[width][height];
    }
    
    public void setController(MinesweeperController cont)
    {
        controller = cont;
    }
    
    public void changeBoardSize(int width, int height )
    {
        board = new String[width][height];
    }
}
