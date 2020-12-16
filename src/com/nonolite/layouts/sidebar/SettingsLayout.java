package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import com.nonolite.layouts.utils.SelectionButtonLayout;
import processing.core.PGraphics;

public class SettingsLayout extends Layout {
    private ButtonLayout _settingsButton;
    private ButtonLayout _saveButton;
    private SelectionButtonLayout _designSelector;
    private SelectionButtonLayout _windowModeSelector;
    private ButtonLayout _quitButton;
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
        
        _designSelector = new SelectionButtonLayout(_pg);
        for (Main.DesignMode design : Main.DesignMode.values()) {
            ButtonLayout button = new ButtonLayout(_pg);
            button.setText(design.toString());
            button.setOnClickListener((keyCode, x, y) -> {
                Main.getInstance().setDesign(design);
                Main.getSaveFileController().saveSetting("design", design.toString());
                return "";
            });
            _designSelector.addSelection(button);
        }
        this.addChild(_designSelector);
        
        _windowModeSelector = new SelectionButtonLayout(_pg);
        ButtonLayout button = new ButtonLayout(_pg);
        button.setText("Windowed");
        button.setOnClickListener((keyCode, x, y) -> {
            Main.getSaveFileController().saveSetting("windowMode", "windowed");
            return "";
        });
        _windowModeSelector.addSelection(button);
        button = new ButtonLayout(_pg);
        button.setText("Fullscreen");
        button.setOnClickListener((keyCode, x, y) -> {
            Main.getSaveFileController().saveSetting("windowMode", "fullscreen");
            return "";
        });
        _windowModeSelector.addSelection(button);
        this.addChild(_windowModeSelector);
        
        _quitButton = new ButtonLayout(_pg);
        _quitButton.setText("Quit");
        _quitButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().exit();
            return "";
        });
        this.addChild(_quitButton);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        Main.getDesign().base2(x, y, width, height);
        _settingsButton.drawLayout(x, y, width, _parentButtonHeight);
        y += _parentButtonHeight;
        height -= _parentButtonHeight;
        
        for (int i = 1; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.width = width;
            childRect.height = min(_parentButtonHeight, height / (getChildCount() - 1));
            childRect.x = x;
            childRect.y = y + (i - 1) * childRect.height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
    
    public void setButtonHeight(float height) {
        _parentButtonHeight = height;
    }
}
