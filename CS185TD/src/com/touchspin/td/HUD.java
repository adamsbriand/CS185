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


	private Map<String,TextureRegion> HealthMap = new HashMap<String, TextureRegion>();
	
	private float scaleFactor;
	private Sprite heroHealth;
	private int healthWidth;
	private int healthHeight;
	private TextureRegion [][] frames;

	public HUD(OrthographicCamera camera, TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;

		heroHealth = new Sprite();
		loadHealthBar();
		
//		heroHealth.setBounds(0, 32, healthWidth * camera.zoom, healthHeight * camera.zoom);
//		heroHealth.setOrigin(heroHealth.getWidth() / 2,
//				heroHealth.getHeight() / 2);
		
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

		// position
		setSpritesPosition();

		// animation
		stateTime += Gdx.graphics.getDeltaTime();

	}

	
	//--------------Private helper method------------------------------------------
	private void setSpritesPosition()
	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		setX(camera.position.x-camera.viewportWidth/2 + 0.1f * camera.viewportWidth/2);
		setY(camera.position.y + camera.viewportHeight/2 - 0.15f*w/h*  camera.viewportHeight/2);
		heroHealth.setX(getX());
		heroHealth.setY(getY());
	}
    
	
    private void loadHealthBar()
	{
		Texture appearance = new Texture("img/hud/HeartBar.png");
		healthWidth = appearance.getWidth() / 2;
		healthHeight = appearance.getHeight() / 26;
		frames = TextureRegion.split(appearance, healthWidth,
				healthHeight);
		heroHealth.setRegion(frames[0][0]);
		heroHealth.setOrigin(healthWidth/2, healthHeight/2);
		heroHealth.setBounds(getX(),getY(), camera.viewportWidth * 0.3f,camera.viewportWidth * 0.3f *  (appearance.getHeight() / 26)/(appearance.getWidth() / 2));
	}

}
