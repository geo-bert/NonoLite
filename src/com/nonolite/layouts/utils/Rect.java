package com.nonolite.layouts.utils;

public class Rect {
    public float x = 0;
    public float y = 0;
    public float width = 0;
    public float height = 0;
    
    public Rect() {
    }
    
    public Rect(float x, float y, float width, float height) {
        set(x, y, width, height);
    }
    
    public void set(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
