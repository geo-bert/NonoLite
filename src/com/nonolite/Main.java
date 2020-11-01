package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import processing.core.PApplet;

public class Main extends PApplet {
    private static String[][] _board;
    private int _screenWidth = 1000;
    private int _screenHeight = 1000;
    
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
        _board = nonoBoard.getBoard();
        
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
    
    public void settings() {
        size(_screenWidth, _screenHeight);
        smooth();
    }
    
    public void draw() {
        background(50);
        
        int columns = _board.length;
        int rows = _board[0].length;
        int cellWidth = _screenWidth / columns;
        int cellHeight = _screenHeight / rows;
        
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                String cellText = _board[column][row];
                int posX = column * cellWidth;
                int posY = row * cellHeight;
                
                if (!cellText.equals(" ")) {
                    fill(50);
                    rect(posX, posY, cellWidth, cellHeight);
                }
                
                fill(200);
                textSize(32);
                textAlign(CENTER, CENTER);
                if (cellText.equals("x")) {
                    push();
                    fill(200, 0, 0);
                    noStroke();
                    
                    int width = (int) Math.hypot(cellWidth, cellHeight) / 2;
                    int height = width / 5;
                    
                    rectMode(CENTER);
                    
                    translate(posX + (float) cellWidth / 2, posY + (float) cellWidth / 2);
                    rotate(radians(45));
                    
                    rect(0, 0, height, width);
                    rotate(radians(90));
                    rect(0, 0, height, width);
                    pop();
                }
                else if (cellText.equals("■")) {
                
                }
                else {
                    text(cellText, posX + (float) cellWidth / 2, posY + (float) cellHeight / 2);
                }
            }
        }
    }
}
