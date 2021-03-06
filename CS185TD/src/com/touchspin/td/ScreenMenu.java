package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

/* ======================================================================================
 * File:			ScreenMenu.java
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
 * Shows the main menu. 
 * 
 * ======================================================================================
 */

public class ScreenMenu extends GameMenu {

	/**----------------------------------------------------------------------------------
	 * Constructor
	 * 
	 * Calls:
	 * 		super
	 * ----------------------------------------------------------------------------------
	 */
	public ScreenMenu (){
    	super();
    }
    
	/**----------------------------------------------------------------------------------
	 * Sets up all the buttons on the screen. This class uses 4 buttons.  
	 * ----------------------------------------------------------------------------------
	 */
    public void buttons(){
    	float xRow1 = Gdx.graphics.getHeight() / 15 * 6;
    	float xRow2 = Gdx.graphics.getHeight() / 15 * 3;
    	float yRow1 = Gdx.graphics.getWidth() / 3;
    	float yRow2 = Gdx.graphics.getWidth() / 3 * 2;
    	float height = 0;
    	float width = 0;

    	final TextButton newGame = new TextButton(g.i().NewGame, style);
        newGame.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Dialog1,playSound,buttonClick");
            }
        });
        stage.addActor(newGame);
        
        final TextButton options = new TextButton(g.i().Options, style);
        options.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,options,playSound,buttonClick");
        	}
        });
        stage.addActor(options);
        
        final TextButton highScore = new TextButton(g.i().Credits, style);
        //highScore.setDisabled(true);
        //highScore.setColor(Color.GRAY);
        highScore.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,credits,playSound,buttonClick");
        	}
        });
        stage.addActor(highScore);
        
        final TextButton exit = new TextButton(g.i().Exit, style);
        exit.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("playSound,buttonClick");
        		Gdx.app.exit();
        	}
        });
        stage.addActor(exit);
        
    	for (int i=0; i < 4; i++){
        	stage.getActors().items[i].setHeight(Gdx.graphics.getHeight() / 7);
        	stage.getActors().items[i].setWidth(Gdx.graphics.getWidth() / 4.5f);
        }
        
        height = stage.getActors().items[0].getHeight() / 2;
        width = stage.getActors().items[0].getWidth() / 2;
        
        stage.getActors().items[0].setPosition(yRow1 - width, xRow1 - height);
        stage.getActors().items[1].setPosition(yRow2 - width, xRow1 - height);
        stage.getActors().items[2].setPosition(yRow1 - width, xRow2 - height);
        stage.getActors().items[3].setPosition(yRow2 - width, xRow2 - height);
    }

    /**----------------------------------------------------------------------------------
	 * Sets the a logo image on the screen. This method is called from the parent class.
	 * ----------------------------------------------------------------------------------
	 */
    public void logo(){
    	FileHandle imgLogo = Gdx.files.internal("img/menu/LogoTitle.png");
    	super.setLogo(imgLogo);
    }
}