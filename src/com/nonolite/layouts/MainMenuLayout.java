package com.nonolite.layouts;

import com.nonolite.Main;
import com.nonolite.layouts.board.BoardLayout;
import com.nonolite.layouts.utils.ButtonLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class MainMenuLayout extends Layout {
    private ButtonLayout _nonoText;
    private ButtonLayout _startButton;
    private ButtonLayout _quitButton;
    private BoardLayout _boardLayout;
    
    public MainMenuLayout(PGraphics pg) {
        super(pg);
        
        _boardLayout = new BoardLayout(_pg);
        this.addChild(_boardLayout);
        
        _nonoText = new ButtonLayout(_pg);
        _nonoText.setText("NonoLite");
        _nonoText.setBox(false);
        this.addChild(_nonoText);
        
        _startButton = new ButtonLayout(_pg);
        _startButton.setText("Game");
        _startButton.setBox(false);
        _startButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().switchToGame();
            return "";
        });
        this.addChild(_startButton);
        
        _quitButton = new ButtonLayout(_pg);
        _quitButton.setText("Quit");
        _quitButton.setBox(false);
        _quitButton.setOnClickListener((keyCode, x, y) -> {
            Main.getInstance().exit();
            return "";
        });
        this.addChild(_quitButton);
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        x += width / 3;
        y += height / 3;
        width -= 2 * width / 3;
        height -= 2 * height / 3;
        for (int i = 0; i < getChildCount(); i++) {
            Rect childRect = new Rect();
            childRect.width = width;
            childRect.height = height / (getChildCount());
            childRect.x = x;
            childRect.y = y + i * height / (getChildCount());
            
            getChildAt(i).drawLayout(childRect.x, childRect.y, childRect.width, childRect.height);
        }
    }
}
