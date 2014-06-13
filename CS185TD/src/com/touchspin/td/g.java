package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* This class holds all game-wide variables.  The name of this class is as short as possible
 * for easy access.  To access variables in this class, use "g.i()." followed by the name of
 * the variable you want to use.  
 * 
 * Because all the variables are open for use anywhere in the game, be sure to check with
 * the other group members before writing code using / changing unknown variables.  
 * 
 * All variable types are in flux.  If you need a variable in a different type and have
 * "ownership" of that variable, you may change it.  Check with the group if you are unsure.
 */

public class g {
	
	// Player variables
	public float playerFriction; // The friction of the player for physics
	public boolean fire;	// Hero is on fire
	public String currentBallType;	// They current ball type
	public int playerHealth;  // Health of the player. 100=full health. 
	public Hero hero;		// The hero class for access game wide
	public HUD hud;			// The HUD class
	public int maxHealth;	// Max health of the hero
	public float playerdyInWater;	// Player in water
	
	// Sound variables
	public boolean sfx;		// Sound effects are activated
	public boolean music;	// Music is activated
	public float sfxLevel;	// Sound effect volume. 0=None, 1=Full
	public float musicLevel;	// Music volume. 0=None, 1=Full
	
	// System variables
	public String language;	/* Language used. This is a to letter code indicating language
							 * en=English
							 * es=Spanish
							 * fr=French
							 * zh=Chinese
							 * ja=Japanese
							 * vi=Vietnamese
							 */
	public FileHandle font;
	public char controls;	/* Control set being used. 
							 * A=Android
							 * K=Keyboard
							 * M=Mouse
							 * D=Desktop (Mouse + Keyboard)
							 */ 
	
	// Background variables
	public InputAnonymizer leAnonymizer;
	public Sounds sound;
	public Trigger t;
	public char gameMode;	/* Current game mode.
							 * M=Maze
							 * R=Runner
							 * D=Dialog
							 */
	
	public ArrayList<NP> mapObjects; 
	public Map<MapObject,NP> npMap; 
	
	private static g singleton = new g( ); // Only this class can create this class.
	
	private g(){
		// Sound
		sfx = true;
		music = true;
		sfxLevel = .5f;
		musicLevel = .5f;

		setZero();
	}
	
	public static g i( ) {
		return singleton;
	}
	
	public void setZero(){
		// Player
		fire = false;
		maxHealth = 100;
		currentBallType = "Base";
		fire = false;
		playerHealth = maxHealth;
	}
	
	public void SetDefaults(MainGame game){
		// Set defaults after the game loads
		switch (Gdx.app.getType()) { // Other platforms: WebGl iOS Applet
			case Android:
				controls = 'A';
				break;
			case Desktop:
				controls = 'D';
			default:
				controls = 'D';
				break;
		}
		// System
		language = Locale.getDefault().getLanguage(); //Get language from device
		languageSet();
		mapObjects = new ArrayList<NP>();
		npMap = new HashMap<MapObject,NP>();
	}
	
	public void languageSet(){
		if (language=="en"){
			font = Gdx.files.internal("Font/ollieFont.fnt");
		} else if (language=="es") {
			font = Gdx.files.internal("Font/ollieFont.fnt");
		} else if (language=="zh") {
			font = Gdx.files.internal("Font/ollieFont.fnt");
		} else if (language=="vi") {
			font = Gdx.files.internal("Font/viFont.fnt");
		} else if (language=="ja") {
			font = Gdx.files.internal("Font/japFont.fnt");
		} else {
			language="en";
			font = Gdx.files.internal("Font/ollieFont.fnt");
		}
	}
	
}
