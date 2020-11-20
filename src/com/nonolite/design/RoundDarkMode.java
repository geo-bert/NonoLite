package com.nonolite.design;

import com.nonolite.layouts.utils.PGTextWriter;
import processing.core.PGraphics;
import static java.lang.Integer.max;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.CENTER;

public class RoundDarkMode implements Design {
    private final PGTextWriter tw;
    private final PGraphics pg;
    
    public RoundDarkMode(PGraphics pg) {
        tw = new PGTextWriter(pg);
        this.pg = pg;
    }
    
    @Override
    public void background() {
        pg.background(35);
    }
    
    @Override
    public void base(int x, int y, int width, int height) {
        pg.push();
        pg.fill(50);
        int marginX = width / 150;
        int marginY = height / 150;
        int margin = max(marginX, marginY);
        pg.noStroke();
        pg.rect(x - margin, y - margin, width + 2 * margin, height + 2 * margin, 10);
        pg.pop();
    }
    
    @Override
    public void baseRect(int x, int y, int width, int height) {
        pg.push();
        pg.fill(60);
        pg.stroke(50);
        pg.strokeWeight(2);
        pg.rect(x, y, width, height);
        pg.pop();
    }
    
    @Override
    public void baseRect2(int x, int y, int width, int height) {
        pg.push();
        pg.fill(40);
        pg.stroke(35);
        pg.strokeWeight(2);
        pg.rect(x, y, width, height);
        pg.pop();
    }
    
    @Override
    public void rect1(int x, int y, int width, int height) {
        pg.push();
        pg.fill(75);
        pg.noStroke();
        int marginX = width / 20;
        int marginY = height / 20;
        pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, 8);
        pg.pop();
    }
    
    @Override
    public void rect2(int x, int y, int width, int height) {
        pg.push();
        pg.fill(60);
        pg.noStroke();
        int marginX = width / 20;
        int marginY = height / 20;
        pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, 8);
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
        pg.fill(170);
    }
    
    private void textEnd() {
        pg.pop();
    }
    
    @Override
    public void symbol1(int x, int y, int width, int height) {
        pg.push();
        pg.fill(132);
        pg.noStroke();
        int marginX = width / 10;
        int marginY = height / 10;
        pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, 8);
        pg.pop();
    }
    
    @Override
    public void symbol2(int x, int y, int width, int height) {
        int rectWidth = (int) Math.hypot(width, height) / 2;
        int rectHeight = rectWidth / 5;
        
        pg.push();
        pg.fill(179, 80, 75);
        pg.noStroke();
        pg.translate(x + (float) width / 2, y + (float) height / 2);
        pg.rotate(radians(45));
        pg.rectMode(CENTER);
        
        pg.rect(0, 0, rectWidth, rectHeight, 4);
        pg.rotate(radians(90));
        pg.rect(0, 0, rectWidth, rectHeight, 4);
        pg.pop();
    }
}
