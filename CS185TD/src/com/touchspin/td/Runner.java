package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
		tiledMapWrapper.renderMap();
		stage.draw();
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
				foregroudCamera.position.x - foregroudCamera.viewportWidth
						- 1, -1, foregroudCamera.viewportWidth * 2 + 2,
						foregroudCamera.viewportHeight + 2);
	}
}
