package com.touchspin.td;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Balls extends GameThing {
	
	public OrthographicCamera camera;
	public MoverBall ballMover;

	private Map<String, TextureRegion> ballTypeMap = new HashMap<String, TextureRegion>();
	private Animation fireAnimation;
	private Animation smokeAnimation;
	private TextureRegion currentFireFrame;
	private TextureRegion currentSmokeFrame;
	private float scaleFactor;

	private int frameCount = 0;
	private Sprite ballSprite;
	private Sprite fireEffect;
	private Sprite smokeEffect;
	public boolean flammable;
	public boolean fireOn;
	public float radius;
	private float countTime;
	public String ballType;
	private boolean active;
	public float dyInWater;
	public float mass;
	public float friction;
	public int health;
	public Balls(String ballType, float x, float y,OrthographicCamera camera,TiledMapWrapper tiledMapWrapper)
	{
		active = true;
		this.camera = camera;
		this.tiledMapWrapper = tiledMapWrapper;
		health = 100;
		ballMover = new MoverBall(this);
		
		ballSprite = new Sprite();

		loadBallType();
		this.ballType = ballType; 
		changeBall(ballType);
		
		ballSprite.setBounds(0, 32, radius*2 * camera.zoom, radius * 2
				* camera.zoom);
		ballSprite.setOrigin(ballSprite.getWidth() / 2,
				ballSprite.getHeight() / 2);

		setHeight(ballSprite.getHeight() * camera.zoom);
		setWidth(ballSprite.getWidth() * camera.zoom);
		setX(x);
		setY(y);

		scaleFactor = 1f;

		// read in file animation
		loadFireAnimation();
		stateTime = 0f;
		currentFireFrame = fireAnimation.getKeyFrame(stateTime, true);

		loadSmokeAnimation();
		currentSmokeFrame = smokeAnimation.getKeyFrame(stateTime, true);

		fireEffect = new Sprite(currentFireFrame);
		fireEffect.setBounds(0, 32, radius*2 * camera.zoom, radius*2
				* fireEffect.getHeight() / fireEffect.getWidth() * camera.zoom);
		fireEffect.setOrigin(ballSprite.getWidth() / 2,
				ballSprite.getHeight() / 2);

		smokeEffect = new Sprite(currentSmokeFrame);
		smokeEffect.setBounds(0, 32, radius*2 * camera.zoom, radius*2
				* smokeEffect.getHeight() / smokeEffect.getWidth()
				* camera.zoom);
		smokeEffect.setOrigin(ballSprite.getWidth() / 2,
				ballSprite.getHeight() / 2);

		
		}
	public void draw(Batch batch, float alpha) {
		if(active){
			batch.setProjectionMatrix(camera.combined);
			if (frameCount == 60) {
				ballSprite.setColor(Color.RED);
			} else if (frameCount == 1) {
				ballSprite.setColor(Color.WHITE);
			}
			ballSprite.draw(batch);
			if (fireOn) {
				drawEffect(batch);
			}
		}
	}

	private void drawEffect(Batch batch) {
		// batch.draw(currentFrame,getX(),getY(),32f,currentFrame.getRegionHeight()*32/currentFrame.getRegionWidth());
		smokeEffect.draw(batch);
		fireEffect.draw(batch);
	}

	@Override
	public void act(float delta) {
		if(active)
		{
		g.i().currentBall = this;
		ballMover.move(this);

		// Attack
		if (frameCount > 1)
			frameCount--;
		// position
		setSpritesPosition();

		// Rotation
		ballSprite.rotate(360 * (ballMover.previousX - getX())
				/ ((float) Math.PI * ballSprite.getRegionHeight()));
		setRotationAndScale();

		// animation
		stateTime += Gdx.graphics.getDeltaTime();

		currentFireFrame = fireAnimation.getKeyFrame(stateTime, true);
		fireEffect.setRegion(currentFireFrame);

		currentSmokeFrame = smokeAnimation.getKeyFrame(stateTime, true);
		smokeEffect.setRegion(currentSmokeFrame);

		if (fireOn) {
			countTime += Gdx.graphics.getDeltaTime();
			if (countTime > 2) {
				health -= 5;
				getHurt();
				countTime = 0;
			}
		}

		if (health < 0) {
			active = false;
		}

		g.i().currentBall = null;
		}
	}

	public void getHurt() {
		frameCount = 20;
	}

	public void changeBall(String type) {

		switch (type) {
		case "PingPong":
			friction = 0.008f;
			flammable = true;
			radius = 2.9f;
			break;
		case "Bowling":
			friction = 0.03f;
			radius = 15f;
			flammable = false;
			fireOn = false;
			break;
		case "Basket":
			friction = 0.011f;
			radius = 15f;
			flammable = true;
			break;
		case "Base":
			friction = 0.008f;
			radius = 11.08f;
			flammable = true;
			break;
		case "Tennis":
			friction = 0.008f;
			radius = 11.08f;
			flammable = true;
			break;
		}
		ballType = type;
		ballSprite.setRegion(ballTypeMap.get(type));
		setSpriteBounds();
		calcualteDyInWater();
	}

	public void igniteBall(boolean fireOn) {
		this.fireOn = fireOn;
	}

	public void changeBallX(float speed) {
		ballMover.speedXPerSecond = speed;
	}

	public void changeBallY(float speed) {
		ballMover.speedYPerSecond = speed;
	}

	public float getYSpeed() {
		return ballMover.speedYPerSecond;
	}

	public float getXSpeed() {
		return ballMover.speedXPerSecond;
	}

	@Override
	public void setPosition(float x, float y) {
		setX(x);
		setY(y);

		camera.position.x = getX() + getWidth() / 2;
		camera.position.y = getY() + getHeight() / 2;
		if (camera.position.x - camera.viewportWidth / 2 < 0)
			camera.position.x = camera.viewportWidth / 2;
		else if(camera.position.x + camera.viewportWidth / 2 > tiledMapWrapper.getPixelWidth())
			camera.position.x = tiledMapWrapper.getPixelWidth() 
			- camera.viewportWidth / 2;
		if (camera.position.x - camera.viewportWidth / 2 < 0)
			camera.position.x = camera.viewportWidth / 2;
		else if (camera.position.y + camera.viewportHeight > tiledMapWrapper
				.getPixelHeight())
			camera.position.y = tiledMapWrapper.getPixelHeight()
					- camera.viewportHeight / 2;
	}

	public Vector2 getCenter()
	{
		Vector2 center = new Vector2(getX()+getWidth()/2,getY()+getHeight()/2);
		return center;
		
	}
	// --------------Private helper
	// method------------------------------------------
	private void setSpritesPosition() {
		ballSprite.setX(getX());
		ballSprite.setY(getY());
		fireEffect.setX(getX());
		fireEffect.setY(getY());
		smokeEffect.setX(getX());
		smokeEffect.setY(getY());
	}

	private void loadBallType() {
		Texture appearance = new Texture("img/spritesheet/BallSquish.png");
		TextureRegion[][] tmp = TextureRegion.split(appearance,
				appearance.getWidth() / 6, appearance.getHeight() / 12);

		ballTypeMap.put("Base", tmp[4][0]);
		ballTypeMap.put("Basket", tmp[1][0]);
		ballTypeMap.put("Beachball", tmp[5][0]);
		ballTypeMap.put("BearingSteel", tmp[3][0]);
		ballTypeMap.put("Bowling", tmp[0][0]);
		ballTypeMap.put("Golfball", tmp[10][0]);
		ballTypeMap.put("Marble", tmp[6][0]);
		ballTypeMap.put("PingPong", tmp[2][0]);
		ballTypeMap.put("Poolball", tmp[8][0]);
		ballTypeMap.put("Soccerball", tmp[7][0]);
		ballTypeMap.put("Tennis", tmp[9][0]);
		ballTypeMap.put("Blackball", tmp[11][0]);
	}

	private void loadFireAnimation() {
		Texture fire = new Texture(
				Gdx.files.internal("img/spritesheet/Fireball.png"));
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

	private void loadSmokeAnimation() {
		Texture fire = new Texture(
				Gdx.files.internal("img/spritesheet/Smoke.png"));
		TextureRegion[][] tmp = TextureRegion.split(fire, fire.getWidth() / 15,
				fire.getHeight() / 6);
		TextureRegion[] smokeFrames = new TextureRegion[15 * 6 - 4];
		int index = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 15; j++) {
				smokeFrames[index++] = tmp[i][j];
				if (index == smokeFrames.length)
					break;
			}
		}
		smokeAnimation = new Animation(0.025f, smokeFrames);
	}

	private void setRotationAndScale() {

		if (ballMover.speedXPerSecond == 0) {
			if (ballMover.speedYPerSecond > 0)
				fireEffect.setRotation((float) (-180));
			else if (ballMover.speedYPerSecond < 0)
				fireEffect.setRotation(0);
		}

		else if (ballMover.speedYPerSecond == 0) {

			if (g.i().gameMode == 'R') {
				if (ballMover.speedXPerSecond > 0)
					fireEffect.setRotation((float) (40 * Math
							.atan(ballMover.speedXPerSecond / 200)));
				else if (ballMover.speedXPerSecond < 0)
					fireEffect.setRotation((float) (40 * Math
							.atan(ballMover.speedXPerSecond / 200)));
			} else {
				if (ballMover.speedXPerSecond > 0)
					fireEffect.setRotation((float) (90));
				else if (ballMover.speedXPerSecond < 0)
					fireEffect.setRotation((float) (-90));
			}
		}

		else if (ballMover.speedXPerSecond * ballMover.speedYPerSecond > 0) {
			if (ballMover.speedXPerSecond > 0)
				fireEffect.setRotation((float) (90 + Math
						.atan(ballMover.speedYPerSecond
								/ ballMover.speedXPerSecond)
						/ Math.PI * 180));
			else
				fireEffect.setRotation((float) (-(90 - Math
						.atan(ballMover.speedYPerSecond
								/ ballMover.speedXPerSecond)
						/ Math.PI * 180)));

		} else if (ballMover.speedXPerSecond * ballMover.speedYPerSecond < 0) {

			if (ballMover.speedXPerSecond > 0)
				fireEffect.setRotation((float) (90 + Math
						.atan(ballMover.speedYPerSecond
								/ ballMover.speedXPerSecond)
						/ Math.PI * 180));
			else
				fireEffect.setRotation((float) (-90 + Math
						.atan(ballMover.speedYPerSecond
								/ ballMover.speedXPerSecond)
						/ Math.PI * 180));
		}

		smokeEffect.setRotation(fireEffect.getRotation());

		scaleFactor = Math.max((float) Math.hypot(ballMover.speedXPerSecond,
				ballMover.speedYPerSecond) / 300, 1f);
		fireEffect.setScale(1f, scaleFactor);
		smokeEffect.setScale(1f, scaleFactor);
	}

	private void setSpriteBounds() {
		if (ballSprite != null) {
			ballSprite.setBounds(0, 32, radius* 2 * camera.zoom, radius*2
					* camera.zoom);
			ballSprite.setOrigin(ballSprite.getWidth() / 2,
					ballSprite.getHeight() / 2);
		}
		setHeight(ballSprite.getHeight() * camera.zoom);
		setWidth(ballSprite.getWidth() * camera.zoom);
		if (fireEffect != null) {
			fireEffect.setBounds(0, 32, radius*2 * camera.zoom, radius*2
					* fireEffect.getHeight() / fireEffect.getWidth()
					* camera.zoom);
			fireEffect.setOrigin(ballSprite.getWidth() / 2,
					ballSprite.getHeight() / 2);
		}
		if (smokeEffect != null) {
			smokeEffect.setBounds(0, 32, radius*2 * camera.zoom, radius*2
					* smokeEffect.getHeight() / smokeEffect.getWidth()
					* camera.zoom);
			smokeEffect.setOrigin(ballSprite.getWidth() / 2,
					ballSprite.getHeight() / 2);
		}
	}

	private void calcualteDyInWater() {
		float radius = 0;
		float floatforce = 0;
		int inWaterdyFactor = 1;
		switch (ballType) {
		case "PingPong":
			mass = 0.0027f;
			radius = 0.02f;
			break;
		case "Bowling":
			mass = 7.3f;
			radius = 0.09f;
			break;
		case "Basket":
			//mass = 0.62f;
			mass = 2.62f;
			radius = 0.121f;
			break;
		case "Base":
			mass = 0.145f;
			radius = 0.0382f;
			break;
		case "Tennis":
			mass = 0.06f;
			radius = 0.0335f;
			break;
		}	
		floatforce = (float) (1000*9.8f * 4/3*Math.PI*Math.pow(radius, 3));
		float force = (floatforce + (-9.8f * mass))*inWaterdyFactor;
		float dy = force/mass;
		dyInWater = dy;
	}
}



