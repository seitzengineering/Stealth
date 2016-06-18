package com.seitzsoftware.game.model;

import android.graphics.Rect;

import com.seitzsoftware.android.Stealth.GameMainActivity;

/**
 * Created by Kevin on 4/28/2016.
 */
public class Player {

    private int x, y, width, height, velX, velY;
    private Rect rect;
    private static final int MOVE_SPEED_X = 6;
    private static final int MOVE_SPEED_Y = 6;

    public Player(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rect(x, y, width, height);
        velX = 0;
        velY = 0;
    }

    public void update(float delta) {
        x += velX;
        y += velY;

        if(x < 0) {
            x = 0;
        } else if(x + width > GameMainActivity.GAME_WIDTH) {
            x = GameMainActivity.GAME_WIDTH - width;
        }

        if(y < 0) {
            y = 0;
        } else if(y + height > GameMainActivity.GAME_HEIGHT) {
            y = GameMainActivity.GAME_HEIGHT - height;
        }


        updateRect();
    }

    public void updateRect() {
        rect.set(x, y, width, height);
    }

    public void accelLeft() {
        velX = -MOVE_SPEED_X;
    }

    public void accelRight() {
        velX = MOVE_SPEED_X;
    }

    public void accelUp() {
        velY = -MOVE_SPEED_Y;
    }

    public void accelDown() {
        velY = MOVE_SPEED_Y;
    }

    public void stop() {
        velX = 0;
        velY = 0;
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

    public Rect getRect() {
        return rect;
    }

}
