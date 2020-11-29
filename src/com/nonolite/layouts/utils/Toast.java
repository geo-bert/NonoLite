package com.nonolite.layouts.utils;

import com.nonolite.Main;
import com.nonolite.layouts.MainLayout;
import processing.core.PGraphics;

public class Toast extends Layout {
    private boolean _hidden = true;
    private String _text = "";
    
    public Toast(PGraphics pg) {
        super(pg);
        
        setOnClickListener((keyCode, x, y) -> "action undefined");
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        MainLayout mainLayout = Main.getInstance().getMainLayout();
        Rect rect = new Rect();
        rect.x = mainLayout.getBoardLayout().getX();
        rect.y = mainLayout.getBoardLayout().getY();
        rect.width = mainLayout.getBoardLayout().getWidth();
        rect.height = mainLayout.getBoardLayout().getHeight();
        
        if (!_hidden) {
            _pg.push();
            _pg.fill(0, 150);
            _pg.rect(rect.x, rect.y, rect.width, rect.height);
            _pg.pop();
            
            Rect messageRect = new Rect();
            messageRect.x = rect.x + 3 * rect.width / 8;
            messageRect.y = rect.y + 3 * rect.height / 8;
            messageRect.width = rect.width / 4;
            messageRect.height = rect.height / 8;
            
            Main.getDesign().baseRect(messageRect.x, messageRect.y, messageRect.width, messageRect.height);
            Main.getDesign().text(_text, messageRect.x, messageRect.y, messageRect.width, messageRect.height);
        }
    }
    
    @Override
    public String mouseInput(int keyCode, float x, float y) {
        click(keyCode, x, y);
        hide();
        return "";
    }
    
    public void hide() {
        _hidden = true;
        Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().startTimer();
        Main.getInstance().resignClickable(this);
        setOnClickListener((keyCode, x, y) -> "action undefined");
    }
    
    public void show(String message) {
        if (!_hidden) {
            hide();
        }
        
        _text = message;
        _hidden = false;
        Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().stopTimer();
        Main.getInstance().applyClickable(this);
    }
    
    public boolean getStatus() {
        return _hidden;
    }
}
