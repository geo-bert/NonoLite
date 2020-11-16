package com.nonolite;

import java.util.List;
import processing.core.PApplet;
import processing.core.PGraphics;

public class Layout extends PApplet {
    private PGraphics _pg;
    private List<Layout> _children;
    
    public Layout() {
    }
    
    public Layout(PGraphics pg) {
        _pg = pg;
    }
    
    public void drawLayout() {
        _pg.push();
        for (Layout child : _children) {
            child.drawLayout();
        }
        _pg.pop();
    }
    
    public void addChild(Layout child) {
        _children.add(child);
    }
    
    public void removeAllChildren() {
        _children.clear();
    }
    
    public void removeChild(int index) {
        _children.remove(index);
    }
    
    public void removeChild(Layout child) {
        _children.remove(child);
    }
}
