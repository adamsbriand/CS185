package com.touchspin.td;

public class AnimationDescription {

	public int frameFirst = 0;
	public int frameLast = 0;
	public int frameRange = 0;
	public String name = "";
	public String nextAnim = "";

	public AnimationDescription() {
	}

	public AnimationDescription(String name, int frameFirst, int frameLast,
			String nextAnim) {
		this.name = name;
		this.frameFirst = frameFirst;
		this.frameLast = frameLast;
		this.nextAnim = nextAnim;
		frameRange = frameLast - frameFirst + 1;
	}
}
