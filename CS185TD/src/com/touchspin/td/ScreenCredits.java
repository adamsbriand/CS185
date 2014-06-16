package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
 * File:			ScreenCredits.java
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
 * Shows the credits image.  
 * 
 * ======================================================================================
 */

public class ScreenCredits extends GameMenu {
	// Constants
	final String Creditsimage = "img/menu/TeamOllie2.png";
	
	// Variables
	long timepoint;
	
	/**----------------------------------------------------------------------------------
	 * Constructor
	 * 
	 * Calls:
	 * 		super
	 * ----------------------------------------------------------------------------------
	 */
	public ScreenCredits(){
		super();
		timepoint = System.currentTimeMillis();
		//reset the game to default values
		g.i().setZero();
	}

	/**----------------------------------------------------------------------------------
	 * Sets up all the buttons on the screen. 
	 * The credits screen is set as one large button. 
	 * ----------------------------------------------------------------------------------
	 */
	@Override
	void buttons() {
			NinePatchDrawable draw = new NinePatchDrawable(
					new NinePatch(new Texture(Gdx.files.internal(Creditsimage))));
			BitmapFont bfont = new BitmapFont();
	        bfont.scale(0);
			TextButton btn = new TextButton("", 
					new TextButtonStyle(draw, draw, draw, bfont));
			float ratio = Gdx.graphics.getHeight() / btn.getHeight();
			btn.setHeight(btn.getHeight() * ratio);
			btn.setWidth(btn.getWidth() * ratio);
			if (btn.getWidth() > Gdx.graphics.getWidth()){
				ratio = Gdx.graphics.getWidth() / btn.getHeight();
				btn.setWidth(btn.getWidth() * ratio);
				btn.setHeight(btn.getHeight() * ratio);
			}
			btn.setOrigin(btn.getWidth() / 2, btn.getHeight() / 2);
			btn.setPosition((Gdx.graphics.getWidth() - btn.getWidth()) / 2, 
					(Gdx.graphics.getHeight() - btn.getHeight()) / 2);
			btn.setVisible(false);
			btn.setVisible(true);
			btn.addListener(new ChangeListener() {
	        	public void changed (ChangeEvent event, Actor actor) {
	        		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || 
	        				Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
	        			g.i().t.game.setScreen(new ScreenTest());
	        		} else {
	        			g.i().t.game.setScreen(new ScreenMenu());
	        		}
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
	}
	
	/**----------------------------------------------------------------------------------
	 * Set this class to auto-change to main menu if the user waits for 30 seconds. 
	 * ----------------------------------------------------------------------------------
	 */
	public void update() {
		final int secondsWait = 30;
		if (TimeUtils.millis()>(timepoint + (1000 * secondsWait))){
			g.i().t.game.setScreen(new ScreenMenu());
		}
	}
}
