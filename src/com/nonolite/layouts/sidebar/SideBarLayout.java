package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.design.Design;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class SideBarLayout extends Layout {
    private final int _BUTTON_HEIGHT = 75;
    private TimerLayout _timerLayout;
    private ButtonLayout _checkButton;
    private ButtonLayout _generateButton;
    private ButtonLayout _resetButton;
    private ButtonLayout _saveButton;
    private InventoryLayout _inventoryLayout;
    private SettingsLayout _settingsLayout;
    private Design _design;
    
    public SideBarLayout(PGraphics pg) {
        super(pg);
        _design = Main.getInstance().getDesign();
        
        _timerLayout = new TimerLayout(_pg);
        this.addChild(_timerLayout);
        
        _checkButton = new ButtonLayout(_pg);
        _checkButton.setText("Check");
        _checkButton.setOnClickListener((keyCode, x, y) -> {
            System.out.println(Main.getInstance().getMainLayout().getBoardLayout().check());
            return "";
        });
        this.addChild(_checkButton);
        
        _generateButton = new ButtonLayout(_pg);
        _generateButton.setText("Generate Random");
        _generateButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().newRandomBoard();
            return "";
        });
        this.addChild(_generateButton);
        
        _resetButton = new ButtonLayout(_pg);
        _resetButton.setText("Reset");
        _resetButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().reset();
            return "";
        });
        this.addChild(_resetButton);
        
        _saveButton = new ButtonLayout(_pg);
        _saveButton.setText("Save");
        _saveButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().save();
            return "";
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
    
        _design.baseRect2(x, y, width, height);
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.x = x;
            childRect.y = currentY;
            childRect.width = width;
            childRect.height = (getChildAt(i) == _inventoryLayout) ?
                               (height - _BUTTON_HEIGHT * (getChildCount() - 1)) :
                               _BUTTON_HEIGHT;
            
            if (i + 1 == getChildCount()) {
                childRect.height = getChildAt(i - 1).getHeight();
            }
            currentY += childRect.height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
}
