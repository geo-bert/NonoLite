package com.nonolite.design;

import processing.core.PGraphics;

public interface Design {
    void background(PGraphics pg);
    
    void base(PGraphics pg, int x, int y, int width, int height);
    
    void rect(PGraphics pg, int x, int y, int width, int height);
    
    void rect1(PGraphics pg, int x, int y, int width, int height);
    
    void rect2(PGraphics pg, int x, int y, int width, int height);
    
    void text(PGraphics pg, char text, float x, float y, float width, float height);
    
    void text(PGraphics pg, String text, float x, float y, float width, float height);
    
    void text(PGraphics pg, String[] text, float x, float y, float width, float height);
    
    void text(PGraphics pg, char text, float x, float y);
    
    void text(PGraphics pg, String text, float x, float y);
    
    void text(PGraphics pg, char text, float x, float y, float size);
    
    void text(PGraphics pg, String text, float x, float y, float size);
    
    void text(PGraphics pg, char text, float x, float y, float size, int align1, int align2);
    
    void text(PGraphics pg, String text, float x, float y, float size, int align1, int align2);
    
    void symbol1(PGraphics pg, int x, int y, int width, int height);
    
    void symbol2(PGraphics pg, int x, int y, int width, int height);
}
