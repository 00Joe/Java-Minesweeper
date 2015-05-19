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
public class MinesweeperController {
    
    private MinesweeperModel model;
    private MinesweeperView view;
    
    public MinesweeperController(MinesweeperModel m, MinesweeperView v){
        this.model = m;
        this.view = v;
    }
    
    public int getWidth()
    {
        return model.getWidth();
    }
    
    public int getHeight()
    {
        return model.getHeight();
    }
    
    public String getCell(int x, int y)
    {
        return model.getCell(x, y);
    }
}
