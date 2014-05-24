package com.touchspin.td;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;



public abstract class GameThing extends Actor {
	public TiledMapWrapper tiledMapWrapper;
	public AnimationSet animationSet;
	public boolean collidable;
	public void setCollidable(boolean collidable)
	{
		this.collidable = false;
	}
	public void setAnimation(String animationName)
	{
		
	}
	
	public void changeLocation(GameThing dest)
	{
		setX(dest.getX());
		setY(dest.getY());
	}
}
