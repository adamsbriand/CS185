package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class HeroRunner extends Hero {

	private Texture normal;
	private Texture attack;
	private Sprite runnerSprite;
	
	private int frameCount = 0;
		
	//private float distancePerFrameX;
	//private float distancePerFrameY;
	//private int gravity = -10;	
		

	public HeroRunner(OrthographicCamera camera,TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;			
		normal = new Texture(Gdx.files.internal("data/lockOn.png"));
		attack = new Texture(Gdx.files.internal("data/lockOnRed.png"));
		runnerSprite = new Sprite(normal);
		setHeight(runnerSprite.getHeight());
		setWidth(runnerSprite.getWidth());
		setX(0);
		setY(32);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		batch.draw(runnerSprite, getX(), getY());
	}

	@Override
	public void act(float delta) {
		heroMover.move(this);
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
}
