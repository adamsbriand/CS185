package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
 
public class ScreenOptions extends GameMenu {
	
	
	String levelReturn;
	Screen previousScreen;
	char menuLevel;
	MainGame saved;

	public ScreenOptions (){
		super();
		levelReturn = "main";
		g.i().sound.sfxMute(true);
    }
	
	public ScreenOptions (Screen savedScreen){
		super();
		levelReturn = "game";
		previousScreen = savedScreen;
	}
    
    public void buttons(){
    	stage.clear();
    	String longest = g.i().Sounds;
    	if (longest.length() < g.i().Controls.length()){
    		longest = g.i().Controls;
    	}
    	if (longest.length() < g.i().Language.length()){
    		longest = g.i().Language;
    	}
    	if (longest.length() < g.i().Back.length()){
    		longest = g.i().Back;
    	}
    	if (longest.length() < g.i().MuteMusic.length()){
    		longest = g.i().MuteMusic;
    	}
    	if (longest.length() < g.i().MuteSFX.length()){
    		longest = g.i().MuteSFX;
    	}
    	final TextButton button1 = new TextButton(longest, textButtonStyle);
    	final TextButton button2 = new TextButton("", textButtonStyle);
    	final TextButton button3 = new TextButton("", textButtonStyle);
    	final TextButton button4 = new TextButton("", textButtonStyle);
    	longest = "\u25BC " + g.i().Keyboard;
    	if (longest.length() < g.i().Gyro.length()){
    		longest = "\u25BC " + g.i().Gyro;
    	}
    	if (longest.length() < g.i().CurrentLanguage.length() + 2){
    		longest = "\u25BC " + g.i().CurrentLanguage;
    	}
    	final TextButton button5 = new TextButton(longest, textButtonStyle);
    	ChangeListener muteMusicListen = new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("playSound,buttonClick");
            	g.i().music = ! g.i().music;
            	if (g.i().music){
            		button1.setChecked(false);
            	} else {
            		button1.setChecked(true);
            	}
            	g.i().sound.BGMusic("mute");
            }};
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
            	}
            	g.i().sound.sfxMute(false);
            }};
        ChangeListener languageListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("playSound,buttonClick");
        		menuLevel = 'L';
        		buttons();
        	}};
        ChangeListener mainMenuListen = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		if (levelReturn == "main") {
        			g.i().t.action("NewLevel,Main,playSound,buttonClick");
        		} else {
        			
        			g.i().t.action("playSound,buttonClick");
        			g.i().leAnonymizer.pausePressed = false;
        			g.i().leAnonymizer.resetAll();
        			g.i().t.game.setScreen(previousScreen);
        		}
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
		    			if (Gdx.input.isPeripheralAvailable(Peripheral.HardwareKeyboard)){
			    			g.i().controls = 'D';
		    			}
		    			break;
		    		case 'D':
		    			if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)){
			    			g.i().controls = 'A';
		    			} else {
			    			g.i().controls = 'D';
		    			}
		    			break;
		    		case 'M':
		    			g.i().controls = 'K';
		    			break;
		    		case 'K':
		    			g.i().controls = 'M';
		    			break;
        		}
        		buttons();
        		button5.setChecked(false);
        	}};
    	ChangeListener changeLanguage = new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor){
        		if (button5.isChecked()){
        			g.i().t.action("playSound,buttonClick");
	        		switch (g.i().language) {
		        		case "zh":
			    			g.i().language = "ja";
			    			break;
			    		case "en":
			    			g.i().language = "es";
			    			break;
			    		case "es":
			    			g.i().language = "zh";
			    			break;
			    		case "ja":
			    			g.i().language = "vi";
			    			break;
			    		case "vi":
			    			g.i().language = "en";
			    			break;
			    	}
        		}
        		g.i().languageSet();
        		setTemplet();
        		buttons();
        		button5.setChecked(false);
        	}};
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
    	stage.addActor(button5);
    	
    	switch (menuLevel){
	    	case 'S':
	    		super.setLogo(Gdx.files.internal("img/menu/LogoSounds.png"));
	    		button1.setText(g.i().MuteMusic);
	            button1.setChecked(! g.i().music);
	            button1.addListener(muteMusicListen);
	            button2.setText(g.i().MuteSFX);
	            button2.setChecked(! g.i().sfx);
	            button2.setVisible(true);
	            button2.addListener(muteSFXListen);
	            button3.setVisible(false);
	            button4.setText(g.i().Back);
	            button4.addListener(optionsBack);
	            button5.setVisible(false);
	    		break;
	    	case 'L':
	    		super.setLogo(Gdx.files.internal("img/menu/LogoLanguage.png"));
	    		button5.setText("\u25BC " + g.i().CurrentLanguage);
	    		button1.setVisible(false);
	    		button2.setVisible(false);
	    		button3.setVisible(false);
	    		button4.setText(g.i().Back);
	            button4.addListener(optionsBack);
	            button5.setVisible(true);
	    		button5.addListener(changeLanguage);
	    		break;
	    	case 'C':
	    		super.setLogo(Gdx.files.internal("img/menu/LogoControls.png"));
	    		switch (g.i().controls) {
		    		case 'A':
		    			button5.setText("\u25BC " + g.i().Gyro);
		    			break;
		    		case 'K':
		    			button5.setText("\u25BC " + g.i().KeyboardMouse);
		    			break;
		    		case 'M':
		    			button5.setText("\u25BC " + g.i().Mouse);
		    			break;
		    		case 'D':
		    			button5.setText("\u25BC " + g.i().Keyboard);
		    			break;
	    		}
	    		button1.setVisible(false);
	    		button2.setVisible(false);
	    		button3.setVisible(false);
	    		button4.setText(g.i().Back);
	            button4.addListener(optionsBack);
	    		button5.addListener(controlChange);
	    		button5.setVisible(true);
	    		break;
	    	default:
	    		super.setLogo(Gdx.files.internal("img/menu/LogoOptions.png"));
	    		menuLevel = 'O';
	    		button1.setText(g.i().Sounds);
	    		button1.setChecked(false);
	    		button1.setVisible(true);
	    		button1.addListener(soundsListen);
	    		button2.setText(g.i().Controls);
	    		button2.setChecked(false);
	    		button2.setVisible(true);
	    		button2.addListener(conrolsListen);
	    		button3.setText(g.i().Language);
	    		button3.setChecked(false);
	    		button3.setVisible(true);
	    		button3.addListener(languageListen);
	    		button4.setText(g.i().Back);
	    		button4.setChecked(false);
	    		button4.addListener(mainMenuListen);
	    		button5.setVisible(false);
    	}
    	
    	if (width == 0){    	
	        for (int i=0; i < 4; i++){
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
        for (int i=0; i < 4; i++){
        	stage.getActors().items[i].setHeight(height);
        	stage.getActors().items[i].setWidth(width);
        }
        
        button1.setPosition(yRow1 - (width / 2), xRow1 - (height / 2));
        button2.setPosition(yRow2 - (width / 2), xRow1 - (height / 2));
        button3.setPosition(yRow1 - (width / 2), xRow2 - (height / 2));
        button4.setPosition(yRow2 - (width / 2), xRow2 - (height / 2));
        button5.setPosition((Gdx.graphics.getWidth() / 2) - 
        		((button5.getWidth() / 2)), xRow1 - (button5.getHeight() / 2));
    }

    public void logo(){
    	super.setLogo(Gdx.files.internal("img/menu/LogoOptions.png"));
    }
}