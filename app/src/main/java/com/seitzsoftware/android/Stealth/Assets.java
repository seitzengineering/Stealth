package com.seitzsoftware.android.Stealth;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kevin on 12/13/2015.
 */
public class Assets {
    private static SoundPool soundPool;
    public static Bitmap welcome, iconimage, bomberpic, MOP, cactus, explode3, bunker, move_right, move_left,
            move_right_unpressed, move_left_unpressed, fireBomb, fireBombPressed, tank;
    public static Color tan;

    public static void load() {
        welcome = loadBitmap("welcome.png", false);
        iconimage = loadBitmap("iconimage.png", false);
        bomberpic = loadBitmap("bomberpic.png", false);
        MOP = loadBitmap("MOP2.png", false);
        cactus = loadBitmap("cactus2.png", false);
        explode3 = loadBitmap("explode3.png", false);
        bunker = loadBitmap("bunker2.png", false);
        move_right = loadBitmap("move_right_pressed.png", false);
        move_left = loadBitmap("move_left_pressed.png", false);
        move_right_unpressed = loadBitmap("move_right_unpressed.png", false);
        move_left_unpressed = loadBitmap("move_left_unpressed.png", false);
        fireBomb = loadBitmap("fireBomb.png", false);
        fireBombPressed = loadBitmap("fireBombPressed.png", false);
        tank = loadBitmap("tank2.png", false);
    }

    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if (transparency) {
            options.inPreferredConfig = Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }

    private static int loadSound(String filename) {
        int soundID = 0;
        if (soundPool == null) {
            soundPool = new SoundPool(25, AudioManager.STREAM_MUSIC, 0);
        }
        try {
            soundID = soundPool.load(GameMainActivity.assets.openFd(filename),
                    1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return soundID;
    }

    public static void playSound(int soundID) {
        soundPool.play(soundID, 1, 1, 1, 0, 1);
    }
}
