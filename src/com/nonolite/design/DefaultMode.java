package com.nonolite.design;

import com.nonolite.layouts.utils.PGTextWriter;
import processing.core.PApplet;
import processing.core.PGraphics;

public class DefaultMode extends PApplet implements Design {
    private final PGraphics _pg;
    private final PGTextWriter _tw;
    
    public DefaultMode(PGraphics pg) {
        _pg = pg;
        _tw = new PGTextWriter(pg);
    }
    
    @Override
    public void background() {
        _pg.background(50);
    }
    
    @Override
    public void base(float x, float y, float xSize, float ySize) {
    }
    
    @Override
    public void baseRect(float x, float y, float xSize, float ySize) {
        _pg.push();
        _pg.fill(50);
        _pg.rect(x, y, xSize, ySize);
        _pg.pop();
    }
    
    public void baseRect2(float x, float y, float xSize, float ySize) {
        baseRect(x, y, xSize, ySize);
    }
    
    @Override
    public void rect1(float x, float y, float xSize, float ySize) {
        _pg.push();
        _pg.fill(50);
        _pg.rect(x, y, xSize, ySize);
        _pg.pop();
    }
    
    @Override
    public void rect2(float x, float y, float xSize, float ySize) {
        _pg.push();
        _pg.fill(50);
        _pg.rect(x, y, xSize, ySize);
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
        _pg.fill(200);
    }
    
    private void textEnd() {
        _pg.pop();
    }
    
    @Override
    public void symbol1(float x, float y, float xSize, float ySize) {
        float widthMargin = xSize / 20;
        float heightMargin = ySize / 20;
        
        _pg.push();
        _pg.fill(150);
        _pg.noStroke();
        
        _pg.rect(x + widthMargin, y + heightMargin, xSize - 2 * widthMargin, ySize - 2 * heightMargin);
        _pg.pop();
    }
    
    @Override
    public void symbol2(float x, float y, float xSize, float ySize) {
        float rectWidth = (float) Math.hypot(xSize, ySize) / 2;
        float rectHeight = rectWidth / 5;
        
        _pg.push();
        _pg.fill(200, 0, 0);
        _pg.noStroke();
        _pg.translate(x + xSize / 2, y + ySize / 2);
        _pg.rotate(radians(45));
        _pg.rectMode(CENTER);
        
        _pg.rect(0, 0, rectWidth, rectHeight);
        _pg.rotate(radians(90));
        _pg.rect(0, 0, rectWidth, rectHeight);
        _pg.pop();
    }
}
