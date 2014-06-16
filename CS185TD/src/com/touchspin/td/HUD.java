package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/* ======================================================================================
 * File:			HUD.java
 * Authors:			Brian Adams - b.adams5736@edmail.edcc.edu
 * 					Russell Brendel - russell.brendel.2925@edmail.edcc.edu
 * 					Damian Forrester - dforrester777@gmail.com
 * 					Wendi Tang - w.tang2404@myedmail.edcc.edu
 * 
 * Organization:	Edmonds Community College
 * Term:			Spring 2014
 * Class:			CS 185 - Game Project Developement
 * Instructor:		Tim Hunt - thunt@edcc.edu
 * 
 * Project:			Ollie
 * --------------------------------------------------------------------------------------
 * 
 * This class display a health bar
 * 
 * ======================================================================================
 */
public class HUD extends GameThing {
	public OrthographicCamera camera;
	private Sprite heroHealth;
	private Sprite cover;
	private int healthWidth;
	private int healthHeight;
	private TextureRegion currentFrame;
	private int previousHealth;
	private Animation  healthBarAnimation;


	/**
	 * The constructor
	 * @param camera - the camera used in the game
	 * @param tiledMapWrapper - the tiledMapWrapper used in the game
	 */
	public HUD(OrthographicCamera camera, TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;

		heroHealth = new Sprite();
		loadHealthBar();
		
		setHeight(heroHealth.getHeight() * camera.zoom);
		setWidth(heroHealth.getWidth() * camera.zoom);
		setX(10);
		setY(100);
	}

	/**
	 * Draw the health bar
	 */
	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		heroHealth.draw(batch);
		cover.draw(batch);
	}

	/**
	 * Update the health bar
	 */
	@Override
	public void act(float delta) {

		// position
		setSpritesPosition();
		if(previousHealth != g.i().playerHealth)
			cover.setScale((100f -g.i().playerHealth)/100f, 1f);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = healthBarAnimation.getKeyFrame(stateTime, true);
		heroHealth.setRegion(currentFrame);
		
		previousHealth = g.i().playerHealth;

	}

	
	//--------------Private helper method------------------------------------------
	/**
	 * Set the sprite's position based on the position of the actor
	 */
	private void setSpritesPosition()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		setX(camera.position.x-camera.viewportWidth/2 + 0.1f * camera.viewportWidth/2);
		setY(camera.position.y + camera.viewportHeight/2 - 0.15f*w/h*  camera.viewportHeight/2);
		heroHealth.setX(getX());
		heroHealth.setY(getY());
		
		cover.setX(getX() + 10f/270f * heroHealth.getWidth());
		cover.setY(getY());
		
	}
    
	/**
	 * Load the health bar animation
	 */
    private void loadHealthBar()
	{
    	TextureRegion [][] tempFrames;
    	TextureRegion [] frames;
		Texture appearance = new Texture("img/hud/HeartBar.png");
		healthWidth = appearance.getWidth() / 2;
		healthHeight = appearance.getHeight() / 26;
		tempFrames = TextureRegion.split(appearance, healthWidth,
				healthHeight);
		int index = 0;
		frames = new TextureRegion[52];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < 2; j++) {
				frames[index++] = tempFrames[i][j];
			}
		}
		healthBarAnimation = new Animation(0.01f, frames);
		heroHealth.setOrigin(healthWidth/2, healthHeight/2);
		heroHealth.setBounds(getX(),getY(), camera.viewportWidth * 0.3f,camera.viewportWidth * 0.3f *  (appearance.getHeight() / 26)/(appearance.getWidth() / 2));
		
		stateTime = 0f;
		currentFrame = healthBarAnimation.getKeyFrame(stateTime, true);
		
		
		Texture tempT = new Texture("img/hud/HeartBarCover.jpg");
		cover = new Sprite(tempT);
		cover.setSize(250f/270f * heroHealth.getWidth(), heroHealth.getHeight());
		cover.setOrigin(cover.getWidth(), cover.getHeight()/2);
		cover.setScale(1f, 1f);
	}
    


}
