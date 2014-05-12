package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Maze extends GameObject {

	MyMazeExplorer mazeExplorer;
	Stage stage;

	float w = Gdx.graphics.getWidth();
	float h = Gdx.graphics.getHeight();

	public Maze() {

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
	public void render() {
		update();
		draw();
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

}
