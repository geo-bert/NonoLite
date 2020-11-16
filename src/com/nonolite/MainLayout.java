package com.nonolite;

import processing.core.PGraphics;

public class MainLayout extends Layout {
    public MainLayout(PGraphics pg) {
        super(pg);
        
        SideBarLayout sideBarLayout = new SideBarLayout(_pg);
        this.addChild(sideBarLayout);
        
        BoardLayout boardLayout = new BoardLayout(_pg);
        this.addChild(boardLayout);
    }
    
    @Override
    public void drawLayout(int x, int y, int width, int height) {
        _pg.push();
        for (int i = 0; i < getChildCount(); i++) {
            int childX = x + i * width / 4;
            int childY = y;
            int childWidth = width;
            int childHeight = height;
            
            getChildAt(i).drawLayout(childX, childY, childWidth, childHeight);
        }
        _pg.pop();
    }
}
