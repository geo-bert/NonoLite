package com.nonolite.design;

import processing.core.PConstants;
import processing.core.PGraphics;

public interface Design {
    void background(PGraphics pg);
    
    void base(PGraphics pg, int x, int y, int xSize, int ySize);
    
    void rect(PGraphics pg, int x, int y, int xSize, int ySize);
    
    void rect1(PGraphics pg, int x, int y, int xSize, int ySize);
    
    void rect2(PGraphics pg, int x, int y, int xSize, int ySize);
    
    void text(PGraphics pg, String text, float x, float y, float size, int align1, int align2);
    
    void leftClick(PGraphics pg, int x, int y, int xSize, int ySize);
    
    void rightClick(PGraphics pg, int x, int y, int xSize, int ySize);
}
