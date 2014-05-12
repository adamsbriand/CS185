package com.touchspin.td;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.input.GestureDetector;


public class MainGame implements ApplicationListener {
    int screenXorg;
    int screenYorg;
	GameObject gameObj;
    @Override public void create () {
    	 gameObj = new Runner();
    	 InputMultiplexer im = new InputMultiplexer();
         GestureDetector gd = new GestureDetector(gameObj.anonymizer);
         im.addProcessor(gd);
         im.addProcessor(gameObj.anonymizer);
    	 Gdx.input.setInputProcessor(im);
    }

    @Override public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameObj.render();
        
    }

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}