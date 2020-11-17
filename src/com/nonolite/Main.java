package com.nonolite;

import processing.core.PApplet;

public class Main extends PApplet {
    private int _screenWidth = 1500;
    private int _screenHeight = 800;
    private MainLayout _mainLayout;
    
    public static void main(String[] args) {
        PApplet.main("com.nonolite.Main");
    }
    
    public void setup() {
        surface.setResizable(true);
        background(50);
        _mainLayout = new MainLayout(getGraphics());
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        _mainLayout.drawLayout(0, 0, width, height);
    }
    
    public void keyReleased() {
        _mainLayout.getBoardLayout().keyInput(keyCode);
    }
    
    public void mousePressed() {
        _mainLayout.getBoardLayout().mouseInput(mouseButton, mouseX, mouseY);
    }
    
    public void mouseDragged() {
        _mainLayout.getBoardLayout().mouseDragInput(mouseButton, mouseX, mouseY);
    }
}
