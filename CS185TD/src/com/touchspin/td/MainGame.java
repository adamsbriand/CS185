package com.touchspin.td;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.input.GestureDetector;


public class MainGame extends Game {
    int screenXorg;
    int screenYorg;
	InputAnonymizer anonymizer;
    @Override 
    public void create () {
    	
    	anonymizer = new InputAnonymizer();
    	//set current game 
    	MessageScreen messageScreen = new MessageScreen(this);
		InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(anonymizer);
        im.addProcessor(gd);
        im.addProcessor(anonymizer);
   	 	Gdx.input.setInputProcessor(im);
    	setScreen(messageScreen);
    	 
    }

    @Override public void render () {

    	super.render();
//		gameObj.render();
        
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