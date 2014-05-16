package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

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

	private void inputMove() {
		accelerationX=anonymizer.tiltSpeed.x;
		anonymizer.tiltSpeed.x = 0;
		if(g.i().gameMode == 1)
		{
			accelerationY=anonymizer.tiltSpeed.y;
			anonymizer.tiltSpeed.y = 0;
		}
		
		if (g.i().gameMode == 0) {
			if (anonymizer.jump) {
				speedYPerSecond = 500;
				anonymizer.jump = false;
			}
		}
	}
}
