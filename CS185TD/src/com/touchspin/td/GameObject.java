package com.touchspin.td;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
/* ======================================================================================
 * File:			GameObject.java
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
 * This is the base class of the screens used in this game
 * 
 * ======================================================================================
 */
public abstract class GameObject implements Screen{

	protected TiledMapWrapper tiledMapWrapper;
	protected OrthographicCamera camera;
	protected InputAnonymizer anonymizer;
    /**
     * Update objects
     */
    public abstract void update();
    
    /**
     * Draw objects
     */
	public abstract void draw();
	
	/**
	 * render the map 
	 */
	@Override
	public abstract void render(float delta);
	@Override
	/**
	 * The function is called when a resize is performed
	 */
	public abstract void resize(int width, int height);
	@Override
	/**
	 * Show this screen
	 */
	public abstract void show();
	/**
	 * When the main game set to another screen 
	 * This function is called.
	 */
	@Override
	public abstract void hide();
	@Override
	/**
	 * This method is called when paused
	 */
	public abstract void pause();
	@Override
	/**
	 * This method is called when resume
	 */
	public abstract void resume();
	/**
	 * Dispose the screen
	 */
	@Override
	public abstract void dispose();
}
