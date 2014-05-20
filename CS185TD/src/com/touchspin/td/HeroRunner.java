package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Rectangle;

public class HeroRunner extends Hero {

	private Texture appearance;
	private Texture fire;
	private Animation fireAnimation;
	private TextureRegion[] fireFrames;
	private TextureRegion currentFrame;
	private int frameCount = 0;
	private Sprite heroSprite;
	private Sprite fireSprite;
	private float stateTime; 
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
		
		//read in file animation
		fire = new Texture(Gdx.files.internal("data/Fireball_Frame100x240_Sheet_1200x960_RC12x4_Frames48.png"));
        TextureRegion[][] tmp = TextureRegion.split(fire, fire.getWidth()/12, fire.getHeight()/4);  
        fireFrames = new TextureRegion[12*4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                fireFrames[index++] = tmp[i][j];
            }
        }
        fireAnimation = new Animation(0.025f, fireFrames); 
        stateTime = 0f;

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
		drawFireEffect(batch);
	}

	private void drawFireEffect(Batch batch)
	{
		batch.draw(currentFrame,getX(),getY(),32f,currentFrame.getRegionHeight()*32/currentFrame.getRegionWidth());
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
		
		//Fire animation
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = fireAnimation.getKeyFrame(stateTime, true);
		
		
	}

	public void attack() {
		frameCount = 15;
	}
	public Rectangle getCollisionRect()
	{
		return heroSprite.getBoundingRectangle();
	}
}
