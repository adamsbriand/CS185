package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Class used to handle Maze aspect of game
 * @author KingD777
 *
 */
public class Maze extends GameObject {

	HeroMazeExplorer mazeExplorer;
	Stage stage;
	MainGame game;

	public Maze(MainGame game) 
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);       
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		this.game = game;
		tiledMapWrapper = new TiledMapWrapper("maps/Maze1.tmx");
		
		camera = new OrthographicCamera();
		
		camera.setToOrtho(false,w,h);
		
		//camera.setToOrtho(false, w * tiledMapWrapper.getPixelWidth() / h,
			//	tiledMapWrapper.getPixelHeight());
		camera.update();
		
		stage = new Stage();
		mazeExplorer = new HeroMazeExplorer(camera, tiledMapWrapper);
		stage.addActor(mazeExplorer);		
	}

	@Override
	public void update() 
	{
		stage.act();
		camera.update();
		cameraTranslate(0, 0);
		
		// render the map from 1 pixel before the left of the camera to 1 pixel
		// after
		// the right of the map.
		tiledMapWrapper.setForegroundView(camera);
		
	}

	@Override
	public void draw() {
		tiledMapWrapper.renderMap();
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
