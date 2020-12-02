package com.nonolite.design;

public interface Design {
    void background();
    
    void base(float x, float y, float width, float height);
    
    void base2(float x, float y, float width, float height);
    
    void baseRect(float x, float y, float width, float height);
    
    void baseRect2(float x, float y, float width, float height);
    
    void rect1(float x, float y, float width, float height);
    
    void rect2(float x, float y, float width, float height);
    
    void buttonRect(float x, float y, float width, float height);
    
    void text(char text, float x, float y, float width, float height);
    
    void text(String text, float x, float y, float width, float height);
    
    void text(String[] text, float x, float y, float width, float height);
    
    void text(char text, float x, float y);
    
    void text(String text, float x, float y);
    
    void text(char text, float x, float y, float size);
    
    void text(String text, float x, float y, float size);
    
    void text(char text, float x, float y, float size, int align1, int align2);
    
    void text(String text, float x, float y, float size, int align1, int align2);
    
    void symbol1(float x, float y, float width, float height);
    
    void symbol2(float x, float y, float width, float height);
}
