package com.touchspin.td;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class NP extends GameThing {

	String name;
	String type;
	Mover npMover;
	String conditional;
	String action;
	Sprite npSprite;
	
	public NP(int startX, int startY, int width, int height, String name, 
			String type, String conditional, String action , Sprite npSprite)
	{
		setX(startX);
		setY(startY);
		setWidth(width);
		setHeight(height);
		this.name = name;
		this.type = type;
		this.conditional = conditional;
		this.action = action;
		
		if(type == "exampleNPC")
			npMover = new MoverPhysics();
		else if(type == "exampleNPO")
			npMover = new MoverAI();
		
		this.npSprite = npSprite;
		
	}
	
	@Override
	public void draw(Batch batch, float alpha)
	{
		npSprite.draw(batch);
	}
	
	@Override
	public void act(float delta)
	{
		npMover.move(this);
	}
}
