package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class InputMover extends PhysicsMover {
	private Sprite mySprite;
	public InputMover() {
		super();
	}

	public void setSprite(Sprite leSprite)
	{
		mySprite = leSprite;
	}
	
	@Override
	public void move(Hero hero) {
		this.hero = hero;		
		
		// Save the previous position
		previousX = hero.getX();
		previousY = hero.getY();
		// Try to move
		
		inputMove();	
		physicsMove();
		setSpriteForCollision(mySprite);
		// If movement is failed, set the position of the
		// actor to previous position
		
		hero.setX(hero.getX()+Gdx.graphics.getDeltaTime()*speedXPerSecond);
		hero.setY(hero.getY()+Gdx.graphics.getDeltaTime()*speedYPerSecond);
		
		if(!isXFree())
			hero.setX(previousX);
		else if(collideLeft())
			hero.setX(previousX - 1);
		else if(collideRight())
			hero.setX(previousX + 1);
		
		if(!isYFree())		
			hero.setY(previousY);	
		else if(collideTop())
			hero.setY(previousY);
		else if(collideBottom())		
			hero.setY(previousY - 1);		
		
	}

	private void inputMove() 
	{
		
		accelerationX = g.i().leAnonymizer.tiltSpeed.x;
		g.i().leAnonymizer.tiltSpeed.x = 0;
		
		if(g.i().gameMode == 1)
		{
			accelerationY= g.i().leAnonymizer.tiltSpeed.y;
			g.i().leAnonymizer.tiltSpeed.y = 0;
		}
		
		else if (g.i().gameMode == 0) {
			if (g.i().leAnonymizer.jump) {
				speedYPerSecond = 500;
				g.i().leAnonymizer.jump = false;
			}
		}
	}
}
