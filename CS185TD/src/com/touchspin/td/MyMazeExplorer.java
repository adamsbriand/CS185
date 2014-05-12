package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyMazeExplorer extends Actor  {
	
	private float actorX = 0, actorY = 0;
	private int speedX ;
	private int speedY ;
	private OrthographicCamera camera;
	private TiledMapWrapper tiledMapWrapper;
	private Sprite mazeExplorerSprite;
	
	public MyMazeExplorer(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;
		mazeExplorerSprite=new Sprite();
	}

	@Override
	public void draw(Batch batch, float alpha) {
	}

	@Override
	public void act(float delta) {
	}
}
