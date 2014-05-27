package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenSplash extends GameObject{
	BitmapFont font;
	String message;
	Texture texture;
	private SpriteBatch batch;
	float x;
	float y;
	MainGame game;
	
	public ScreenSplash( MainGame mainGame)
	{
		batch = new SpriteBatch();
		game = mainGame;
        setSplash();
	}
	
	public void setSplash()
	{
		texture = new Texture(Gdx.files.internal("data/splash.png"));
	}
	
	@Override
	public void update() {
		if(g.i().leAnonymizer.click)
		{
			g.i().t.action("menu,Main");
		}
		if (TimeUtils.millis()>(g.i().timeStartGame+5000)){
			g.i().t.action("menu,Main");
		}
	}

	@Override
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture,0,0);
        //font.drawMultiLine(batch, message, x, y-100);
        batch.end();	
	}
	
	@Override
	public void render(float delta) {

			update();
			draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		texture.dispose();
        batch.dispose();
	}
}
