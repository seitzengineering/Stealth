package com.seitzsoftware.game.model;

import android.graphics.Rect;

/**
 * Created by Kevin on 5/1/2016.
 */
public abstract class Model {

    private int x, y, width, height;
    private Rect rect;

    public void updateRect() {
        rect.set(x, y, x + width, y + height);
    }

    public Rect getRect() {
        return rect;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
