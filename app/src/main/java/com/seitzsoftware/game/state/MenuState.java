package com.seitzsoftware.game.state;

import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.android.Stealth.Assets;
import com.seitzsoftware.framework.util.Painter;

/**
 * Created by Kevin on 4/28/2016.
 */
public class MenuState extends State {
    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0);
        g.setFont(Typeface.DEFAULT_BOLD,20);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        setCurrentState(new PlayState());
        return false;
    }
}