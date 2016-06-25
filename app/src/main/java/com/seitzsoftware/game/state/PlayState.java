package com.seitzsoftware.game.state;

import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.seitzsoftware.android.Stealth.Assets;
import com.seitzsoftware.android.Stealth.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;
import com.seitzsoftware.framework.util.RandomNumberGenerator;
import com.seitzsoftware.framework.util.UIButton;
import com.seitzsoftware.game.model.Bomb;
import com.seitzsoftware.game.model.Bunker;
import com.seitzsoftware.game.model.Cactus;
import com.seitzsoftware.game.model.Model;
import com.seitzsoftware.game.model.Player;
import com.seitzsoftware.game.model.Tank;

import java.util.ArrayList;

/**
 * Created by Kevin on 4/28/2016.
 */
public class PlayState extends State {

    private Player player;
    private Bomb MOP1, MOP2;
    private Bunker bunker1, bunker2;
    private Cactus cactus1, cactus2, cactus3;
    private Tank tank1;

    private ArrayList<Model> models;

    private static final int PLAYER_WIDTH = 125;
    private static final int PLAYER_HEIGHT = 75;
    private static final int BOMB_WIDTH = 30;
    private static final int BOMB_HEIGHT = 30;
    private static final int BUNKER_WIDTH = 50;
    private static final int BUNKER_HEIGHT = 50;
    private static final int CACTUS_WIDTH = 20;
    private static final int CACTUS_HEIGHT = 38;
    private static final int TANK_WIDTH = 50;
    private static final int TANK_HEIGHT = 50;

    private UIButton moveLeft, moveRight, fireBomb;

    private int Ammo, Health;
    private int playerScore;
    float delta = 1;

    @Override
    public void init() {
        models = new ArrayList<Model>();
        player = new Player(325, 240, PLAYER_WIDTH, PLAYER_HEIGHT);

        tank1 = new Tank(RandomNumberGenerator.getRandIntBetween(200, 600), RandomNumberGenerator.getRandIntBetween(-400, -200) - 200, TANK_WIDTH, TANK_HEIGHT);
        models.add(tank1);

        bunker1 = new Bunker(RandomNumberGenerator.getRandIntBetween(200, 600), RandomNumberGenerator.getRandIntBetween(-400, -200) - 200, BUNKER_WIDTH, BUNKER_HEIGHT);
        models.add(bunker1);
        bunker2 = new Bunker(RandomNumberGenerator.getRandIntBetween(200, 600), RandomNumberGenerator.getRandIntBetween(-400, -200) - 200, BUNKER_WIDTH, BUNKER_HEIGHT);
        models.add(bunker2);

        MOP1 = new Bomb(325, 375, BOMB_WIDTH, BOMB_HEIGHT, 250);
        MOP2 = new Bomb(325, 375, BOMB_WIDTH, BOMB_HEIGHT, 250);

        cactus1 = new Cactus(RandomNumberGenerator.getRandIntBetween(50, 700), RandomNumberGenerator.getRandIntBetween(-1000, 100), CACTUS_WIDTH, CACTUS_HEIGHT);
        models.add(cactus1);
        cactus2 = new Cactus(RandomNumberGenerator.getRandIntBetween(50, 700), RandomNumberGenerator.getRandIntBetween(-1000, 100), CACTUS_WIDTH, CACTUS_HEIGHT);
        models.add(cactus2);
        cactus3 = new Cactus(RandomNumberGenerator.getRandIntBetween(50, 700), RandomNumberGenerator.getRandIntBetween(-1000, 100), CACTUS_WIDTH, CACTUS_HEIGHT);
        models.add(cactus3);

        moveLeft = new UIButton(40, 348, 120, 428, Assets.move_left, Assets.move_left_unpressed);
        moveRight = new UIButton(680, 348, 760, 428, Assets.move_right, Assets.move_right_unpressed);
        fireBomb = new UIButton(40, 248, 120, 328, Assets.fireBomb, Assets.fireBombPressed);


        playerScore = 0;
        Health = 3;
    }

