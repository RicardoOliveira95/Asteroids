package com.mygdx.game.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.main.AsteroidMain;
import com.mygdx.game.managers.GameStateManager;

/**
 * Created by fachrur_122 on 07/08/2015.
 */
public class MenuState extends GameState{
	private SpriteBatch sb;
	private BitmapFont font;

    public MenuState(GameStateManager gsm) {
		super(gsm);
		init();
		this.gsm=gsm;
	}
	protected GameStateManager gsm;

    public void init() {
    	sb=new SpriteBatch();
    	font=new BitmapFont();
    	font.setColor(Color.YELLOW);
    };
    public void update(float dt) {
    	handleInput();
    };
    public void draw() {
    	System.out.println("MENU STATE");
    	sb.begin();
    	font.draw(sb,"CLICK TO PLAY!",AsteroidMain.WIDTH/2,AsteroidMain.HEIGHT/2);
    	sb.end();
    };
    public void handleInput() {
    	if(Gdx.input.justTouched()) {
    		System.out.println("CHANGE STATE");
            gsm.setState(GameStateManager.PLAY);
            dispose();
        }
    };
    public void dispose() {};

}