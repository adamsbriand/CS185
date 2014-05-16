package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PhysicsMover extends Mover {
	float previousX;
	float previousY;
	InputAnonymizer anonymizer;
	float gravityPerSecond = -20;
	float accelerationY = 0;
	float accelerationX = 0;

	public PhysicsMover() {
		anonymizer = g.i().leAnonymizer;
		speedXPerSecond = 0;
		speedYPerSecond = 0;
	}

	@Override
	public void move(Hero hero) {
		this.hero = hero;
		// Save the previous position
		previousX = hero.getX();
		previousY = hero.getY();
		// Try to move
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

	protected void physicsMove() {
		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
		if (g.i().gameMode == 0) {
			speedYPerSecond += gravityPerSecond;
		}
	}

	protected boolean isXFree() {
		boolean free = true;
		if (hero.getX() + hero.getWidth() > hero.tiledMapWrapper
				.getPixelWidth()) {
			previousX = hero.tiledMapWrapper.getPixelWidth() - hero.getWidth();
			free = false;
		}
		if (hero.getX() < 0) {
			previousX = 0;
			free = false;
		}
		return free;
	}

	protected boolean isYFree() {
		boolean free = true;
		if (hero.getY() + hero.getHeight() > hero.tiledMapWrapper
				.getPixelHeight()) {
			previousY = hero.tiledMapWrapper.getPixelHeight()
					- hero.getHeight();
			free = false;
		}
		if (hero.getY() < 32) {
			previousY = 32;
			anonymizer.jump = false;
			free = false;
		}
		return free;
	}

}
