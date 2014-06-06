package com.touchspin.td;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainGame extends Game {
	int screenXorg;
	int screenYorg;
	Stage stage;

	@Override
	public void create() {
		//initialize variable left in g class
		g.i().SetDefaults(this);
		g.i().leAnonymizer = new InputAnonymizer();
		g.i().sound = new Sounds();
		g.i().t = new Trigger(this);
		g.i().currentBallType = "Base";
		g.i().fire = false;
		g.i().playerHealth = 100;
		
		stage = new Stage();
		stage.addActor(g.i().leAnonymizer);
		setScreen(new ScreenSplash(this));

	}

	public void update() {
		stage.act();
	};

	public void draw() {
		super.render();
	};

	@Override
	public void render() {
			update();
			draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {

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