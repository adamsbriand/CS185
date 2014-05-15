package com.touchspin.td;

import com.badlogic.gdx.Gdx;

public class g {
	
	public int level;
	public boolean sfx;
	public boolean music;
	public int sfxLevel;
	public int musicLevel;
	public String language;
	public char controls;
	public float accelX;
	public float accelY;
	
	private static g singleton = new g( );
	
	private g(){
		level = 0;
		sfx = true;
		music = true;
		sfxLevel = 100;
		musicLevel = 100;
		language = "EN";
		controls = 'A';
		accelX = 0;
		accelY = 0;
		
	}
	   
	public static g i( ) {
		return singleton;
	}
   
	
	
}
