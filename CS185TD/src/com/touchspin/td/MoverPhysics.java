package com.touchspin.td;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;

public class MoverPhysics extends Mover {
	float previousX;
	float previousY;	
	float gravityPerSecond = -20;	
	float accelerationY = 0;
	float accelerationX = 0;	

	RectangleMapObject temp;
	Rectangle rect;
	
	public MoverPhysics() {		
		speedXPerSecond = 0;
		speedYPerSecond = 0;
	}

	@Override
	public void move(GameThing gameThing) {
		this.gameThing = gameThing;
	}

	protected void physicsMove() {
		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
		if (g.i().gameMode == 'R') 
			speedYPerSecond += gravityPerSecond;
		
	}
	
	/**
	 * Checks whether the player has rolled off the map
	 * in the X direction or not
	 * @return
	 */
	protected boolean isXFree() 
	{
		//---Check if player has reached the right or left edge of the map		
		if (gameThing.getX() + gameThing.getWidth() > gameThing.tiledMapWrapper
				.getPixelWidth()) 
		{			
			speedXPerSecond = 0;
			return false;			
		}
		if (gameThing.getX() < 0) 
		{			
			speedXPerSecond = 0;
			return false;
		}
		
		for(MapObject object : gameThing.tiledMapWrapper.collisionObjects) 
		{
			if (object instanceof RectangleMapObject)
			{
				temp = (RectangleMapObject)object;
				rect = temp.getRectangle();		
				
				// collision from the left
				if(rect.x < gameThing.getX() + gameThing.getWidth())// 1				
					if(gameThing.getX() < rect.x)//5
						if(rect.y + rect.height < gameThing.getY() + gameThing.getHeight() + 1)//3
							if(rect.y > gameThing.getY())//4
							{
								speedXPerSecond = 0;
								return false;								
							}
				
				// collision from the right
				if(rect.x + rect.getWidth() > gameThing.getX())// 1b				
					if(rect.x + rect.getWidth() < gameThing.getX() + gameThing.getWidth())//2b
						if(rect.y + rect.height < gameThing.getY() + gameThing.getHeight() + 1)//3
							if(rect.y > gameThing.getY())//4
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
		if (gameThing.getY() + gameThing.getHeight() > gameThing.tiledMapWrapper
				.getPixelHeight()) 
		{
			speedYPerSecond = 0;
			return false;			
		}
		if (gameThing.getY() < 0) 
		{
			speedYPerSecond = 0;
			gravityPerSecond = 0;
			return false;
		}
		
		for(MapObject object : gameThing.tiledMapWrapper.collisionObjects) 
		{
			if (object instanceof RectangleMapObject)
			{
				temp = (RectangleMapObject)object;
				rect = temp.getRectangle();	
				
				if(rect.y + rect.height > gameThing.getY() &&
						rect.y + rect.height < gameThing.getY() + gameThing.getHeight())
				{
					if((rect.x + rect.width > gameThing.getX() && rect.x < gameThing.getX()) ||
						(rect.x + rect.width >gameThing.getX() + gameThing.getWidth() && 
						rect.x < gameThing.getX() + gameThing.getWidth()))
					{
						gravityPerSecond = 0;
						speedYPerSecond = 0;
						return false;
					}
				}	
				

				// collide with bottom
				if(rect.x + rect.getWidth() > gameThing.getX())	
					if(rect.x < gameThing.getX() + gameThing.getWidth() - 1)
						if(rect.y < gameThing.getY() + gameThing.getHeight())
							if(rect.y > gameThing.getY())
							{
								speedYPerSecond = -1;								
								return false;	
							}
			}}
				
		gravityPerSecond = -20;
		return true;			
		
	}//end of isYFree()	
	
}// end of PhysicsMover
