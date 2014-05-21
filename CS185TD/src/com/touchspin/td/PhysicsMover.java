package com.touchspin.td;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class PhysicsMover extends Mover {
	float previousX;
	float previousY;	
	float gravityPerSecond = -20;	
	float accelerationY = 0;
	float accelerationX = 0;	

	RectangleMapObject temp;
	Rectangle rect;
	
	public PhysicsMover() {		
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
	 * check if the player has collided with the right hand side of a object
	 * @return
	 */
	protected boolean collideRight()
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
				
				// collision from the right
				if(rect.x + rect.getWidth() > hero.getX())// 1b				
					if(rect.x + rect.getWidth() < hero.getX() + hero.getWidth())//2b
						if(rect.y + rect.height < hero.getY() + hero.getHeight() + 1)//3
							if(rect.y > hero.getY())//4
							{								
								speedXPerSecond = 0;
								return true;							
							}
			}
		}// end of for loop
		return false;
	}// end of collideRight
	
	/**
	 * check if the player has collided with the left hand side of a object
	 * @return
	 */
	protected boolean collideLeft()
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
				if(rect.x < hero.getX() + hero.getWidth())// 1				
					if(hero.getX() < rect.x)//5
						if(rect.y + rect.height < hero.getY() + hero.getHeight() + 1)//3
							if(rect.y > hero.getY())//4
							{
								speedXPerSecond = 0;
								return true;								
							}
			}
		}//end of for loop
		return false;
	}// end of collideLeft
	
	/**
	 * Check if the player has collided with the top of a object
	 * @return
	 */
	protected boolean collideTop()
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
				
				if(rect.y + rect.height > hero.getY() &&
						rect.y + rect.height < hero.getY() + hero.getHeight())
				{
					if((rect.x + rect.width > hero.getX() && rect.x < hero.getX()) ||
						(rect.x + rect.width >hero.getX() + hero.getWidth() && 
						rect.x < hero.getX() + hero.getWidth()))
					{
						gravityPerSecond = 0;
						speedYPerSecond = 0;
						return true;
					}
				}				
			}
			gravityPerSecond = -20F;
		}// end of for loop
		return false;
	}// end of collideTop
	
	/**
	 * check if player has collided with the bottom of an object
	 * @return
	 */
	protected boolean collideBottom()
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
				
				// collide with bottom
				if(rect.x + rect.getWidth() > hero.getX())	
					if(rect.x < hero.getX() + hero.getWidth() - 1)
						if(rect.y < hero.getY() + hero.getHeight())
							if(rect.y > hero.getY())
							{
								speedYPerSecond = -1;								
								return true;	
							}
			}
		}//end of for loop
		return false;
	}// end of collideBottom	
	
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
		
		for(MapObject object : hero.tiledMapWrapper.collisionObjects) 
		{
			if (object instanceof RectangleMapObject)
			{
				temp = (RectangleMapObject)object;
				rect = temp.getRectangle();		
				
				// collision from the left
				if(rect.x < hero.getX() + hero.getWidth())// 1				
					if(hero.getX() < rect.x)//5
						if(rect.y + rect.height < hero.getY() + hero.getHeight() + 1)//3
							if(rect.y > hero.getY())//4
							{
								speedXPerSecond = 0;
								return false;								
							}
				
				// collision from the right
				if(rect.x + rect.getWidth() > hero.getX())// 1b				
					if(rect.x + rect.getWidth() < hero.getX() + hero.getWidth())//2b
						if(rect.y + rect.height < hero.getY() + hero.getHeight() + 1)//3
							if(rect.y > hero.getY())//4
							{								
								speedXPerSecond = 0;
								return false;							
							}
			}
		}
			
		return true;
	}// end of isXFree()

	protected boolean isYFree() 
	{
		//---Check if player has reached the top or bottom of the map		
		if (hero.getY() + hero.getHeight() > hero.tiledMapWrapper
				.getPixelHeight()) 
		{
			speedYPerSecond = 0;
			return false;			
		}
		if (hero.getY() < 0) 
		{
			speedYPerSecond = 0;
			gravityPerSecond = 0;
			return false;
		}
		
		for(MapObject object : hero.tiledMapWrapper.collisionObjects) 
		{
			if (object instanceof RectangleMapObject)
			{
				temp = (RectangleMapObject)object;
				rect = temp.getRectangle();	
				
				if(rect.y + rect.height > hero.getY() &&
						rect.y + rect.height < hero.getY() + hero.getHeight())
				{
					if((rect.x + rect.width > hero.getX() && rect.x < hero.getX()) ||
						(rect.x + rect.width >hero.getX() + hero.getWidth() && 
						rect.x < hero.getX() + hero.getWidth()))
					{
						gravityPerSecond = 0;
						speedYPerSecond = 0;
						return false;
					}
				}	
				

				// collide with bottom
				if(rect.x + rect.getWidth() > hero.getX())	
					if(rect.x < hero.getX() + hero.getWidth() - 1)
						if(rect.y < hero.getY() + hero.getHeight())
							if(rect.y > hero.getY())
							{
								speedYPerSecond = -1;								
								return false;	
							}
			}}
				
		gravityPerSecond = -20;
		return true;			
		
	}//end of isYFree()	
	
}// end of PhysicsMover
