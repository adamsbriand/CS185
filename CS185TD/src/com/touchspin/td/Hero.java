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

public class Hero extends GameThing {
	public OrthographicCamera camera;
	public MoverInput heroMover = new MoverInput();

	private Map<String,TextureRegion> ballTypeMap = new HashMap<String, TextureRegion>();
	private Animation fireAnimation;
	private Animation smokeAnimation;
	private TextureRegion currentFireFrame;
	private TextureRegion currentSmokeFrame;
	private float scaleFactor;
	
	private int frameCount = 0;
	private Sprite heroSprite;
	private Sprite fireEffect;
	private Sprite smokeEffect;
	public boolean flammable;
	private float ballHeight;
	private float ballWidth;
	private float countTime;

	// private float distancePerFrameX;
	// private float distancePerFrameY;
	// private int gravity = -10;

	public Hero(OrthographicCamera camera, TiledMapWrapper tiledMapWrapper) {
		this.tiledMapWrapper = tiledMapWrapper;
		this.camera = camera;

		heroSprite = new Sprite();
		
		loadBallType();
		changeBall(g.i().currentBallType);
		
		heroSprite.setBounds(0, 32, ballWidth * camera.zoom, ballHeight * camera.zoom);
		heroSprite.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);
		
		setHeight(heroSprite.getHeight() * camera.zoom);
		setWidth(heroSprite.getWidth() * camera.zoom);
		setX(10);
		setY(100);
		
		scaleFactor = 1f;

		// read in file animation
		loadFireAnimation();
		stateTime = 0f;
		currentFireFrame = fireAnimation.getKeyFrame(stateTime, true);
		
		loadSmokeAnimation();
		currentSmokeFrame = smokeAnimation.getKeyFrame(stateTime, true);

