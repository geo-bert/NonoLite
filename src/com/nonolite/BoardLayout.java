package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import processing.core.PGraphics;

public class BoardLayout extends Layout {
    private static Board _board;
    
    public BoardLayout(PGraphics pg) {
        super(pg);
        
        Main.getInstance().applyClickable(this);
        
        Board nonoBoard = new NonoBoard();
        try (BufferedReader inputStream = new BufferedReader(new FileReader("out/save.txt"))) {
            
            StringBuilder boardString = new StringBuilder();
            String line;
            while ((line = inputStream.readLine()) != null) {
                boardString.append(line.concat("\r\n"));
            }
            nonoBoard.loadBoard(readBoard(boardString.toString()));
        }
        catch (IOException exception) {
            System.out.println("file not found");
            nonoBoard.generateBoard(2, 2);
        }
        _board = nonoBoard;
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
    public void onLayout(int x, int y, int width, int height) {
        _board.drawBoard(_pg, x, y, width, height);
    }
    
    public void keyInput(int keyCode) {
        _board.keyInput(keyCode);
    }
    
    @Override
    public String mouseInput(int keyCode, int mouseX, int mouseY) {
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
    
    public void save() {
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter("out/save.txt"))) {
            outputStream.write(writeBoard(_board.getSaveBoard()));
        }
        catch (IOException exception) {
            System.out.println("file not found");
        }
    }
    
    public void newRandomBoard(){
        _board.generateBoard(1,50);
    }
}
