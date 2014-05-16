package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HeroMazeExplorer extends Hero  {
	
	private int frameCount = 0;
		
	private float actorX = 0, actorY = 0;
	private float distancePerFrameX;
	private float distancePerFrameY;
		
	private Sprite mazeExplorerSprite;
	
	public HeroMazeExplorer(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		mazeExplorerSprite=new Sprite(new Texture(Gdx.files.internal("data/Ball100Pool.png")));
		actorX = 18;
		actorY = 18;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(mazeExplorerSprite, actorX, actorY);
	}

	@Override
	public void act(float delta) 
	{		

		distancePerFrameX = g.i().accelX;
		
		if (actorX + mazeExplorerSprite.getWidth() + distancePerFrameX < tiledMapWrapper
				.getPixelWidth())
			actorX += distancePerFrameX;
		
		distancePerFrameY = g.i().accelY;
		
		if (actorY + mazeExplorerSprite.getHeight() + distancePerFrameX < tiledMapWrapper
				.getPixelHeight())
			actorY += distancePerFrameY;
		
		if (frameCount > 1)
			frameCount--;
		if (frameCount == 1) {
			mazeExplorerSprite.setTexture((new Texture(Gdx.files.internal("data/Ball100Pool.png"))));
			frameCount = 0;
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
}
