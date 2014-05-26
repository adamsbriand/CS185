package com.touchspin.td;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MoverAI extends MoverPhysics {

	private NP np;
	private final float movingSpeed;
	private Vector2 path;
	public MoverAI() {
		super();
		movingSpeed = 20;
		path = new Vector2(0,0);
	}
	public void move(NP np) {
		this.np = np;		
		
		// Save the previous position
		previousX = np.getX();
		previousY = np.getY();
		// Try to move	
		AIMove();	
		//physicsMove();
		// If movement is failed, set the position of the
		// actor to previous position
		
		np.setX(np.getX()+Gdx.graphics.getDeltaTime()*speedXPerSecond);
		if(!isXFree())
		{
			np.setX(previousX);
			speedXPerSecond = 0;
			np.chooseDestination();
		}
		
		np.setY(np.getY()+Gdx.graphics.getDeltaTime()*speedYPerSecond);
		if(!isYFree())		
		{
			np.setY(previousY);	
			speedYPerSecond = 0;
			np.chooseDestination();
		}
	}
	
	public void AIMove()
	{
		path.x = np.destination.x - np.getX();
		path.y = np.destination.y - np.getY();
		
		//-------not finished yet---------------
		speedXPerSecond = 20 * MathUtils.cos(path.getAngleRad());
		speedYPerSecond = 20 * MathUtils.sin(path.getAngleRad());
	}
}
