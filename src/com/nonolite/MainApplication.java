package com.nonolite;

import processing.core.PApplet;

public class MainApplication extends PApplet {
    private int _screenWidth = 1500;
    private int _screenHeight = 800;
    private MainLayout mainLayout;
    public MainApplication(){
    }
    
    public void init(){
        mainLayout = new MainLayout(getGraphics());
    }
    
    public void setup() {
        surface.setResizable(true);
        background(50);
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        mainLayout.drawLayout(0,0,_screenWidth,_screenHeight);
    }
}
