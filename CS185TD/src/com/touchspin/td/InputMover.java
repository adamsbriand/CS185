package com.touchspin.td;

import com.badlogic.gdx.Gdx;

public class InputMover extends PhysicsMover {

	public InputMover() {
		super();
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
		// If movement is failed, set the position of the
		// actor to previous postion
				
		hero.setX(hero.getX()+Gdx.graphics.getDeltaTime()*speedXPerSecond);
		hero.setY(hero.getY()+Gdx.graphics.getDeltaTime()*speedYPerSecond);
		if (!isXFree()) {
			hero.setX(previousX);
		}
		if (!isYFree()) {
			hero.setY(previousY);
		}
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
