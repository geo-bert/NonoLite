package com.nonolite;

import java.util.Arrays;

public class NonoBoard implements Board {
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
        for(int i = 0; i < maxVHints; i++){
            for(int j = 0; j < maxHHints; j++){
                abc[i][j] = " ";
            }
        }
        
        // board
        for(int x = maxVHints; x < abc.length; x++){
            if (abc[0].length - maxHHints >= 0)
                System.arraycopy(board[x - maxVHints], 0, abc[x], maxHHints, abc[0].length - maxHHints);
        }
        
        // hint horiz
        for(int x = maxVHints; x < abc.length; x++){
            String[] hints = hHints[x - maxVHints].split(" ");
            int k = maxHHints - hints.length;
            
            for(int i = 0; i < k; i++)
                abc[x][i] = " ";
    
            if (maxHHints - k >= 0)
                System.arraycopy(hints, 0, abc[x], k, maxHHints - k);
        }
        
        // hint vert
        for(int y = maxHHints; y < abc[0].length; y++){
            String[] hints = vHints[y - maxHHints].split(" ");
            int k = maxVHints - hints.length;
    
            for(int i = 0; i < k; i++)
                abc[i][y] = " ";
    
            for(int x = 0; x < maxVHints - k; x++){
                abc[x + k][y] = hints[x];
            }
        }
        
        return abc;
    }
    
    @Override
    public void generateBoard(int height, int width) {
        board = new String[width][height];
        hHints = new String[width];
        vHints = new String[height];
        this.height = height;
        this.width = width;
        
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(Math.random() < 0.6)
                    board[x][y] = "1";
                else
                    board[x][y] = "0";
            }
        }
        
        generateHorizHints();
        generateVertHints();
        
        resetBoard();
    }
    
    @Override
    public void generateBoard(String[][] board) {
        // nyi
    }
    
    @Override
    public String put(String symbol, int x, int y) {
        if(!symbol.equals("x") && !symbol.equals("■"))
            return "invalid symbol";
        
        if(x < 0 || y < 0 || x > hHints.length - 1 || y > vHints.length - 1)
            return "out of bounds";
        
        if(!board[x][y].equals(symbol))
            board[x][y] = symbol;
        else
            board[x][y] = " ";
        
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
        for(int i = 0; i < width; i++){
            String[] hints = hHints[i].split(" ");
            int j = 0;
            int y = 0;
            
            // goto first element
            while(y < height && !board[i][y].equals("■")){
                y++;
            }
            
            if(!(y < height))
                return false;
            
            int k = y + Integer.parseInt(hints[j]);
            
            while(y < k){
                if(!board[i][y].equals("■"))
                    return false;
                
                y++;
            }
        }
    
        for(int i = 0; i < height; i++){
            String[] hints = vHints[i].split(" ");
            int j = 0;
            int x = 0;
    
            // goto first element
            while(x < width && !board[x][i].equals("■")){
                x++;
            }
            
            
            if(!(x < width))
                return false;
    
            int k = x + Integer.parseInt(hints[j]);
    
            while(x < k){
                if(!board[x][i].equals("■"))
                    return false;
        
                x++;
            }
        }
        
        return true;
    }
    
    public String HHints(){
        return Arrays.toString(hHints);
    }
    
    public String VHints(){
        return Arrays.toString(vHints);
    }
    
    private void generateHorizHints(){
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int hNr = 0;
        
        for(int i = 0; i < width; i++){
            for(int y = 0; y < height; y++){
                if(board[i][y].equals("1")){
                    c++;
                }
                else if(c != 0){
                    sb.append(c).append(" ");
                    c = 0;
                    hNr++;
                }
            }
            if(c != 0) {
                sb.append(c).append(" ");
                hNr++;
            }
            
            c = 0;
        
            if(sb.length() > 0)
                sb.deleteCharAt(sb.length() - 1);
            else
                sb.append(0);
        
            if(hNr > maxHHints)
                maxHHints = hNr;
            
            hNr = 0;
            hHints[i] = sb.toString();
            sb.setLength(0);
        }
    }
    
    private void generateVertHints(){
        StringBuilder sb = new StringBuilder();
        int c = 0;
        int vNr = 0;
        
        for(int i = 0; i < height; i++){
            for(int x = 0; x < width; x++){
                if(board[x][i].equals("1")){
                    c++;
                }
                else if(c != 0){
                    sb.append(c).append(" ");
                    c = 0;
                    vNr++;
                }
            }
            if(c != 0) {
                sb.append(c).append(" ");
                vNr++;
            }
            c = 0;
        
            if(sb.length() > 0)
                sb.deleteCharAt(sb.length() - 1);
            else
                sb.append(0);
    
            if(vNr > maxVHints)
                maxVHints = vNr;
        
            vNr = 0;
            vHints[i] = sb.toString();
            sb.setLength(0);
        }
    }
}
