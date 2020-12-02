package com.nonolite.layouts.utils.toast;

import com.nonolite.Main;
import com.nonolite.layouts.utils.Layout;
import processing.core.PGraphics;

public class ToastClickable extends Layout {
    private final Toast _parent;
    
    protected ToastClickable(PGraphics pg, Toast parent) {
        super(pg);
        
        _parent = parent;
        setDepth(100);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        _pg.noStroke();
        _pg.fill(0, 125);
        _pg.rect(x, y, width, height);
    }
    
    @Override
    public String mouseInput(int keyCode, float x, float y) {
        _parent.hide();
        return "";
    }
    
    public void hide() {
        Main.getInstance().resignClickable(this);
    }
    
    public void show() {
        Main.getInstance().applyClickable(this);
    }
}
