package com.touchspin.td;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Stage;
/* ======================================================================================
 * File:			MainGame.java
 * Authors:			Brian Adams - b.adams5736@edmail.edcc.edu
 * 					Russell Brendel - russell.brendel.2925@edmail.edcc.edu
 * 					Damian Forrester - dforrester777@gmail.com
 * 					Wendi Tang - w.tang2404@myedmail.edcc.edu
 * 
 * Organization:	Edmonds Community College
 * Term:			Spring 2014
 * Class:			CS 185 - Game Project Developement
 * Instructor:		Tim Hunt - thunt@edcc.edu
 * 
 * Project:			Ollie
 * --------------------------------------------------------------------------------------
 * 
 * This class holds the basic cycle of the game
 * 
 * ======================================================================================
 */
public class MainGame extends Game {
	int screenXorg;
	int screenYorg;
	Stage stage;

	@Override
	/**
	 * Initialization on creation
	 */
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
		setScreen(new ScreenSplash());

	}

	/**
	 * update method
	 */
	public void update() {
		stage.act();
	};
	/**
	 * draw method
	 */
	public void draw() {
		super.render();
	};

	/**
	 * Render the game world including updating and drawing
	 */
	@Override
	public void render() {
			update();
			draw();
	}

	@Override
	/**
	 * This method will be called when the game is resized
	 */
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * This method will be called when the game is paused
	 */
	@Override
	public void pause() {

	}
	/**
	 * This method will be called when the game is resumed
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * Dispose the game
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}