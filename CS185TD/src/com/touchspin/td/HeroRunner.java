package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class HeroRunner extends Hero {

	private Texture appearance;
	private Sprite runnerSprite;
	
	private int frameCount = 0;
		
	//private float distancePerFrameX;
	//private float distancePerFrameY;
	//private int gravity = -10;	
		

	public HeroRunner(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		
		appearance = new Texture(Gdx.files.internal("data/Ball100BallBearingBrass.png"));
		runnerSprite = new Sprite(appearance);
		runnerSprite.setBounds(0, 32, 32 * camera.zoom, 32 * camera.zoom);
		runnerSprite.setOrigin(runnerSprite.getWidth()/2, runnerSprite.getHeight()/2);
		setHeight(runnerSprite.getRegionHeight());
		setWidth(runnerSprite.getRegionWidth());
		setX(0);
		setY(32);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		if(frameCount == 15)
		{
			runnerSprite.setColor(Color.RED);
		}
		else if(frameCount == 1)
		{
			runnerSprite.setColor(Color.WHITE);
		}
		runnerSprite.draw(batch);
	}

	@Override
	public void act(float delta) {
		heroMover.move(this);
		
		//Attack
		if (frameCount > 1)
			frameCount--;
		if(g.i().leAnonymizer.attack)
		{
			attack();
			g.i().leAnonymizer.attack = false;
		}
		//position
		runnerSprite.setX(getX());
		runnerSprite.setY(getY());
		
		//Rotation
		runnerSprite.rotate(360*(heroMover.previousX - getX())/((float)Math.PI * runnerSprite.getRegionHeight()));
	}

	public void attack() {
		frameCount = 15;
	}
	
	public Rectangle getCollisionRect()
	{
		return runnerSprite.getBoundingRectangle();
	}
}
