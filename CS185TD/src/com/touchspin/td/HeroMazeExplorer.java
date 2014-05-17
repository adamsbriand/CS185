package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * 
 * @author 
 *
 */
public class HeroMazeExplorer extends Hero  {
	
	private int frameCount = 0;			
	private Sprite mazeExplorerSprite;
	
	public HeroMazeExplorer(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) 
	{
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		mazeExplorerSprite=new Sprite(new Texture((Gdx.files.internal("data/Ball100Pool.png"))));
		setHeight(mazeExplorerSprite.getHeight());
		setWidth(mazeExplorerSprite.getWidth());
		setX(15);
		setY(9);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(mazeExplorerSprite, getX(), getY());
	}

	@Override
	public void act(float delta) 
	{		
		heroMover.move(this);			
		if (frameCount > 1)
			frameCount--;
		if (frameCount == 1) 			
			frameCount = 0;		
	}	
	
}// end of HerMazeRunner class
