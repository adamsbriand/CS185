package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 
public class ScreenOptions extends GameMenu {
	String levelReturn;
	char menuLevel;

	public ScreenOptions (MainGame MainGame){
		super(MainGame);
		levelReturn = "Main";
    }
	
	public ScreenOptions (MainGame MainGame, String level){
		super(MainGame);
		levelReturn = level;
	}
    
    public void buttons(){
    	stage.clear();
    	final TextButton button1 = new TextButton("Mute Music", textButtonStyle);
    	final TextButton button2 = new TextButton("", textButtonStyle);
    	final TextButton button3 = new TextButton("", textButtonStyle);
    	final TextButton button4 = new TextButton("", textButtonStyle);
    	ChangeListener muteMusicListen = new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("playSound,buttonClick");
            	g.i().music = ! g.i().music;
            	if (g.i().music){
            		button1.setChecked(false);
            		g.i().sound.BGMusic("mute");
            	} else {
            		button1.setChecked(true);
            		g.i().sound.BGMusic("mute");
            	}}};
        ChangeListener muteSFXListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("playSound,buttonClick");
        		g.i().sfx = ! g.i().sfx;
            	if (g.i().sfx){
            		button2.setChecked(false);
            		g.i().sfxLevel = 1;
            	} else {
            		button2.setChecked(true);
            		g.i().sfxLevel = 0;
            	}}};
        ChangeListener languageListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("playSound,buttonClick");
        		menuLevel = 'L';
        	}};
        ChangeListener mainMenuListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel," + levelReturn + ",playSound,buttonClick");
        	}};
        ChangeListener soundsListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		g.i().t.action("playSound,buttonClick");
        		menuLevel = 'S';
        		buttons();
        	}};
        ChangeListener conrolsListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		g.i().t.action("playSound,buttonClick");
        		menuLevel = 'C';
        		buttons();
        	}};
        ChangeListener optionsBack = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		g.i().t.action("playSound,buttonClick");
        		menuLevel = 'O';
        		buttons();
        	}};
        ChangeListener controlChange = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		g.i().t.action("playSound,buttonClick");
        		switch (g.i().controls) {
		    		case 'A':
		    			button1.setText("Mouse & Keyboard");
		    			g.i().controls = 'D';
		    			break;
		    		case 'K':
		    			if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)){
			    			button1.setText("Gyro");
			    			g.i().controls = 'A';
		    			} else {
		    				button1.setText("Mouse");
			    			g.i().controls = 'M';
		    			}
		    			break;
		    		case 'M':
		    			button1.setText("Keyboard");
		    			g.i().controls = 'K';
		    			break;
		    		case 'D':
		    			button1.setText("Mouse");
		    			g.i().controls = 'M';
		    			break;
    		}}};
    	ChangeListener changeLanguage = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		g.i().t.action("playSound,buttonClick");
        		switch (g.i().language) {
		    		case "en":
		    			button1.setText("English");
		    			g.i().language = "en";
		    			break;
    		}}};
    	final float xRow1 = Gdx.graphics.getHeight() / 15 * 6;
    	final float xRow2 = Gdx.graphics.getHeight() / 15 * 3;
    	final float yRow1 = Gdx.graphics.getWidth() / 3;
    	final float yRow2 = Gdx.graphics.getWidth() / 3 * 2;
    	float height = 0;
    	float width = 0;
    	stage.addActor(button1);
    	stage.addActor(button2);
    	stage.addActor(button3);
    	stage.addActor(button4);
    	
    	switch (menuLevel){
	    	case 'S':
	    		button1.setText("Mute Music");
	            button1.setChecked(! g.i().music);
	            button1.addListener(muteMusicListen);
	            button2.setText("Mute SFX");
	            button2.setChecked(! g.i().sfx);
	            button2.setVisible(true);
	            button2.addListener(muteSFXListen);
	            button3.setVisible(false);
	            button4.setText("Back");
	            button4.addListener(optionsBack);
	    		break;
	    	case 'L':
	    		switch (g.i().language) {
		    		case "en":
		    			button1.setText("English");
		    			break;
	    		}
	    		button1.addListener(changeLanguage);
	    		button2.setVisible(false);
	    		button3.setVisible(false);
	    		button4.setText("Back");
	            button4.addListener(optionsBack);
	    		break;
	    	case 'C':
	    		switch (g.i().controls) {
		    		case 'A':
		    			button1.setText("Gyro");
		    			break;
		    		case 'K':
		    			button1.setText("Keyboard");
		    			break;
		    		case 'M':
		    			button1.setText("Mouse");
		    			break;
		    		case 'D':
		    			button1.setText("Mouse & Keyboard");
		    			break;
	    		}
	    		button1.addListener(controlChange);
	    		button2.setVisible(false);
	    		button3.setVisible(false);
	    		button4.setText("Back");
	            button4.addListener(optionsBack);
	    		break;
	    	default:
	    		menuLevel = 'O';
	    		button1.setText("Sounds");
	    		button1.setChecked(false);
	    		button1.addListener(soundsListen);
	    		button2.setText("Controls");
	    		button2.setChecked(false);
	    		button2.setVisible(true);
	    		button2.addListener(conrolsListen);
	    		button3.setText("Language");
	    		button3.setChecked(false);
	    		button3.setVisible(true);
	    		button3.addListener(languageListen);
	    		button4.setText("Back");
	    		button4.setChecked(false);
	    		button4.addListener(mainMenuListen);
    	}
    	
    	if (width == 0){    	
	        for (int i=0; i < stage.getActors().size; i++){
	        	if (height < stage.getActors().items[i].getHeight()){
	        		height = stage.getActors().items[i].getHeight();
	        	}
	        	if (width < stage.getActors().items[i].getWidth()){
	        		width = stage.getActors().items[i].getWidth();
	        	}
	        	@SuppressWarnings("unused")
				int teststop = stage.getActors().size;
	        	@SuppressWarnings("unused")
				int test = 0;
	        }
    	}
        for (int i=0; i < stage.getActors().size; i++){
        	stage.getActors().items[i].setHeight(height);
        	stage.getActors().items[i].setWidth(width);
        }
        
        button1.setPosition(yRow1 - (width / 2), xRow1 - (height / 2));
        button2.setPosition(yRow2 - (width / 2), xRow1 - (height / 2));
        button3.setPosition(yRow1 - (width / 2), xRow2 - (height / 2));
        button4.setPosition(yRow2 - (width / 2), xRow2 - (height / 2));
    }

    public void logo(){
    	super.setLogo(Gdx.files.internal("img/menu/LogoOptions.png"));
    }
}