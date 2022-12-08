package com.mygdx.game.gamestate;

import com.mygdx.game.managers.GameStateManager;

/**
 * Created by fachrur_122 on 07/08/2015.
 */
public abstract class GameState {

    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }

    public abstract void init();
    public abstract void update(float dt);
    public abstract void draw();
    public abstract void handleInput();
    public abstract void dispose();

}