		fireEffect = new Sprite(currentFireFrame);
		fireEffect.setBounds(0, 32, ballWidth * camera.zoom,
				ballHeight * fireEffect.getHeight() / fireEffect.getWidth()
						* camera.zoom);
		fireEffect.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);
		
		smokeEffect = new Sprite(currentSmokeFrame);
		smokeEffect.setBounds(0, 32, ballWidth * camera.zoom,
				ballHeight * smokeEffect.getHeight() / smokeEffect.getWidth()
						* camera.zoom);
		smokeEffect.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);
		
		for (int i=0; i < g.i().mapObjects.size(); i++){
			if (g.i().mapObjects.get(i).getName().equalsIgnoreCase("enter1")){
				setX(g.i().mapObjects.get(i).getX());
				setY(g.i().mapObjects.get(i).getY());
			}
		}
		
		if(g.i().gameMode == 'M')
		{
			camera.position.x = getX();
	    	camera.position.y = getY();
		}

	}

	@Override
	public void draw(Batch batch, float alpha) {
		batch.setProjectionMatrix(camera.combined);
		if (frameCount == 15) {
			heroSprite.setColor(Color.RED);
		} else if (frameCount == 1) {
			heroSprite.setColor(Color.WHITE);
		}
		heroSprite.draw(batch);
		if (g.i().fire) {
			drawEffect(batch);
		}
	}

	private void drawEffect(Batch batch) {
		// batch.draw(currentFrame,getX(),getY(),32f,currentFrame.getRegionHeight()*32/currentFrame.getRegionWidth());
		smokeEffect.draw(batch);
		fireEffect.draw(batch);
	}

	@Override
	public void act(float delta) {
		heroMover.move(this);

		// Attack
		if (frameCount > 1)
			frameCount--;
		if (g.i().leAnonymizer.attack) {
			attack();
			g.i().leAnonymizer.attack = false;
		}
		// position
		setSpritesPosition();

		// Rotation
		heroSprite.rotate(360 * (heroMover.previousX - getX())
				/ ((float) Math.PI * heroSprite.getRegionHeight()));
		setRotationAndScale();

		// animation
		stateTime += Gdx.graphics.getDeltaTime();
		
		currentFireFrame = fireAnimation.getKeyFrame(stateTime, true);
		fireEffect.setRegion(currentFireFrame);
		
		currentSmokeFrame = smokeAnimation.getKeyFrame(stateTime, true);
		smokeEffect.setRegion(currentSmokeFrame);
		
		if(g.i().fire)
		{
			countTime += Gdx.graphics.getDeltaTime();
			if(countTime > 2)
			{
				g.i().playerHealth--;
				countTime = 0;
			}
			if(g.i().playerHealth < 0)
				g.i().playerHealth = 0;
		}

	}

	public void attack() {
		frameCount = 15;
	}

	public void changeBall(String type)
	{
		switch(type)
		{
			case "PingPong":
				g.i().playerFriction = 0.005f;
				flammable = true;
				ballWidth = 24f;
				ballHeight = 24f;
				break;
			case "Bowling":
				g.i().playerFriction = 0.03f;
				g.i().fire = false;
				ballWidth = 32f;
				ballHeight = 32f;
				flammable = false;
				break;
			case "Basketball":
				g.i().playerFriction = 0.01f;
				ballWidth = 32f;
				ballHeight = 32f;
				flammable = false;
				g.i().fire = false;
				break;
			case "Baseball":
				g.i().playerFriction = 0.008f;
				ballWidth = 28f;
				ballHeight = 28f;
				flammable = false;
				g.i().fire = false;
				break;
		}
		if(!g.i().fire)
		{
			g.i().sound.fire(false);
		}
		heroSprite.setRegion(ballTypeMap.get(type));
		g.i().currentBallType = type;
		g.i().sound.setBounce();
		
		setSpriteBounds();
	}
	
	public void igniteBall(boolean fireOn)
	{
		g.i().fire = fireOn;
		if(!g.i().fire)
		{
			g.i().sound.fire(false);
		}
	}
	
    public void changeBalldx(float delta)
    {
    	heroMover.accelerationX = delta;
    }
    public void changeBalldy(float delta)
    {
    	heroMover.accelerationY = delta;
    }
    
    public float getYSpeed()
    {
    	return heroMover.speedYPerSecond;
    }
    
    public float getXSpeed()
    {
    	return heroMover.speedXPerSecond;
    }
	
    @Override
    public void setPosition(float x, float y)
    {
    	setX(x);
    	setY(y);
    	
    	if(g.i().gameMode == 'M')
    	{
    	camera.position.x = getX() + getWidth()/2;
    	if(camera.position.x < camera.viewportWidth/2)
    		camera.position.x = camera.viewportWidth/2;	
    	camera.position.y = getY() + getHeight()/2;
    	if(camera.position.y > tiledMapWrapper.getPixelHeight() - camera.viewportHeight/2 -32)
    		camera.position.y = camera.position.y = getY() + getHeight()/2 -20;
    	}else
    	{
    		camera.position.x = getX() + getWidth()/2;
    	}
    }
	
	
	//--------------Private helper method------------------------------------------
	private void setSpritesPosition()
	{
		heroSprite.setX(getX());
		heroSprite.setY(getY());
		fireEffect.setX(getX());
		fireEffect.setY(getY());
		smokeEffect.setX(getX());
		smokeEffect.setY(getY());
	}
    
    private void loadBallType()
	{
		Texture appearance = new Texture("img/spritesheet/BallSquish.png");
		TextureRegion [][] tmp = TextureRegion.split(appearance, appearance.getWidth() / 6,
				appearance.getHeight() / 12);
		
		ballTypeMap.put("Baseball", tmp[4][0]);
		ballTypeMap.put("Basketball", tmp[1][0]);
		ballTypeMap.put("Beachball", tmp[5][0]);
		ballTypeMap.put("BearingSteel", tmp[3][0]);
		ballTypeMap.put("Bowling", tmp[0][0]);
		ballTypeMap.put("Golfball", tmp[10][0]);
		ballTypeMap.put("Marble", tmp[6][0]);
		ballTypeMap.put("PingPong", tmp[2][0]);
		ballTypeMap.put("Poolball", tmp[8][0]);
		ballTypeMap.put("Soccerball", tmp[7][0]);
		ballTypeMap.put("Tennisball", tmp[9][0]);
		ballTypeMap.put("Blackball", tmp[11][0]);
	}
	
	private void loadFireAnimation()
	{
		Texture fire = new Texture(
				Gdx.files
						.internal("img/spritesheet/Fireball.png"));
		TextureRegion[][] tmp = TextureRegion.split(fire, fire.getWidth() / 12,
				fire.getHeight() / 4);
		TextureRegion[] fireFrames = new TextureRegion[12 * 4];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 12; j++) {
				fireFrames[index++] = tmp[i][j];
			}
		}
		fireAnimation = new Animation(0.025f, fireFrames);
	}
	
	private void loadSmokeAnimation()
	{
		Texture fire = new Texture(
				Gdx.files
						.internal("img/spritesheet/Smoke.png"));
		TextureRegion[][] tmp = TextureRegion.split(fire, fire.getWidth() / 15,
				fire.getHeight() / 6);
		TextureRegion[] smokeFrames = new TextureRegion[15 * 6 - 4];
		int index = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 15; j++) {
				smokeFrames[index++] = tmp[i][j];
				if(index == smokeFrames.length)
					break;
			}
		}
		smokeAnimation = new Animation(0.025f, smokeFrames);
	}
	private void setRotationAndScale() {

		if (heroMover.speedXPerSecond == 0) {
			if (heroMover.speedYPerSecond > 0)
				fireEffect.setRotation((float) (-180));
			else if (heroMover.speedYPerSecond < 0)
				fireEffect.setRotation(0);
		}

		else if (heroMover.speedYPerSecond == 0) {

			if (g.i().gameMode == 'R') {
				if (heroMover.speedXPerSecond > 0)
					fireEffect.setRotation((float) (40 * Math
							.atan(heroMover.speedXPerSecond / 200)));
				else if (heroMover.speedXPerSecond < 0)
					fireEffect.setRotation((float) (40 * Math
							.atan(heroMover.speedXPerSecond / 200)));
			} else {
				if (heroMover.speedXPerSecond > 0)
					fireEffect.setRotation((float) (90));
				else if (heroMover.speedXPerSecond < 0)
					fireEffect.setRotation((float) (-90));
			}
		}

		else if (heroMover.speedXPerSecond * heroMover.speedYPerSecond > 0) {
			if (heroMover.speedXPerSecond > 0)
				fireEffect.setRotation((float) (90 + Math
						.atan(heroMover.speedYPerSecond
								/ heroMover.speedXPerSecond)
						/ Math.PI * 180));
			else
				fireEffect.setRotation((float) (-(90 - Math
						.atan(heroMover.speedYPerSecond
								/ heroMover.speedXPerSecond)
						/ Math.PI * 180)));

		} else if (heroMover.speedXPerSecond * heroMover.speedYPerSecond < 0) {

			if (heroMover.speedXPerSecond > 0)
				fireEffect.setRotation((float) (90 + Math
						.atan(heroMover.speedYPerSecond
								/ heroMover.speedXPerSecond)
						/ Math.PI * 180));
			else
				fireEffect.setRotation((float) (-90 + Math
						.atan(heroMover.speedYPerSecond
								/ heroMover.speedXPerSecond)
						/ Math.PI * 180));
		}
		
		smokeEffect.setRotation(fireEffect.getRotation());
		
		scaleFactor = Math.max((float) Math.hypot(heroMover.speedXPerSecond, heroMover.speedYPerSecond)/300,1f);
		fireEffect.setScale(1f, scaleFactor);
		smokeEffect.setScale(1f, scaleFactor);
	}

	private void setSpriteBounds()
	{
		if(heroSprite != null){
		heroSprite.setBounds(0, 32, ballWidth * camera.zoom, ballHeight * camera.zoom);
		heroSprite.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);}
		setHeight(heroSprite.getHeight() * camera.zoom);
		setWidth(heroSprite.getWidth() * camera.zoom);
		if(fireEffect != null){
		fireEffect.setBounds(0, 32, ballWidth * camera.zoom,
				ballHeight * fireEffect.getHeight() / fireEffect.getWidth()
						* camera.zoom);
		fireEffect.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);}
		if(smokeEffect != null){
		smokeEffect.setBounds(0, 32, ballWidth * camera.zoom,
				ballHeight * smokeEffect.getHeight() / smokeEffect.getWidth()
						* camera.zoom);
		smokeEffect.setOrigin(heroSprite.getWidth() / 2,
				heroSprite.getHeight() / 2);
		}
	}
}
