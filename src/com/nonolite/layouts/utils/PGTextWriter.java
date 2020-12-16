package com.nonolite.layouts.utils;

import processing.core.PApplet;
import processing.core.PGraphics;

public class PGTextWriter extends PApplet {
    private final PGraphics _pg;
    private final float _CHAR_RATIO = 0.55f;
    private final boolean _MONOSPACE_FONT = true;
    private boolean _newLineOnSpace = true;
    
    public PGTextWriter(PGraphics pg) {
        _pg = pg;
    }
    
    public void writeText(char symbol, float x, float y, float width, float height) {
        writeText(new String[]{"" + symbol}, x, y, width, height);
    }
    
    public void writeText(String word, float x, float y, float width, float height) {
        if (_newLineOnSpace) {
            String[] words = word.split(" ");
            if (width / height > (float) word.length() / words.length) {
                words = new String[]{word};
            }
            writeText(words, x, y, width, height);
        }
        else {
            writeText(new String[]{word}, x, y, width, height);
        }
    }
    
    public void writeText(String[] words, float x, float y, float width, float height) {
        float availableWidth = width * 0.75f;
        float availableHeight = height * (words.length == 1 ? 0.55f : 0.75f);
        
        int maxLength = 0;
        for (String word : words) {
            if (maxLength < word.length()) {
                maxLength = word.length();
            }
        }
        
        _pg.push();
        _pg.textSize(min(availableWidth / maxLength / _CHAR_RATIO, availableHeight / words.length));
        for (int i = 0; i < words.length; i++) {
            float posX = x;
            float posY = y;
            
            switch (_pg.textAlign) {
                case LEFT:
                    break;
                case RIGHT:
                    posX += width - words[i].length() * (_pg.textSize * _CHAR_RATIO);
                    break;
                default:
                case CENTER:
                    posX += width / 2 - words[i].length() * (_pg.textSize * _CHAR_RATIO) / 2;
                    break;
            }
            switch (_pg.textAlignY) {
                case TOP:
                    posY += i * _pg.textSize;
                    break;
                case BOTTOM:
                    posY += height + (i - words.length) * _pg.textSize;
                    break;
                default:
                case CENTER:
                    posY += height / 2 + (i - (float) words.length / 2) * _pg.textSize;
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
                        if (!_MONOSPACE_FONT) {
                            _pg.push();
                            float ratio = 0.9f;
                            float size = _pg.textSize;
                            _pg.textSize(_pg.textSize * ratio);
                            _pg.text(words[i].charAt(j), posX + (j + 0.5f) * size * _CHAR_RATIO, posY + (size - _pg.textSize) * _CHAR_RATIO / ratio);
                            _pg.pop();
                            continue;
                        }
                    default:
                        _pg.text(words[i].charAt(j), posX + (j + 0.5f) * _pg.textSize * _CHAR_RATIO, posY);
                        break;
                }
            }
            _pg.pop();
        }
        _pg.pop();
    }
}
