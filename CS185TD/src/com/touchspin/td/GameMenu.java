package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    TextButtonStyle textButtonStyle;
    float width;
    float height;
    long timeresize = 0;
    
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
    
    abstract void buttons();
    abstract void logo();
    
    void setBG(){
	    camera = new OrthographicCamera();
	    bg = new Sprite(new Texture(bgImage));
	    bg.setOrigin(bg.getWidth() / 2, bg.getHeight() / 2);
	    bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    protected void setTemplet(){
    	
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        NinePatchDrawable npUP = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonUp), 20, 20, 20, 20));
        NinePatchDrawable npDown = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonDown), 20, 20, 20, 20));
        NinePatchDrawable npCheck = new NinePatchDrawable(
        		new NinePatch(new Texture(ButtonClick), 20, 20, 20, 20));
 
        BitmapFont font = new BitmapFont(g.i().font);
        //if (Gdx.app.getType().toString() == "Desktop"){
        //	font.scale(1);
        //} else {
        //	font.scale(3);
        //}
        font.scale(Gdx.graphics.getDensity());
        textButtonStyle = new TextButtonStyle(npUP,  npDown, npCheck, font);
        textButtonStyle.pressedOffsetX = 3;
        textButtonStyle.pressedOffsetY = -3;
    }
    
    protected void setLogo(FileHandle logoImg){
    	if (logoImg == null) return;
    	logo = new Sprite(new Texture(logoImg));
    	logo.setSize(Gdx.graphics.getWidth() / 9 * 4, Gdx.graphics.getHeight() / 9 * 4);
    	float x = (float)((Gdx.graphics.getWidth() - logo.getWidth()) / 2) ;
    	float y = (float)(Gdx.graphics.getHeight() / 4 * 3 - logo.getHeight() / 2);
    	logo.setPosition(x, y);
    }
 
    public void render (float delta) {
    	update();
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        bg.draw(batch);
        if (logo != null) logo.draw(batch);
        batch.end();
        stage.draw();
        Table.drawDebug(stage);
    }

	@Override
	public void update() {
		if (width != Gdx.graphics.getWidth() || height != Gdx.graphics.getHeight()){
			width = Gdx.graphics.getWidth();
			height = Gdx.graphics.getHeight();
			timeresize = TimeUtils.millis();
		}
		if (timeresize!=0){
			if (TimeUtils.millis()>(timeresize + 100)){
				stage.clear();
				batch = new SpriteBatch();
				setBG();
				buttons();
		        logo();
		        timeresize = 0;
			}
		}
	}
 
    @Override
    public void resize (int width, int height) {
    	// TODO Auto-generated method stub
    }
 
    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
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