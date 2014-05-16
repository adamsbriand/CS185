package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Maze extends GameObject {

	HeroMazeExplorer mazeExplorer;
	Stage stage;
	MainGame game;

	public Maze(MainGame game) {
		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		this.game = game;
		tiledMapWrapper = new TiledMapWrapper("Maze1.tmx");
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight() / h,
				tiledMapWrapper.getPixelHeight());
		camera.update();
		
		stage = new Stage();
		mazeExplorer = new HeroMazeExplorer(camera, tiledMapWrapper);
		stage.addActor(mazeExplorer);

		anonymizer = game.anonymizer;
		}

	@Override
	public void update() {
		stage.act();
		camera.update();
		cameraTranslate(mazeExplorer.getDistancePerFrameX(), 0);
	}

	@Override
	public void draw() {
		tiledMapWrapper.parallaxRender();
		stage.draw();
	}

	@Override
	public void render(float delta) {
		update();
		draw();
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}
	
	private void cameraTranslate(float x, float y) {
		if (mazeExplorer.getX() + mazeExplorer.getWidth() / 2 > camera.position.x
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
