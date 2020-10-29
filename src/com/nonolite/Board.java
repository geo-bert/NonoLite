package com.nonolite;

public interface Board {
    String[][] getBoard();
    void generateBoard(int height, int width);
    void generateBoard(String[][] board);
    String put(String symbol, int x, int y);
    void resetBoard();
    boolean checkBoard();
}
