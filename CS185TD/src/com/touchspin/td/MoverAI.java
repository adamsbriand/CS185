package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MoverAI extends MoverPhysics {

	private NP np;
	private final float movingAcceleration;
	private Vector2 path;
	public MoverAI() {
		super();
		movingAcceleration = 20;
		path = new Vector2(0,0);
	}
	public void move(NP np) {
		this.np = np;		
		
		// Save the previous position
		previousX = this.np.getX();
		previousY = this.np.getY();
		// Try to move	
		AIMove();	
		
		this.np.setX(np.getX() + Gdx.graphics.getDeltaTime()
				* speedXPerSecond);
		this.np.setY(np.getY() + Gdx.graphics.getDeltaTime()
				* speedYPerSecond);
		
	}
	
	public void AIMove()
	{
		path.x = g.i().hero.getX() - np.getX();
		path.y = g.i().hero.getY() - np.getY();
		
		//-------not finished yet---------------
		accelerationX = 20 * MathUtils.cos(path.getAngleRad());
		accelerationY = 20 * MathUtils.sin(path.getAngleRad());
		
		fractionFactor = 0.003f;
		speedXPerSecond *= (1 - fractionFactor);
		speedYPerSecond *= (1 - fractionFactor);

		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
	}
}
