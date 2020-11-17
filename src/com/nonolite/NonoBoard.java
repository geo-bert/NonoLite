package com.nonolite;

import java.util.Arrays;
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
    float xTranslation;
    float yTranslation;
    
    public NonoBoard() {
    }
    
    @Override
    public String[][] getBoard() {
        String[][] abc = new String[width + maxVertical][height + maxHorizontal];
        // space left corner
        for (int i = 0; i < maxVertical; i++) {
            for (int j = 0; j < maxHorizontal; j++) {
                abc[i][j] = " ";
            }
        }
        
        // board
        for (int x = maxVertical; x < abc.length; x++) {
            if (abc[0].length - maxHorizontal >= 0) {
                System.arraycopy(board[x - maxVertical], 0, abc[x], maxHorizontal, abc[0].length - maxHorizontal);
            }
        }
        
        // hint horiz
        for (int x = maxVertical; x < abc.length; x++) {
            String[] hints = horizontal[x - maxVertical].split(" ");
            int k = maxHorizontal - hints.length;
            
            for (int i = 0; i < k; i++) {
                abc[x][i] = " ";
            }
            
            if (maxHorizontal - k >= 0) {
                System.arraycopy(hints, 0, abc[x], k, maxHorizontal - k);
            }
        }
        
        // hint vert
        for (int y = maxHorizontal; y < abc[0].length; y++) {
            String[] hints = vertical[y - maxHorizontal].split(" ");
            int k = maxVertical - hints.length;
            
            for (int i = 0; i < k; i++) {
                abc[i][y] = " ";
            }
            
            for (int x = 0; x < maxVertical - k; x++) {
                abc[x + k][y] = hints[x];
            }
        }
        
        return abc;
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                f.append(board[x][y]);
            }
            saveData[y + 3] = f.toString();
            f.setLength(0);
        }
        
        return saveData;
    }
    
    @Override
    public void generateBoard(int height, int width) {
        board = new String[width][height];
        horizontal = new String[width];
        vertical = new String[height];
        this.height = height;
        this.width = width;
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() < 0.6) {
                    board[x][y] = "1";
                }
                else {
                    board[x][y] = "0";
                }
            }
        }
        
        generateHints();
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
        
        if (keyCode != LEFT && keyCode != RIGHT) {
            return "invalid symbol";
        }
        
        if (x < 0 || y < 0 || x > horizontal.length - 1 || y > vertical.length - 1) {
            return "out of bounds";
        }
        
        if (x == lastX && y == lastY) {
            return "";
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
    public void resetBoard() {
        for (String[] strings : board) {
            Arrays.fill(strings, " ");
        }
    }
    
    @Override
    public boolean checkBoard() {
        return check(true) && check(false);
    }
    
    private boolean check(boolean h) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        String[] hints = new String[h ? width : height];
        
        for (int out = 0; out < (h ? width : height); out++) {
            for (int in = 0; in < (h ? height : width); in++) {
                if (board[h ? out : in][h ? in : out].equals("■")) {
                    count++;
                }
                else if (count > 0) {
                    sb.append(count).append(" ");
                    count = 0;
                }
            }
            
            if (count > 0) {
                sb.append(count).append(" ");
                count = 0;
            }
            
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            else {
                sb.append("0");
            }
            
            hints[out] = sb.toString();
            sb.setLength(0);
        }
        
        return Arrays.equals(hints, h ? horizontal : vertical);
    }
    
    @Override
    public void drawBoard(PGraphics pg, int x, int y, int width, int height) {
        String[][] board = getBoard();
        int columns = board.length;
        int rows = board[0].length;
        int cellSize = min(width / columns, height / rows);
        pg.push();
        xTranslation = x + (float) width / 2 - (float) cellSize * columns / 2;
        yTranslation = y + (float) height / 2 - (float) cellSize * rows / 2;
        pg.translate(xTranslation, yTranslation);
        
        this.cellSize = cellSize;
        
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                String cellText = board[column][row];
                int posX = column * cellSize;
                int posY = row * cellSize;
                
                
                switch (cellText) {
                    case " ":
                        break;
                    case "x":
                        pg.push();
                        pg.fill(50);
                        pg.rect(posX, posY, cellSize, cellSize);
                        pg.pop();
                        
                        int rectWidth = (int) Math.hypot(cellSize, cellSize) / 2;
                        int rectHeight = rectWidth / 5;
                        
                        pg.push();
                        pg.fill(200, 0, 0);
                        pg.noStroke();
                        pg.translate(posX + (float) cellSize / 2, posY + (float) cellSize / 2);
                        pg.rotate(radians(45));
                        pg.rectMode(CENTER);
                        
                        pg.rect(0, 0, rectWidth, rectHeight);
                        pg.rotate(radians(90));
                        pg.rect(0, 0, rectWidth, rectHeight);
                        pg.pop();
                        break;
                    case "■":
                        pg.push();
                        pg.fill(50);
                        pg.rect(posX, posY, cellSize, cellSize);
                        pg.pop();
                        
                        int widthMargin = cellSize / 20;
                        int heightMargin = cellSize / 20;
                        
                        pg.push();
                        pg.fill(150);
                        pg.noStroke();
                        
                        pg.rect(posX + widthMargin, posY + heightMargin, cellSize - 2 * widthMargin, cellSize - 2 * heightMargin);
                        pg.pop();
                        break;
                    default:
                        pg.push();
                        pg.fill(50);
                        pg.rect(posX, posY, cellSize, cellSize);
                        pg.pop();
                        pg.push();
                        pg.fill(200);
                        pg.textSize((float) min(width, height) / 12);
                        pg.textAlign(CENTER, CENTER);
                        pg.text(cellText, posX + (float) cellSize / 2, posY + (float) cellSize / 2);
                        pg.pop();
                        break;
                }
            }
        }
        pg.pop();
    }
    
    private void generateHints() {
        generateHints(true);
        generateHints(false);
    }
    
    private void generateHints(boolean h) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int amountOfHints = 0;
        
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[h ? i : j][h ? j : i].equals("1")) {
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
            
            if (h) {
                if (amountOfHints > maxHorizontal) {
                    maxHorizontal = amountOfHints;
                }
                else if (amountOfHints > maxVertical) {
                    maxVertical = amountOfHints;
                }
            }
            
            amountOfHints = 0;
            
            if (h) {
                horizontal[i] = sb.toString();
            }
            else {
                vertical[i] = sb.toString();
            }
            
            sb.setLength(0);
        }
    }
}
