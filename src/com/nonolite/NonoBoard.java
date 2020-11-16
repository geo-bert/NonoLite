package com.nonolite;

import java.util.Arrays;
import processing.core.PApplet;
import processing.core.PGraphics;

public class NonoBoard extends PApplet implements Board {
    private String[][] board;
    private String[] hHints;
    private String[] vHints;
    private int maxVHints;
    private int maxHHints;
    private int width;
    private int height;
    
    public NonoBoard() {
    }
    
    @Override
    public String[][] getBoard() {
        String[][] abc = new String[width + maxVHints][height + maxHHints];
        // space left corner
        for (int i = 0; i < maxVHints; i++) {
            for (int j = 0; j < maxHHints; j++) {
                abc[i][j] = " ";
            }
        }
    
        // board
        for (int x = maxVHints; x < abc.length; x++) {
            if (abc[0].length - maxHHints >= 0) {
                System.arraycopy(board[x - maxVHints], 0, abc[x], maxHHints, abc[0].length - maxHHints);
            }
        }
    
        // hint horiz
        for (int x = maxVHints; x < abc.length; x++) {
            String[] hints = hHints[x - maxVHints].split(" ");
            int k = maxHHints - hints.length;
        
            for (int i = 0; i < k; i++) {
                abc[x][i] = " ";
            }
        
            if (maxHHints - k >= 0) {
                System.arraycopy(hints, 0, abc[x], k, maxHHints - k);
            }
        }
        
        // hint vert
        for (int y = maxHHints; y < abc[0].length; y++) {
            String[] hints = vHints[y - maxHHints].split(" ");
            int k = maxVHints - hints.length;
        
            for (int i = 0; i < k; i++) {
                abc[i][y] = " ";
            }
        
            for (int x = 0; x < maxVHints - k; x++) {
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
        for (String s : hHints) {
            h.append(s).append(",");
        }
        h.deleteCharAt(h.length() - 1);
        saveData[1] = h.toString();
    
        StringBuilder v = new StringBuilder();
        for (String s : vHints) {
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
        hHints = new String[width];
        vHints = new String[height];
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
        hHints = board[1].split(",");
        vHints = board[2].split(",");
        width = Integer.parseInt(dim[0]);
        height = Integer.parseInt(dim[1]);
    
        // fill board
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.board[x][y] = "" + board[x + 3].charAt(y);
            }
        }
    
        // get maxHHints
        for (String hHint : hHints) {
            String[] tmp = hHint.split(" ");
            if (tmp.length > maxHHints) {
                maxHHints = tmp.length;
            }
        }
    
        // get maxVHints
        for (String vHint : vHints) {
            String[] tmp = vHint.split(" ");
            if (tmp.length > maxVHints)
                maxVHints = tmp.length;
        }
    }
    
    @Override
    public String put(String symbol, int x, int y) {
        if (!symbol.equals("x") && !symbol.equals("■")) {
            return "invalid symbol";
        }
    
        if (x < 0 || y < 0 || x > hHints.length - 1 || y > vHints.length - 1) {
            return "out of bounds";
        }
    
        if (!board[x][y].equals(symbol)) {
            board[x][y] = symbol;
        }
        else {
            board[x][y] = " ";
        }
    
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
        for (int i = 0; i < width; i++) {
            String[] hints = hHints[i].split(" ");
            int j = 0;
            int y = 0;
        
            // goto first element
            while (y < height && !board[i][y].equals("■")) {
                y++;
            }
        
            if (!(y < height) && !(hints[0].equals("0"))) {
                return false;
            }
        
            int k = y + Integer.parseInt(hints[j]);
        
            while (y < k) {
                if (!board[i][y].equals("■")) {
                    return false;
                }
            
                y++;
            }
        }
    
        for (int i = 0; i < height; i++) {
            String[] hints = vHints[i].split(" ");
            int j = 0;
            int x = 0;
        
            // goto first element
            while (x < width && !board[x][i].equals("■")) {
                x++;
            }
        
        
            if (!(x < width) && !(hints[0].equals("0"))) {
                return false;
            }
        
            int k = x + Integer.parseInt(hints[j]);
        
            while (x < k) {
                if (!board[x][i].equals("■")) {
                    return false;
                }
            
                x++;
            }
        }
        
        return true;
    }
    
    @Override
    public void drawBoard(PGraphics pg, int x, int y, int width, int height) {
        String[][] board = getBoard();
        int columns = board.length;
        int rows = board[0].length;
        int cellSize = min(width / columns, height / rows);
        pg.push();
        pg.translate(x + (float) width / 2 - (float) cellSize * columns / 2,
            y + (float) height / 2 - (float) cellSize * rows / 2);
        
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
                if (amountOfHints > maxHHints) {
                    maxHHints = amountOfHints;
                }
                else if (amountOfHints > maxVHints) {
                    maxVHints = amountOfHints;
                }
            }
            
            amountOfHints = 0;
    
            if (h) {
                hHints[i] = sb.toString();
            }
            else {
                vHints[i] = sb.toString();
            }
            
            sb.setLength(0);
        }
    }
}
