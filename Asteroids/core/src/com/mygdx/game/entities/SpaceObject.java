package com.mygdx.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.main.AsteroidMain;

/**
 * Created by fachrur_122 on 07/08/2015.
 */
public class SpaceObject {

    //position
    protected float x;
    protected float y;

    //vector
    protected float dx;
    protected float dy;

    protected float radians;
    protected float speed;
    protected float rotationSpeed;

    //size
    protected int width;
    protected int height;

    //shape
    protected float[] shapex;
    protected float[] shapey;
    protected Rectangle rect;

    protected void wrap() {
        if (x < 0) x = AsteroidMain.WIDTH;
        if (x > AsteroidMain.WIDTH) x = 0;
        if (y < 0) y = AsteroidMain.HEIGHT;
        if (y > AsteroidMain.HEIGHT) y = 0;
    }

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getDx() {
		return dx;
	}

	public void setDx(float dx) {
		this.dx = dx;
	}

	public float getDy() {
		return dy;
	}

	public void setDy(float dy) {
		this.dy = dy;
	}

	public float getRadians() {
		return radians;
	}

	public void setRadians(float radians) {
		this.radians = radians;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(float rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float[] getShapex() {
		return shapex;
	}

	public void setShapex(float[] shapex) {
		this.shapex = shapex;
	}

	public float[] getShapey() {
		return shapey;
	}

	public void setShapey(float[] shapey) {
		this.shapey = shapey;
	}
	
	public Rectangle getBounds() {
		rect=new Rectangle(x,y,width,height);
		return rect;
	}
    
}
