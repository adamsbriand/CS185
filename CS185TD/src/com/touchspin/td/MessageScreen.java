package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class MessageScreen extends GameObject{
	BitmapFont font;
	String message;
	Texture texture;
	private SpriteBatch batch;
	float x;
	float y;
	MainGame game;
	
	public MessageScreen( MainGame mainGame)
	{
		batch = new SpriteBatch();
		game = mainGame;
		font = new BitmapFont();
        font.setScale(3);
        font.setColor(Color.WHITE);
        message = "Click to start the game";
        setMessage(message);
	}
	
	public void setMessage(String message)
	{
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();
		this.message = message;
		texture = new Texture(Gdx.files.internal("data/splash.png"));
		TextBounds tb = font.getBounds(message);
        x = w/2 - texture.getWidth()/2;
        y = h/2 + texture.getHeight()/2;
	}
	
	@Override
	public void update() {
		if(g.i().leAnonymizer.click)
		{
			new Trigger(game,"menu","Main");
		}
		if (TimeUtils.millis()>(g.i().timeStartGame+5000)){
			new Trigger(game,"menu","Main");
		}
		g.i().accelZ = 0;
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
