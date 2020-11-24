package com.nonolite.layouts.utils;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Layout extends PApplet {
    protected final PGraphics _pg;
    private float _x;
    private float _y;
    private float _width;
    private float _height;
    private int _depth = 0;
    private OnClickListener _listener;
    private final List<Layout> _children = new ArrayList<>();
    
    public Layout(PGraphics pg) {
        _pg = pg;
    }
    
    public void onLayout(float x, float y, float width, float height) {
        for (Layout child : _children) {
            child.drawLayout(x, y, width, height);
        }
    }
    
    public String mouseInput(int keyCode, float x, float y) {
        return "no key input defined";
    }
    
    public final void drawLayout(float x, float y, float width, float height) {
        _x = x;
        _y = y;
        _width = width;
        _height = height;
        _pg.push();
        onLayout(x, y, width, height);
        _pg.pop();
    }
    
    public final void setOnClickListener(OnClickListener listener) {
        _listener = listener;
    }
    
    public final void click(int keyCode, float x, float y) {
        _listener.onClick(keyCode, x, y);
    }
    
    public final void addChild(Layout child) {
        _children.add(child);
    }
    
    public final void removeAllChildren() {
        _children.clear();
    }
    
    public final void removeChild(int i) {
        _children.remove(i);
    }
    
    public final void removeChild(Layout child) {
        _children.remove(child);
    }
    
    public final int getChildCount() {
        return _children.size();
    }
    
    public final Layout getChildAt(int i) {
        return _children.get(i);
    }
    
    public final float getX() {
        return _x;
    }
    
    public final float getY() {
        return _y;
    }
    
    public final float getWidth() {
        return _width;
    }
    
    public final float getHeight() {
        return _height;
    }
    
    public final int getDepth() {
        return _depth;
    }
    
    public final void setDepth(int depth) {
        _depth = depth;
    }
}
