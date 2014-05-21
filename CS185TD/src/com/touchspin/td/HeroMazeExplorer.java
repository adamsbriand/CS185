package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * 
 * @author 
 *
 */
public class HeroMazeExplorer extends Hero  {
	
	private int frameCount = 0;	
	private Texture appearance;
	private Sprite heroSprite;
	
	public HeroMazeExplorer(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) 
	{
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		
		appearance = new Texture(Gdx.files.internal("data/Ball100BallBearingBrass.png"));
		heroSprite = new Sprite(appearance);
		heroSprite.setBounds(0, 32, 32 * camera.zoom, 32 * camera.zoom);
		heroSprite.setOrigin(heroSprite.getWidth()/2, heroSprite.getHeight()/2);
		
		setHeight(heroSprite.getRegionHeight());
		setWidth(heroSprite.getRegionWidth());
		
		setX(0);
		setY(0);		
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		heroSprite.draw(batch);
	}

	@Override
	public void act(float delta) 
	{		
		heroMover.setSprite(heroSprite);
		heroMover.move(this);			
		if (frameCount > 1)
			frameCount--;
		if (frameCount == 1) 			
			frameCount = 0;	
		
		//position
		heroSprite.setX(getX());
		heroSprite.setY(getY());
				
		//Rotation
		heroSprite.rotate(360*(heroMover.previousX - getX())/((float)Math.PI * heroSprite.getRegionHeight()));
	}

	@Override
	public Rectangle getCollisionRect() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}// end of HerMazeRunner class
