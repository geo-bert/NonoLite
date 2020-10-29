package com.nonolite;

public interface Board {
    char[][] getBoard();
    void generateBoard();
    void generateBoard(char[][] board);
    String put(char symbol, int x, int y);
    void resetBoard();
    boolean checkBoard();
}
