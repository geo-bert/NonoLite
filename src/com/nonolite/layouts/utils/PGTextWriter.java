package com.nonolite.layouts.utils;

import processing.core.PApplet;
import processing.core.PGraphics;

public class PGTextWriter extends PApplet {
    private final PGraphics _pg;
    private final float _CHARRATIO = 0.55f;
    private boolean _newLineOnSpace = true;
    
    public PGTextWriter(PGraphics pg) {
        _pg = pg;
    }
    
    public void writeText(char symbol, int x, int y, int width, int height) {
        writeText(new String[]{"" + symbol}, x, y, width, height);
    }
    
    public void writeText(String word, int x, int y, int width, int height) {
        if (_newLineOnSpace) {
            String[] words = word.split(" ");
            if (width / height > word.length() / words.length) {
                words = new String[]{word};
            }
            writeText(words, x, y, width, height);
        }
        else {
            writeText(new String[]{word}, x, y, width, height);
        }
    }
    
    public void writeText(String[] words, int x, int y, int width, int height) {
        int availableWidth = (int) (width / 1.5f);
        int availableHeight = (int) (height / 1.5f);
        
        int maxLength = 0;
        for (String word : words) {
            if (maxLength < word.length()) {
                maxLength = word.length();
            }
        }
        
        _pg.push();
        _pg.fill(200);
        _pg.textSize(min((float) availableWidth / maxLength / _CHARRATIO, (float) availableHeight / words.length));
        for (int i = 0; i < words.length; i++) {
            int posX = x;
            int posY = y;
            
            switch (_pg.textAlign) {
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
            switch (_pg.textAlignY) {
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
                        float ratio = 0.9f;
                        float size = _pg.textSize;
                        _pg.textSize(_pg.textSize * ratio);
                        _pg.text(words[i].charAt(j), posX + (j + 0.5f) * size * _CHARRATIO, posY + (size - _pg.textSize) * _CHARRATIO / ratio);
                        _pg.pop();
                        continue;
                    default:
                        _pg.text(words[i].charAt(j), posX + (j + 0.5f) * _pg.textSize * _CHARRATIO, posY);
                        break;
                }
            }
            _pg.pop();
        }
        _pg.pop();
    }
}
