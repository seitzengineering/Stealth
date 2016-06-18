package com.seitzsoftware.game.model;

import android.graphics.Rect;

import com.seitzsoftware.android.Stealth.GameMainActivity;
import com.seitzsoftware.framework.util.RandomNumberGenerator;

/**
 * Created by Kevin on 4/28/2016.
 */
public class Cactus extends Model {

    private int x, y, width, height;
    private static final int velY = 2;
    private Rect rect;



    public Cactus(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rect = new Rect(x, y, x + width, y + height);
    }

    public void update(float delta) {
        y += (velY * delta);

        if(y > GameMainActivity.GAME_HEIGHT) {
            y -= (GameMainActivity.GAME_HEIGHT + RandomNumberGenerator.getRandIntBetween(100, 1200));
            x = RandomNumberGenerator.getRandInt(GameMainActivity.GAME_WIDTH - 30);
        }

        updateRect();
    }

    public void updateRect() {
        rect.set(x, y, x + width, y + height);
    }

    public Rect getRect() {
        return rect;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
