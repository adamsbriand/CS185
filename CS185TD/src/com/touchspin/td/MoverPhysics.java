package com.touchspin.td;

import java.util.HashSet;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MoverPhysics extends Mover {
	float previousX;
	float previousY;
	float gravityPerSecond = -9.8F;
	float accelerationY = 0;
	float accelerationX = 0;
	float tileWidth;
	float tileHeight;
	float fractionFactor = 0.005F;;
	Vector2 circleCenter;
	float radius;
	RectangleMapObject temp;
	Rectangle rect;
	HashSet<NP> triggeredNP = new HashSet<NP>();
	protected NP water;
	boolean isInWater;

	public MoverPhysics() {
		speedXPerSecond = 0;
		speedYPerSecond = 0;
		water = getObjNamed("water");
	}

	public void move(GameThing gameThing) {
		this.gameThing = gameThing;
	}

	protected void physicsMove() {

		isInWater();
		
		fractionFactor = g.i().playerFriction;
		if (isInWater) {
			speedXPerSecond *= (1 - fractionFactor * 2);
			speedYPerSecond *= (1 - fractionFactor * 2);
		} else {
			speedXPerSecond *= (1 - fractionFactor);
			speedYPerSecond *= (1 - fractionFactor);
		}
		
		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;

		if (g.i().gameMode == 'R') {
			if (isInWater) {
				speedYPerSecond += g.i().playerdyInWater;
			} else
				speedYPerSecond += gravityPerSecond;
		}
		radius = gameThing.getWidth() / 2;
		tileWidth = tileHeight = 32;
	}

	/**
	 * Checks whether the player has rolled off the map in the X direction or
	 * not
	 * 
	 * @return
	 */
	private void isInWater() {
		if (water != null) {
			if (gameThing.getY() + gameThing.getHeight() / 2 > water.getY()
					&& gameThing.getY() + gameThing.getHeight() / 2 < water
							.getY() + water.getHeight()) {

				if (gameThing.getX() + gameThing.getWidth() / 2 > water.getX()
						&& gameThing.getX() + gameThing.getWidth() / 2 < water
								.getX() + water.getWidth()) {
					isInWater = true;
					return;

				}
			}
		}
		if(isInWater)
		{
			previousY = water.getY() + water.getHeight();
		}
		isInWater = false;
	}

	private NP getObjNamed(String name) {
		for (int iO = 0; iO < g.i().mapObjects.size(); iO++)
			if (name.equalsIgnoreCase(g.i().mapObjects.get(iO).getName()))
				return g.i().mapObjects.get(iO);
		return null;
	}

	protected boolean isXFree() {
		if (speedXPerSecond < 0) // going to the left
		{
			if (gameThing.getX() < 0) // collide with left edge of map
				return false;

			// check object collision
			circleCenter = new Vector2(gameThing.getX() + radius,
					gameThing.getY() + radius);
			// iterate through objects
			for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
				if (object instanceof RectangleMapObject) {
					temp = (RectangleMapObject) object;
					rect = temp.getRectangle();

					// check if object is not to the left of the player
					// or if the object is more than 1 tile away from the player
					if (rect.x > (gameThing.getX() + gameThing.getWidth())
							|| gameThing.getX() - (rect.x + rect.width) > 2 * tileWidth)
						continue;

					// iterate through y values of object
					for (int countY = (int) rect.y; countY < (rect.y + rect.height); countY++) {
						// player collides with object
						if (circleCenter.dst(rect.x + rect.width, countY) < radius) {
							if (g.i().npMap.get(object) != null) {
								triggeredNP.add(g.i().npMap.get(object));
								if (g.i().npMap.get(object).collidable) {
									previousX = rect.x + rect.width;
									return false;
								}
							} else {
								previousX = rect.x + rect.width;
								return false;
							}
						}
					}

				}// end of if object is a rectanlgeMapObject
			}// end of for object iterator
		}// end of going to the left check

		else if (speedXPerSecond > 0) // going to the right
		{
			// collide with right edge of the map
			if (gameThing.getX() + gameThing.getWidth() > gameThing.tiledMapWrapper
					.getPixelWidth())
				return false;

			// check object collision
			circleCenter = new Vector2(gameThing.getX() + radius,
					gameThing.getY() + radius);
			// iterate through objects
			for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
				if (object instanceof RectangleMapObject) {
					temp = (RectangleMapObject) object;
					rect = temp.getRectangle();

					// check if object is not to the right of the player
					// or if the object is more than 1 tile away from the player
					if (rect.x < gameThing.getX()
							|| rect.x
									- (gameThing.getX() + gameThing.getWidth()) > 2 * tileWidth)
						continue;

					// iterate through y values of object
					for (int countY = (int) rect.y; countY < (rect.y + rect.height); countY++) {
						// player collides with object
						if (circleCenter.dst(rect.x, countY) < radius) {

							if (g.i().npMap.get(object) != null) {
								triggeredNP.add(g.i().npMap.get(object));
								if (g.i().npMap.get(object).collidable) {
									previousX = rect.x - gameThing.getWidth();
									return false;
								}
							} else {
								previousX = rect.x - gameThing.getWidth();
								return false;
							}
						}
					}

				}// end of if object is a rectanlgeMapObject
			}// end of for object iterator
		}// end of going to the right check
		return true;
	}// end of isXFree()

	protected boolean isYFree() {
		if (speedYPerSecond < 0) // going down
		{
			if (gameThing.getY() < 0) // collide with bottom of map
			{
				return false;
			}

			// check object collision
			circleCenter = new Vector2(gameThing.getX() + radius,
					gameThing.getY() + radius);
			// iterate through objects
			for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
				if (object instanceof RectangleMapObject) {
					temp = (RectangleMapObject) object;
					rect = temp.getRectangle();

					// check if object is not to the bottom of the player
					// or if the object is more than 1 tile away from the player
					if (rect.y > gameThing.getY()
							|| gameThing.getY() - (rect.y + rect.height) > 2 * tileHeight)
						continue;

					// iterate through x values of object
					for (int countX = (int) rect.x; countX < (rect.x + rect.width); countX++) {
						// player collides with object
						if (circleCenter.dst(countX, rect.y + rect.height) < radius) {
							if (g.i().npMap.get(object) != null) {
								triggeredNP.add(g.i().npMap.get(object));
								if (g.i().npMap.get(object).collidable) {
									previousY = rect.y + rect.height;
									return false;
								}
							} else {
								previousY = rect.y + rect.height;
								return false;
							}
						}

					}

				}// end of if object is a rectanlgeMapObject
			}// end of for object iterator
		}// end of going down check

		else if (speedYPerSecond > 0) // going up
		{
			// collide with top of the map
			if (gameThing.getY() + gameThing.getHeight() > gameThing.tiledMapWrapper
					.getPixelHeight())
				return false;

			// check object collision
			circleCenter = new Vector2(gameThing.getX() + radius,
					gameThing.getY() + radius);
			// iterate through objects
			for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
				if (object instanceof RectangleMapObject) {
					temp = (RectangleMapObject) object;
					rect = temp.getRectangle();

					// check if object is not above the player
					// or if the object is more than 1 tile away from the player
					if (rect.y < gameThing.getY()
							|| rect.y
									- (gameThing.getY() + gameThing.getHeight()) > 2 * tileHeight)
						continue;

					// iterate through y values of object
					for (int countX = (int) rect.x; countX < (rect.x + rect.width); countX++) {
						// player collides with object
						if (circleCenter.dst(countX, rect.y) < radius) {

							if (g.i().npMap.get(object) != null) {
								triggeredNP.add(g.i().npMap.get(object));
								if (g.i().npMap.get(object).collidable) {
									previousY = rect.y - gameThing.getHeight();
									return false;
								}
							} else {
								previousY = rect.y - gameThing.getHeight();
								return false;
							}
						}
					}

				}// end of if object is a rectanlgeMapObject
			}// end of for object iterator
		}// end of going up
		return true;
	}// end of isYFree()

}// end of PhysicsMover
