package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import java.util.Locale;

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
	public int level; 		// Progress counter
	public float levelTimer; // Time stamp at start of level. Time is measured in seconds.
	public String playerGraphic; // Type of ball for graphics and physics
	public int playerWeight; // The weight of the player for physics
	public int playerFriction; // The friction of the player for physics
	
	// Sound variables
	public boolean sfx;		// Sound effects are activated
	public boolean music;	// Music is activated
	public int sfxLevel;	// Sound effect volume. 0=None, 100=Full
	public int musicLevel;	// Music volume. 0=None, 100=Full
	
	// System variables
	public String language;	/* Language used. This is a to letter code indicating language
							 * en=English
							 * es=Spanish
							 * fr=French
							 * zh=Chinese
							 * ja=Japanese
							 */
	public char controls;	/* Control set being used. 
							 * A=Android
							 * K=Keyboard
							 * M=Mouse
							 * D=Desktop (Mouse + Keyboard)
							 */ 
	public long timeStartGame; //Time stamp at start of game
	
	// Background variables
	public float accelX;	// Support for accelerometer X axis. This is for the Vertical axes.
	public float accelY;	// Support for accelerometer Y axis. This is for the Horizontal axes.
	public float accelZ;	// Support for accelerometer Z axis. 
	InputAnonymizer leAnonymizer;
	int gameMode; // 0 for runner and 1 for maze
	
	private static g singleton = new g( ); // Only this class can create this class.
	
	// Controls
	public String Click;
	public String Velocity;
	public String Pause;
	public String Attack;
	public String Crouch;
	public String Jump;
	public String Dash;
	
	private g(){

		// Player
		level = 0;
		levelTimer = 0;
		
		// Sound
		sfx = true;
		music = true;
		sfxLevel = 100;
		musicLevel = 100;
		
		// System
		language = Locale.getDefault().getLanguage(); //Get language from device
		if (language=="en");		// Set language to English for testing.
		else if (language=="")
			language="en";
		else language="en";

		timeStartGame = System.currentTimeMillis();
		
		// Background
		accelX = 0;
		accelY = 0;
		accelZ = 0;
	}
	
	public static g i( ) {
		return singleton;
	}
   
	public void StartNewLevel(){
		// reset parameters for a new level
		level += 1;
		levelTimer = 0;
	}
	
	public void timer(){
		// increment the level timer. This needs to be called for each draw. 
		levelTimer += Gdx.graphics.getDeltaTime();
	}
	
	public int GetLevelTimeMin(){ 
		return (int) ((levelTimer % 3600) / 60);
	}
	
	public int GetLevelTimeSec(){
		return (int) (levelTimer % 60);
	}
	
	public void SetControls(char value){
		controls = value;
		ChangeInputs();
	}
	
	private void ChangeInputs(){
		switch (controls){
		case 'A':
			Click = "tap";
			Velocity = "tilt";
			Pause = "tap pause";
			Attack = "tap";
			Crouch = "swipe down";
			Jump = "swipe up";
			Dash = "swipe left or right";
			break;
		case 'D':
			Click = "any key";
			Velocity = "mouse";
			Pause = "Escape";
			Attack = "X";
			Crouch = "Z";
			Jump = "Space";
			Dash = "Left Mouse Click";
			break;
		case 'K':
			break;
		case 'M':
			break;
		default:
			Click = "";
			Velocity = "";
			Pause = "";
			Attack = "";
			Crouch = "";
			Jump = "";
			Dash = "";
			break;
		}
	}
}
