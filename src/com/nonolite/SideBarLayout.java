package com.nonolite;

import processing.core.PGraphics;

public class SideBarLayout extends Layout {
    private final int _BUTTONHEIGHT = 75;
    private TimerLayout _timerLayout;
    private ButtonLayout _checkButton;
    private ButtonLayout _resetButton;
    private ButtonLayout _saveButton;
    private InventoryLayout _inventoryLayout;
    private SettingsLayout _settingsLayout;
    
    public SideBarLayout(PGraphics pg) {
        super(pg);
        
        _timerLayout = new TimerLayout(_pg);
        this.addChild(_timerLayout);
        
        _inventoryLayout = new InventoryLayout(_pg);
        this.addChild(_inventoryLayout);
        
        _settingsLayout = new SettingsLayout(_pg);
        this.addChild(_settingsLayout);
    }
    
    @Override
    public void onLayout(int x, int y, int width, int height) {
        int currentY = y;
        
        _pg.fill(60);
        _pg.rect(x, y, width, height);
        for (int i = 0; i < getChildCount(); i++) {
            int childX = x;
            int childY = currentY;
            int childWidth = width;
            int childHeight = (getChildAt(i) == _inventoryLayout) ?
                              (height - _BUTTONHEIGHT * (getChildCount() - 1)) :
                              _BUTTONHEIGHT;
            
            if (i + 1 == getChildCount()) {
                childHeight = getChildAt(i - 1).getHeight();
            }
            currentY += childHeight;
            
            getChildAt(i).drawLayout(childX, childY, childWidth, childHeight);
        }
    }
}
