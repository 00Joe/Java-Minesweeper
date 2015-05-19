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
public class MineSweeper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MinesweeperModel model = new MinesweeperModel();
        GamePanel panel = new GamePanel();
        MinesweeperView view = new MinesweeperView();
        MinesweeperController controller = new MinesweeperController(model, view);
        panel.setGameSize(controller.getWidth(), controller.getHeight());
        panel.setController(controller);
        panel.viewBoard();
        
        view.setContentPane(panel);
        view.setVisible(true);
        view.pack();
        
    }
    
}
