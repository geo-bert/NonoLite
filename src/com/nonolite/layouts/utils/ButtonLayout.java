package com.nonolite.layouts.utils;

import com.nonolite.Main;
import processing.core.PGraphics;

public class ButtonLayout extends Layout {
    private final int _BUTTONHEIGHT = 75;
    private final float _CHARRATIO = 0.55f;
    private int _horizontalAlignment = CENTER;
    private int _verticalAlignment = CENTER;
    private String _text = "Button";
    
    public ButtonLayout(PGraphics pg) {
        super(pg);
        Main.getInstance().applyClickable(this);
    }
    
    @Override
    public void onLayout(int x, int y, int width, int height) {
        _pg.fill(60);
        _pg.rect(x, y, width, _BUTTONHEIGHT);
        
        String[] words = _text.split(" ");
        int availableWidth = (int) (width / 1.5f);
        int availableHeight = (int) (_BUTTONHEIGHT / 1.5f);
        int maxLength = 0;
        for (String word : words) {
            if (maxLength < word.length()) {
                maxLength = word.length();
            }
        }
        
        _pg.fill(200);
        _pg.textSize(min((float) availableWidth / maxLength / _CHARRATIO, (float) availableHeight / words.length));
        
        for (int i = 0; i < words.length; i++) {
            int posX = x;
            int posY = y;
            
            switch (_horizontalAlignment) {
                case LEFT:
                    break;
                case RIGHT:
                    posX += width - words[i].length() * (_pg.textSize * _CHARRATIO);
                    break;
                default:
                case CENTER:
                    posX += width / 2 - words[i].length() * (_pg.textSize * _CHARRATIO) / 2;
                    break;
            }
            switch (_verticalAlignment) {
                case TOP:
                    posY += i * words.length * _pg.textSize / 2;
                    break;
                case BOTTOM:
                    posY += height + (i - words.length) * _pg.textSize;
                    break;
                default:
                case CENTER:
                    posY += height / 2 + (i - 1) * words.length * _pg.textSize / 2;
                    break;
            }
            
            _pg.push();
            _pg.textAlign(CENTER, TOP);
            for (int j = 0; j < words[i].length(); j++) {
                switch (words[i].charAt(j)) {
                    case 'Q':
                    case 'C':
                    case 'w':
                    case 'm':
                    case 'M':
                        _pg.push();
                        float size = _pg.textSize;
                        _pg.textSize(_pg.textSize * 0.9f);
                        _pg.text(words[i].charAt(j), posX + (j + 0.5f) * size * _CHARRATIO, posY + (size - _pg.textSize) * 0.75f);
                        _pg.pop();
                        continue;
                    default:
                        _pg.text(words[i].charAt(j), posX + (j + 0.5f) * _pg.textSize * _CHARRATIO, posY);
                        break;
                }
            }
            _pg.pop();
        }
    }
    
    @Override
    public String mouseInput(int keyCode, int x, int y) {
        click(keyCode, x, y);
        return "";
    }
    
    public String getText() {
        return _text;
    }
    
    public void setText(String text) {
        _text = text;
    }
    
    public void alignText(int horizontalAlignment, int verticalAlignment) {
        _horizontalAlignment = horizontalAlignment;
        _verticalAlignment = verticalAlignment;
    }
    
    public void horizontalAlignText(int horizontalAlignment) {
        _horizontalAlignment = horizontalAlignment;
    }
    
    public void verticalAlignText(int verticalAlignment) {
        _verticalAlignment = verticalAlignment;
    }
}

