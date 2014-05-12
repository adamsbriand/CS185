package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Maze extends GameObject {

	MyMazeExplorer mazeExplorer;
	Stage stage;
	MainGame game;

	public Maze(MainGame game) {

		this.game = game;
		tiledMapWrapper = new TiledMapWrapper("mapname");
		camera = new OrthographicCamera();
		camera.update();
		stage = new Stage();
		mazeExplorer = new MyMazeExplorer(camera, tiledMapWrapper);
		stage.addActor(mazeExplorer);

		anonymizer = new MazeInputAnonymizer() {

			@Override
			public boolean attack() {
				// TODO Auto-generated method stub
				return false;
			}


			@Override
			public boolean dash() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean pause() {
				// TODO Auto-generated method stub
				return false;
			}


			@Override
			public Vector2 direction(int x, int y) {
				// TODO Auto-generated method stub
				return null;
			}


			@Override
			public int speed(int speed) {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}

	@Override
	public void update() {
		stage.act();
		camera.update();
	}

	@Override
	public void draw() {
		tiledMapWrapper.getTiledMapRenderer().render();
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

}
