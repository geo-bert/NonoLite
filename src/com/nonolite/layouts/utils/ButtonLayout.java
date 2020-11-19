package com.nonolite.layouts.utils;

import com.nonolite.Main;
import processing.core.PGraphics;

public class ButtonLayout extends Layout {
    private final int _BUTTONHEIGHT = 75;
    private int _horizontalAlignment = CENTER;
    private int _verticalAlignment = CENTER;
    private String _text = "Button";
    private OnClickListener _listener;
    
    public ButtonLayout(PGraphics pg) {
        super(pg);
        Main.getInstance().applyClickable(this);
    }
    
    @Override
    public void onLayout(int x, int y, int width, int height) {
        _pg.fill(60);
        _pg.rect(x, y, width, _BUTTONHEIGHT);
        
        _pg.fill(200);
        _pg.textSize((float) min(width, height) / 4);
        _pg.textAlign(_horizontalAlignment, _verticalAlignment);
        _pg.text(_text, x + (float) width / 2, y + (float) height / 2);
    }
    
    @Override
    public String mouseInput(int keyCode, int x, int y) {
        _listener.onClick(keyCode, x, y);
        return "";
    }
    
    public void setText(String text) {
        _text = text;
    }
    
    public String getText() {
        return _text;
    }
    
    public void alignText(int horizontalAlignment, int verticalAlignment) {
        _horizontalAlignment = horizontalAlignment;
        _verticalAlignment = verticalAlignment;
    }
    
    public void horizontalAlignText(int horizontalAlignment) {
        _horizontalAlignment = horizontalAlignment;
    }
    
    public void verticalAlignText(int verticalAlignment) {
        _verticalAlignment = verticalAlignment;
    }
    
    public void setOnClickListener(OnClickListener listener) {
        _listener = listener;
    }
}

