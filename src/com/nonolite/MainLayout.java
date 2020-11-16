package com.nonolite;

import processing.core.PGraphics;

public class MainLayout extends Layout {
    private SideBarLayout _sideBarLayout;
    private BoardLayout _boardLayout;
    
    public MainLayout(PGraphics pg) {
        super(pg);
    
        _sideBarLayout = new SideBarLayout(_pg);
        this.addChild(_sideBarLayout);
        
        _boardLayout = new BoardLayout(_pg);
        this.addChild(_boardLayout);
    }
    
    @Override
    public void drawLayout(int x, int y, int width, int height) {
        _pg.push();
        for (int i = 0; i < getChildCount(); i++) {
            int childX = x;
            int childY = y;// + i * width / 4;
            int childWidth = width;
            int childHeight = height;//(1 + 2 * i) * height / 4;
            
            getChildAt(i).drawLayout(childX, childY, childWidth, childHeight);
        }
        _pg.pop();
    }
    
    public BoardLayout getBoardLayout(){
        return _boardLayout;
    }
}
