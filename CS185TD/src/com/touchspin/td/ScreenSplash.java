package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenSplash extends GameMenu{
	BitmapFont font;
	String message;
	Texture texture;
	private SpriteBatch batch;
	float x;
	float y;
	MainGame game;
	Sprite sprite;

	public ScreenSplash( MainGame mainGame){
		super(mainGame);
		//batch = new SpriteBatch();
	}

	@Override
	protected void setBG(){
    	float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();
	    camera = new OrthographicCamera();
	    bg = new Sprite(new Texture(Gdx.files.internal("img/menu/Title.png")));
	    bg.setOrigin(bg.getWidth() / 2, bg.getHeight() / 2);
	    bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

	@Override
	public void update() {
		if(g.i().leAnonymizer.click)
		{
			g.i().t.action("menu,Main");
		}
		if (TimeUtils.millis()>(g.i().timeStartGame+1000)){
			g.i().t.action("menu,Main");
		}
	}

	/*
	@Override
	public void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();	
	}

	@Override
	public void render(float delta) {

			update();
			draw();
	}
*/
	
	@Override
	public void dispose() {
		texture.dispose();
        batch.dispose();
	}

	@Override
	void buttons() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void logo() {
		// TODO Auto-generated method stub
		
	}
}