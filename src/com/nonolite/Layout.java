package com.nonolite;

import java.util.List;
import processing.core.PApplet;
import processing.core.PGraphics;

public abstract class Layout extends PApplet {
    private PGraphics _pg;
    private List<Layout> _children;
    
    public Layout() {
    }
    
    public Layout(PGraphics pg) {
        _pg = pg;
    }
    
    public void drawLayout(int x, int y, int width, int height) {
        _pg.push();
        for (Layout child : _children) {
            child.drawLayout(x, y, width, height);
        }
        _pg.pop();
    }
    
    public final void addChild(Layout child) {
        _children.add(child);
    }
    
    public final void removeAllChildren() {
        _children.clear();
    }
    
    public final void removeChild(int index) {
        _children.remove(index);
    }
    
    public final void removeChild(Layout child) {
        _children.remove(child);
    }
}
