package com.touchspin.td;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameObject implements Screen{

	protected TiledMapWrapper tiledMapWrapper;
	protected OrthographicCamera camera;
	protected InputAnonymizer anonymizer;
    
    public abstract void update();
	public abstract void draw();
	@Override
	public abstract void render(float delta);
	@Override
	public abstract void resize(int width, int height);
	@Override
	public abstract void show();
	@Override
	public abstract void hide();
	@Override
	public abstract void pause();
	@Override
	public abstract void resume();
	@Override
	public abstract void dispose();
}
