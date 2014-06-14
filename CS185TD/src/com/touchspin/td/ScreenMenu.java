package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 
public class ScreenMenu extends GameMenu {

	public ScreenMenu (){
    	super();
    }
    
    public void buttons(){

    	float xRow1 = Gdx.graphics.getHeight() / 15 * 6;
    	float xRow2 = Gdx.graphics.getHeight() / 15 * 3;
    	float yRow1 = Gdx.graphics.getWidth() / 3;
    	float yRow2 = Gdx.graphics.getWidth() / 3 * 2;
    	float height = 0;
    	float width = 0;
    	
    	final TextButton newGame = new TextButton(g.i().NewGame, textButtonStyle);
        newGame.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Dialog1,playSound,buttonClick");
            }
        });
        stage.addActor(newGame);
        
        final TextButton options = new TextButton(g.i().Options, textButtonStyle);
        options.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,options,playSound,buttonClick");
        	}
        });
        stage.addActor(options);
        
        final TextButton highScore = new TextButton(g.i().HighScore, textButtonStyle);
        //highScore.setDisabled(true);
        highScore.setColor(Color.GRAY);
        highScore.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,Test,playSound,buttonClick");
        	}
        });
        stage.addActor(highScore);
        
        final TextButton exit = new TextButton(g.i().Exit, textButtonStyle);
        exit.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("playSound,buttonClick");
        		Gdx.app.exit();
        	}
        });
        stage.addActor(exit);
        
        for (int i=0; i < stage.getActors().size; i++){
        	if (height < stage.getActors().items[i].getHeight()){
        		height = stage.getActors().items[i].getHeight();
        	}
        	if (width < stage.getActors().items[i].getWidth()){
        		width = stage.getActors().items[i].getWidth();
        	}
        }
        for (int i=0; i < stage.getActors().size; i++){
        	stage.getActors().items[i].setHeight(height);
        	stage.getActors().items[i].setWidth(width);
        }
        height = height / 2;
        width = width / 2;
        
        newGame.setPosition(yRow1 - width, xRow1 - height);
        options.setPosition(yRow2 - width, xRow1 - height);
        highScore.setPosition(yRow1 - width, xRow2 - height);
        exit.setPosition(yRow2 - width, xRow2 - height);
    }

    public void logo(){
    	FileHandle imgLogo = Gdx.files.internal("img/menu/LogoTitle.png");
    	super.setLogo(imgLogo);
    }
}