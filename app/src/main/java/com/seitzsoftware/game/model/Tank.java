package com.seitzsoftware.game.model;

import android.graphics.Rect;

import com.seitzsoftware.android.Stealth.GameMainActivity;
import com.seitzsoftware.framework.util.RandomNumberGenerator;

/**
 * Created by Kevin on 6/25/2016.
 */
public class Tank extends Model {

    private int x, y, width, height;
    private Rect rect;
    private static final int velY = 2;
    private boolean isAlive;

    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        isAlive = true;
        rect = new Rect(x, y, x + width, y + height);
    }

    public void update(float delta) {
        y += (velY * delta);

        if(y > GameMainActivity.GAME_HEIGHT) {
            y -= (GameMainActivity.GAME_HEIGHT + RandomNumberGenerator.getRandIntBetween(100, 1500));
            x = RandomNumberGenerator.getRandIntBetween(200, 600);
            isAlive = true;
        }
        updateRect();
    }

    public void updateRect() {
        rect.set(x, y, x + width, y + height);
    }

    public Rect getRect() {
        return rect;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public void isDead() {
        isAlive = false;
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
