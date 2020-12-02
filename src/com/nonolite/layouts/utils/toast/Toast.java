package com.nonolite.layouts.utils.toast;

import com.nonolite.Main;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Rect;
import processing.core.PGraphics;

public class Toast extends Layout {
    private boolean _hidden = true;
    private String _text = "";
    private final Rect _clickableArea = new Rect(0, 0, 0, 0);
    
    public Toast(PGraphics pg) {
        super(pg);
        
        for (int i = 0; i < 4; i++) {
            addChild(new ToastClickable(_pg, this));
        }
        setOnClickListener((keyCode, x, y) -> "action undefined");
    }
    
    @Override
    public void onLayout(float x, float y, float width, float height) {
        if (!_hidden) {
            getChildAt(0).drawLayout(x, y, width, _clickableArea.y - y);
            getChildAt(1).drawLayout(x, _clickableArea.y + _clickableArea.height, width, height - _clickableArea.height - getChildAt(0).getHeight());
            getChildAt(2).drawLayout(x, _clickableArea.y, _clickableArea.x - x, _clickableArea.height);
            getChildAt(3).drawLayout(_clickableArea.x + _clickableArea.width, _clickableArea.y, width - _clickableArea.width - getChildAt(2).getWidth(), _clickableArea.height);
            
            Rect[] areas = new Rect[]{
                new Rect(x, y, width, _clickableArea.y - y),
                new Rect(x, _clickableArea.y + _clickableArea.height, width, height - _clickableArea.height - getChildAt(0).getHeight()),
                new Rect(x, y, _clickableArea.x - x, height),
                new Rect(_clickableArea.x + _clickableArea.width, y, width - _clickableArea.width - getChildAt(2).getWidth(), height)
            };
            
            for (int i = 1; i < areas.length; i++) {
                if (areas[i].width * areas[i].height > areas[0].width * areas[0].height) {
                    areas[0] = areas[i];
                }
            }
            
            Main.getDesign().text(_text, areas[0].x, areas[0].y, areas[0].width, areas[0].height);
        }
    }
    
    public void hide() {
        if (!_hidden) {
            _hidden = true;
            click(LEFT, getX(), getY());
            Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().startTimer();
            for (int i = 0; i < getChildCount(); i++) {
                ((ToastClickable) getChildAt(i)).hide();
            }
            setOnClickListener((keyCode, x, y) -> "action undefined");
            _clickableArea.set(0, 0, 0, 0);
        }
    }
    
    public void show(String message) {
        if (!_hidden) {
            hide();
        }
        
        _text = message;
        _hidden = false;
        Main.getInstance().getMainLayout().getSideBarLayout().getTimerLayout().stopTimer();
        for (int i = 0; i < getChildCount(); i++) {
            ((ToastClickable) getChildAt(i)).show();
        }
    }
    
    public void setClickableArea(float x, float y, float width, float height) {
        _clickableArea.set(x, y, width, height);
    }
    
    public boolean getStatus() {
        return !_hidden;
    }
}
