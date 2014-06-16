package com.touchspin.td;

import com.badlogic.gdx.scenes.scene2d.Actor;

/* ======================================================================================
 * File:			GameThing.java
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
 * This is an abstract class used as the base class of map objects
 * 
 * ======================================================================================
 */

public abstract class GameThing extends Actor {
	public TiledMapWrapper tiledMapWrapper;
	protected float stateTime;
	/**
	 * Change the loction of this object to the location of the other object
	 * @param dest - the destination object 
	 */
	public void changeLocation(GameThing dest)
	{
		setX(dest.getX());
		setY(dest.getY());
	}
}
