package com.touchspin.td;

public abstract class Mover {
	GameThing gameThing;
	float speedXPerSecond;
	float speedYPerSecond;
	public abstract void move(GameThing gameThing);
}
