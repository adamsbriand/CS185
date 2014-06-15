package com.touchspin.td;
/* ======================================================================================
 * File:			Mover.java
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
 * This is a base abstract class for movers.
 * 
 * ======================================================================================
 */
public abstract class Mover {
	GameThing gameThing;
	float speedXPerSecond;
	float speedYPerSecond;
	public abstract void move(GameThing gameThing);
}
