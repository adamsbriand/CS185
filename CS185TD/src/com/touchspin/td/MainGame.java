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
	
    @Override 
    public void create () {
    	MessageScreen messageScreen = new MessageScreen(this);
    	//set current game 
    	setScreen(messageScreen);
    	//set current Anonymizer
    	changeAnonymizer(messageScreen.anonuymizer);
//    	setScreen(runner);
//    	changeAnonymizer(runner.anonymizer);
    	 
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
	
	public void changeAnonymizer(InputAnonymizer anonymizer)
	{
		InputMultiplexer im = new InputMultiplexer();
        GestureDetector gd = new GestureDetector(anonymizer);
        im.addProcessor(gd);
        im.addProcessor(anonymizer);
   	 	Gdx.input.setInputProcessor(im);
	}

}