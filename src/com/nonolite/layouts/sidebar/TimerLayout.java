package com.nonolite.layouts.sidebar;

import java.util.Timer;
import java.util.TimerTask;
import com.nonolite.Main;
import com.nonolite.layouts.utils.Layout;
import processing.core.PGraphics;

public class TimerLayout extends Layout {
    private Timer _timer = new Timer();
    private int _remainingSecs;
    private boolean _countdown = false;
    
    public TimerLayout(PGraphics pg) {
        super(pg);
        
        startTimer(0);
    }
    
    @Override
    public void onLayout(int x, int y, int width, int height) {
        Main.getDesign().baseRect2(x, y, width, height);
        Main.getDesign().text(formatTime(), x, y, width, height);
    }
    
    private void startTimer() {
        startTimer(_remainingSecs);
    }
    
    private void startTimer(int secs) {
        _remainingSecs = secs;
        
        _timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (_countdown) {
                    _remainingSecs--;
                    if (_remainingSecs <= 0) {
                        endTimer();
                    }
                }
                else {
                    _remainingSecs++;
                }
            }
        }, 0, 1000);
    }
    
    private void stopTimer() {
        _timer.cancel();
    }
    
    private void endTimer() {
        _remainingSecs = 0;
        stopTimer();
    }
    
    private String formatTime() {
        return _remainingSecs < 3600 ?
               String.format("%d:%02d", _remainingSecs / 60, _remainingSecs % 60) :
               String.format("%d:%02d:%02d", _remainingSecs / 3600, (_remainingSecs % 3600) / 60, _remainingSecs % 60);
    }
}
