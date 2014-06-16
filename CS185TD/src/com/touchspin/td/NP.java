package com.touchspin.td;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
/* ======================================================================================
 * File:			NP.java
 * Authors:			Brian Adams - b.adams5736@edmail.edcc.edu
 * 					Russell Brendel - russell.brendel.2925@edmail.edcc.edu
 * 					Damian Forrester - dforrester777@gmail.com
 * 					Wendi Tang - w.tang2404@myedmail.edcc.edu
 * 
 * Organization:	Edmonds Community College
 * Term:			Spring 2014
 * Class:			CS 185 - Game Project Developement
 * Instructor:		Tim Hunt - thunt@edcc.edu
 * 
 * Project:			Ollie
 * --------------------------------------------------------------------------------------
 * 
 * This class holds information for non-payer object.
 * 
 * ======================================================================================
 */
public class NP extends GameThing {

	String name;
	String type;
	Mover npMover;
	String conditions;
	String action;
	String anims;
	Sprite npSprite;
	String spriteSheet;
	String collisionParameter;
	Texture spriteSheetTexture;
	Vector2 originalPosition;
	Vector2 destination;
	boolean collidable;
	boolean inCollision;
	public AnimationSet animationSet;

	private Animation currentAnimation;
	private TextureRegion currentFrame;
	private Map<String, Animation> animationMap = new HashMap<String, Animation>();
	private int animRows;
	private int animCols;
	public int roamingRadius;
	private Camera camera;
	public boolean active;
	float rotation;
	private float MaxDistance = 500;
	private Vector2 myPosition = new Vector2();
/**
 *  The constructor
 * @param startX - the start x position
 * @param startY - the start y position
 * @param width - the width of the object
 * @param height - the height of the object
 * @param name - the name of the object
 * @param type - the type of the object
 * @param conditions - the condition parameter which need to be pass to the trigger
 * when this object is triggerd.
 * @param action - the actions which will be performed if the object is triggered.
 * @param anims - animation description
 * @param roamingRadius - the roamingRadius
 * @param spriteSheet - the spriteSheet used by this object
 * @param animRows - the rows in the spritesheet
 * @param animCols - the cols in the spritesheet
 * @param collidable - if this object is collidable
 * @param collisionParameter - collision parameter
 * @param camera - the camera used in the game screen
 * @param active - if this object is active
 * @param rotation - rotation of the sprite
 */
	public NP(int startX, int startY, int width, int height, String name,
			String type, String conditions, String action, String anims,
			int roamingRadius, String spriteSheet, int animRows, int animCols,
			boolean collidable, String collisionParameter,
			OrthographicCamera camera, String active,float rotation) {
		originalPosition = new Vector2(startX, startY);
		this.camera = camera;
		setX(startX);
		setY(startY);
		setWidth(width);
		setHeight(height);
		this.name = name;
		this.type = type;
		this.conditions = conditions;
		this.action = action;
		this.collidable = collidable;
		this.roamingRadius = roamingRadius;
		this.spriteSheet = spriteSheet;
		this.anims = anims;
		this.collisionParameter = collisionParameter;
		npSprite = new Sprite();
		animationSet = new AnimationSet();
		inCollision = false;
		setActive(active);

		if (type.equalsIgnoreCase("npcEnemy"))
			npMover = new MoverAI(this);
		else
			npMover = new MoverNull();
		if (!spriteSheet.equalsIgnoreCase("")) {
			this.spriteSheetTexture = new Texture(spriteSheet);
			this.animRows = animRows;
			this.animCols = animCols;
			loadAnimation();

			if (!spriteSheet.equalsIgnoreCase("img/spritesheet/Campfire.png")) {
				stateTime = 0;
			} else {
				Random randomno = new Random();
				float timePerFrame = 0.025f;
				stateTime = randomno.nextInt(12) * timePerFrame;

			}
			currentFrame = currentAnimation.getKeyFrame(stateTime, false);
			npSprite.setRegion(currentFrame);
			npSprite.setBounds(getX(), getY(), getWidth(), getHeight());
			npSprite.setOrigin(npSprite.getWidth()/2,npSprite.getHeight()/2 );
			npSprite.setRotation(rotation);
		} else {
			this.animRows = 0;
			this.animCols = 0;
		}

	}

