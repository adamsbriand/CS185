package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.TimeUtils;

/* ======================================================================================
 * File:			GameMenu.java
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
 * Holds methods common to all the game menus.
 * 
 * ======================================================================================
 */
 
public abstract class GameMenu extends GameObject {
	
	// Constants
	private static final FileHandle ButtonUp = 
			Gdx.files.internal("img/menu/Btn9SliceUp.png");
	private static final FileHandle ButtonDown = 
			Gdx.files.internal("img/menu/Btn9SliceDown.png");
	private static final FileHandle ButtonClick = 
			Gdx.files.internal("img/menu/Btn9SliceChecked.png");
	private static final FileHandle bgImage = 
			Gdx.files.internal("img/menu/ScreenBG.png");
	
	// Variables
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    MainGame game;
    Sprite bg;
    Sprite logo;
    TextButtonStyle style;
    float width;
    float height;
    long timeDelay = 0;
    
    /* ----------------------------------------------------------------------------------
	 * Constructor.
	 * Sets defaults
	 * 
	 * Calls:
	 * 		setTemplet
	 * 		setBG
	 * 		buttons
	 * 		logo
	 * ----------------------------------------------------------------------------------
	 */
    public GameMenu (){
    	setTemplet();
        setBG();
        buttons();
        logo();
    }
    
    /* ----------------------------------------------------------------------------------
	 * Abstract methods
	 * ----------------------------------------------------------------------------------
	 */
    abstract void buttons();
    abstract void logo();
    
    /* ----------------------------------------------------------------------------------
	 * Sets up the background for the screen. 
	 * ----------------------------------------------------------------------------------
	 */
    void setBG(){
	    bg = new Sprite(new Texture(bgImage));
	    bg.setOrigin(bg.getWidth() / 2, bg.getHeight() / 2);
	    bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    /* ----------------------------------------------------------------------------------
	 * Sets up the stage and the styles used for the buttons. 
	 * ----------------------------------------------------------------------------------
	 */
    protected void setTemplet(){
    	// Stage setup
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        // Button textures
        NinePatchDrawable npUP = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonUp), 20, 20, 20, 20));
        NinePatchDrawable npDown = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonDown), 20, 20, 20, 20));
        NinePatchDrawable npCheck = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonClick), 20, 20, 20, 20));
 
        // Font information
        BitmapFont font = new BitmapFont(g.i().font);
        //if (Gdx.app.getType().toString() == "Desktop"){
        //	font.scale(1);
        //} else {
        //	font.scale(3);
        //}
        font.scale(Gdx.graphics.getDensity());
        
        // Set the style
        style = new TextButtonStyle(npUP,  npDown, npCheck, font);
        style.pressedOffsetX = 3;
        style.pressedOffsetY = -3;
    }
    
    /* ----------------------------------------------------------------------------------
	 * Sets up a logo from for the screen.
	 * 
	 * Input:
	 * 		logoImg - The file location of the logo img
	 * ----------------------------------------------------------------------------------
	 */
    protected void setLogo(FileHandle logoImg){
    	// Check to see if file name has been provided
    	if (logoImg == null) return;
    	
    	// Set the logo image and position. 
    	logo = new Sprite(new Texture(logoImg));
    	logo.setSize(Gdx.graphics.getWidth() / 9 * 4, Gdx.graphics.getHeight() / 9 * 4);
    	float x = ((Gdx.graphics.getWidth() - logo.getWidth()) / 2f) ;
    	float y = (Gdx.graphics.getHeight() / 4f * 3f - logo.getHeight() / 2f);
    	logo.setPosition(x, y);
    }
 
    /* ----------------------------------------------------------------------------------
	 * Draw the bg and the stage.
	 * 
	 * Input:
	 * 		delta
	 * ----------------------------------------------------------------------------------
	 */
    public void render (float delta) {
    	update();
        batch.begin();
        bg.draw(batch);
        if (logo != null) logo.draw(batch);
        batch.end();
        stage.draw();
        //Table.drawDebug(stage);
    }
    
    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
 
    @Override
    public void resize (int width, int height) {
    	// TODO Auto-generated method stub
    }
 
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void hide() {
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
	public void draw() {
		// TODO Auto-generated method stub
	}
}