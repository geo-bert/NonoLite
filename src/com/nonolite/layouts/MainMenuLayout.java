package com.nonolite.layouts;

import com.nonolite.Main;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import processing.core.PGraphics;

public class MainMenuLayout extends Layout {
    private ButtonLayout _startButton;
    
    public MainMenuLayout(PGraphics pg) {
        super(pg);
        
        _startButton = new ButtonLayout(_pg);
        _startButton.setText("Game");
        _startButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().switchToGame();
            return "";
        });
        this.addChild(_startButton);
    }
}
