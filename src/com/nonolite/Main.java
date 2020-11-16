package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import processing.core.PApplet;

public class Main extends PApplet {
    private static Board _board;
    private int _screenWidth = 1500;
    private int _screenHeight = 800;
    
    public static void main(String[] args) {
        PApplet.main("com.nonolite.Main");
        
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
        
        
        try (BufferedWriter outputStream = new BufferedWriter(new FileWriter("out/save.txt"))) {
            outputStream.write(writeBoard(nonoBoard.getSaveBoard()));
        }
        catch (IOException exception) {
            System.out.println("file not found");
        }
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
    
    public void setup() {
        surface.setResizable(true);
        background(50);
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        int widgetWidth = width;
        int widgetHeight = height;
        
        _board.drawBoard(getGraphics(), 0, 0, widgetWidth, widgetHeight);
    }
    
    public void keyReleased() {
        _board.keyInput(keyCode);
    }
    
    public void mouseReleased() {
        _board.mouseInput(mouseButton, mouseX, mouseY);
    }
}
