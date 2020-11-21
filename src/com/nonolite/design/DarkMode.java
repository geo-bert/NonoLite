package com.nonolite.design;

import com.nonolite.layouts.utils.PGTextWriter;
import processing.core.PApplet;
import processing.core.PGraphics;

public class DarkMode extends PApplet implements Design {
    private final PGraphics _pg;
    private final PGTextWriter _tw;
    
    public DarkMode(PGraphics pg) {
        _pg = pg;
        _tw = new PGTextWriter(pg);
    }
    
    @Override
    public void background() {
        _pg.background(40);
    }
    
    @Override
    public void base(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(50);
        
        _pg.noStroke();
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void baseRect(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(60);
        _pg.stroke(50);
        _pg.strokeWeight(2);
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void baseRect2(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(40);
        _pg.stroke(35);
        _pg.strokeWeight(2);
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void rect1(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(70);
        _pg.noStroke();
        int marginX = width / 20;
        int marginY = height / 20;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY);
        _pg.pop();
    }
    
    @Override
    public void rect2(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(60);
        _pg.noStroke();
        int marginX = width / 20;
        int marginY = height / 20;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY);
        _pg.pop();
    }
    
    @Override
    public void text(char text, float x, float y, float width, float height) {
        textSetup();
        _pg.textAlign(CENTER, CENTER);
        _tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(String text, float x, float y, float width, float height) {
        textSetup();
        _pg.textAlign(CENTER, CENTER);
        _tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(String[] text, float x, float y, float width, float height) {
        textSetup();
        _pg.textAlign(CENTER, CENTER);
        _tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y) {
        text("" + text, x, y);
    }
    
    @Override
    public void text(String text, float x, float y) {
        textSetup();
        _pg.text(text, x, y);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y, float size) {
        text("" + text, x, y, size);
    }
    
    @Override
    public void text(String text, float x, float y, float size) {
        textSetup();
        _pg.textSize(size);
        _pg.text(text, x, y);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y, float size, int align1, int align2) {
        text("" + text, x, y, size, align1, align2);
    }
    
    @Override
    public void text(String text, float x, float y, float size, int align1, int align2) {
        textSetup();
        _pg.textSize(size);
        _pg.textAlign(align1, align2);
        _pg.text(text, x, y);
        textEnd();
    }
    
    private void textSetup() {
        _pg.push();
        _pg.fill(150);
    }
    
    private void textEnd() {
        _pg.pop();
    }
    
    @Override
    public void symbol1(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(132);
        _pg.noStroke();
        int marginX = width / 10;
        int marginY = height / 10;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY);
        _pg.pop();
    }
    
    @Override
    public void symbol2(int x, int y, int width, int height) {
        int rectWidth = (int) Math.hypot(width, height) / 2;
        int rectHeight = rectWidth / 5;
        
        _pg.push();
        _pg.fill(179, 80, 75);
        _pg.noStroke();
        _pg.translate(x + (float) width / 2, y + (float) height / 2);
        _pg.rotate(radians(45));
        _pg.rectMode(CENTER);
        
        _pg.rect(0, 0, rectWidth, rectHeight);
        _pg.rotate(radians(90));
        _pg.rect(0, 0, rectWidth, rectHeight);
        _pg.pop();
    }
}
