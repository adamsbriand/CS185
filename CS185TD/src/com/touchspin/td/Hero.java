package com.touchspin.td;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Hero extends Actor {
	public OrthographicCamera camera;
	public TiledMapWrapper tiledMapWrapper;
	public static InputMover heroMover = new InputMover();
	public abstract Rectangle getCollisionRect();
}
