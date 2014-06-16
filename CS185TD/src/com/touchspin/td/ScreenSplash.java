package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
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
 * Shows a splash screen. 
 * 
 * ======================================================================================
 */

public class ScreenSplash extends GameMenu{
	long timeStartGame;

	/**----------------------------------------------------------------------------------
	 * Constructor
	 * 
	 * Calls:
	 * 		super
	 * ----------------------------------------------------------------------------------
	 */
	public ScreenSplash(){
		super();
		timeStartGame = System.currentTimeMillis();
		g.i().sound.BGMusic("menu");
	}

	/**----------------------------------------------------------------------------------
	 * Set this class to auto-change to main menu if the user waits for 5 seconds. 
	 * ----------------------------------------------------------------------------------
	 */
	@Override
	public void update() {
		final int secondsWait = 5;
		if (TimeUtils.millis()>(timeStartGame + ( 1000 * secondsWait))){
			g.i().t.game.setScreen(new ScreenMenu());
		}
	}

	/**----------------------------------------------------------------------------------
	 * Sets up all the buttons on the screen. 
	 * The splash screen is set as one large button. 
	 * ----------------------------------------------------------------------------------
	 */
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
        		g.i().t.game.setScreen(new ScreenMenu());
        	}
        });
		stage.addActor(btn);
	}

	/**----------------------------------------------------------------------------------
	 * Sets the a logo image on the screen. This method is called from the parent class
	 * and is not set in this case. 
	 * ----------------------------------------------------------------------------------
	 */
	@Override
	void logo() {
		// TODO Auto-generated method stub
	}
}