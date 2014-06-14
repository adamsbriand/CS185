package com.touchspin.td;

/* ======================================================================================
 * Trigger.java
 * 
 * This class handles any triggers that the user may encounter with objects. Each
 * argument must be formatted as a comma delimited string.  The string will then be split
 * into pairs.  The first argument will be the command to be called.  The second argument
 * will be the value the change will make.  Some commands have multiple values to
 * process.  These values must be dash delimited. 
 * 
 * If you wish to check a set of conditions to check before running the action, then
 * send these as a separate string proceeding the action. 
 * ======================================================================================
 */

public class Trigger {
	
	// Constants
	private final static String L1R1 = "map/Level1Runner1.tmx";
	private final static String L1M1 = "map/Level1Maze1.tmx";
	private final static String L2M1 = "map/Level2Maze1.tmx";
	private final static String L1M2 = "map/Level1Maze2.tmx";
	private final static String L1D1 = "scripts/BeginingDialog.xml";
	private final static String androidInstructions = "scripts/AndroidInstructions.xml";
	private final static String PCInstructions = "scripts/PCInstructions.xml";	
	private final static String EndDialog = "scripts/EndingDialog.xml";
	private final static String MidDialog = "scripts/MidGameDialog.xml";
	private final static String L2R1 = "map/Level2Runner1.tmx";
	
	// Variables
	MainGame game;

	// constructor
	public Trigger(MainGame game){
		this.game = game;
	}
	
