package com.mygdx.game.gamestate;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.Asteroid;
import com.mygdx.game.entities.Bullet;
import com.mygdx.game.entities.Player;
import com.mygdx.game.main.AsteroidMain;
import com.mygdx.game.managers.GameKeys;
import com.mygdx.game.managers.GameStateManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by fachrur_122 on 07/08/2015.
 */
public class PlayState extends GameState {

    private ShapeRenderer sr;
    private SpriteBatch sb;

    private Player player;
    private ArrayList<Bullet> bullets;
    private ArrayList<Asteroid> asteroids;
    private float game_time;
    private Random rand;
    private float hit;
    private int score;
    private BitmapFont font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    @Override
    public void init() {
        sr = new ShapeRenderer();

        bullets = new ArrayList<Bullet>();

        player = new Player(bullets);

        asteroids = new ArrayList<Asteroid>();
        asteroids.add(new Asteroid(100, 100, Asteroid.LARGE));
        asteroids.add(new Asteroid(200, 100, Asteroid.MEDIUM));
        asteroids.add(new Asteroid(300, 100, Asteroid.SMALL));
        
        game_time=0;
        rand=new Random();
        score=0;
        font=new BitmapFont();
        sb=new SpriteBatch();
    }

    @Override
    public void update(float dt) {
    	//gameover
    	if(player.getHealth()<=0) {
    		new MenuState(this.gsm);
    		System.out.println("DIE");
    	}
        //get user input
        handleInput();

        //update player
        player.update(dt);

        //update player bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(dt);
            if (bullets.get(i).shouldRemove()) {
                bullets.remove(i);
                i--;
            }
        }

        //update asteroids
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).update(dt);
            if (asteroids.get(i).shouldRemove()) {
                switch(asteroids.get(i).getType()) {
                case 0:
                	score+=1;
                case 1:
                	score+=2;
                case 2:
                	score+=3;
            	default:
            		break;
                }
                asteroids.remove(i);
                i--;
            }
        }
        
        for(Asteroid ast:asteroids) {
        	if(ast.getBounds().contains(player.getBounds())) {
        		switch(ast.getType()) {
        		case 0:
        			hit=-.5f;
        			break;
        		case 1:
        			hit=-1.2f;
        			break;
        		case 2:
        			hit=-2.3f;
        			break;
        		default:
        			break;
        		}
        		player.setHealth(hit);
        		System.out.println("COLLISION!"+player.getHealth());
        	}
        	for(Bullet bullet:bullets) {
        		if(ast.getBounds().contains(bullet.getBounds()))
        			ast.remove();
        	}
        }
        
        game_time+=dt;
        
        if(game_time>10) {
        	asteroids.add(new Asteroid(rand.nextFloat()*AsteroidMain.WIDTH,rand.nextFloat()*AsteroidMain.HEIGHT,rand.nextInt(2)));
        	game_time=0;
        }
    }

    @Override
    public void draw() {
        //draw player
        player.draw(sr);

        //draw bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(sr);
        }

        //draw asteroids
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).draw(sr);
        }
        //draw hud
        sb.begin();
        font.setColor(Color.ORANGE);
        font.draw(sb,"SCORE: "+score,30,30);
        sb.end();
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0, 1, 0, 0);
        sr.rect(AsteroidMain.WIDTH-150, AsteroidMain.HEIGHT-50, player.getHealth(), 15);
        sr.end();
    }

    @Override
    public void handleInput() {
        player.setLeft(GameKeys.isDown(GameKeys.LEFT));
        player.setRight(GameKeys.isDown(GameKeys.RIGHT));
        player.setUp(GameKeys.isDown(GameKeys.UP));
//        player.setDown(GameKeys.isDown(GameKeys.DOWN));
        if (GameKeys.isPressed(GameKeys.SPACE)) {
            player.shoot();
        }
    }

    @Override
    public void dispose() {
    	font.dispose();
    	sb.dispose();
    }

}
