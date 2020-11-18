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
    public void onLayout(int x, int y, int width, int height) {
        for (int i = 0; i < getChildCount(); i++) {
            int childX = x + i * width / 4;
            int childY = y;
            int childWidth = (1 + 2 * i) * width / 4;
            int childHeight = height;
            
            getChildAt(i).drawLayout(childX, childY, childWidth, childHeight);
        }
    }
    
    public BoardLayout getBoardLayout() {
        return _boardLayout;
    }
}
