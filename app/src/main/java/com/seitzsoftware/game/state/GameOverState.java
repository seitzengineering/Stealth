package com.seitzsoftware.game.state;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;

import com.seitzsoftware.android.Stealth.GameMainActivity;
import com.seitzsoftware.framework.util.Painter;

/**
 * Created by Kevin on 4/28/2016.
 */
public class GameOverState extends State {

    private String playerScore;
    private String gameOverMessage = "GAME OVER";

    public GameOverState(int Score) {
        this.playerScore = "" + Score;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(Painter g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, GameMainActivity.GAME_WIDTH, GameMainActivity.GAME_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(Typeface.DEFAULT_BOLD, 50);
        g.drawString("GAME OVER", 250, 200);
        g.drawString("Score:" + playerScore, 350, 300);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        if(e.getAction() == MotionEvent.ACTION_UP) {
            setCurrentState(new MenuState());
        }
        return true;
    }

}
