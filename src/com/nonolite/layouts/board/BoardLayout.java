package com.nonolite.layouts.board;

import com.nonolite.Main;
import com.nonolite.board.Board;
import com.nonolite.board.NonoBoard;
import com.nonolite.layouts.utils.Layout;
import processing.core.PGraphics;

public class BoardLayout extends Layout {
    private static Board _board;
    private int currWidth = 2;
    private int currHeight = 2;
    private int level;
    
    public BoardLayout(PGraphics pg) {
        super(pg);
        
        Main.getInstance().applyClickable(this);
        /*
        Board nonoBoard = new NonoBoard();
        String save = Main.getSaveFileController().loadState();
        if (!save.equals("")) {
            nonoBoard.loadBoard(readBoard(save));
        }
        else {
            nonoBoard.generateBoard(2, 2);
        }
        
        _board = nonoBoard;
        
         */
        _board = new NonoBoard();
        _board.generateBoard(currWidth, currHeight);
    }
    
    private static String[] readBoard(String boardString) {
        return boardString.split("\r\n");
    }
    
    private static String writeBoard(String[] board) {
        StringBuilder boardString = new StringBuilder();
        for (String line : board) {
            boardString.append(line).append("\r\n");
        }
        return boardString.toString();
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        _board.drawBoard(_pg, x, y, width, height);
    }
    
    public void keyInput(int keyCode) {
        _board.keyInput(keyCode);
    }
    
    @Override
    public String mouseInput(int keyCode, float mouseX, float mouseY) {
        return _board.mouseInput(keyCode, mouseX, mouseY);
    }
    
    public void mouseDragInput(int keyCode, int mouseX, int mouseY) {
        _board.mouseDragInput(keyCode, mouseX, mouseY);
    }
    
    public boolean check() {
        return _board.checkBoard();
    }
    
    public void reset() {
        _board.resetBoard();
    }
    
    public void load() {
        _board.loadBoard(readBoard(Main.getSaveFileController().loadState()));
    }
    
    public void save() {
        Main.getSaveFileController().saveState(writeBoard(_board.getSaveBoard()));
    }
    
    public void newRandomBoard() {
        _board.generateBoard(currWidth, currHeight);
        level++;
        
        if ((level % 2 == 0)) {
            currWidth++;
        }
        else {
            currHeight++;
        }
        
        if (level == 15) {
            level = 0;
            currWidth = 2;
            currHeight = 2;
        }
    }
}
