package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
        
        Scanner inputStream = new Scanner(System.in);
        String input = "c 0 0";
        do {
            switch (input) {
                case "check":
                    if (nonoBoard.checkBoard()) {
                        System.out.println("Board correct, well done!");
                        break;
                    }
                    else {
                        System.out.println("Board incorrect, keep trying.");
                    }
                    break;
                case "reset":
                    nonoBoard.resetBoard();
                    break;
                default:
                    String[] inputs = input.split(" ");
                    String inputSymbol;
                    int inputX = Integer.parseInt(inputs[1]);
                    int inputY = Integer.parseInt(inputs[2]);
                    switch (inputs[0]) {
                        case "b":
                            inputSymbol = "■";
                            break;
                        case "x":
                        case "X":
                            inputSymbol = "x";
                            break;
                        default:
                            inputSymbol = inputs[0];
                            break;
                    }
                    
                    String msg = nonoBoard.put(inputSymbol, inputX, inputY);
                    System.out.println(msg);
                    break;
            }
            _board = nonoBoard;
            System.out.print(printBoard(nonoBoard.getBoard()));
            System.out.println("Input (stop, check, reset, b/x x y):");
            input = inputStream.nextLine();
        } while (!input.equals("stop"));
        
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
    
    private static String printBoard(String[][] board) {
        StringBuilder boardString = new StringBuilder();
        for (int column = 0; column < board[0].length; column++) {
            for (String[] strings : board) {
                boardString.append(strings[column]);
            }
            boardString.append("\r\n");
        }
        return boardString.toString();
    }
    
    public void setup() {
        surface.setResizable(true);
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        background(50);
        
        int widgetWidth = width;
        int widgetHeight = height;
        int widgetX = 0;
        int widgetY = 0;
        
        push();
        translate(widgetX + (float) width / 2 - (float) widgetWidth / 2, widgetY + (float) height / 2 - (float) widgetHeight / 2);
        _board.drawBoard(widgetWidth, widgetHeight);
        pop();
    }
}
