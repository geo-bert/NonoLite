package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class SettingsLayout extends Layout {
    private ButtonLayout _settingsButton;
    private ButtonLayout _saveButton;
    private DesignSelectionLayout _designSelector;
    private float _parentButtonHeight;
    
    public SettingsLayout(PGraphics pg) {
        super(pg);
        
        _settingsButton = new ButtonLayout(_pg);
        _settingsButton.setText("Settings");
        _settingsButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getSideBarLayout().toggleSettings();
            return "";
        });
        
        this.addChild(_settingsButton);
        
        _saveButton = new ButtonLayout(_pg);
        _saveButton.setText("Save");
        _saveButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().getMainLayout().getBoardLayout().save();
            return "";
        });
        this.addChild(_saveButton);
    
        _designSelector = new DesignSelectionLayout(_pg);
        this.addChild(_designSelector);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        Main.getDesign().base(x, y, width, height);
        _settingsButton.drawLayout(x, y, width, _parentButtonHeight);
        y += _parentButtonHeight;
        height -= _parentButtonHeight;
        
        for (int i = 1; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.width = width;
            childRect.height = height / (getChildCount() - 1);
            childRect.x = x;
            childRect.y = y + (i - 1) * childRect.height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
    
    public void setButtonHeight(float height) {
        _parentButtonHeight = height;
    }
}
