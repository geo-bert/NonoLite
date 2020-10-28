package com.nonolite;

import processing.core.PApplet;

public class Main extends PApplet {
    public void settings(){
        size(200, 200);
    }

    public void draw(){
        background(100);
        ellipse(mouseX, mouseY, 20, 20);
    }

    public static void main(String[] args){
        PApplet.main("com.nonolite.Main");
    }
}
