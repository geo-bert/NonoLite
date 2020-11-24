package com.nonolite.layouts.utils;

import com.nonolite.Main;
import com.nonolite.layouts.MainLayout;
import processing.core.PGraphics;

public class Toast extends Layout {
    boolean hidden;
    
    public Toast(PGraphics pg) {
        super(pg);
        Main.getInstance().applyClickable(this);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        MainLayout mainLayout = Main.getInstance().getMainLayout();
        Rect r = new Rect();
        r.x = mainLayout.getBoardLayout().getX();
        r.y = mainLayout.getBoardLayout().getY();
        r.width = mainLayout.getBoardLayout().getWidth();
        r.height = mainLayout.getBoardLayout().getHeight();
        
        if (!hidden) {
            PGraphics pg = Main.getInstance().getGraphics();
            pg.push();
            pg.fill(0, 150);
            pg.rect(r.x,r.y,r.width,r.height);
            pg.pop();
            
            Main.getDesign().baseRect(
                r.x + 3 * r.width / 8,
                r.y + 3 * r.height / 8,
                r.width / 4,
                r.height / 8
            );
            
            Main.getDesign().text(
                "Well done!",
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
        
        hidden = !hidden;
        if (hidden) {
            Main.getInstance().resignClickable(this);
        }
        return "";
    }
    
    public void unhide() {
        hidden = false;
        Main.getInstance().applyClickable(this);
    }
}
