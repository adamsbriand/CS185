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
 * File:			ScreenGameOver.java
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
 * Displays a game over screen. 
 * 
 * ======================================================================================
 */

public class ScreenGameOver extends GameMenu{
	
	// Constants
	private static final String bgImage = "img/menu/TeamOllie1200.png";
	private static final String logoImage = "img/menu/LogoGameOver.png";
	
	// Variables
	long timepoint;
	
	/**----------------------------------------------------------------------------------
	 * Constructor
	 * 
	 * Calls:
	 * 		super
	 * ----------------------------------------------------------------------------------
	 */
	public ScreenGameOver(){
		super();
		timepoint = System.currentTimeMillis();
		g.i().sound.sfxMute(true);
		g.i().sound.BGMusic("menu");
		
		//reset
		g.i().setZero();
	}
	
	/**----------------------------------------------------------------------------------
	 * Sets up all the buttons on the screen. 
	 * The game over screen is set as one large button. 
	 * ----------------------------------------------------------------------------------
	 */
	void buttons() {
		stage.clear();
		NinePatchDrawable draw = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal(bgImage))));
		BitmapFont bfont = new BitmapFont();
        bfont.scale(0);
		TextButton btn = new TextButton("", new TextButtonStyle(draw, draw, draw, bfont));
		float ratio = Gdx.graphics.getHeight() / btn.getHeight();
		btn.setHeight(btn.getHeight() * ratio);
		btn.setWidth(btn.getWidth() * ratio);
		if (btn.getWidth() > Gdx.graphics.getWidth()){
			ratio = Gdx.graphics.getWidth() / btn.getHeight();
			btn.setWidth(btn.getWidth() * ratio);
			btn.setHeight(btn.getHeight() * ratio);
		}
		btn.setOrigin(btn.getWidth() / 2, btn.getHeight() / 2);
		btn.setPosition((Gdx.graphics.getWidth() - btn.getWidth()) / 2, (Gdx.graphics.getHeight() - btn.getHeight()) / 2);
		btn.setVisible(false);
		btn.setVisible(true);
		btn.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,Main");
        	}
        });
		stage.addActor(btn);
	}

	/**----------------------------------------------------------------------------------
	 * Sets the a logo image on the screen. This method is called from the parent class.
	 * The size and position are overridden for this class. 
	 * ----------------------------------------------------------------------------------
	 */
	void logo() {
		super.setLogo(Gdx.files.internal(logoImage));
		logo.setSize(logo.getWidth() * .75f, logo.getHeight() *.75f);
		logo.setPosition((Gdx.graphics.getWidth() - logo.getWidth()) / 2, 
				logo.getY() - logo.getHeight() / 5);
	}
	
	/**----------------------------------------------------------------------------------
	 * Set this class to auto-change to main menu if the user waits for 30 seconds. 
	 * ----------------------------------------------------------------------------------
	 */
	public void update() {
		final int secondsWait = 30;
		if (TimeUtils.millis()>(timepoint + (1000 * secondsWait))){
			g.i().t.action("menu,Main");
		}
	}

}