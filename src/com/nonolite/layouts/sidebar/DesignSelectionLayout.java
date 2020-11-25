package com.nonolite.layouts.sidebar;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class DesignSelectionLayout extends Layout {
    public DesignSelectionLayout(PGraphics pg) {
        super(pg);
        
        for (Main.DesignMode design : Main.DesignMode.values()) {
            ButtonLayout button = new ButtonLayout(_pg);
            button.setText(design.toString());
            button.setOnClickListener((keyCode, x, y) -> {
                Main.getInstance().setDesign(design);
                Main.getSaveFileController().saveSetting("design", design.toString());
                return "";
            });
            this.addChild(button);
        }
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.x = x + i * width / getChildCount();
            childRect.y = y;
            childRect.width = width / getChildCount();
            childRect.height = height;
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
}
