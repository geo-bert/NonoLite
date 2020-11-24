package com.nonolite.board;

import processing.core.PGraphics;

public interface Board {
    
    String[] getSaveBoard();
    
    void generateBoard(int width, int height);
    
    void loadBoard(String[] board);
    
    String keyInput(int keyCode);
    
    String mouseInput(int keyCode, float x, float y);
    
    String mouseDragInput(int keyCode, int x, int y);
    
    void resetBoard();
    
    boolean checkBoard();
    
    void drawBoard(PGraphics pg, float x, float y, float width, float height);
}
