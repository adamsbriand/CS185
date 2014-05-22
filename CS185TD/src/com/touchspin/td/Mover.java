package com.touchspin.td;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Mover {
	GameThing gameThing;
	float speedXPerSecond;
	float speedYPerSecond;
	public abstract void move(GameThing gameThing);
}
