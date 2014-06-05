package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGameOver extends GameMenu{
	long timeStartGame;
	
	public ScreenGameOver(MainGame MainGame){
		super(MainGame);
		timeStartGame = System.currentTimeMillis();
		g.i().sound.sfxMute(true);
		g.i().sound.BGMusic("menu");
		
		//reset
		g.i().currentBallType = "Baseball";
		g.i().fire = false;
		g.i().playerHealth = 100;
	}

	void buttons() {
		final float xRow1 = Gdx.graphics.getHeight() / 15 * 6;
		final float xRow2 = Gdx.graphics.getHeight() / 15 * 3;
    	final float yRow1 = Gdx.graphics.getWidth() / 3;
    	final float yRow1_2 = Gdx.graphics.getWidth() / 2;
    	final float yRow2 = Gdx.graphics.getWidth() / 3 * 2;
    	float height = 0;
    	float width = 0;
    	
    	//final Label message = new Label("You have won the game.", null);
    	
    	final TextButton continueButton = new TextButton("Continue", textButtonStyle);
    	continueButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("menu,Main");
            }
        });
        stage.addActor(continueButton);
        
        height = continueButton.getHeight() / 2;
        width = continueButton.getWidth() / 2;
        
        continueButton.setPosition(yRow1_2 - width, xRow2 - height);
	}

	@Override
	void logo() {
		super.setLogo(Gdx.files.internal("img/menu/LogoGameOver.png"));
	}
	
	public void update() {
		int secondsWait = 30;
		if (TimeUtils.millis()>(timeStartGame + (1000 * secondsWait))){
			g.i().t.action("menu,Main");
		}
	}

}