	/* ==================================================================================
	 * ==================================================================================
	 *                                      Public Actions
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Processes an action. Input is a comma delimited string.
	 * 
	 * Input:
	 * 		actionString - comma delimited string of actions to be performed.
	 * 
	 * Calls:
	 * 		TriggerActions
	 * ----------------------------------------------------------------------------------
	 */
	public void action(String actionString){
		// Check to see if a command was actually sent. Return if no.
		if (actionString==null || actionString.equals("")) return;
		
		// Split the command into commands and values and process them. 
		String[] Values = actionString.split(",");
		for (int i = 0; i < Values.length; i +=2){
			TriggerActions(Values[i], Values[i+1]);
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Processes a condition and an action. Input is a comma delimited string for both. 
	 * 
	 * Input:
	 * 		Conditional - comma delimited string of conditions to be met before an action
	 * 			can be performed. 
	 * 		Action - comma delimited string of actions to be performed.
	 * 
	 * Calls:
	 * 		condition
	 * 		action
	 * ----------------------------------------------------------------------------------
	 */
	public void action(String Conditional, String Action){
		// Check to see if a command was actually sent. Return if no.
		if (Action==null || Action.equals("")) return;
		// Check to see if conditions are meet. 
		boolean go = condition(Conditional);
		// Send actions to be processed if conditions are meet. 
		if (go) action(Action);
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                      Private Actions
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Determines the type of command to be processed
	 * 
	 * Input:
	 * 		type - action commands
	 * 		value - how to process the command
	 * 
	 * Calls:
	 * 		NewLevel
	 * 		menu
	 * 		changeMyAnimation
	 * 		playSound
	 * 		changeMusic
	 * 		changeLocation
	 * 		changeBallY
	 * 		playSoundLoop
	 * 		changeOtherAnim
	 * 		changeOtherCollidable
	 * 		igniteBall
	 * 		toggleState
	 * 		changeHealth
	 * 		changeActive
	 * ----------------------------------------------------------------------------------
	 */
	private void TriggerActions(String type, String value) {
		switch (type){
			case "NewLevel":
			case "changeMap":
				NewLevel(value);
				break;
			case "menu":
				menu(value);
				break;
			case "changeMyAnim":
				changeMyAnimation(value);
				break;
			case "playSound":
				playSound(value);
				break;
			case "changeMusic":
				changeMusic(value);
				break;
			case "changeLocation":
				changeLocation(value);
				break;
			case "changeBallY":
				changeBallY(value);
				break;
			case "playSoundLoop":
				playSoundLoop(value);
				break;
			case "changeOthersAnim":
				changeOthersAnim(value);
				break;
			case "changeOthersCollidable":
				changeOthersCollidable(value);
				break;
			case "igniteBall":
				igniteBall(value);
				break;
			case "toggleState":
				toggleState(value);
				break;
			case "reduceHealth":
				changeHealth(value);
				break;
			case "changeActive":
				changeActive(value);
			default:
		}
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                   Changes to the hero
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Sets the ball on fire or extinguishes the fire.
	 * Adjusts the playing of the fire sound if fire was extinguished. 
	 * 
	 * Input:
	 * 		value - String representation of boolean value
	 * ----------------------------------------------------------------------------------
	 */
	private void igniteBall(String value) {
		g.i().fire = (value.equalsIgnoreCase("true"));
		if (!g.i().fire) g.i().sound.fire( false );
	}

	/* ----------------------------------------------------------------------------------
	 * Changes the speed of the ball in the Y direction.
	 * 
	 * Input:
	 * 		value - String representation of the float value to change the direction by.
	 * ----------------------------------------------------------------------------------
	 */
	private void changeBallY(String value) {
		float change = Float.parseFloat(value);
		g.i().hero.changeBallY(change);
	}

	/* ----------------------------------------------------------------------------------
	 * Change the location of the ball to the location of another object. 
	 * 
	 * Input:
	 * 		value - Name of the object to move the ball to.
	 * 
	 * Calls: 
	 * 		getObjNamed
	 * ----------------------------------------------------------------------------------
	 */
	private void changeLocation(String value) {
		// Check to see if location exists and get its information
		NP obj = getObjNamed(value);
		if (obj!=null) {
			float x = obj.getX();
			float y = obj.getY();
			g.i().hero.setPosition(x, y);
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Changes the type of ball shown 
	 * 
	 * Input:
	 * 		value - Name of new ball type
	 * ----------------------------------------------------------------------------------
	 */
	private void changeMyAnimation(String value) {
		g.i().hero.changeBall(value);
	}
	
	/* ----------------------------------------------------------------------------------
	 * Changes the health of the hero
	 * 
	 * Input:
	 * 		value - String representation of the value to change the health by.
	 * 			Positive numbers reduce health
	 * 			Negative numbers increase health 
	 * ----------------------------------------------------------------------------------
	 */
	private void changeHealth(String value) {
		g.i().playerHealth -= Integer.parseInt(value);
		
		// Check to see if health is above the maximum. 
		// Another section checks for minimum health. 
		if (g.i().playerHealth > g.i().maxHealth) {
			g.i().playerHealth = g.i().maxHealth;
		}
		g.i().hero.getHurt();
	}
	
	/* ==================================================================================
	 * ==================================================================================
	 *                                   Changes to sound
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Adjusts looping sound effects.
	 * 
	 * Input:
	 * 		value - Name of sound to be turned on and off and the state of the sound
	 * ----------------------------------------------------------------------------------
	 */
	private void playSoundLoop(String value) {
		// Check to see if sound effects should be played. 
		if (g.i().sfx) {
			switch (value) {
				case "sndFanOn":
					g.i().sound.wind(true);
					break;
				case "sndFanOff":
					g.i().sound.wind(false);
					break;
				case "sndFire":
					g.i().sound.fire(true);
					break;
				case "sndFireOff":
					g.i().sound.fire(false);
					break;
			}
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Plays short sound effects
	 * 
	 * Input:
	 * 		value - Name of sound to be played
	 * ----------------------------------------------------------------------------------
	 */
	private void playSound(String value) {
		// Check to see if sound effects should be played. 
		if (g.i().sfx){
			switch (value){
			case "sndGlassBreak":
				g.i().sound.sndSwitch("break");
				break;
			case "sndLightSwitch":
				g.i().sound.sndSwitch("light");
				break;
			case "sndDoorOpen":
	
				break;
			case "sndSlideWhistleDown":
				g.i().sound.SlideWhistle("down");
				break;
			case "bounce":
				g.i().sound.Bounce();
				break;
			case "sndSplash":
				g.i().sound.sndSwitch("splash");
				break;
			case "Teleport":
				g.i().sound.sndSwitch("Teleport");
				break;
			case "transmorgify":
				g.i().sound.sndSwitch("transmorgify");
				break;
			case "buttonClick":
				g.i().sound.buttonClick();
				break;
			case "sndDoorCreak":
				g.i().sound.sndSwitch("doorCreak");
				break;
			case "sndAirPuff":
				g.i().sound.sndSwitch("airPuff");
				break;
			case "sndYummy":
				g.i().sound.sndSwitch("yummy");
				break;
			}
		}
	}
	
	/* ----------------------------------------------------------------------------------
	 * Changes the background music
	 * 
	 * Input:
	 * 		value - Name of music to be played
	 * ----------------------------------------------------------------------------------
	 */
	private void changeMusic(String value) {
		g.i().sound.BGMusic(value);
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                 Changes to environment
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Toggles the animation state of an object
	 * 
	 * Input:
	 * 		value - Name of object
	 * 
	 * Calls:
	 * 		getObjNamed
	 * ----------------------------------------------------------------------------------
	 */
	private void toggleState(String value) {
		NP obj = getObjNamed(value);
		if (obj!=null) 
			obj.setAnimation((obj.getAnimation().equalsIgnoreCase("on")) ? "off" : "on");
	}
	
	/* ----------------------------------------------------------------------------------
	 * Changes weather an object is collidable
	 * 
	 * Input:
	 * 		value - Dash delimited String. First part is the object to be changed. Second
	 * 			part is a boolean value to set object as collidable or not. 
	 * 
	 * Calls:
	 * 		getObjNamed 
	 * ----------------------------------------------------------------------------------
	 */
	private void changeOthersCollidable(String value) {
		// Split input into separate parts. 
		String[] Values = value.split("-");
		String objectName = Values[0];
		boolean collidable = (Values[1]=="true");
		NP obj = getObjNamed(objectName);
		if (obj!=null) 
			obj.setCollidable(collidable);
	}

	/* ----------------------------------------------------------------------------------
	 * Changes the animation type of an object
	 * 
	 * Input:
	 * 		value - Dash delimited String. First part is the object to be changed. Second
	 * 			part is the name of the animation to change to.   
	 * 
	 * Calls:
	 * 		getObjNamed
	 * ----------------------------------------------------------------------------------
	 */
	private void changeOthersAnim(String value) {
		String[] Values = value.split("-");
		NP obj = getObjNamed(Values[0]);
		if (obj!=null) 
			obj.setAnimation(Values[1]);
	}
	
	/* ----------------------------------------------------------------------------------
	 * Changes weather an object is active. 
	 * 
	 * Input:
	 * 		value - Dash delimited String. First part is the object to be changed. Second
	 * 			part is a boolean value to set object as active or not.   
	 * 
	 * Calls:
	 * 		getObjNamed
	 * ----------------------------------------------------------------------------------
	 */
	private void changeActive(String value)
	{
		String[] Values = value.split("-");
		NP obj = getObjNamed(Values[0]);
		if (obj!=null) 
			obj.setActive(Values[1]);
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                   Change screens
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Sets a menu screen to be displayed. 
	 * 
	 * Input:
	 * 		value - The menu screen to be displayed. 
	 * ----------------------------------------------------------------------------------
	 */
	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new ScreenMenu());
			break;
		case "Test":
			game.setScreen(new ScreenTest());
			break;
		case "gameOver":
			game.setScreen(new ScreenGameOver());
			break;
		case "options":
				game.setScreen(new ScreenOptions());
			break;
		case "credits":
			game.setScreen(new ScreenCredits());
			break;
		}
	}

	/* ----------------------------------------------------------------------------------
	 * Sets a new level to play.
	 * 
	 * Input:
	 * 		value - The level to be played. 
	 * ----------------------------------------------------------------------------------
	 */
	private void NewLevel(String Value){
		switch (Value){
			case "Main":
				game.setScreen(new ScreenMenu());
				break;
			case "Level1Runner1":
			case "Level1Run1":
				g.i().gameMode = 'R';
				game.setScreen(new GameScreen(game, L1R1));
				changeMusic("");
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Maze1":
				g.i().gameMode = 'M';
				game.setScreen(new GameScreen(game, L1M1));
				changeMusic("outro2");
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Maze2":
				g.i().gameMode = 'M';
				game.setScreen(new GameScreen(game, L1M2));
				changeMusic("outro2");
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Dialog1":
				g.i().gameMode = 'D';
				game.setScreen(new GameDialog(game, L1D1));
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level2Maze1":
				g.i().gameMode = 'M';
				game.setScreen(new GameScreen(game, L2M1));
				changeMusic("song4");
				break;
			case "Level2Run1":
			case "Level2Runner1":
				g.i().gameMode = 'R';
				game.setScreen(new GameScreen(game, L2R1));
				changeMusic("song3");
				break;
			case "dialogOutro":
				g.i().gameMode = 'D';
				game.setScreen(new GameDialog(game, EndDialog));
				changeMusic("song4");
				break;
			case "midDialog" :
				g.i().gameMode = 'D';
				game.setScreen(new GameDialog(game, MidDialog));
				break;
			case "Instructions" :
				g.i().gameMode = 'D';
				if(g.i().controls == 'A')				
					game.setScreen(new GameDialog(game, androidInstructions));
				else
					game.setScreen(new GameDialog(game, PCInstructions));
				break;
		}
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                    Conditions
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Check to see if a set of conditions are valid
	 * 
	 * Input:
	 * 		value - A comma delimited string of conditions to be met before an action
	 * 			can be performed.
	 * 
	 * Calls:
	 * 		TriggerConditions
	 * 
	 * Returns:
	 * 		boolean - Returns false if any of the conditions are false
	 * ----------------------------------------------------------------------------------
	 */
	private boolean condition(String conditionString){
		// Check to see if a condition has been sent. Return true if no condition sent.
		if (conditionString == null || conditionString.equals("")) return true;
		// Split the command into conditions and values and process them.
		String[] Values = conditionString.split(",");
		for (int i = 0; i < Values.length; i +=2){
			boolean status = TriggerConditions(Values[i], Values[i+1]);
			if (!status) return false;
		}
		return true;
	}
	
	/* ----------------------------------------------------------------------------------
	 * Determines the type of condition to be processed
	 * 
	 * Input:
	 * 		type - Type of condition to check 
	 * 		value - Value to be checked against. 
	 * 
	 * Calls:
	 * 		AnimationIs
	 * 		velGTE
	 * 
	 * Returns:
	 * 		boolean - Returns false if any of the conditions are false. Returns false if
	 * 			the type of condition is not in the list.
	 * ----------------------------------------------------------------------------------
	 */
	private boolean TriggerConditions(String type, String value){
		switch (type){
			case "ballFlammable":
				return g.i().hero.flammable;
			case "ballType":
				return value.equalsIgnoreCase(g.i().currentBallType);
			case "animationName":
				return true; // not implemented
			case "myAnimationIs":
				return AnimationIs(value);
			case "velGTE":
				return velGTE(value);
			}
		return false;
	}

	/* ----------------------------------------------------------------------------------
	 * Check to see if the hero is moving fast enough
	 * 
	 * Input:
	 * 		value - The speed the hero must be greater than to be true
	 * 
	 * Returns:
	 * 		boolean - Returns if the hero is fast enough for the action
	 * ----------------------------------------------------------------------------------
	 */
	private boolean velGTE(String value) {
		if(Math.pow(Math.pow(g.i().hero.heroMover.speedXPerSecond,2)+
				Math.pow(g.i().hero.heroMover.speedYPerSecond,2),0.5) >Float.parseFloat(value))
		return true;
		return false;
	}

	/* ----------------------------------------------------------------------------------
	 * Check to see if an object animation is in the correct state
	 * 
	 * Input:
	 * 		value - A dash delimited string of the object to check and the type of
	 * 			animation to be checked against.
	 * 
	 * Returns:
	 * 		boolean - Returns if the animation matches
	 * ----------------------------------------------------------------------------------
	 */
	private boolean AnimationIs(String value) {
		String[] split = value.split("-");
		if (split.length > 0){				// Check to make sure proper format used. 
			NP obj = getObjNamed(split[0]);
			if (obj!=null) return (split[1].equalsIgnoreCase(obj.getAnimation()));
		}
		return false;
	}
	
	/* ==================================================================================
	 * ==================================================================================
	 *                                Private helper methods
	 * ==================================================================================
	 * ==================================================================================
	 */
	
	/* ----------------------------------------------------------------------------------
	 * Finds an object named 
	 * 
	 * Input:
	 * 		name - Name of object to be found  
	 * 
	 * Returns:
	 * 		The object named.
	 * ----------------------------------------------------------------------------------
	 */
	private NP getObjNamed(String name) {
		for (int i=0; i < g.i().mapObjects.size(); i++)
			if (name.equalsIgnoreCase(  g.i().mapObjects.get(i).getName()  ) )
					return g.i().mapObjects.get(i);
		return null;
	}
	

}