    @Override
    public void update(float a) {
        player.update(delta);
        calculateAmmo();

        cactus1.update(delta);
        cactus2.update(delta);
        cactus3.update(delta);
        bunker1.update(delta);
        bunker2.update(delta);
        tank1.update(delta);

        //if any of the 2 models overlap each other, generate another placement for the model to fix it
        while(checkIntersection(cactus1)) {
            cactus1.update(delta);
        }
        while(checkIntersection(cactus2)) {
            cactus2.update(delta);
        }
        while(checkIntersection(cactus3)) {
            cactus3.update(delta);
        }
        while(checkIntersection(bunker1)) {
            bunker1.update(delta);
        }
        while(checkIntersection(bunker2)) {
            bunker2.update(delta);
        }
        while(checkIntersection(tank1)) {
            tank1.update(delta);
        }

        MOP1.update(delta);
        MOP2.update(delta);

        updateEnemies();
        updateHealth();
        updateModels();
    }

    private boolean checkIntersection(Model a) {
        int j = 0;
        for(int i = 0; i < models.size(); i++) {
            Model m = models.get(i);
            if(Rect.intersects(a.getRect(), m.getRect())) {
                j++;
            }
        }
        return (j > 1);
    }

    private void updateModels() {
        for(int i = 0; i < models.size(); i++) {
            Model m = models.get(i);
            m.updateRect();
        }
    }

    private void updateEnemies() {

        //TODO need to make the bombs more accurate with what is visually being displayed
        //TODO find a better way to check the intersections of the bombs here?

        //is bunker 1 alive and did it explode with MOP1
        if(bunker1.getIsAlive() && MOP1.getIsExploded() && Rect.intersects(MOP1.getRect(), bunker1.getRect())) {
            bunker1.isDead();
            playerScore++;
        }
        //is bunker 1 alive and did it explode with MOP2
        else if(bunker1.getIsAlive() && MOP2.getIsExploded() && Rect.intersects(MOP2.getRect(), bunker1.getRect())) {
            bunker1.isDead();
            playerScore++;
        }
        else if(bunker2.getIsAlive() && MOP1.getIsExploded() && Rect.intersects(MOP1.getRect(), bunker2.getRect())) {
            bunker2.isDead();
            playerScore++;
        }
        else if(bunker2.getIsAlive() && MOP2.getIsExploded() && Rect.intersects(MOP1.getRect(), bunker2.getRect())) {
            bunker2.isDead();
            playerScore++;
        }
        else if(tank1.getIsAlive() && MOP1.getIsExploded() && Rect.intersects(MOP1.getRect(), tank1.getRect())) {
            tank1.isDead();
            playerScore++;
        }
        else if(tank1.getIsAlive() && MOP2.getIsExploded() && Rect.intersects(MOP2.getRect(), tank1.getRect())) {
            tank1.isDead();
            playerScore++;
        }
    }

    private void updateHealth() {
        if(bunker1.getIsAlive() && bunker1.getY() > (GameMainActivity.GAME_HEIGHT -5)) {
            Health--;
            bunker1.isDead();
        }
        if(bunker2.getIsAlive() && bunker2.getY() > (GameMainActivity.GAME_HEIGHT -5)) {
            Health--;
            bunker2.isDead();
        }
        if(tank1.getIsAlive() && tank1.getY() > (GameMainActivity.GAME_HEIGHT - 5)) {
            Health--;
            tank1.isDead();
        }
        if(Health == 0) {
            setCurrentState(new GameOverState(playerScore));
        }
    }

    private void calculateAmmo() {
        Ammo = 0;
        if(!MOP1.getIsFired() && MOP1.getRespawnTime() < 1) {
            Ammo++;
        }
        if(!MOP2.getIsFired() && MOP2.getRespawnTime() < 1) {
            Ammo++;
        }
    }


    @Override
    public void render(Painter g) {

        //TODO need to add animation for bombs exploding and have them get smaller on screen as time passes

        g.setColor(Color.rgb(245, 206, 162));
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);

