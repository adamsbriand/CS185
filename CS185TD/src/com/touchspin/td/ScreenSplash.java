package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.TimeUtils;

/* ======================================================================================
 * File:			ScreenSplash.java
 * Authors:			Brian Adams - b.adams5736@edmail.edcc.edu
 * 					Russell Brendel - russell.brendel.2925@edmail.edcc.edu
 * 					Damian Forrester - dforrester777@gmail.com
 * 					Wendi Tang - w.tang2404@myedmail.edcc.edu
 * 
 * Organization:	Edmonds Community College
 * Term:			Spring 2014
 * Class:			CS 185 - Game Project Developement
 * Instructor:		Tim Hunt - thunt@edcc.edu
 * 
 * Project:			Ollie
 * --------------------------------------------------------------------------------------
 * 
 *  
 * 
 * ======================================================================================
 */

public class ScreenSplash extends GameMenu{
	long timeStartGame;

	public ScreenSplash(){
		super();
		timeStartGame = System.currentTimeMillis();
		g.i().sound.BGMusic("menu");
	}

	@Override
	public void update() {
		final int secondsWait = 5;
		if (TimeUtils.millis()>(timeStartGame + ( 1000 * secondsWait))){
			g.i().t.action("menu,Main");
		}
		if (width != Gdx.graphics.getWidth() || height != Gdx.graphics.getHeight()){
			width = Gdx.graphics.getWidth();
			height = Gdx.graphics.getHeight();
			timeDelay = TimeUtils.millis();
		}
		if (timeDelay!=0){
			if (TimeUtils.millis()>(timeDelay + 100)){
				stage.clear();
				batch = new SpriteBatch();
				stage.getCamera().viewportWidth = Gdx.graphics.getWidth();
			    stage.getCamera().viewportHeight = Gdx.graphics.getHeight();
				setBG();
				buttons();
		        logo();
		        timeDelay = 0;
			}
		}
	}

	@Override
	void buttons() {
		NinePatchDrawable draw = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal("img/menu/Title.png"))));
		BitmapFont bfont = new BitmapFont();
        bfont.scale(0);
		TextButton btn = new TextButton("", new TextButtonStyle(draw, draw, draw, bfont));
		btn.setHeight(Gdx.graphics.getHeight());
		btn.setWidth(Gdx.graphics.getWidth());
		btn.setVisible(false);
		btn.setVisible(true);
		btn.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,Main");
        	}
        });
		stage.addActor(btn);
	}

	@Override
	void logo() {
		// TODO Auto-generated method stub
	}
}