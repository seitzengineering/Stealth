package com.seitzsoftware.game.model;

import android.graphics.Rect;

/**
 * Created by Kevin on 4/28/2016.
 */
public class Bomb {

    private int x, y, width, height;
    private int fallDuration, explodeDuration, initialRespawnTime, respawnTime;
    private Rect rect;
    private boolean isFired, isExploded;

    public Bomb(int x, int y, int width, int height, int respawnTime) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.initialRespawnTime = respawnTime;
        this.respawnTime = 0;
        rect = new Rect(x, y, x + width, y + height);
        isFired = false;
        isExploded = true;
    }

    public void update(float delta) {
        if(isFired && fallDuration == 0) {
            isFired = false;
            isExploded = true;
            explodeDuration = 10;
        }
        else if(isFired) {
            fallDuration--;
        }

        if(isExploded && explodeDuration != 0) {
            explodeDuration--;
        }
        else if(isExploded && explodeDuration == 0) {
            isExploded = false;
        }

        respawnTime--;
        updateRect();
    }

    public void setX(int a) {
        x = a;
    }

    public void setY(int a) {
        y = a;
    }

    public void fire() {
        isFired = true;
        fallDuration = 50;
        respawnTime = initialRespawnTime;
    }

    public void updateRect() {
        rect.set(x, y, x + width, y + height);
    }

    public Rect getRect() {
        return rect;
    }

    public boolean getIsFired() {
        return isFired;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public boolean getIsExploded() {
        return isExploded;
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

    public int getDuration() {
        return fallDuration;
    }

}
