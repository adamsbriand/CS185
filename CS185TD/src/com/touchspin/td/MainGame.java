package com.touchspin.td;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainGame extends Game {
	int screenXorg;
	int screenYorg;
	Stage stage;
	//InputAnonymizer anonymizer;

	@Override
	public void create() {
		//initialize variable left in g class
		switch (Gdx.app.getType()) { // Get platform
		case Android:
			g.i().SetControls('A');
			break;
		case Desktop:
			g.i().SetControls('D');
		default:
			g.i().SetControls('D');
			break;
		}
		g.i().leAnonymizer = new InputAnonymizer();
		
		//anonymizer = g.i().leAnonymizer;
		stage = new Stage();
		stage.addActor(g.i().leAnonymizer);
		//stage.addActor(anonymizer);
		MessageScreen messageScreen = new MessageScreen(this);
		setScreen(messageScreen);

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