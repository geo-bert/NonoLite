package com.nonolite;

import processing.core.PGraphics;

public interface Board {
    String[][] getBoard();
    String[] getSaveBoard();
    void generateBoard(int height, int width);
    void loadBoard(String[] board);
    String keyInput(int keyCode);
    String mouseInput(int keyCode, int x, int y);
    void resetBoard();
    boolean checkBoard();
    void drawBoard(PGraphics pg, int x, int y, int width, int height);
}
