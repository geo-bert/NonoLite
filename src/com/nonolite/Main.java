package com.nonolite;

import java.util.ArrayList;
import java.util.List;
import com.nonolite.design.DarkMode;
import com.nonolite.design.DefaultMode;
import com.nonolite.design.Design;
import com.nonolite.design.RoundDarkMode;
import com.nonolite.layouts.MainLayout;
import com.nonolite.layouts.utils.Layout;
import com.nonolite.layouts.utils.Toast;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

public class Main extends PApplet {
    private static Main _main;
    private static SaveFileController _saveFileController;
    private PGraphics _pg;
    private int _screenWidth = 1500;
    private int _screenHeight = 800;
    private final List<Layout> _clickables = new ArrayList<>();
    private static Design _design;
    private Design[] _designModes;
    private MainLayout _mainLayout;
    private Toast _toast;
    
    public enum DesignMode {
        DefaultMode,
        DarkMode,
        RoundDarkMode
    }
    
    public static void main(String[] args) {
        PApplet.main("com.nonolite.Main");
    }
    
    public static Main getInstance() {
        return _main;
    }
    
    public void setup() {
        textAlign(LEFT, TOP);
        surface.setResizable(true);
        _main = this;
        _saveFileController = new SaveFileController();
        _pg = getGraphics();
        _designModes = new Design[]{new DefaultMode(_pg), new DarkMode(_pg), new RoundDarkMode(_pg)};
        PFont font = createFont("Courier New Bold", 32);
        textFont(font);
        loadSettings();
        
        _mainLayout = new MainLayout(_pg);
        _toast = new Toast(_pg);
        _mainLayout.getBoardLayout().load();
    }
    
    private void loadSettings() {
        setDesign(_saveFileController.loadSetting("design", DesignMode.DefaultMode.toString()));
    }
    
    public void settings() {
        size(_screenWidth, _screenHeight);
    }
    
    public void draw() {
        clear();
        _design.background();
        _mainLayout.drawLayout(0, 0, width, height);
        _toast.drawLayout(
            0, 0, width, height);
    }
    
    public void keyReleased() {
        switch (keyCode) {
            case 32:
                if (Main.getInstance().getToast().getStatus() && Main.getInstance().getMainLayout().getBoardLayout().check()) {
                    Main.getInstance().getToast().show("Well done!");
                    break;
                }
    
                if (!Main.getInstance().getToast().getStatus()) {
                    Main.getInstance().getToast().mouseInput(LEFT, width, height);
                }
                break;
        }
        
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
            _mainLayout.getBoardLayout().mouseDragInput(mouseButton, mouseX, mouseY);
        }
    }
    
    public void applyClickable(Layout layout) {
        for (Layout clickable : _clickables) {
            if (layout == clickable) {
                return;
            }
        }
        
        for (int i = 0; i < _clickables.size(); i++) {
            if (layout.getDepth() == _clickables.get(i).getDepth()) {
                layout.setDepth(layout.getDepth() + 1);
                i = 0;
            }
        }
        _clickables.add(layout);
    }
    
    public void resignClickable(Layout layout) {
        _clickables.remove(layout);
    }
    
    public static SaveFileController getSaveFileController() {
        return _saveFileController;
    }
    
    public MainLayout getMainLayout() {
        return _mainLayout;
    }
    
    public static Design getDesign() {
        return _design;
    }
    
    public void setDesign(DesignMode designMode) {
        _design = _designModes[designMode.ordinal()];
    }
    
    public void setDesign(String designMode) {
        try {
            _design = _designModes[DesignMode.valueOf(designMode).ordinal()];
        }
        catch (IllegalArgumentException exception) {
            _design = _designModes[DesignMode.DefaultMode.ordinal()];
        }
    }
    
    public Toast getToast() {
        return _toast;
    }
}
