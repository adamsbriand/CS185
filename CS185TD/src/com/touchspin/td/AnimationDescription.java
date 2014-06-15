package com.touchspin.td;

public class AnimationDescription {
/* ======================================================================================
 * AnimationDescription.java
 * 
 * This class holds  information about an animation includes name, startFrame, endFrame
 * frame range and the next animation name after the current animation.
 * ======================================================================================
 */
	public int frameFirst = 0;
	public int frameLast = 0;
	public int frameRange = 0;
	public String name = "";
	public String nextAnim = "";

/*
 * Defualt constructor
 */
	public AnimationDescription() {
	}
/**
 * Overloaded constructor
 * @param name - the name of current anmiation
 * @param frameFirst - the index of the region of the 
 * first frame on the spritesheet
 * @param frameLast - the index of the region of last frame on the spritesheet
 * @param nextAnim - the name of the next animation
 */
	public AnimationDescription(String name, int frameFirst, int frameLast,
			String nextAnim) {
		this.name = name;
		this.frameFirst = frameFirst;
		this.frameLast = frameLast;
		this.nextAnim = nextAnim;
		frameRange = frameLast - frameFirst + 1;
	}
}
