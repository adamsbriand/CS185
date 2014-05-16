package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HeroRunner extends Actor {

	private Texture normal;
	private Texture attack;
	private Sprite runnerSprite;
	
	private int frameCount = 0;
		
	private float actorX = 0, actorY = 0;
	private float distancePerFrameX;
	private float distancePerFrameY;
	private int gravity = -10;
	
	private OrthographicCamera camera;
	private TiledMapWrapper tiledMapWrapper;	
		
	private boolean jumpFinish = true;

	public HeroRunner(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
				
		normal = new Texture(Gdx.files.internal("data/lockOn.png"));
		attack = new Texture(Gdx.files.internal("data/lockOnRed.png"));
		runnerSprite = new Sprite(normal);
		actorX = 0;
		actorY = 32;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(runnerSprite, actorX, actorY);
	}

	@Override
	public void act(float delta) {
		running();
		if (frameCount > 1)
			frameCount--;
		if (frameCount == 1) {
			runnerSprite.setTexture(normal);
			frameCount = 0;
		}
	}

	public void attack() {
		frameCount = 15;
		runnerSprite.setTexture(attack);
	}

	private void running() {
		
		distancePerFrameX = g.i().accelX;
		if (actorX + runnerSprite.getWidth() + distancePerFrameX < tiledMapWrapper
				.getPixelWidth())
			actorX += distancePerFrameX;
		
		//Vertical movement
		if (!jumpFinish) 
		{
			g.i().accelY += gravity;
			distancePerFrameY = g.i().accelY * Gdx.graphics.getDeltaTime();
			actorY += distancePerFrameY;
			
			if(actorY <= 32) //check if on the ground
			{
				actorY=32;
				jumpFinish = true; //jump is finished
				g.i().accelY = 0;
			}
		}
	}

	public void jump(int iniVelocity) 
	 {

		if (jumpFinish) 
		{
			g.i().accelY = iniVelocity;
			distancePerFrameY = g.i().accelY * Gdx.graphics.getDeltaTime();
			jumpFinish=false;			
		}
	}
	
	public float getDistancePerFrameX()
	{
		return distancePerFrameX;
	}
	
	public float getDistancePerFrameY()
	{
		return distancePerFrameY;
	}
	
	public float getX()
	{
		return actorX;
	}
	
	public float getY()
	{
		return actorY;
	}		
	
	public boolean isAtTheEndOfTheMap()
	{
		if (actorX + runnerSprite.getWidth() + distancePerFrameX > tiledMapWrapper
				.getPixelWidth() - 1)
		{
			return true;
		}
		return false;
	}

}
