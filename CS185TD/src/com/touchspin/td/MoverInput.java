package com.touchspin.td;

import com.badlogic.gdx.Gdx;

public class MoverInput extends MoverPhysics {
	
	public MoverInput() {
		super();
	}
	
	@Override
	public void move(GameThing gameThing) 
	{
		this.gameThing = gameThing;		
		
		// Save the previous position
		previousX = gameThing.getX();
		previousY = gameThing.getY();
		// Try to move
		
		inputMove();	
		physicsMove();
		// If movement is failed, set the position of the
		// actor to previous position
		
		gameThing.setX(gameThing.getX()+Gdx.graphics.getDeltaTime()*speedXPerSecond);
		if(!isXFree())
		{
			gameThing.setX(previousX);
			speedXPerSecond = 0;
		}
		
		gameThing.setY(gameThing.getY()+Gdx.graphics.getDeltaTime()*speedYPerSecond);
		if(!isYFree())		
		{
			gameThing.setY(previousY);	
			speedYPerSecond = 0;
		}
		
		if(triggeredNP != null)
		{
			for(int i = 0 ; i < triggeredNP.size(); i++)
			{
				g.i().t.action(triggeredNP.get(i).conditions, triggeredNP.get(i).action);
			}
		}
		
	}

	private void inputMove() 
	{
		
		accelerationX = g.i().leAnonymizer.tiltSpeed.x;
		g.i().leAnonymizer.tiltSpeed.x = 0;
		
		if(g.i().gameMode == 'M')
		{
			accelerationY= g.i().leAnonymizer.tiltSpeed.y;
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
