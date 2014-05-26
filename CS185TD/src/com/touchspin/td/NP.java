package com.touchspin.td;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class NP extends GameThing {

	String name;
	String type;
	Mover npMover;
	String conditions;
	String action;
	String anims;
	Sprite npSprite;
	Texture spriteSheet;
	Vector2 originalPosition;
	Vector2 destination;
	boolean collidable;
	public AnimationSet animationSet;
	
	private Animation currentAnimation;
	private TextureRegion currentFrame;
	private Map<String,Animation> animationMap = new HashMap<String,Animation>();
	private int animRows;
	private int animCols;
	public int roamingRadius;
	
	public NP(int startX, int startY, int width, int height, String name, 
			String type, String conditions, String action ,String anims,int roamingRadius, String spriteSheet,
			int animRows, int animCols, boolean collidable)
	{
		originalPosition = new Vector2(startX,startY);
		setX(startX);
		setY(startY);
		setWidth(width);
		setHeight(height);
		this.name = name;
		this.type = type;
		this.conditions = conditions;
		this.action = action;
		this.spriteSheet = new Texture(spriteSheet);
		this.animRows = animRows;
		this.animCols = animCols;
		this.collidable = collidable;
		this.roamingRadius = roamingRadius;
		
		if(type == "npcEnemy")
			npMover = new MoverAI();
		else if(type == "npo")
			npMover = new MoverPhysics();
		loadAnimation();
		setAnimation(animationSet.get(0).name);
		
		stateTime = 0;
		currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		npSprite.setRegion(currentFrame);
		
		npSprite.setBounds(getX(), getY(), getWidth(), getHeight());	

	}
	
	
	public void setCollidable(boolean collidable)
	{
		this.collidable = false;
	}
	
	public void setAnimation(String animationName)
	{
		stateTime = 0;
		currentAnimation = animationMap.get(animationSet.find(animationName).name);
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
		npSprite.setX(getX());
		npSprite.setY(getY());
		
		//animation
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = currentAnimation.getKeyFrame(stateTime, true);
		npSprite.setRegion(currentFrame);
		if(currentAnimation.getKeyFrameIndex(stateTime) == 
				(animationSet.getCurrentAnimationDescription().frameRange - 1) )
		{
			setAnimation(animationSet.next().name);
		}
	}
	
	//---------------------Private helper method -------------------------------------------
	private void loadAnimation()
	{

		TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / animRows,
				spriteSheet.getHeight() / animCols);
		TextureRegion[] Frames = new TextureRegion[animRows * animCols];
		int index = 0;
		for (int i = 0; i < animRows; i++) {
			for (int j = 0; j < animCols; j++) {
				Frames[index++] = tmp[i][j];
			}
		}
		
		loadAnimationSet();
		loadAnimationMap(Frames);

	}
	
	private void loadAnimationSet()
	{
		String temp[] = anims.split(",");
		for(int i = 0; i <temp.length; i++)
		{
			animationSet.add(new AnimationDescription(temp[i], Integer.parseInt(temp[i+1]),
					Integer.parseInt(temp[i+2]), temp [i+3]));
		}
		
	}
	
	private void loadAnimationMap(TextureRegion[] Frames)
	{	Animation temp;
		TextureRegion[] tempRegion;
	
		
		for(int i = 0; i < animationSet.getSize(); i ++)
		{
			tempRegion = new TextureRegion[animationSet.get(i).frameRange];
			for(int j = 0; j < animationSet.get(i).frameRange ; j++)
			{
				tempRegion[j] = Frames[animationSet.get(i).frameFirst + j];
			}
			temp =  new Animation(0.025f, tempRegion);
			animationMap.put(animationSet.get(i).name, temp);
		}
	}
	
	public void chooseDestination()
	{
		destination.set(MathUtils.random(0,roamingRadius), MathUtils.random(0,roamingRadius));
		if(originalPosition.dst(destination) > roamingRadius)
			chooseDestination();
	}
}
