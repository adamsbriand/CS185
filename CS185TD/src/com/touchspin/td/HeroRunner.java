package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;

public class HeroRunner extends Hero {

	private Texture appearance;
	private int frameCount = 0;
	private Sprite heroSprite;
	//private float distancePerFrameX;
	//private float distancePerFrameY;
	//private int gravity = -10;	
		

	public HeroRunner(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		
		appearance = new Texture(Gdx.files.internal("data/Ball100BallBearingBrass.png"));
		heroSprite = new Sprite(appearance);
		heroSprite.setBounds(0, 32, 32 * camera.zoom, 32 * camera.zoom);
		heroSprite.setOrigin(heroSprite.getWidth()/2, heroSprite.getHeight()/2);
		setHeight(heroSprite.getRegionHeight());
		setWidth(heroSprite.getRegionWidth());
		setX(10);
		setY(100);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		if(frameCount == 15)
		{
			heroSprite.setColor(Color.RED);
		}
		else if(frameCount == 1)
		{
			heroSprite.setColor(Color.WHITE);
		}
		heroSprite.draw(batch);
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
		heroSprite.setX(getX());
		heroSprite.setY(getY());
		
		//Rotation
		heroSprite.rotate(360*(heroMover.previousX - getX())/((float)Math.PI * heroSprite.getRegionHeight()));
	}

	public void attack() {
		frameCount = 15;
	}
	public Rectangle getCollisionRect()
	{
		return heroSprite.getBoundingRectangle();
	}
}
