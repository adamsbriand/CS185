package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 
/* ======================================================================================
 * File:			ScreenTest.java
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
 * This menu is for development. This provides quick access to parts of the game for
 * testing.  Most of this code is from tutorials. It looks bad, but is not intended for
 * public consumption. 
 * 
 * ======================================================================================
 */

public class ScreenTest implements Screen {
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    
    /**----------------------------------------------------------------------------------
	 * Constructor
	 * 
	 * Calls:
	 * 		super
	 * ----------------------------------------------------------------------------------
	 */
    public ScreenTest (){
        create();
    }
    
    /**----------------------------------------------------------------------------------
	 * Sets up the stage, and sets the skins for the buttons. Then sets the buttons.
	 * This is all very messy. Only for developers type of thing. Why are you reading
	 * this comment. It is completely useless. Well, not completely. I guess it does have
	 * some use. Well, in any case, this method is a complete mess. 
	 * ----------------------------------------------------------------------------------
	 */
    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
 
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
 
        skin.add("white", new Texture(pixmap));
 
        BitmapFont bfont=new BitmapFont();
        bfont.scale(1);
        skin.add("default",bfont);
 
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);
        
        final int dX     = 200; // 300
        final int dY     = 100; // 120
        final int xOff = 50; // 200
        final int yOff = 100; // 200
        
        final TextButton textButton=new TextButton("L1 R1",textButtonStyle);
        textButton.setPosition(xOff+0*dX, yOff+1*dY);
        stage.addActor(textButton);
        
        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Run1,changeMusic,song1");
            }
        });
        
        final TextButton textButton2=new TextButton("L1 M1",textButtonStyle);
        textButton2.setPosition(xOff+1*dX, yOff+1*dY);
        stage.addActor(textButton2);
        
        textButton2.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,Level1Maze1");
        	}
        });
        
        final TextButton textButton4=new TextButton("L1 M2",textButtonStyle);
        textButton4.setPosition(xOff+2*dX, yOff+1*dY);
        stage.addActor(textButton4);
        
        textButton4.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,Level1Maze2,changeMyAnim,Bowling");
        	}
        });
        
        final TextButton textButton3=new TextButton("L2 M1",textButtonStyle);
        textButton3.setPosition(xOff+0*dX, yOff+0*dY);
        stage.addActor(textButton3);
        
        textButton3.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,Level2Maze1");
        	}
        });
        
        final TextButton textButton5=new TextButton("L2 R1",textButtonStyle);
        textButton5.setPosition(xOff+1*dX, yOff+0*dY);
        stage.addActor(textButton5);
        
        textButton5.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,Level2Runner1,changeMyAnim,Tennis");
        	}
        });
        
        final TextButton textButton6=new TextButton("volcano",textButtonStyle);
        textButton6.setPosition(xOff+2*dX, yOff+0*dY);
        stage.addActor(textButton6);
        
        textButton6.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,volcano");
        	}
        });
        
        final TextButton textButton7=new TextButton("endDialog",textButtonStyle);
        textButton7.setPosition(xOff+0*dX, yOff+2*dY);
        stage.addActor(textButton7);
        
        textButton7.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,dialogOutro");
        	}
        });
    }
 
    /**----------------------------------------------------------------------------------
     * Display everything. 
     * ----------------------------------------------------------------------------------
     */
    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Table.drawDebug(stage);
    }
 
    /**----------------------------------------------------------------------------------
     * Unused abstract method.
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void resize (int width, int height) {
    }
 
    /**----------------------------------------------------------------------------------
     * Dispose of stage and skin when disposed. 
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
    }
 
    /**----------------------------------------------------------------------------------
     * Unused abstract method.
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void show() {
        // TODO Auto-generated method stub
    }
 
    /**----------------------------------------------------------------------------------
     * Unused abstract method.
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }
    
    /**----------------------------------------------------------------------------------
     * Unused abstract method.
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }
    
    /**----------------------------------------------------------------------------------
     * Unused abstract method.
     * ----------------------------------------------------------------------------------
     */
    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }
}