	/**
	 * Change collidability
	 * @param collidable - true if this object is collidable 
	 */
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	/**
	 * Change the current animation to the animation with the given name
	 * @param animationName - the name of the animation to be changed to
	 */
	public void setAnimation(String animationName) {
		if (animationSet.getCurrentAnimationDescription().name
				.equalsIgnoreCase("Broken")) {
			active = false;
			return;
		}
		stateTime = 0;
		currentAnimation = animationMap.get(animationName);
		animationSet.iCurrAnim = animationSet.getIndexOf(animationName);

	}

	/**
	 * Get the name of current animation
	 * @return the name of current animation
	 */
	public String getAnimation() {
		return animationSet.getCurrentAnimationDescription().name;
	}

	/**
	 * Draw the object
	 */
	@Override
	public void draw(Batch batch, float alpha) {
		if(myPosition.dst(g.i().hero.getX(), g.i().hero.getY())<MaxDistance || g.i().controls != 'A')
		{
			if (!spriteSheet.equalsIgnoreCase("")) {
				batch.setProjectionMatrix(camera.combined);
				npSprite.draw(batch);
			}
		}
	}

	/**
	 * Get the name of this object
	 * @return the name of this object
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return true if this object's name matches the given name
	 * @param name - the name to compare to
	 * @return true if the name matches, else false
	 */
	public boolean nameMatch(String name) {
		return this.name.equalsIgnoreCase(name);
	}

	/**
	 * Update the object
	 */
	@Override
	public void act(float delta) {
		if (name.equalsIgnoreCase("Dragon"))
			{
				npMover.move(this);
				npSprite.setX(getX());
				npSprite.setY(getY());
			}
		if(myPosition.dst(g.i().hero.getX(), g.i().hero.getY())<MaxDistance || g.i().controls != 'A'){

		npSprite.setX(getX());
		npSprite.setY(getY());
		if (!spriteSheet.equalsIgnoreCase("")) {
			// animation
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = currentAnimation.getKeyFrame(stateTime, false);
			npSprite.setRegion(currentFrame);
			if (currentAnimation.isAnimationFinished(stateTime)) {
				setAnimation(animationSet.next().name);
			}
		}
		}
		myPosition.x =  getX();
		myPosition.y =  getY();
	}

	/**
	 * Change the active state
	 * @param value the true or false
	 */
	public void setActive(String value) {

		if (value.equalsIgnoreCase("true"))
			active = true;
		else
			active = false;
	}

	/**
	 * 
	 * @return current active state
	 */
	public boolean getActive() { return active; }

	// ---------------------Private helper method -------------------------------------------
	
	/**
	 * load animations
	 */
	private void loadAnimation() {

		TextureRegion[][] tmp = TextureRegion.split(spriteSheetTexture,
				spriteSheetTexture.getWidth() / animCols,
				spriteSheetTexture.getHeight() / animRows);
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

	/**
	 * load animationset
	 */
	private void loadAnimationSet() {
		String temp[] = anims.split(",");
		for (int i = 0; i < temp.length; i += 4) {
			animationSet.add(new AnimationDescription(temp[i], Integer
					.parseInt(temp[i + 1]), Integer.parseInt(temp[i + 2]),
					temp[i + 3]));
		}

	}

	/**
	 * Load animation map
	 * @param Frames
	 */
	private void loadAnimationMap(TextureRegion[] Frames) {
		Animation temp;
		TextureRegion[] tempRegion;

		for (int i = 0; i < animationSet.getSize(); i++) {
			tempRegion = new TextureRegion[animationSet.get(i).frameRange];
			for (int j = 0; j < animationSet.get(i).frameRange; j++) {
				tempRegion[j] = Frames[animationSet.get(i).frameFirst + j];
			}
			temp = new Animation(0.025f, tempRegion);
			animationMap.put(animationSet.get(i).name, temp);
			if (i == 0)
				currentAnimation = new Animation(0.025f, tempRegion);
		}
	}
}
