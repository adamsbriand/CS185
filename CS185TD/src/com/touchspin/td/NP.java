package com.touchspin.td;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NP extends GameThing {

	String name;
	String type;
	Mover npMover;
	String conditional;
	String action;
	Sprite npSprite;
	boolean collidable;
	
	private Animation fireAnimation;
	private TextureRegion currentFrame;
	
	public NP(int startX, int startY, int width, int height, String name, 
			String type, String conditional, String action , Sprite npSprite, boolean collidable)
	{
		setX(startX);
		setY(startY);
		setWidth(width);
		setHeight(height);
		this.name = name;
		this.type = type;
		this.conditional = conditional;
		this.action = action;
		this.npSprite = npSprite;
		this.collidable = collidable;
		
		if(!collidable)
			npMover = new MoverNull();
		else if(collidable)
			if(type == "exampleNPC")
			npMover = new MoverAI();
			else if(type == "exampleNPO")
				npMover = new MoverPhysics();

	}
	
	
	public void setCollidable(boolean collidable)
	{
		this.collidable = false;
	}
	
	public void setAnimation(String animationName)
	{
		
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
