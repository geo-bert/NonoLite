package com.nonolite.design;

import com.nonolite.layouts.utils.PGTextWriter;
import processing.core.PGraphics;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.CENTER;

public class Default implements Design {
    private final PGTextWriter tw;
    private final PGraphics pg;
    
    public Default(PGraphics pg) {
        tw = new PGTextWriter(pg);
        this.pg = pg;
    }
    
    @Override
    public void background() {
        pg.background(50);
    }
    
    @Override
    public void base(int x, int y, int xSize, int ySize) {
    }
    
    @Override
    public void rect(int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void rect1(int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void rect2(int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void text(char text, float x, float y, float width, float height) {
        textSetup();
        pg.textAlign(CENTER, CENTER);
        tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(String text, float x, float y, float width, float height) {
        textSetup();
        pg.textAlign(CENTER, CENTER);
        tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(String[] text, float x, float y, float width, float height) {
        textSetup();
        pg.textAlign(CENTER, CENTER);
        tw.writeText(text, x, y, width, height);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y) {
        text("" + text, x, y);
    }
    
    @Override
    public void text(String text, float x, float y) {
        textSetup();
        pg.text(text, x, y);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y, float size) {
        text("" + text, x, y, size);
    }
    
    @Override
    public void text(String text, float x, float y, float size) {
        textSetup();
        pg.textSize(size);
        pg.text(text, x, y);
        textEnd();
    }
    
    @Override
    public void text(char text, float x, float y, float size, int align1, int align2) {
        text("" + text, x, y, size, align1, align2);
    }
    
    @Override
    public void text(String text, float x, float y, float size, int align1, int align2) {
        textSetup();
        pg.textSize(size);
        pg.textAlign(align1, align2);
        pg.text(text, x, y);
        textEnd();
    }
    
    private void textSetup() {
        pg.push();
        pg.fill(200);
    }
    
    private void textEnd() {
        pg.pop();
    }
    
    @Override
    public void symbol1(int x, int y, int xSize, int ySize) {
        int widthMargin = xSize / 20;
        int heightMargin = ySize / 20;
        
        pg.push();
        pg.fill(150);
        pg.noStroke();
        
        pg.rect(x + widthMargin, y + heightMargin, xSize - 2 * widthMargin, ySize - 2 * heightMargin);
        pg.pop();
    }
    
    @Override
    public void symbol2(int x, int y, int xSize, int ySize) {
        int rectWidth = (int) Math.hypot(xSize, ySize) / 2;
        int rectHeight = rectWidth / 5;
        
        pg.push();
        pg.fill(200, 0, 0);
        pg.noStroke();
        pg.translate(x + (float) xSize / 2, y + (float) ySize / 2);
        pg.rotate(radians(45));
        pg.rectMode(CENTER);
    
        pg.rect(0, 0, rectWidth, rectHeight);
        pg.rotate(radians(90));
        pg.rect(0, 0, rectWidth, rectHeight);
        pg.pop();
    }
}
