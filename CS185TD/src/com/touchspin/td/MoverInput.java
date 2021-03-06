package com.touchspin.td;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
/* ======================================================================================
 * File:			MoverInput.java
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
 * This class handles the input movement for player.
 * 
 * ======================================================================================
 */
public class MoverInput extends MoverPhysics {
	private HashSet<NP> prev = new HashSet<NP>();
	private boolean jumpable = true;
	private int frameCount = 30;
	Vector2 speed = new Vector2();

	/**
	 * Default constructor
	 */
	public MoverInput() {
		super();
	}
	/**
	 * Set the position of player based on the movement
	 * Also trigger objects within range
	 */
	@Override
	public void move(GameThing gameThing) {
		this.gameThing = gameThing;

		// Save the previous position
		previousX = gameThing.getX();
		previousY = gameThing.getY();
		// Try to move

		inputMove();
		physicsMove();
		// If movement is failed, set the position of the
		// actor to previous position

		gameThing.setX(gameThing.getX() + Gdx.graphics.getDeltaTime()
				* speedXPerSecond);
		if (!isXFree()) {
			if (speedXPerSecond < -100 || speedXPerSecond > 100)
				g.i().sound.Bounce();
			gameThing.setX(previousX);
			speedXPerSecond = -0.5f * speedXPerSecond;
		}

		gameThing.setY(gameThing.getY() + Gdx.graphics.getDeltaTime()
				* speedYPerSecond);
		if (!isYFree()) {
			if (speedYPerSecond < 0)
			{
				frameCount = 30;
				jumpable = true;
			}
			if (speedYPerSecond < -100 || speedYPerSecond > 100)
				g.i().sound.Bounce();
			gameThing.setY(previousY);
			speedYPerSecond = -0.5f * speedYPerSecond;
		}else{
			if(frameCount != 0)
			frameCount--;
			if(frameCount == 0)
			jumpable = false;
		}

		//--------trigger objects----------
		if (triggeredNP.size() != 0) {
			for (NP object : triggeredNP) {
				if (!prev.contains(object) && object.active) {
					g.i().t.action(object.conditions, object.action);
				}
			}
			prev.clear();
			for (NP object : triggeredNP) {
				prev.add(object);
			}
			triggeredNP.clear();
		} else {
			prev.clear();
		}
		
		if(waterList!=null && isInWater)
		{
			for(NP water : waterList)
			{
				if(gameThing.getY()+gameThing.getHeight()/2 > water.getY() + water.getHeight() - 10)
				jumpable = true;
			}
		}

	}

	/**
	 * Do the input move
	 */
	private void inputMove() {

		accelerationX = g.i().leAnonymizer.tiltSpeed.x;
		g.i().leAnonymizer.tiltSpeed.x = 0;

		if (g.i().gameMode == 'M') {
			accelerationY = g.i().leAnonymizer.tiltSpeed.y;
			g.i().leAnonymizer.tiltSpeed.y = 0;
		}

		else if (g.i().gameMode == 'R') {
			if (jumpable) {
				if (g.i().leAnonymizer.jump) {
					speedYPerSecond = 400;
					jumpable = false;
					g.i().leAnonymizer.jump = false;
				}
			} else
				g.i().leAnonymizer.jump = false;
		}
	}
	
}
