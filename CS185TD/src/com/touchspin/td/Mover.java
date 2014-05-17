package com.touchspin.td;

public abstract class Mover {
	Hero hero;
	float speedXPerSecond;
	float speedYPerSecond;
	public abstract void move(Hero hero);
}
