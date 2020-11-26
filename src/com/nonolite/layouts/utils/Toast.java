package com.nonolite.layouts.utils;

import com.nonolite.Main;
import com.nonolite.layouts.MainLayout;
import processing.core.PGraphics;

public class Toast extends Layout {
    boolean _hidden;
    String _text = "";
    
    public Toast(PGraphics pg) {
        super(pg);
        _hidden = true;
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        MainLayout mainLayout = Main.getInstance().getMainLayout();
        Rect r = new Rect();
        r.x = mainLayout.getBoardLayout().getX();
        r.y = mainLayout.getBoardLayout().getY();
        r.width = mainLayout.getBoardLayout().getWidth();
        r.height = mainLayout.getBoardLayout().getHeight();
        
        if (!_hidden) {
            PGraphics pg = Main.getInstance().getGraphics();
            pg.push();
            pg.fill(0, 150);
            pg.rect(r.x, r.y, r.width, r.height);
            pg.pop();
            
            Main.getDesign().baseRect(
                r.x + 3 * r.width / 8,
                r.y + 3 * r.height / 8,
                r.width / 4,
                r.height / 8
            );
            
            Main.getDesign().text(
                _text,
                r.x + 3 * r.width / 8,
                r.y + 3 * r.height / 8,
                r.width / 4,
                r.height / 8
            );
        }
    }
    
    @Override
    public String mouseInput(int keyCode, float x, float y) {
        Main.getInstance().getMainLayout().getBoardLayout().newRandomBoard();
        Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().resetTimer();
        Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().startTimer();
        
        _hidden = !_hidden;
        if (_hidden) {
            Main.getInstance().resignClickable(this);
        }
        return "";
    }
    
    public void show(String message) {
        _text = message;
        _hidden = false;
        Main.getInstance().applyClickable(this);
    }
    
    public boolean getStatus() {
        return _hidden;
    }
}
