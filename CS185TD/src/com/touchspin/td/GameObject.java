package com.touchspin.td;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public abstract class GameObject {

	TiledMapWrapper tiledMapWrapper;
    OrthographicCamera camera;
    InputAnonymizer anonymizer;
    
    public abstract void update();
	public abstract void render();
	public abstract void draw();
}
