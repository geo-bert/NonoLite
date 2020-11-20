package com.nonolite.board;

import java.util.Arrays;
import com.nonolite.Main;
import com.nonolite.design.Design;
import processing.core.PApplet;
import processing.core.PGraphics;

public class NonoBoard extends PApplet implements Board {
    // Board
    private String[][] board;
    private int width;
    private int height;
    // Hints
    private String[] horizontal;
    private String[] vertical;
    private int maxHorizontal;
    private int maxVertical;
    // GUI
    private int cellSize;
    private int lastX = -1;
    private int lastY = -1;
    private float xTranslation;
    private float yTranslation;
    private Design d;
    
    public NonoBoard() {
        d = Main.getInstance().getDesign();
    }
    
    @Override
    public String[] getSaveBoard() {
        String[] saveData = new String[3 + height];
        saveData[0] = width + " " + height;
        
        StringBuilder h = new StringBuilder();
        for (String s : horizontal) {
            h.append(s).append(",");
        }
        h.deleteCharAt(h.length() - 1);
        saveData[1] = h.toString();
        
        StringBuilder v = new StringBuilder();
        for (String s : vertical) {
            v.append(s).append(",");
        }
        v.deleteCharAt(v.length() - 1);
        saveData[2] = v.toString();
        
        StringBuilder f = new StringBuilder();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                f.append(board[x][y]);
            }
            saveData[x + 3] = f.toString();
            f.setLength(0);
        }
        
        return saveData;
    }
    
    @Override
    public void generateBoard(int width, int height) {
        board = new String[width][height];
        this.height = height;
        this.width = width;
        maxHorizontal = 0;
        maxVertical = 0;
        randomSeed(0);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (random(1) < 0.6) {
                    board[x][y] = "■";
                }
                else {
                    board[x][y] = " ";
                }
            }
        }
    
        horizontal = generateHints(true, true);
        vertical = generateHints(false, true);
        resetBoard();
    }
    
    @Override
    public void loadBoard(String[] board) {
        String[] dim = board[0].split(" ");
        this.board = new String[Integer.parseInt(dim[0])][Integer.parseInt(dim[1])];
        horizontal = board[1].split(",");
        vertical = board[2].split(",");
        width = Integer.parseInt(dim[0]);
        height = Integer.parseInt(dim[1]);
        
        // fill board
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.board[x][y] = "" + board[x + 3].charAt(y);
            }
        }
        
        // get maxHHints
        for (String hHint : horizontal) {
            String[] tmp = hHint.split(" ");
            if (tmp.length > maxHorizontal) {
                maxHorizontal = tmp.length;
            }
        }
        
        // get maxVHints
        for (String vHint : vertical) {
            String[] tmp = vHint.split(" ");
            if (tmp.length > maxVertical) {
                maxVertical = tmp.length;
            }
        }
    }
    
    @Override
    public String keyInput(int keyCode) {
        return "no key input defined";
    }
    
    @Override
    public String mouseInput(int keyCode, int xPos, int yPos) {
        int x = (int) Math.floor((xPos - (maxVertical * cellSize) - xTranslation) / cellSize);
        int y = (int) Math.floor((yPos - (maxHorizontal * cellSize) - yTranslation) / cellSize);
        
        if (keyCode != LEFT && keyCode != RIGHT) {
            return "invalid symbol";
        }
        
        if (x < 0 || y < 0 || x > horizontal.length - 1 || y > vertical.length - 1) {
            return "out of bounds";
        }
        
        if (keyCode == LEFT) {
            board[x][y] = (board[x][y].equals("■")) ? " " : "■";
        }
        
        if (keyCode == RIGHT) {
            board[x][y] = (board[x][y].equals("x")) ? " " : "x";
        }
        
        lastX = x;
        lastY = y;
        
        return "";
    }
    
    @Override
    public String mouseDragInput(int keyCode, int xPos, int yPos) {
        int x = (int) Math.floor((xPos - (maxVertical * cellSize) - xTranslation) / cellSize);
        int y = (int) Math.floor((yPos - (maxHorizontal * cellSize) - yTranslation) / cellSize);
        
        if (x == lastX && y == lastY) {
            return "";
        }
        
        return mouseInput(keyCode, xPos, yPos);
    }
    
    @Override
    public void resetBoard() {
        for (String[] strings : board) {
            Arrays.fill(strings, " ");
        }
    }
    
    @Override
    public boolean checkBoard() {
        return Arrays.equals(horizontal, generateHints(true, false)) &&
               Arrays.equals(vertical, generateHints(false, false));
    }
    
    @Override
    public void drawBoard(PGraphics pg, int x, int y, int width, int height) {
        int vert = max(maxVertical, 1);
        int hor = max(maxHorizontal, 1);
        int columns = board.length + vert;
        int rows = board[0].length + hor;
        cellSize = min(width / columns, height / rows);
        pg.push();
        xTranslation = x + (float) width / 2 - (float) cellSize * columns / 2;
        yTranslation = y + (float) height / 2 - (float) cellSize * rows / 2;
        pg.translate(xTranslation, yTranslation);
    
        drawBase(columns, rows, vert, hor);
    
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                int posX = column * cellSize;
                int posY = row * cellSize;
            
                if (column > vert - 1 && row > hor - 1) {
                    drawClick(column, row);
                }
                else if (column > vert - 1) {
                    drawHint(horizontal, maxHorizontal, column, vert, row, posX, posY);
                }
                else if (row > hor - 1) {
                    drawHint(vertical, maxVertical, row, hor, column, posX, posY);
                }
            }
        }
        pg.pop();
    }
    
    private void drawBase(int columns, int rows, int vert, int hor) {
        for (int column = vert; column < columns; column++) {
            d.base(
                column * cellSize,
                (hor - horizontal[column - vert].split(" ").length) * cellSize,
                cellSize,
                (horizontal[column - vert].split(" ").length + board[0].length) * cellSize
            );
        }
        
        for (int row = hor; row < rows; row++) {
            d.base(
                (vert - vertical[row - hor].split(" ").length) * cellSize,
                row * cellSize,
                (vertical[row - hor].split(" ").length + board[0].length) * cellSize - cellSize / 2,
                cellSize
            );
        }
    }
    
    private void drawClick(int column, int row) {
        int vert = max(maxVertical, 1);
        int hor = max(maxHorizontal, 1);
        int posX = column * cellSize;
        int posY = row * cellSize;
        
        d.rect1(posX, posY, cellSize, cellSize);
        
        switch (board[column - vert][row - hor]) {
            case "x":
                d.symbol2(posX, posY, cellSize, cellSize);
                break;
            case "■":
                d.symbol1(posX, posY, cellSize, cellSize);
                break;
        }
    }
    
    private void drawHint(String[] hint, int max, int x, int y, int z, int posX, int posY) {
        String[] a = hint[x - y].split(" ");
        int first = max - a.length;
        if (first <= z) {
            d.rect2(posX, posY, cellSize, cellSize);
            d.text(a[z - first], posX + (float) cellSize / 2, posY + (float) cellSize / 8, (float) cellSize / 1.5f, CENTER, TOP);
        }
    }
    
    private String[] generateHints(boolean h, boolean c) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int amountOfHints = 0;
        String[] hints = new String[h ? width : height];
        
        for (int i = 0; i < (h?width:height); i++) {
            for (int j = 0; j < (h?height:width); j++) {
                if (board[h ? i : j][h ? j : i].equals("■")) {
                    count++;
                }
                else if (count != 0) {
                    sb.append(count).append(" ");
                    count = 0;
                    amountOfHints++;
                }
            }
            
            if (count != 0) {
                sb.append(count).append(" ");
                amountOfHints++;
            }
            
            count = 0;
            
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            else {
                sb.append(0);
            }
    
            if (h && c && amountOfHints > maxHorizontal) {
                maxHorizontal = amountOfHints;
            }
            if (!h && c && amountOfHints > maxVertical) {
                maxVertical = amountOfHints;
            }
            
            amountOfHints = 0;
            hints[i] = sb.toString();
            sb.setLength(0);
        }
        return hints;
    }
}
