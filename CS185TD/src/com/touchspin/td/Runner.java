package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Runner extends GameObject {

	HeroRunner runner;
	Stage stage;
	MainGame game;

	public Runner(MainGame game) {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		this.game = game;
		
		tiledMapWrapper = new TiledMapWrapper("SideScroller1.tmx");

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight() / h,
				tiledMapWrapper.getPixelHeight());
		camera.update();
		stage = new Stage();
		runner = new HeroRunner(camera, tiledMapWrapper);
		stage.addActor(runner);

		anonymizer = game.anonymizer ;

	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	@Override
	public void update() {
		stage.act();
		camera.update();
		cameraTranslate(runner.getDistancePerFrameX(), 0);
		// render the map from 1 pixel before the left of the camera to 1 pixel
		// after
		// the right of the map.
		tiledMapWrapper.getTiledMapRenderer().setView(camera.combined,
				camera.position.x - camera.viewportWidth - 1, 0,
				camera.viewportWidth * 2 + 2, camera.viewportHeight);
		
		if(runner.isAtTheEndOfTheMap())
		{
			MessageScreen messageScreen = new MessageScreen(game);
			messageScreen.setMessage("Level Finshed.Click to start again");
			game.setScreen(messageScreen);
		}
		
		if(anonymizer.jump)
		{
			runner.jump(30 * 10);
			anonymizer.jump = false;
		}
		if(anonymizer.attack)
		{
			runner.attack();
			anonymizer.attack = false;
		}
	}

	@Override
	public void draw() {
		tiledMapWrapper.getTiledMapRenderer().render();
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
	
	private void cameraTranslate(float x, float y) {
		if (runner.getX() + runner.getWidth() / 2 > camera.position.x
				&& camera.position.x + x >= camera.viewportWidth / 2
				&& camera.position.x + x + camera.viewportWidth / 2 <= tiledMapWrapper
						.getPixelWidth())
			camera.translate(x, 0);
		if (camera.position.y + y >= camera.viewportHeight / 2
				&& camera.position.y + y + camera.viewportHeight / 2 <= tiledMapWrapper
						.getPixelHeight())
			camera.translate(0, y);
	}
	
	

}
