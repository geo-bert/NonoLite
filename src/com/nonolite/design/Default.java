package com.nonolite.design;

import processing.core.PGraphics;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.*;

public class Default implements Design{
    @Override
    public void background(PGraphics pg) {
        pg.background(50);
    }
    
    @Override
    public void base(PGraphics pg, int x, int y, int xSize, int ySize) {
    
    }
    
    @Override
    public void rect(PGraphics pg, int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void rect1(PGraphics pg, int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void rect2(PGraphics pg, int x, int y, int xSize, int ySize) {
        pg.push();
        pg.fill(50);
        pg.rect(x, y, xSize, ySize);
        pg.pop();
    }
    
    @Override
    public void text(PGraphics pg, String text, float x, float y, float size, int align1, int align2) {
        pg.push();
        pg.fill(200);
        pg.textSize(size);
        pg.textAlign(align1, align2);
        pg.text(text, x, y);
        pg.pop();
    }
    
    @Override
    public void leftClick(PGraphics pg, int x, int y, int xSize, int ySize) {
        int widthMargin = xSize / 20;
        int heightMargin = ySize / 20;
    
        pg.push();
        pg.fill(150);
        pg.noStroke();
    
        pg.rect(x + widthMargin, y + heightMargin, xSize - 2 * widthMargin, ySize - 2 * heightMargin);
        pg.pop();
    }
    
    @Override
    public void rightClick(PGraphics pg, int x, int y, int xSize, int ySize) {
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
