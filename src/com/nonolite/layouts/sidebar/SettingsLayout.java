package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import processing.core.PGraphics;

public class SettingsLayout extends Layout {
    private ButtonLayout _settingsbutton;
    private float _parentButtonHeight;
    private boolean _expanded = false;
    
    public SettingsLayout(PGraphics pg) {
        super(pg);
        
        _settingsbutton = new ButtonLayout(_pg);
        _settingsbutton.setText("Settings");
        _settingsbutton.setOnClickListener((keyCode, x, y) -> {
            _expanded = !_expanded;
            Main.getInstance().getMainLayout().getSideBarLayout().expandSettings(_expanded);
            return "";
        });
        this.addChild(_settingsbutton);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        Main.getDesign().base(x, y, width, height);
        
        _settingsbutton.drawLayout(x, y, width, (int) (_parentButtonHeight > 0 ? _parentButtonHeight : height));
    }
    
    public void setButtonHeight(float height) {
        _parentButtonHeight = height;
    }
}
