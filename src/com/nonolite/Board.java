package com.nonolite;

import processing.core.PGraphics;
import processing.core.PSurface;

public interface Board {
    String[][] getBoard();
    String[] getSaveBoard();
    void generateBoard(int height, int width);
    void loadBoard(String[] board);
    String put(String symbol, int x, int y);
    void resetBoard();
    boolean checkBoard();
    void drawBoard(PGraphics pg, int width, int height);
}
