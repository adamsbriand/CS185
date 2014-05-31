package com.touchspin.td;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HUD extends GameThing {
	public OrthographicCamera camera;
	public MoverInput heroMover = new MoverInput();

	private Map<String,TextureRegion> HealthMap = new HashMap<String, TextureRegion>();
	
	private float scaleFactor;
	private Sprite heroHealth;
	private int healthWidth;
	private int healthHeight;

	public HUD(OrthographicCamera camera, TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;

		heroHealth = new Sprite();
		
		heroHealth.setBounds(0, 32, healthWidth * camera.zoom, healthHeight * camera.zoom);
		heroHealth.setOrigin(heroHealth.getWidth() / 2,
				heroHealth.getHeight() / 2);
		
		setHeight(heroHealth.getHeight() * camera.zoom);
		setWidth(heroHealth.getWidth() * camera.zoom);
		setX(10);
		setY(100);
		
		scaleFactor = 1f;
	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		heroHealth.draw(batch);
	}

	private void drawEffect(Batch batch) {
		// batch.draw(currentFrame,getX(),getY(),32f,currentFrame.getRegionHeight()*32/currentFrame.getRegionWidth());
	}

	@Override
	public void act(float delta) {
		heroMover.move(this);

		// position
		setSpritesPosition();

		// animation
		stateTime += Gdx.graphics.getDeltaTime();

	}

	
	//--------------Private helper method------------------------------------------
	private void setSpritesPosition()
	{
		heroHealth.setX(getX());
		heroHealth.setY(getY());
	}
    
	
    private void loadBallType()
	{
		Texture appearance = new Texture("img/hud/HeartBarl.png");
		TextureRegion [][] tmp = TextureRegion.split(appearance, appearance.getWidth() / 6,
				appearance.getHeight() / 12);
		
		HealthMap.put("Baseball", tmp[4][0]);
	}
    
	private void setSpriteBounds()
	{
		if(heroHealth != null){
			heroHealth.setBounds(0, 32, healthWidth * camera.zoom, healthHeight * camera.zoom);
			heroHealth.setOrigin(heroHealth.getWidth() / 2,
					heroHealth.getHeight() / 2);}
		setHeight(heroHealth.getHeight() * camera.zoom);
		setWidth(heroHealth.getWidth() * camera.zoom);

	}
}
