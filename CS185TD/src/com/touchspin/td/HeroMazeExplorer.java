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
	private Sprite heroSprite;
	
	public HeroMazeExplorer(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) 
	{
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		heroSprite=new Sprite(new Texture((Gdx.files.internal("data/Ball100Pool.png"))));
		
		setHeight(heroSprite.getHeight());
		setWidth(heroSprite.getWidth());
		setX(15);
		setY(9);		
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(heroSprite, getX(), getY());
	}

	@Override
	public void act(float delta) 
	{		
		heroMover.move(this);			
		if (frameCount > 1)
			frameCount--;
		if (frameCount == 1) 			
			frameCount = 0;	
		
		camera.position.set(getX(), getY(), 0);
	}

	@Override
	public Rectangle getCollisionRect() {
		// TODO Auto-generated method stub
		return null;
	}	
	
}// end of HerMazeRunner class
