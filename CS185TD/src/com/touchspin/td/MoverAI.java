package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MoverAI extends MoverPhysics {
	/* ======================================================================================
	 * File:			MoverAI.java
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
	 * This class handles the movement for dragon
	 * 
	 * ======================================================================================
	 */
	private NP np;
	private RectangleMapObject collisionObject;
	private final float movingAcceleration;
	private final float movingDeceleration;
	private Vector2 path;
	private boolean active = false;

	/**
	 * The constructor
	 * @param np - the non player object to move
	 */
	public MoverAI(NP np) {
		movingAcceleration = 10;
		movingDeceleration = 25;
		path = new Vector2(0, 0);
		this.np = np;
		String[] tempCP = np.collisionParameter.split(",");
		if (tempCP.length == 4) {
			collisionObject = new RectangleMapObject(np.getX()
					+ Float.parseFloat(tempCP[0]), np.getY()
					+ Float.parseFloat(tempCP[1]), Float.parseFloat(tempCP[2]),
					Float.parseFloat(tempCP[3]));
			g.i().hud.tiledMapWrapper.collisionObjects.add(collisionObject);
			g.i().npMap.put(collisionObject, np);
		}
	}

	/**
	 * Calculate the AI path and move the object based on it. 
	 */
	@Override
	public void move(GameThing gameThing) {

		if (!active) {
			path.x = g.i().hero.getX() + g.i().hero.getWidth() / 2 - np.getX()
					- np.getWidth() / 2;
			path.y = g.i().hero.getY() + g.i().hero.getHeight() / 2 - np.getY()
					- np.getHeight() / 2;
			if (path.len() < 320)
			{
				active = true;
				g.i().sound.BGMusic("dragon");
				g.i().sound.npNoise();
			}
		} else {
			// Save the previous position
			previousX = this.np.getX();
			previousY = this.np.getY();
			// Try to move
			AIMove();

			this.np.setX(np.getX() + Gdx.graphics.getDeltaTime()
					* speedXPerSecond);
			this.np.setY(np.getY() + Gdx.graphics.getDeltaTime()
					* speedYPerSecond);
			collisionObject.getRectangle().setCenter(
					np.getX() + np.getWidth() / 2,
					np.getY() + np.getHeight() / 2);
		}

	}

	/**
	 * Calculate the movement path.
	 */
	public void AIMove() {
		path.x = g.i().hero.getX() + g.i().hero.getWidth() / 2 - np.getX()
				- np.getWidth() / 2;
		path.y = g.i().hero.getY() + g.i().hero.getHeight() / 2 - np.getY()
				- np.getHeight() / 2;

		if (path.x * accelerationX < 0) {
			accelerationX = movingDeceleration
					* MathUtils.cos(path.getAngleRad());
		} else {
			accelerationX = movingAcceleration
					* MathUtils.cos(path.getAngleRad());
		}
		if (path.y * accelerationY < 0) {
			accelerationY = movingDeceleration
					* MathUtils.sin(path.getAngleRad());
		} else {
			accelerationY = movingAcceleration
					* MathUtils.sin(path.getAngleRad());
		}

		fractionFactor = 0.003f;
		speedXPerSecond *= (1 - fractionFactor);
		speedYPerSecond *= (1 - fractionFactor);

		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
	}
}
