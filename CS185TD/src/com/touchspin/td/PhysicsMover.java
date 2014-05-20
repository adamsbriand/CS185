package com.touchspin.td;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class PhysicsMover extends Mover {
	float previousX;
	float previousY;
	//InputAnonymizer anonymizer;
	float gravityPerSecond = -20;
	//float gravityPerSecond = 0;
	float accelerationY = 0;
	float accelerationX = 0;

	public PhysicsMover() {
		//anonymizer = g.i().leAnonymizer;
		speedXPerSecond = 0;
		speedYPerSecond = 0;
	}

	@Override
	public void move(Hero hero) {
		this.hero = hero;
	}

	protected void physicsMove() {
		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
		if (g.i().gameMode == 0) 
			speedYPerSecond += gravityPerSecond;
		
	}

	protected boolean isXFree() {
		boolean free = true;
		
		if (hero.getX() + hero.getWidth() > hero.tiledMapWrapper
				.getPixelWidth()) {
			previousX = hero.tiledMapWrapper.getPixelWidth() - hero.getWidth();
			{
				free = false;
				speedXPerSecond = 0;
			}
		}
		if (hero.getX() < 0) {
			previousX = 0;
			free = false;
			speedXPerSecond = 0;
		}
		return free;
	}

	protected boolean isYFree() {
		RectangleMapObject temp;
		Rectangle rect;
		boolean free = true;
		for(int i = 0; i < hero.tiledMapWrapper.collisionObjects.getCount();i++)
		{
			temp = (RectangleMapObject)hero.tiledMapWrapper.collisionObjects.get(i);
			rect = temp.getRectangle();	
			//check below
			
			if(rect.y + rect.height > hero.getY() &&
					rect.x + rect.height < hero.getY() + hero.getHeight())
			{
				if((rect.x + rect.width> hero.getX() && rect.x < hero.getX()) ||
					(rect.x + rect.width >hero.getX()+hero.getWidth() && 
					rect.x < hero.getX()+hero.getWidth()))
				{
					gravityPerSecond = 0;
					speedYPerSecond = 0;
					return false;
				}
			}
			
			gravityPerSecond = -20;
		}
		
		return free;
	}	
	
}
