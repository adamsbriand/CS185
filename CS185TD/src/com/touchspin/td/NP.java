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
	String spriteSheet;
	String collisionParameter;
	Texture spriteSheetTexture;
	Vector2 originalPosition;
	Vector2 destination;
	boolean collidable;
	public AnimationSet animationSet;

	private Animation currentAnimation;
	private TextureRegion currentFrame;
	private Map<String, Animation> animationMap = new HashMap<String, Animation>();
	private int animRows;
	private int animCols;
	public int roamingRadius;

	public NP(int startX, int startY, int width, int height, String name,
			String type, String conditions, String action, String anims,
			int roamingRadius, String spriteSheet, int animRows, int animCols,
			boolean collidable, String collisionParameter) {
		originalPosition = new Vector2(startX, startY);
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
		npSprite = new Sprite();
		animationSet = new AnimationSet();

		if (type.equalsIgnoreCase("npcEnemy"))
			npMover = new MoverAI();
		else if (type.equalsIgnoreCase("npo"))
			npMover = new MoverPhysics();
		if (!spriteSheet.equalsIgnoreCase("")) {
			this.spriteSheetTexture = new Texture(spriteSheet);
			this.animRows = animRows;
			this.animCols = animCols;
			loadAnimation();

			stateTime = 0;
			currentFrame = currentAnimation.getKeyFrame(stateTime, true);
			npSprite.setRegion(currentFrame);

			npSprite.setBounds(getX(), getY(), getWidth(), getHeight());
		}
		else
		{
			this.animRows = 0;
			this.animCols = 0;
		}

	}

	public void setCollidable(boolean collidable) {
		this.collidable = false;
	}

	public void setAnimation(String animationName) {
		stateTime = 0;
		currentAnimation = animationMap
				.get(animationSet.find(animationName).name);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		if(!spriteSheet.equalsIgnoreCase(""))
		npSprite.draw(batch);
	}

	public String getName()
	{
		return name;
	}
	
	public boolean nameMatch(String name)
	{
		return this.name.equalsIgnoreCase(name);
	}
	@Override
	public void act(float delta) {
		// npMover.move(this);
		npSprite.setX(getX());
		npSprite.setY(getY());

		if (!spriteSheet.equalsIgnoreCase("")) {
			// animation
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = currentAnimation.getKeyFrame(stateTime, true);
			npSprite.setRegion(currentFrame);
			if (currentAnimation.getKeyFrameIndex(stateTime) == (animationSet
					.getCurrentAnimationDescription().frameRange - 1)) {
				setAnimation(animationSet.next().name);
			}
		}
	}

	// ---------------------Private helper method
	// -------------------------------------------
	private void loadAnimation() {

		TextureRegion[][] tmp = TextureRegion.split(spriteSheetTexture,
				spriteSheetTexture.getWidth() / animCols, spriteSheetTexture.getHeight()
						/ animRows);
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

	private void loadAnimationSet() {
		String temp[] = anims.split(",");
		for (int i = 0; i < temp.length; i+=4) {
			animationSet.add(new AnimationDescription(temp[i], Integer
					.parseInt(temp[i + 1]), Integer.parseInt(temp[i + 2]),
					temp[i + 3]));
		}

	}

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
			if(i == 0)
				currentAnimation = new Animation(0.025f, tempRegion);
		}
	}

	public void chooseDestination() {
		destination.set(MathUtils.random(0, roamingRadius),
				MathUtils.random(0, roamingRadius));
		if (originalPosition.dst(destination) > roamingRadius)
			chooseDestination();
	}
}
