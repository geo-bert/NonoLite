package com.nonolite.layouts;

import com.nonolite.layouts.board.BoardLayout;
import com.nonolite.layouts.sidebar.SideBarLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
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
    public void onLayout(float x, float y, float width, float height) {
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.x = x + i * width / 4;
            childRect.y = y;
            childRect.width = (1 + 2 * i) * width / 4;
            childRect.height = height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
    
    public BoardLayout getBoardLayout() {
        return _boardLayout;
    }
}
