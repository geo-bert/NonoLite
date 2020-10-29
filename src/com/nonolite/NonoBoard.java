package com.nonolite;

public class NonoBoard implements Board {
    String[][] board;
    
    public NonoBoard() {
    }
    
    @Override
    public String[][] getBoard() {
        return board;
    }
    
    @Override
    public void generateBoard(int height, int width) {
        board = new String[width + 1][height + 1];
        board[0][0] = " ";
        
        for (int i = 1; i < board.length; i++) {
            board[i][0] = "1";
        }
    
        for (int i = 2; i < board.length; i++) {
            board[0][i] = "0";
        }
        
        board[0][1] = Integer.toString(width - 1);
        
        for (int x = 1; x < board.length; x++) {
            for (int y = 1; y < board[x].length; y++) {
                board[x][y] = " ";
            }
        }
    }
    
    @Override
    public void generateBoard(String[][] board) {
        this.board = board;
    }
    
    @Override
    public String put(String symbol, int x, int y) {
        if (symbol.equals("■") || symbol.equals("x") || symbol.equals(" ")) {
            if (x < 0 || y < 0 || x >= board.length - 1 || y >= board[1].length - 1) {
                return "out of bounds";
            }
            board[x + 1][y + 1] = symbol;
            return "";
        }
        return "invalid character";
    }
    
    @Override
    public void resetBoard() {
        for (int x = 1; x < board.length; x++) {
            for (int y = 1; y < board[x].length; y++) {
                board[x][y] = " ";
            }
        }
    }
    
    @Override
    public boolean checkBoard() {
        // unfinished because only one hint number allowed
        for (int x = 1; x < board.length; x++) {
            int cnt = 0;
            
            for (int y = 1; y < board[x].length; y++) {
                if (board[x][y].equals("■")) {
                    cnt++;
                }
            }
    
            if (cnt != Integer.parseInt(board[x][0])) {
                return false;
            }
        }
        
        for (int x = 1; x < board.length; x++) {
            int cnt = 0;
            
            for (int y = 1; y < board[x].length; y++) {
                if (board[y][x].equals("■")) {
                    cnt++;
                }
            }
    
            if (cnt != Integer.parseInt(board[0][x])) {
                return false;
            }
        }
        return true;
    }
}
