package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.MapObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* ======================================================================================
 * g.java
 * 
 * This class holds all game-wide variables.  The name of this class is as short as 
 * possible for easy access.  To access variables in this class, use "g.i()." followed by
 * the name of the variable you want to use.  
 * 
 * Because all the variables are open for use anywhere in the game, be sure to check with
 * the other group members before writing code using / changing unknown variables.  
 * 
 * All variable types are in flux.  If you need a variable in a different type and have
 * "ownership" of that variable, you may change it.  Check with the group if you are 
 * unsure.
 * ======================================================================================
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
	public String language;	/* Language used. This is a two letter code indicating language
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
	
	// String values to hold translations
    public String KeyboardMouse;
	public String Continue;
	public String Gyro;
	public String Keyboard;
	public String Mouse;
	public String MuteMusic;
	public String MuteSFX;
	public String Back;
	public String Sounds;
	public String Controls;
	public String Language;
	public String NewGame;
	public String Options;
	public String HighScore;
	public String Credits;
	public String Exit;
	public String CurrentLanguage;
	
	/* ----------------------------------------------------------------------------------
	 * Creates a new instance of this class
	 * ----------------------------------------------------------------------------------
	 */
	private static g singleton = new g( ); // Only this class can create this class.
	
	/* ----------------------------------------------------------------------------------
	 * Default private constructor.
	 * Sets defaults
	 * 
	 * Calls:
	 * 		setZero
	 * ----------------------------------------------------------------------------------
	 */
	private g(){
		// Sound levels
		sfx = true;
		music = true;
		sfxLevel = .5f;
		musicLevel = .5f;
		
		// Zero out any variables
		setZero();
	}
	
	/* ----------------------------------------------------------------------------------
	 * Public access to this class
	 * 
	 * Return:
	 * 		g - returns this class
	 * ----------------------------------------------------------------------------------
	 */
	public static g i( ) {return singleton;}
	
	/* ----------------------------------------------------------------------------------
	 * Sets values to default state
	 * ----------------------------------------------------------------------------------
	 */
	public void setZero(){
		maxHealth = 100;
		fire = false;
		currentBallType = "Base";
		fire = false;
		playerHealth = maxHealth;
	}
	
	/* ----------------------------------------------------------------------------------
	 * Sets default game environment
	 * 
	 * Calls:
	 * 		languageSet
	 * ----------------------------------------------------------------------------------
	 */
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
	
	/* ----------------------------------------------------------------------------------
	 * Sets the language. 
	 * Sets all the menu text to the language
	 * ----------------------------------------------------------------------------------
	 */
	// unicode converter: http://www.mobilefish.com/services/unicode_escape_sequence_converter/unicode_escape_sequence_converter.php
	public void languageSet(){
		switch (language){
		case "zh":
			font = Gdx.files.internal("Font/ollieFont.fnt");
			CurrentLanguage = "\u4e2d\u6587";
			Continue = "\u7ee7\u7eed";
			Gyro = "\u9640\u87ba\u4eea";
			Keyboard = "\u952e\u76d8";
			Mouse = "\u9f20\u6807";
			KeyboardMouse = "\u9f20\u6807\u4e0e\u952e\u76d8";
			MuteMusic = "\u9759\u97f3\u80cc\u666f\u97f3\u4e50";
			MuteSFX = "\u9759\u97f3\u97f3\u6548";
			Back = "\u540e\u9000";
			Sounds = "\u97f3\u6548";
			Controls = "\u63a7\u5236";
			Language = "\u8bed\u8a00";
			NewGame = "\u65b0\u6e38\u620f";
			Options = "\u9009\u9879";
			HighScore = "\u6700\u9ad8\u5206";
			Credits = "\u5236\u4f5c\u4eba\u5458\u540d\u5355";
			Exit = "\u9000\u51fa";
			break;
		case "es":
			font = Gdx.files.internal("Font/ollieFont.fnt");
			CurrentLanguage = "Espa\u00f1ol";
			Continue = "Contin\u00fae";
			Gyro = "giroszk\u00f3p";
			Keyboard = "Teclado";
			Mouse = "Rat\u00f3n";
			KeyboardMouse = "Teclado & Rat\u00f3n";
			MuteMusic = "Silenciar M\u00fasica";
			MuteSFX = "Silenciar SFX";
			Back = "Retorno";
			Sounds = "Sonidos";
			Controls = "Configuraci\u00f3n";
			Language = "Idioma";
			NewGame = "Nuevo juego";
			Options = "Opciones";
			HighScore = "R\u00e9cord";
			Credits = "Cr\u00e9dito";
			Exit = "Salida";
			break;
		case "vi":
			font = Gdx.files.internal("Font/viFont.fnt");
			CurrentLanguage = "ti\u1ebfng Vi\u1ec7t";
			Continue = "ti\u1ebfp t\u1ee5c";
			Gyro = "con quay h\u1ed3i chuy\u1ec3n";
			Keyboard = "b\u00e0n ph\u00edm";
			Mouse = "chu\u1ed9t";
			KeyboardMouse = "chu\u1ed9t v\u00e0 b\u00e0n ph\u00edm";
			MuteMusic = "t\u1eaft nh\u1ea1c n\u1ec1n";
			MuteSFX = "t\u1eaft nh\u1ea1c ngo\u1ea1i c\u00e3nh";
			Back = "quay l\u1ea1i";
			Sounds = "\u00e2m thanh";
			Controls = "danh m\u1ee5c \u0111i\u1ec1u khi\u1ec3n";
			Language = "ng\u00f4n ng\u1eef";
			NewGame = "thi\u1ebft l\u1eadp l\u1ea1i tr\u00f2 ch\u01a1i";
			Options = "danh m\u1ee5c \u0111i\u1ec1u ch\u1ec9nh";
			HighScore = "danh m\u1ee5c \u0111i\u1ec3m";
			Credits = "c\u00f4ng tr\u1ea1ng";
			Exit = "tho\u00e1t ra";
			break;
		case "ja":
			font = Gdx.files.internal("Font/japFont.fnt");
			CurrentLanguage = "\u65e5\u672c\u8a9e";
			Continue = "\u30b3\u30f3\u30c6\u30a3\u30cb\u30e5\u30fc";
			Gyro = "\u30b8\u30e3\u30a4\u30ed";
			Keyboard = "\u30ad\u30fc\u30dc\u30fc\u30c9";
			Mouse = "\u30de\u30a6\u30b9";
			KeyboardMouse = "\u30de\u30a6\u30b9&\u30ad\u30fc\u30dc\u30fc\u30c9";
			MuteMusic = "\u30df\u30e5\u30fc\u30c8Music";
			MuteSFX = "\u30df\u30e5\u30fc\u30c8SFX";
			Back = "\u30ea\u30bf\u30fc\u30f3";
			Sounds = "\u30b5\u30a6\u30f3\u30c9";
			Controls = "\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb";
			Language = "\u8a00\u8a9e";
			NewGame = "\u30cb\u30e5\u30fc\u30b2\u30fc\u30e0";
			Options = "\u30aa\u30d7\u30b7\u30e7\u30f3";
			HighScore = "\u30cf\u30a4\u30b9\u30b3\u30a2";
			Credits = "\u30af\u30ec\u30b8\u30c3\u30c8";
			Exit = "\u30a8\u30b0\u30b8\u30c3\u30c8";
			break;
		case "fr":
			font = Gdx.files.internal("Font/ollieFont.fnt");
			language = "en";
			CurrentLanguage = "fran\u00e7aise";
			Continue = "Continue";
			Gyro = "Gyro";
			Keyboard = "Keyboard";
			Mouse = "Mouse";
			KeyboardMouse = "Keyboard & Mouse";
			MuteMusic = "Mute Music";
			MuteSFX = "Mute SFX";
			Back = "Back";
			Sounds = "Sounds";
			Controls = "Controls";
			Language = "Language";
			NewGame = "New Game";
			Options = "Options";
			HighScore = "High Score";
			Credits = "Credits";
			Exit = "Exit";
			break;
		case "en":
		default:
			font = Gdx.files.internal("Font/ollieFont.fnt");
			language = "en";
			CurrentLanguage = "English";
			Continue = "Continue";
			Gyro = "Gyro";
			Keyboard = "Keyboard";
			Mouse = "Mouse";
			KeyboardMouse = "Keyboard & Mouse";
			MuteMusic = "Mute Music";
			MuteSFX = "Mute SFX";
			Back = "Back";
			Sounds = "Sounds";
			Controls = "Controls";
			Language = "Language";
			NewGame = "New Game";
			Options = "Options";
			HighScore = "High Score";
			Credits = "Credits";
			Exit = "Exit";
			break;
		}
	}
	
}
