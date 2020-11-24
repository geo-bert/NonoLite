package com.nonolite.layouts.utils;

import com.nonolite.Main;
import processing.core.PGraphics;

public class ButtonLayout extends Layout {
    private String _text = "Button";
    
    public ButtonLayout(PGraphics pg) {
        super(pg);
        Main.getInstance().applyClickable(this);
    }
    
    @Override
    public void onLayout(int x, int y, int width, int height) {
        Main.getDesign().baseRect(x, y, width, height);
        
        Main.getDesign().text(_text, x, y, width, height);
    }
    
    @Override
    public String mouseInput(int keyCode, int x, int y) {
        click(keyCode, x, y);
        return "";
    }
    
    public String getText() {
        return _text;
    }
    
    public void setText(String text) {
        _text = text;
    }
}

