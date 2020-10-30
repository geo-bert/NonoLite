package com.nonolite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        //PApplet.main("com.nonolite.Main");
        
        Board nonoBoard = new NonoBoard();
        nonoBoard.generateBoard(4, 2);
        
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
        
        try {
            BufferedWriter outputStream = new BufferedWriter(new FileWriter("out/save.txt"));
            
            outputStream.write(printBoard(nonoBoard.getBoard()));
            
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("file not found");
        }
    }
    
    private static String printBoard(String[][] board) {
        StringBuilder str = new StringBuilder();
        for (int column = 0; column < board[0].length; column++) {
            for (String[] strings : board) {
                str.append(strings[column]);
            }
            str.append("\r\n");
        }
        return str.toString();
    }
    
    public void settings() {
        size(200, 200);
    }
    
    public void draw() {
        background(100);
        ellipse(mouseX, mouseY, 20, 20);
    }
}
