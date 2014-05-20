package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapObject;
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

	/**
	 * Checks for collision with solid objects on the map
	 * called by: 	
	 * @return true or false
	 */
	protected boolean calObjCollision(Sprite mySprite)
	{
		
		// go through each collision object and determine what type of object it is.
		// then check boundaries of object with player position
		for(MapObject object : hero.tiledMapWrapper.collisionObjects) 
		{
			if (object instanceof RectangleMapObject)
			{
				RectangleMapObject temp;
				Rectangle rect;
				temp = (RectangleMapObject)object;
				rect = temp.getRectangle();
								
				// collision from the left
				if(rect.x < mySprite.getX() + mySprite.getWidth())// 1				
					if(mySprite.getX() < rect.x)//5
						if(rect.y + rect.height < mySprite.getY() + mySprite.getHeight())//3
							if(rect.y > mySprite.getY())//4
							{
								speedXPerSecond = -1;
								return true;								
							}
				
				// collision from the right
				if(rect.x + rect.getWidth() > hero.getX())// 1b				
					if(rect.x + rect.getWidth() < hero.getX() + mySprite.getWidth())//2b
						if(rect.y < hero.getY() + mySprite.getHeight())//3b
							if(rect.y + rect.getHeight() > hero.getY() +mySprite.getHeight())//4b
							{								
								speedXPerSecond = 1;
								return true;							
							}
				/*// collide from the top
				if(rect.x + rect.getWidth() > hero.getX() + mySprite.getWidth())	
					if(rect.x < mySprite.getX() + mySprite.getWidth())
						if(rect.y < hero.getY())
							if(rect.y + rect.getHeight() > hero.getY())
							{
								speedYPerSecond = 0;
								return true;		
							}
				
				if(rect.y + rect.height > hero.getY() &&
						rect.y + rect.height < hero.getY() + hero.getHeight())
				{
					if((rect.x + rect.width > hero.getX() && rect.x < hero.getX()) ||
						(rect.x + rect.width >hero.getX()+hero.getWidth() && 
						rect.x < hero.getX()+hero.getWidth()))
					{
						gravityPerSecond = 0;
						speedYPerSecond = 0;
						return true;
					}
				}
				/*
				// collide with bottom
				if(rect.x + rect.getWidth() > hero.getX() + mySprite.getWidth())	
					if(rect.x < mySprite.getX() + mySprite.getWidth())
						if(rect.y < hero.getY() + mySprite.getHeight())
							if(rect.y + rect.height > hero.getY() + mySprite.getHeight())
							{
								speedYPerSecond *= -1;
								gravityPerSecond = -9.8F;
								break;	
							}*/
			}
			
		}			
			return false;	
	}
	
	/**
	 * Checks whether the player has rolled off the map
	 * in the X direction or not
	 * @return
	 */
	protected boolean isXFree() 
	{
		//---Check if player has reached the right or left edge of the map		
		if (hero.getX() + hero.getWidth() > hero.tiledMapWrapper
				.getPixelWidth()) 
		{			
			speedXPerSecond = 0;
			return false;			
		}
		if (hero.getX() < 0) 
		{			
			speedXPerSecond = 0;
			return false;
		}
		
		return true;
	}

	protected boolean isYFree() {
		RectangleMapObject temp;
		Rectangle rect;
		
		for(int i = 0; i < hero.tiledMapWrapper.collisionObjects.getCount();i++)
		{
			temp = (RectangleMapObject)hero.tiledMapWrapper.collisionObjects.get(i);
			rect = temp.getRectangle();	
			//check below
			
			if(rect.y + rect.height > hero.getY() &&
					rect.y + rect.height < hero.getY() + hero.getHeight())
			{
				if((rect.x + rect.width > hero.getX() && rect.x < hero.getX()) ||
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
		
		return true;
	}//end of isYFree()	
	
	
	/*protected boolean isYFree() 
	{
		//---Check if player has reached the top or bottom of the map		
		if (hero.getY() + hero.getHeight() > hero.tiledMapWrapper
				.getPixelHeight()) 
		{
			previousY = hero.tiledMapWrapper.getPixelHeight() - hero.getHeight();			
			speedYPerSecond = 0;
			return false;			
		}
		if (hero.getY() < 0) 
		{
			previousY = 0;
			speedYPerSecond = 0;
			return false;
		}
				
		gravityPerSecond = -20;
		return true;			
		
	}//end of isYFree()	
	*/
}// end of PhysicsMover
