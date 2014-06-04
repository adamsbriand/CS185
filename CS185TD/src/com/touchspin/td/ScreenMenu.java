package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 
public class ScreenMenu extends GameMenu {

	public ScreenMenu (MainGame MainGame){
    	super(MainGame);
    }
    
    public void buttons(){
    	float x;
    	float y;
    	TextButton textButton;
        textButton = new TextButton("New Game", textButtonStyle);
        x = ((Gdx.graphics.getHeight() / 11) * 4) - (textButton.getHeight() / 2);
        y = (Gdx.graphics.getWidth() / 3) - (textButton.getWidth() / 2);
        textButton.setPosition(y, x);
        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Dialog1");
            }
        });
        stage.addActor(textButton);
        
        textButton = new TextButton("Test Menu", textButtonStyle);
        x = ((Gdx.graphics.getHeight() / 11) * 4) - (textButton.getHeight() / 2);
        y = ((Gdx.graphics.getWidth() / 3) * 2)  - (textButton.getWidth() / 2);
        textButton.setPosition(y, x);
        textButton.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,Test");
        	}
        });
        stage.addActor(textButton);
    }

    public void logo(){
    	FileHandle imgLogo = Gdx.files.internal("img/menu/LogoTitle.png");
    	super.setLogo(imgLogo);
    }
}