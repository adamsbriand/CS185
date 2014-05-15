package com.touchspin.td;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainGame extends Game {
	int screenXorg;
	int screenYorg;
	Stage stage;
	InputAnonymizer anonymizer;

	@Override
	public void create() {

		anonymizer = new InputAnonymizer();
		stage = new Stage();
		stage.addActor(anonymizer);
		MessageScreen messageScreen = new MessageScreen(this);
		setScreen(messageScreen);

	}

	public void update() {
		stage.act();
	};

	public void draw() {

	};

	@Override
	public void render() {

		super.render();
		// gameObj.render();

	}

	@Override
	public void resize(int width, int height) {
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