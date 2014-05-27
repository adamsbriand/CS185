package com.touchspin.td;

import com.badlogic.gdx.scenes.scene2d.Actor;



public abstract class GameThing extends Actor {
	public TiledMapWrapper tiledMapWrapper;
	protected float stateTime;
	
	public void changeLocation(GameThing dest)
	{
		setX(dest.getX());
		setY(dest.getY());
	}
}
