/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joseph
 */
public class MinesweeperModel {

    private String[][] board;
    private MinesweeperController controller;
    private int width;
    private int height;
    private int mines;
    private StringBuilder stb;

    private final File f = new File("startup.mnsp");
    private FileWriter fw;
    private PrintWriter pw;
    private String data;

    private final Constants constant = new Constants();

    public MinesweeperModel() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line, delims = ",";
            String[] UaP;
            line = br.readLine();
            UaP = line.split(delims);
            this.width = Integer.parseInt(UaP[0]);
            this.height = Integer.parseInt(UaP[1]);
            this.mines = Integer.parseInt(UaP[2]);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, e);
        }
        board = new String[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = constant.EMPTY;
            }
        }
        setMines();
        setHints();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public void setController(MinesweeperController cont) {
        controller = cont;
    }

    /**
     * Changes board size and updates the startup file
     *
     * @param width
     * @param height
     */
    public void changeBoardSize(int width, int height) {
        String toChange;
        String changeTo;

        stb = new StringBuilder();
        stb.append(Integer.toString(this.width));
        stb.append(",");
        stb.append(Integer.toString(this.height));
        toChange = stb.toString();

        stb = new StringBuilder(Integer.toString(width));
        stb.append(",");
        stb.append(Integer.toString(height));
        changeTo = stb.toString();

        updateLine(toChange, changeTo);

        board = new String[width][height];
    }

    /**
     * used to update a specific line in a file
     *
     * @param toUpdate
     * @param updated
     */
    @SuppressWarnings("null")
    private void updateLine(String toUpdate, String updated) {
        try {
            BufferedReader file = null;
            try {
                file = new BufferedReader(new FileReader("startup.mnsp"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            String line;
            String input = "";

            try {
                while ((line = file.readLine()) != null) {
                    input += line + System.lineSeparator();
                }
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            input = input.replace(toUpdate, updated);

            FileOutputStream os = null;
            try {
                os = new FileOutputStream("startup.mnsp");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            os.write(input.getBytes());

            file.close();
            os.close();
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(MinesweeperModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setMines() {
        int ranCol;
        int ranRow;
        int placedMines = 0;
        while (placedMines < mines) {
            ranCol = 0 + (int) (Math.random() * ((width - 1) + 1));
            //System.out.println(ranCol);
            ranRow = 0 + (int) (Math.random() * ((height - 1) + 1));
            //System.out.println(ranRow);
            board[ranCol][ranRow] = constant.MINE;
            placedMines++;
        }
    }

    public void setHints() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!board[i][j].equals(constant.MINE)) {
                    board[i][j] = Integer.toString(checkNeighbors(i, j));
                }
            }
        }
    }

    public int checkNeighbors(int i, int j) {
        int count = 0;

        if (i-1 < 9 && i-1 > -1 && j < 9 && j > -1 && board[i - 1][j].equals(constant.MINE)) // above
        {
            count++;
        }
        if ( i+1 < 9 && i+1 > -1 && j < 9 && j > -1 && board[i + 1][j].equals(constant.MINE)) // below
        {
            count++;
        }
        if ( i < 9 && i > -1 && j+1 < 9 && j+1 > -1 && board[i][j + 1].equals(constant.MINE)) // right
        {
            count++;
        }
        if ( i < 9 && i > -1 && j-1 < 9 && j-1 > -1 && board[i][j - 1].equals(constant.MINE)) // left
        { 
            count++;
        }
        if (i-1 < 9 && i-1 > -1 && j-1 < 9 && j-1 > -1 && board[i - 1][j - 1].equals(constant.MINE)) // diagonal up left
        {
            count++;
        }
        if (i-1 < 9 && i-1 > -1 && j+1 < 9 && j+1 > -1 && board[i - 1][j + 1].equals(constant.MINE)) // diagonal up right
        {
            count++;
        }
        if (i+1 < 9 && i+1 > -1 && j-1 < 9 && j-1 > -1 && board[i + 1][j - 1].equals(constant.MINE)) // diagonal below left
        {
            count++;
        }
        if (i+1 < 9 && i+1 > -1 && j+1 < 9 && j+1 > -1 && board[i + 1][j + 1].equals(constant.MINE)) // diagonal below right
        {
            count++;
        }

        return count;

    }
    
    public String getCell(int x, int y)
    {
        return board[x][y];
    }
}
