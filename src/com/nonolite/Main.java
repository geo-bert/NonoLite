package com.nonolite;

import java.util.Scanner;
import processing.core.PApplet;

public class Main extends PApplet {
    public static void main(String[] args) {
        //PApplet.main("com.nonolite.Main");
        
        Board nonoBoard = new NonoBoard();
        nonoBoard.generateBoard(2, 2);
        
        Scanner inputStream = new Scanner(System.in);
        String input = "c 0 0";
        do {
            if (input.equals("check")) {
                if (nonoBoard.checkBoard()) {
                    System.out.println("Board correct, well done!");
                    break;
                }
                else {
                    System.out.println("Board incorrect, keep trying.");
                }
            }
            else {
                String[] inputs = input.split(" ");
                String inputSymbol = " ";
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
                        break;
                }
                
                String msg = nonoBoard.put(inputSymbol, inputX, inputY);
                System.out.println(msg);
                printBoard(nonoBoard.getBoard());
            }
            System.out.println("Input (stop, check, b/x x y):");
            input = inputStream.nextLine();
        } while (!input.equals("stop"));
    }
    
    private static void printBoard(String[][] board) {
        for (String[] column : board) {
            for (int row = 0; row < board[0].length; row++) {
                System.out.print(column[row]);
            }
            System.out.println();
        }
    }
    
    public void settings() {
        size(200, 200);
    }
    
    public void draw() {
        background(100);
        ellipse(mouseX, mouseY, 20, 20);
    }
}
