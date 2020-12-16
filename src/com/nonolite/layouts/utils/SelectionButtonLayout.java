package com.nonolite.layouts.utils;

import processing.core.PGraphics;

public class SelectionButtonLayout extends Layout {
    public SelectionButtonLayout(PGraphics pg) {
        super(pg);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.x = x + i * width / getChildCount();
            childRect.y = y;
            childRect.width = width / getChildCount();
            childRect.height = height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
    
    public void addSelection(ButtonLayout selection) {
        this.addChild(selection);
    }
}
