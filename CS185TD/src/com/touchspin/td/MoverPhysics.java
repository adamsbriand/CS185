package com.touchspin.td;

import java.util.ArrayList;
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
	protected Vector2 speedPerUnit = new Vector2();
	protected ArrayList<NP> waterList;
	boolean isInWater;
	protected float maxDyInwater = 50;

	public MoverPhysics() {
		speedXPerSecond = 0;
		speedYPerSecond = 0;
		waterList = new ArrayList<NP>();
		for (int i = 0; i < g.i().mapObjects.size(); i++)
			if (g.i().mapObjects.get(i).getName().contains("Water"))
				waterList.add(g.i().mapObjects.get(i));
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
				speedYPerSecond += (maxDyInwater < g.i().playerdyInWater)?maxDyInwater:g.i().playerdyInWater;
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
	protected void isInWater() {
		if (waterList != null) {
			for (NP water : waterList) {
				if (water != null) {
					float gty = gameThing.getY() + gameThing.getHeight() / 2;
					float gtx = gameThing.getX() + gameThing.getWidth() / 2;
					float wL = water.getX();
					float wR = wL + water.getWidth();
					float wT = water.getY();
					float wB = wT + water.getHeight();
					isInWater = (gtx > wL && gtx < wR && gty > wT && gty < wB);
					if (isInWater)
						return;
				}
				if (isInWater) {
					previousY = water.getY() + water.getHeight();
				}
				isInWater = false;
			}
		}
	}

	protected boolean isXFree() {
		if (speedXPerSecond < 0) // going to the left
		{
			// unit for loop
			for (float currentX = previousX; currentX > gameThing.getX(); currentX--) {
				if (currentX < 0) // collide with left edge of map
					return false;

				// check object collision
				circleCenter = new Vector2(currentX + radius, gameThing.getY()
						+ radius);
				// iterate through objects
				for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
					if (object instanceof RectangleMapObject) {
						temp = (RectangleMapObject) object;
						rect = temp.getRectangle();

						// check if object is not to the left of the player
						// or if the object is more than 1 tile away from the
						// player
						if (rect.x > (currentX + gameThing.getWidth())
								|| currentX - (rect.x + rect.width) > 2 * tileWidth)
							continue;

						// iterate through y values of object
						for (int countY = (int) rect.y + 1; countY < (rect.y + rect.height); countY++) {
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
			}// end of unit for loop
		}// end of going to the left check

		else if (speedXPerSecond > 0) // going to the right
		{
			// unit for loop
			for (float currentX = previousX; currentX < gameThing.getX(); currentX++) {
				// collide with right edge of the map
				if (currentX + gameThing.getWidth() > gameThing.tiledMapWrapper
						.getPixelWidth())
					return false;

				// check object collision
				circleCenter = new Vector2(currentX + radius, gameThing.getY()
						+ radius);
				// iterate through objects
				for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
					if (object instanceof RectangleMapObject) {
						temp = (RectangleMapObject) object;
						rect = temp.getRectangle();

						// check if object is not to the right of the player
						// or if the object is more than 1 tile away from the
						// player
						if (rect.x < currentX
								|| rect.x - (currentX + gameThing.getWidth()) > 2 * tileWidth)
							continue;

						// iterate through y values of object
						for (int countY = (int) rect.y + 1; countY < (rect.y + rect.height); countY++) {
							// player collides with object
							if (circleCenter.dst(rect.x, countY) < radius) {

								if (g.i().npMap.get(object) != null) {
									triggeredNP.add(g.i().npMap.get(object));
									if (g.i().npMap.get(object).collidable) {
										previousX = rect.x
												- gameThing.getWidth();
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
			}// end of unit for loop
		}// end of going to the right check
		return true;
	}// end of isXFree()

	protected boolean isYFree() {
		if (speedYPerSecond < 0) // going down
		{
			// unit for loop
			for (float currentY = previousY; currentY > gameThing.getY(); currentY--) {
				if (currentY < 0) // collide with bottom of map
				{
					return false;
				}

				// check object collision
				circleCenter = new Vector2(gameThing.getX() + radius, currentY
						+ radius);
				// iterate through objects
				for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
					if (object instanceof RectangleMapObject) {
						temp = (RectangleMapObject) object;
						rect = temp.getRectangle();

						// check if object is not to the bottom of the player
						// or if the object is more than 1 tile away from the
						// player
						if (rect.y > currentY
								|| currentY - (rect.y + rect.height) > 2 * tileHeight)
							continue;

						// iterate through x values of object
						for (int countX = (int) rect.x + 1; countX < (rect.x + rect.width); countX++) {
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
			}// end of unit for loop
		}// end of going down check

		else if (speedYPerSecond > 0) // going up
		{
			// unit for loop
			for (float currentY = previousY; currentY < gameThing.getY(); currentY++) {
				// collide with top of the map
				if (currentY + gameThing.getHeight() > gameThing.tiledMapWrapper
						.getPixelHeight())
					return false;

				// check object collision
				circleCenter = new Vector2(gameThing.getX() + radius, currentY
						+ radius);
				// iterate through objects
				for (MapObject object : gameThing.tiledMapWrapper.collisionObjects) {
					if (object instanceof RectangleMapObject) {
						temp = (RectangleMapObject) object;
						rect = temp.getRectangle();

						// check if object is not above the player
						// or if the object is more than 1 tile away from the
						// player
						if (rect.y < currentY
								|| rect.y - (currentY + gameThing.getHeight()) > 2 * tileHeight)
							continue;

						// iterate through y values of object
						for (int countX = (int) rect.x + 1; countX < (rect.x + rect.width); countX++) {
							// player collides with object
							if (circleCenter.dst(countX, rect.y) < radius) {

								if (g.i().npMap.get(object) != null) {
									triggeredNP.add(g.i().npMap.get(object));
									if (g.i().npMap.get(object).collidable) {
										previousY = rect.y
												- gameThing.getHeight();
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
			}// end of unit for loop
		}// end of going up
		return true;
	}// end of isYFree()

}// end of PhysicsMover
