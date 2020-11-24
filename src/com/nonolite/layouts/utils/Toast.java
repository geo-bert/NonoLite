package com.nonolite.layouts.utils;

import com.nonolite.Main;
import processing.core.PGraphics;

public class Toast extends Layout {
    boolean hidden;
    
    public Toast(PGraphics pg) {
        super(pg);
        Main.getInstance().applyClickable(this);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        if (!hidden) {
            Main.getDesign().baseRect(x, y, width, height);
            Main.getDesign().text("Well done!", x, y, width, height);
        }
    }
    
    @Override
    public String mouseInput(int keyCode, float x, float y) {
        hidden = !hidden;
        if (hidden) {
            Main.getInstance().resignClickable(this);
        }
        return "";
    }
}
