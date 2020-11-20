package com.nonolite.design;

public interface Design {
    void background();
    
    void base(int x, int y, int width, int height);
    
    void baseRect(int x, int y, int width, int height);
    
    void baseRect2(int x, int y, int width, int height);
    
    void rect1(int x, int y, int width, int height);
    
    void rect2(int x, int y, int width, int height);
    
    void text(char text, float x, float y, float width, float height);
    
    void text(String text, float x, float y, float width, float height);
    
    void text(String[] text, float x, float y, float width, float height);
    
    void text(char text, float x, float y);
    
    void text(String text, float x, float y);
    
    void text(char text, float x, float y, float size);
    
    void text(String text, float x, float y, float size);
    
    void text(char text, float x, float y, float size, int align1, int align2);
    
    void text(String text, float x, float y, float size, int align1, int align2);
    
    void symbol1(int x, int y, int width, int height);
    
    void symbol2(int x, int y, int width, int height);
}
