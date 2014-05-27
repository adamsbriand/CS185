package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Runner extends GameObject {

	Hero hero;
	Stage stage;
	MainGame game;
	private OrthographicCamera backGroundCamera;
	private OrthographicCamera foregroudCamera;

	public Runner(MainGame game, String mapPath) {

		this.game = game;

		tiledMapWrapper = new TiledMapWrapper(mapPath);
		setUpCamera();
		stage = new Stage();
		// anonymizer = game.anonymizer;
		hero = new Hero(camera, tiledMapWrapper);
		stage.addActor(hero);
		loadNPs();
	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	@Override
	public void update() {
		float tempX = hero.getX();
		float tempY = hero.getY();
		stage.act();
		camera.update();
		backGroundCamera.update();
		foregroudCamera.update();

		cameraTranslate(hero.getX() - tempX, hero.getY() - tempY);
		setView();

	}

	@Override
	public void draw() {
		tiledMapWrapper.renderBackground();
		tiledMapWrapper.renderPlayerlayer();
		stage.draw();
		tiledMapWrapper.renderForeground();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

	}

	/**
	 * Damian: Camera doesnt follow the user if the user rolls backwards
	 * 
	 * @param x
	 * @param y
	 */
	private void cameraTranslate(float x, float y) {
		if (hero.getX() >= camera.viewportWidth / 2
				&& hero.getX() + camera.viewportWidth / 2 <= tiledMapWrapper
						.getPixelWidth())
			camera.translate(x, 0);
		if (hero.getY() >= camera.viewportHeight / 2
				&& hero.getY() + camera.viewportHeight / 2 <= tiledMapWrapper
						.getPixelHeight())
			camera.translate(0, y);
	}

	private void setBackGroundCameraView() {
		backGroundCamera.position.x = tiledMapWrapper.backgroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ backGroundCamera.viewportWidth / 2;
		backGroundCamera.position.y = tiledMapWrapper.backgroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ backGroundCamera.viewportHeight / 2;
	}

	private void setForegroundCameraView() {
		foregroudCamera.position.x = tiledMapWrapper.foregroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ foregroudCamera.viewportWidth / 2;
		foregroudCamera.position.y = tiledMapWrapper.foregroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ foregroudCamera.viewportHeight / 2;
	}

	private void setUpCamera()

	{
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight() / h,
				tiledMapWrapper.getPixelHeight());
		camera.update();

		backGroundCamera = new OrthographicCamera();
		backGroundCamera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight()
				/ h, tiledMapWrapper.getPixelHeight());
		backGroundCamera.update();

		foregroudCamera = new OrthographicCamera();
		foregroudCamera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight()
				/ h, tiledMapWrapper.getPixelHeight());
		foregroudCamera.update();
	}

	private void setView() {
		tiledMapWrapper.setPlayerLayerView(camera.combined, camera.position.x
				- camera.viewportWidth - 1, -1, camera.viewportWidth * 2 + 2,
				camera.viewportHeight + 2);
		// render the background base one the position of background camera
		setBackGroundCameraView();
		tiledMapWrapper.setBackGroundView(backGroundCamera.combined,
				backGroundCamera.position.x - backGroundCamera.viewportWidth
						- 1, -1, backGroundCamera.viewportWidth * 2 + 2,
				backGroundCamera.viewportHeight + 2);
		setForegroundCameraView();
		tiledMapWrapper.setForegroundView(foregroudCamera.combined,
				foregroudCamera.position.x - foregroudCamera.viewportWidth - 1,
				-1, foregroudCamera.viewportWidth * 2 + 2,
				foregroudCamera.viewportHeight + 2);
	}

	private void loadNPs() {
		NP temp;
		int startX = 0;
		int startY = 0;
		int width = 0;
		int height = 0;
		String name = "";
		String type = "";
		String conditions = "";
		String action = "";
		String anims = "";
		int roamingRadius = 0;
		String spriteSheet = "";
		String collisionParameter = "";
		int animRows = 0;
		int animCols = 0;
		boolean collidable = false;
		MapProperties tempProperties;

		for (MapObject object : tiledMapWrapper.npObjects) {
			tempProperties = object.getProperties();

			if (tempProperties.get("x") != null) {
				startX = MathUtils.round((float)tempProperties.get("x"));
			}

			if (tempProperties.get("y") != null) {
				startY = MathUtils.round((float)tempProperties.get("y"));
			}

			if(object instanceof RectangleMapObject )
			{
				width = (int) ((RectangleMapObject)object).getRectangle().width;
				height = (int) ((RectangleMapObject)object).getRectangle().height;
			}
				else{
			width = 32;
			height = 32;
			}
			
			if (tempProperties.get("name") != null) {
				name = (String) tempProperties.get("name");
			}
			
			if (tempProperties.get("type") != null) {
				type = (String) tempProperties.get("type");
			}
			
			if (tempProperties.get("conditions") != null) {
				conditions = (String) tempProperties.get("conditions");
			}
			
			if (tempProperties.get("actions") != null) {
				action = (String) tempProperties.get("actions");
			}
			
			if (tempProperties.get("animations") != null) {
				anims = (String) tempProperties.get("animations");
			}
			
			if(tempProperties.get("roamingRadius") != null)
			{
				roamingRadius = Integer.parseInt((String) tempProperties.get("roamingRadius"));
			}
			
			if(tempProperties.get("spriteSheet") != null)
			{
				spriteSheet = (String)tempProperties.get("spriteSheet");
			}
			
			if(tempProperties.get("collidable") != null)
			{
				collidable = Boolean.parseBoolean((String)tempProperties.get("collidable"));
			}
			
			if(tempProperties.get("collisionParams") != null)
			{
				collisionParameter = (String)tempProperties.get("collisionParams");
			}
			
			if(spriteSheet.equalsIgnoreCase("transmorgifier.png"))
			{
				animRows = 5;
				animCols = 5;
			}
			else if (spriteSheet.equalsIgnoreCase("GlassBreak.png"))
			{
				animRows = 5;
				animCols = 5;
			}
			else if(spriteSheet.equalsIgnoreCase("LightSwitch.png"))
			{
				animRows = 4;
				animCols = 5;
			}
			else if (spriteSheet.equalsIgnoreCase("FlameWall.png"))
			{
				animRows = 12;
				animCols = 4;
			}
			else if (spriteSheet.equalsIgnoreCase("doorOpen.png"))
			{
				animRows = 6;
				animCols = 4;
			}
			
			if(!spriteSheet.equalsIgnoreCase(""))
			spriteSheet = "data/"+ spriteSheet;
			
			temp = new NP(startX,startY, width, height, name,
			type, conditions, action, anims,
			roamingRadius, spriteSheet, animRows, animCols,
			collidable, collisionParameter);
			
			startX = 0;
			startY = 0;
			width = 0;
			height = 0;
			name = "";
			type = "";
			conditions = "";
			action = "";
			anims = "";
			roamingRadius = 0;
			spriteSheet = "";
			collisionParameter = "";
			animRows = 0;
			animCols = 0;
			collidable = false;
			
			stage.addActor(temp);
		}
	}
}
