package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class SideBarLayout extends Layout {
    private final float _INVENTORY_SPACE = (float) 1 / 2;
    private float _settingsExpansion = 0.0f;
    private int _settingsExpansionDir = 0;
    private TimerLayout _timerLayout;
    private ButtonLayout _checkButton;
    private ButtonLayout _generateButton;
    private ButtonLayout _resetButton;
    private InventoryLayout _inventoryLayout;
    private SettingsLayout _settingsLayout;
    
    public SideBarLayout(PGraphics pg) {
        super(pg);
        
        _timerLayout = new TimerLayout(_pg);
        this.addChild(_timerLayout);
        
        _checkButton = new ButtonLayout(_pg);
        _checkButton.setText("Check");
        _checkButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().check();
            return "";
        });
        this.addChild(_checkButton);
        
        _generateButton = new ButtonLayout(_pg);
        _generateButton.setText("Generate Random");
        _generateButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().newRandomBoard();
            Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().resetTimer();
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
        
        _inventoryLayout = new InventoryLayout(_pg);
        this.addChild(_inventoryLayout);
        
        _settingsLayout = new SettingsLayout(_pg);
        this.addChild(_settingsLayout);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        float currentY = y;
        float buttonHeight = height * (1 - _INVENTORY_SPACE) / (getChildCount() - 1);
        
        _settingsLayout.setButtonHeight(buttonHeight);
        Main.getDesign().baseRect2(x, y, width, height);
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.x = x;
            childRect.y = currentY;
            childRect.width = width;
            
            if (getChildAt(i) == _inventoryLayout) {
                _settingsExpansion = max(0, min(1, _settingsExpansion + 0.1f * _settingsExpansionDir));
                childRect.height = height * _INVENTORY_SPACE * (1 - _settingsExpansion);
            }
            else if (getChildAt(i) == _settingsLayout) {
                childRect.height = height * _INVENTORY_SPACE + buttonHeight;
            }
            else {
                childRect.height = buttonHeight;
            }
            
            currentY += childRect.height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
    
    public void toggleSettings() {
        toggleSettings(_settingsExpansionDir <= 0);
    }
    
    public void toggleSettings(boolean bool) {
        _settingsExpansionDir = bool ? 1 : -1;
        if (bool) {
            Main.getToast().show("Paused");
            Main.getToast().setOnClickListener((keyCode, x, y) -> {
                Main.getInstance().getMainLayout().getSideBarLayout().toggleSettings(false);
                return "";
            });
        }
        else {
            Main.getToast().hide();
        }
    }
    
    public TimerLayout getTimerLayout() {
        return _timerLayout;
    }
}
