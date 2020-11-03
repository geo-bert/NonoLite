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
        
        String[][] board = _board.getBoard();
        int columns = board.length;
        int rows = board[0].length;
        int widgetWidth = width;
        int widgetHeight = height;
        //int cellSize = min(width / columns, height / rows);
        
        push();
        translate((float) width / 2 - (float) cellSize * columns / 2, (float) height / 2 - (float) cellSize * rows / 2);
        _board.drawBoard(widgetWidth, widgetHeight);
        pop();
        /*
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                String cellText = board[column][row];
                int posX = column * cellSize;
                int posY = row * cellSize;
                
                
                
                switch (cellText) {
                    case " ":
                        break;
                    case "x":
                        push();
                        fill(50);
                        rect(posX, posY, cellSize, cellSize);
                        pop();
                        
                        int rectWidth = (int) Math.hypot(cellSize, cellSize) / 2;
                        int rectHeight = rectWidth / 5;
                        
                        push();
                        fill(200, 0, 0);
                        noStroke();
                        translate(posX + (float) cellSize / 2, posY + (float) cellSize / 2);
                        rotate(radians(45));
                        rectMode(CENTER);
                        
                        rect(0, 0, rectWidth, rectHeight);
                        rotate(radians(90));
                        rect(0, 0, rectWidth, rectHeight);
                        pop();
                        break;
                    case "■":
                        push();
                        fill(50);
                        rect(posX, posY, cellSize, cellSize);
                        pop();
                        
                        int widthMargin = cellSize / 20;
                        int heightMargin = cellSize / 20;
                        
                        push();
                        fill(150);
                        noStroke();
                        
                        rect(posX + widthMargin, posY + heightMargin, cellSize - 2 * widthMargin, cellSize - 2 * heightMargin);
                        pop();
                        break;
                    default:
                        push();
                        fill(50);
                        rect(posX, posY, cellSize, cellSize);
                        pop();
                        push();
                        fill(200);
                        textSize((float) min(width, height) / 12);
                        textAlign(CENTER, CENTER);
                        text(cellText, posX + (float) cellSize / 2, posY + (float) cellSize / 2);
                        pop();
                        break;
                }
            }
        }*/
    }
}
