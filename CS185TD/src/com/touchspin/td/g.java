package com.touchspin.td;

import com.badlogic.gdx.Gdx;

public class g {
	
	// Player variables
	public int level; 		// Progress counter
	
	// Sound variables
	public boolean sfx;		// Sound effects are activated
	public boolean music;	// Music is activated
	public int sfxLevel;	// Sound effect volume. 0=None, 100=Full
	public int musicLevel;	// Music volume. 0=None, 100=Full
	
	// System variables
	public String language;	// Language used. This is a to letter code indicating language
							// en=English.
	public char controls;	// Control set being used. A=Android, K=Keyboard, M=Mouse
							// D=Desktop (Mouse + Keyboard)
	
	// Background variables
	public float accelX;	// Support for accelerometer X axis
	public float accelY;	// Support for accelerometer Y axis
	public InputAnonymizer InputAnonymizer = new InputAnonymizer();
	
	private static g singleton = new g( );
	
	private g(){
		level = 0;
		sfx = true;
		music = true;
		sfxLevel = 100;
		musicLevel = 100;
		language = java.util.Locale.getDefault().getLanguage();
		if (language=="en");
		else if (language=="")
			language="en";
		else language="en";
		switch (Gdx.app.getType()){
			case Android:
				controls = 'A';
				break;
			default:
				controls = 'D';
				break;
		}
		accelX = 0;
		accelY = 0;
		
	}
	   
	public static g i( ) {
		return singleton;
	}
   
	
	
}
