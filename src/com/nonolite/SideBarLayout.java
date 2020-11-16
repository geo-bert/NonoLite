package com.nonolite;

import processing.core.PGraphics;

public class SideBarLayout extends Layout {
    /*
    private TimerLayout _timerLayout;
    private InventoryLayout _inventoryLayout;
    private SettingsLayout _settingsLayout;*/
    
    public SideBarLayout(PGraphics pg) {
        super(pg);
    /*
        _timerLayout = new TimerLayout(_pg);
        this.addChild(_timerLayout);
        
        _inventoryLayout = new InventoryLayout(_pg);
        this.addChild(_inventoryLayout);
        
        _settingsLayout = new SettingsLayout(_pg);
        this.addChild(_settingsLayout);*/
    }
    
    @Override
    public void drawLayout(int x, int y, int width, int height) {
        _pg.push();
        _pg.fill(60);
        _pg.rect(x, y, width, height);
        for (int i = 0; i < getChildCount(); i++) {
            int childX = x;
            int childY = y ;
            int childWidth = width;
            int childHeight = height;
            
            getChildAt(i).drawLayout(childX, childY, childWidth, childHeight);
        }
        _pg.pop();
    }
}
