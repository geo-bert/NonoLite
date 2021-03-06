package com.nonolite.design;

import com.nonolite.layouts.utils.PGTextWriter;
import processing.core.PApplet;
import processing.core.PGraphics;

public class RoundDarkMode extends PApplet implements Design {
    private final PGraphics _pg;
    private final PGTextWriter _tw;
    
    public RoundDarkMode(PGraphics pg) {
        _pg = pg;
        _tw = new PGTextWriter(pg);
    }
    
    @Override
    public void background() {
        _pg.background(35);
    }
    
    @Override
    public void base(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(50);
        float marginX = width / 150;
        float marginY = height / 150;
        float margin = max(marginX, marginY);
        _pg.noStroke();
        _pg.rect(x - margin, y - margin, width + 2 * margin, height + 2 * margin, (float) (min(width, height) * 0.20));
        _pg.pop();
    }
    
    @Override
    public void base2(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(45);
        _pg.noStroke();
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void baseRect(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(60);
        _pg.stroke(50);
        _pg.strokeWeight(2);
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void baseRect2(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(40);
        _pg.stroke(35);
        _pg.strokeWeight(2);
        _pg.rect(x, y, width, height);
        _pg.pop();
    }
    
    @Override
    public void rect1(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(75);
        _pg.noStroke();
        float marginX = width / 20;
        float marginY = height / 20;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, (float) (min(width, height) * 0.18));
        _pg.pop();
    }
    
    @Override
    public void rect2(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(60);
        _pg.noStroke();
        float marginX = width / 20;
        float marginY = height / 20;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, (float) (min(width, height) * 0.18));
        _pg.pop();
    }
    
    @Override
    public void buttonRect(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(60);
        _pg.noStroke();
        float marginX = width - 3;
        float marginY = height - 3;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, (min(width, height) * 0.1f));
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
        _pg.fill(170);
    }
    
    private void textEnd() {
        _pg.pop();
    }
    
    @Override
    public void symbol1(float x, float y, float width, float height) {
        _pg.push();
        _pg.fill(132);
        _pg.noStroke();
        float marginX = width / 10;
        float marginY = height / 10;
        _pg.rect(x + marginX, y + marginY, width - 2 * marginX, height - 2 * marginY, (float) (min(width, height) * 0.18));
        _pg.pop();
    }
    
    @Override
    public void symbol2(float x, float y, float width, float height) {
        float rectWidth = (float) Math.hypot(width, height) / 2;
        float rectHeight = rectWidth / 5;
        
        _pg.push();
        _pg.fill(179, 80, 75);
        _pg.noStroke();
        _pg.translate(x + width / 2, y + height / 2);
        _pg.rotate(radians(45));
        _pg.rectMode(CENTER);
        
        _pg.rect(0, 0, rectWidth, rectHeight, (float) (min(width, height) * 0.18));
        _pg.rotate(radians(90));
        _pg.rect(0, 0, rectWidth, rectHeight, (float) (min(width, height) * 0.18));
        _pg.pop();
    }
}
