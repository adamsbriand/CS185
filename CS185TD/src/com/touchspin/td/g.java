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
	public int level; 		// Progress counter
	public float levelTimer; // Time stamp at start of level. Time is measured in seconds.
	public String playerGraphic; // Type of ball for graphics and physics
	public int playerWeight; // The weight of the player for physics
	public float playerFriction; // The friction of the player for physics
	public boolean fire;	// Hero is on fire
	public String currentBallType;
	public int playerHealth;  // Health of the player. 100=full health. 
	public Hero hero;
	public HUD hud;
	public int maxHealth;
	
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
	//public long timeStartGame; //Time stamp at start of game
	// Maze const Variables
	public float cameraWidth;
	public float cameraHeight;
	public float playerdyInWater;
	
	// Background variables
	public float accelX;	// Support for accelerometer X axis. This is for the Vertical axes.
	public float accelY;	// Support for accelerometer Y axis. This is for the Horizontal axes.
	public float accelZ;	// Support for accelerometer Z axis. 
	InputAnonymizer leAnonymizer;
	Sounds sound;
	public char gameMode;	// M=Maze
							// R=Runner
	
	// Sound
	public Sounds snd = new Sounds();
	
	public ArrayList<NP> mapObjects; 
	public Map<MapObject,NP> npMap; 
	
	Trigger t;
	
	// Controls
	public String Click;
	public String Velocity;
	public String Pause;
	public String Attack;
	public String Crouch;
	public String Jump;
	public String Dash;
	
	private static g singleton = new g( ); // Only this class can create this class.
	
	private g(){
		// Sound
		sfx = true;
		music = true;
		sfxLevel = 1;
		musicLevel = 1;

		setZero();
	}
	
	public static g i( ) {
		return singleton;
	}
	
	public void setZero(){
		// Player
		level = 0;
		levelTimer = 0;
		fire = false;
		maxHealth = 100;
		currentBallType = "Base";
		fire = false;
		playerHealth = maxHealth;
		
		//Maze
		cameraWidth = 400;
		cameraHeight = 400;
		
		// Background
		accelX = 0;
		accelY = 0;
		accelZ = 0;
	}
	
	public void SetDefaults(MainGame game){
		// Set defaults after the game loads
		switch (Gdx.app.getType()) { // Other platforms: WebGl iOS Applet
			case Android:
				g.i().SetControls('A');
				break;
			case Desktop:
				g.i().SetControls('D');
			default:
				g.i().SetControls('D');
				break;
		}
		// System
		language = Locale.getDefault().getLanguage(); //Get language from device
		languageSet();
		
	//	timeStartGame = System.currentTimeMillis();
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