        renderCacti(g);
        renderTanks(g);
        renderBunker(g);
        renderBombs(g);
        renderPlayer(g);
        renderAmmo(g);
        renderScore(g);
        renderHealth(g);
        moveLeft.render(g);
        moveRight.render(g);
        fireBomb.render(g);
    }

    private void renderTanks(Painter g) {
        if(tank1.getIsAlive()) {
            g.drawImage(Assets.tank, tank1.getX(), tank1.getY(), 80, 90);
        }
    }

    private void renderHealth(Painter g) {
        g.setColor(Color.BLACK);
        g.drawString("Health", 600, 135);
        int j = 0;
        for(int i = 0; i < Health; i++) {
            g.drawImage(Assets.bomberpic, 590 + j, 150, 42, 25);
            j += 50;
        }
    }

    private void renderScore(Painter g) {
        g.setColor(Color.BLACK);
        g.drawString("Score: " + playerScore, 600, 35);
    }

    private void renderBunker(Painter g) {
        if(bunker1.getIsAlive()) {
            g.drawImage(Assets.bunker, bunker1.getX(), bunker1.getY(), 58, 35);
        }
        if(bunker2.getIsAlive()) {
            g.drawImage(Assets.bunker, bunker2.getX(), bunker2.getY(), 58, 35);
        }
    }

    private void renderAmmo(Painter g) {
        g.setColor(Color.BLACK);
        //g.drawString("MOP", 700, 35);
        int j = 0;
        for(int i = 0; i < Ammo; i++) {
            g.drawImage(Assets.MOP, 690 + j, 50, 21, 42);
            j += 50;
        }
    }

    private void renderCacti(Painter g) {
        g.drawImage(Assets.cactus, cactus1.getX(), cactus1.getY(), 20, 38);
        g.drawImage(Assets.cactus, cactus2.getX(), cactus2.getY(), 20, 38);
        g.drawImage(Assets.cactus, cactus3.getX(), cactus3.getY(), 20, 38);
    }

    private void renderPlayer(Painter g) {
        g.drawImage(Assets.bomberpic, player.getX(), player.getY(), 125, 75);
    }

    private void renderBombs(Painter g) {
        if(MOP1.getIsFired()) {
            g.drawImage(Assets.MOP, MOP1.getX(), MOP1.getY(), 21, 42);
        }
        else if(MOP1.getIsExploded()) {
            g.drawImage(Assets.explode3, MOP1.getX() - 50, MOP1.getY() - 40, 100, 100);
        }


        if(MOP2.getIsFired()) {
            g.drawImage(Assets.MOP, MOP2.getX(), MOP2.getY(), 21, 42);
        }
        else if(MOP2.getIsExploded()) {
            g.drawImage(Assets.explode3, MOP2.getX() - 50, MOP2.getY() - 40, 100, 100);
        }
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_DOWN) {
            moveLeft.onTouchDown(scaledX, scaledY);
            moveRight.onTouchDown(scaledX, scaledY);
            fireBomb.onTouchDown(scaledX, scaledY);

            if(moveLeft.isPressed(scaledX, scaledY)) {
                player.accelLeft();
            } else if(moveRight.isPressed(scaledX, scaledY)) {
                player.accelRight();
            }

            if(fireBomb.isPressed(scaledX, scaledY)) {
                if(!MOP1.getIsFired() && MOP1.getRespawnTime() < 1) {
                    MOP1.fire();
                    MOP1.setX(player.getX() + 55);
                    MOP1.setY(player.getY() + 10);
                }
                else if(!MOP2.getIsFired() && MOP2.getRespawnTime() < 1) {
                    MOP2.fire();
                    MOP2.setX(player.getX() + 55);
                    MOP2.setY(player.getY() + 10);
                }
            }
        }

        if(e.getAction() == MotionEvent.ACTION_UP) {
            moveLeft.cancel();
            moveRight.cancel();
            fireBomb.cancel();
            player.stop();
        }
        return true;
    }


/*
    @Override
    public void onClick(MouseEvent e) {
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            player.accelUp();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            player.accelDown();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.accelLeft();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.accelRight();
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {

            if(MOP1.getIsFired() != true && MOP1.getRespawnTime() < 1) {
                MOP1.fire();
                MOP1.setX(player.getX() + 55);
                MOP1.setY(player.getY() + 10);
            }
            else if(MOP2.getIsFired() != true && MOP2.getRespawnTime() < 1) {
                MOP2.fire();
                MOP2.setX(player.getX() + 55);
                MOP2.setY(player.getY() + 10);
            }
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
                || e.getKeyCode() == KeyEvent.VK_LEFT  || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.stop();
        }
    }
    */
}

