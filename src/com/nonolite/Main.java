package com.nonolite;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

public class Main extends PApplet {
    private static Main main;
    private int _screenWidth = 1500;
    private int _screenHeight = 800;
    private final List<Layout> _clickables = new ArrayList<>();
    private MainLayout _mainLayout;
    
    public static void main(String[] args) {
        PApplet.main("com.nonolite.Main");
    }
    
    public static Main getInstance() {
        return main;
    }
    
    public void setup() {
        main = this;
        surface.setResizable(true);
        background(50);
        _mainLayout = new MainLayout(getGraphics());
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        _mainLayout.drawLayout(0, 0, width, height);
    }
    
    public void keyReleased() {
        _mainLayout.getBoardLayout().keyInput(keyCode);
    }
    
    public void mousePressed() {
        Layout clickedLayout = null;
        for (Layout clickable : _clickables) {
            if (!(mouseX >= clickable.getX() &&
                  mouseX <= clickable.getX() + clickable.getWidth())) {
                continue;
            }
            if (!(mouseY >= clickable.getY() &&
                  mouseY <= clickable.getY() + clickable.getHeight())) {
                continue;
            }
            
            if (clickedLayout != null) {
                if (clickedLayout.getDepth() < clickable.getDepth()) {
                    clickedLayout = clickable;
                }
            }
            else {
                clickedLayout = clickable;
            }
        }
        
        if (clickedLayout != null) {
            clickedLayout.mouseInput(mouseButton, mouseX, mouseY);
        }
    }
    
    public void mouseDragged() {
        _mainLayout.getBoardLayout().mouseDragInput(mouseButton, mouseX, mouseY);
    }
    
    public void applyClickable(Layout layout) {
        for (Layout clickable : _clickables) {
            if (layout == clickable) {
                return;
            }
        }
        
        for (int i = 0; i < _clickables.size(); i++) {
            if (layout.getDepth() == _clickables.get(i).getDepth()) {
                layout.setDepth(layout.getDepth() - 1);
                i = 0;
            }
        }
        _clickables.add(layout);
    }
    
    public void resignClickable(Layout layout) {
        for (Layout clickable : _clickables) {
            if (layout == clickable) {
                _clickables.remove(layout);
            }
        }
    }
    
    public MainLayout getMainLayout() {
        return _mainLayout;
    }
}
