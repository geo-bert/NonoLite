package com.nonolite;

public interface Board {
    String[][] getBoard();
    String[] getSaveBoard();
    void generateBoard(int height, int width);
    void loadBoard(String[] board);
    String put(String symbol, int x, int y);
    void resetBoard();
    boolean checkBoard();
}
