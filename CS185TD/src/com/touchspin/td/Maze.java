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

	Hero hero;
	Stage stage;
	MainGame game;	

	public Maze(MainGame game, String mapPath) 
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);     
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();		
		this.game = game;
		
		tiledMapWrapper = new TiledMapWrapper("maps/Maze1.tmx");
		
		camera = new OrthographicCamera(); 
		camera.setToOrtho(false,400, 400 * h/w);
		camera.update();
		
		stage = new Stage();
		hero = new Hero(camera, tiledMapWrapper);
		stage.addActor(hero);		
	}

	@Override
	public void update() 
	{
		float tempX = hero.getX();
		float tempY = hero.getY();
		stage.act();
		camera.update();
		cameraTranslate(hero.getX() - tempX,  hero.getY() - tempY);
		
		//tiledMapWrapper.setForegroundView(camera);
		tiledMapWrapper.setForegroundView(camera.combined,
				camera.position.x - camera.viewportWidth/2 - 1, 
				camera.position.y - camera.viewportHeight/2 - 1,
				camera.viewportWidth+ 2, camera.viewportHeight+2);
		
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
		if (hero.getX() >= camera.viewportWidth / 2
				&& hero.getX() + camera.viewportWidth / 2 <= tiledMapWrapper
						.getPixelWidth())
			camera.translate(x, 0);
		if (hero.getY() >= camera.viewportHeight / 2
				&& hero.getY() + camera.viewportHeight / 2 <= tiledMapWrapper
				.getPixelHeight())
			camera.translate(0, y);
	}

}
