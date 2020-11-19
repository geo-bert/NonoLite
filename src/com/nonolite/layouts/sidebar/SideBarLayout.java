package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.OnClickListener;
import processing.core.PGraphics;

public class SideBarLayout extends Layout {
    private final int _BUTTONHEIGHT = 75;
    private TimerLayout _timerLayout;
    private ButtonLayout _checkButton;
    private ButtonLayout _generateButton;
    private ButtonLayout _resetButton;
    private ButtonLayout _saveButton;
    private InventoryLayout _inventoryLayout;
    private SettingsLayout _settingsLayout;
    
    public SideBarLayout(PGraphics pg) {
        super(pg);
        
        _timerLayout = new TimerLayout(_pg);
        this.addChild(_timerLayout);
        
        _checkButton = new ButtonLayout(_pg);
        _checkButton.setText("Check");
        _checkButton.setOnClickListener(new OnClickListener() {
            @Override
            public String onClick(int keyCode, int x, int y) {
                System.out.println(Main.getInstance().getMainLayout().getBoardLayout().check());
                return "";
            }
        });
        this.addChild(_checkButton);
    
        _generateButton = new ButtonLayout(_pg);
        _generateButton.setText("Generate Random");
        _generateButton.setOnClickListener(new OnClickListener() {
            @Override
            public String onClick(int keyCode, int x, int y) {
                Main.getInstance().getMainLayout().getBoardLayout().newRandomBoard();
                return "";
            }
        });
        this.addChild(_generateButton);
        
        _resetButton = new ButtonLayout(_pg);
        _resetButton.setText("Reset");
        _resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public String onClick(int keyCode, int x, int y) {
                Main.getInstance().getMainLayout().getBoardLayout().reset();
                return "";
            }
        });
        this.addChild(_resetButton);
        
        _saveButton = new ButtonLayout(_pg);
        _saveButton.setText("Save");
        _saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public String onClick(int keyCode, int x, int y) {
                Main.getInstance().getMainLayout().getBoardLayout().save();
                return "";
            }
        });
        this.addChild(_saveButton);
        
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
