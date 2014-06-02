package com.touchspin.td;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;

public class MoverInput extends MoverPhysics {
	private HashSet<NP> prev = new HashSet<NP>();

	public MoverInput() {
		super();
	}

	@Override
	public void move(GameThing gameThing) {
		this.gameThing = gameThing;

		// Save the previous position
		previousX = gameThing.getX();
		previousY = gameThing.getY();
		// Try to move

		inputMove();
		physicsMove();
		// If movement is failed, set the position of the
		// actor to previous position

		gameThing.setX(gameThing.getX() + Gdx.graphics.getDeltaTime()
				* speedXPerSecond);
		if (!isXFree()) {
			if(speedXPerSecond < -100 || speedXPerSecond > 100)
				g.i().sound.Bounce();
			gameThing.setX(previousX);
			speedXPerSecond = 0;
		}

		gameThing.setY(gameThing.getY() + Gdx.graphics.getDeltaTime()
				* speedYPerSecond);
		if (!isYFree()) {
			if(speedYPerSecond < -100 || speedYPerSecond > 100)
				g.i().sound.Bounce();
			gameThing.setY(previousY);
			speedYPerSecond = 0;
		}
		
		if(triggeredNP.size()!=0)
		{
			for (NP object : triggeredNP) {
				if (!prev.contains(object) && object.active) {
					g.i().t.action(object.conditions, object.action);
				}
			}
			prev.clear();
			for (NP object : triggeredNP) {
				prev.add(object);
			}
			triggeredNP.clear();
		} else {
			prev.clear();
		}

	}

	private void inputMove() {

		accelerationX = g.i().leAnonymizer.tiltSpeed.x;
		g.i().leAnonymizer.tiltSpeed.x = 0;

		if (g.i().gameMode == 'M') {
			accelerationY = g.i().leAnonymizer.tiltSpeed.y;
			g.i().leAnonymizer.tiltSpeed.y = 0;
		}

		else if (g.i().gameMode == 'R') {
			if (g.i().leAnonymizer.jump) {
				speedYPerSecond = 500;
				g.i().leAnonymizer.jump = false;
			}
		}
	}
}
