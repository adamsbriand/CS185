package com.touchspin.td;

import com.badlogic.gdx.Screen;

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
	
	public void action(String actionString){
		if (actionString==null || actionString.equalsIgnoreCase("")){
			return;
		}
		String[] Values = actionString.split(",");
		for (int i = 0; i < Values.length; i +=2){
			TriggerActions(Values[i], Values[i+1]);
		}
	}
	
	public void action(String Conditional, String Action){
		if (Action==null || Action.equalsIgnoreCase("")){
			return;
		}
		boolean go = condition(Conditional);
		if (go){
			action(Action);
		}
	}

	/* ==================================================================================
	 * ==================================================================================
	 *                                      Private Actions
	 * ==================================================================================
	 * ==================================================================================
	 */
	private void TriggerActions(String type, String value) {
		switch (type){
			case "NewLevel":
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
			case "changeMap":
				NewLevel(value);
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
				reduceHealth(value);
				break;
			case "changeActive":
				changeActive(value);
			default:
		}
	}

	// Changes to the hero
	private void igniteBall(String value) {
		g.i().fire = (value.equalsIgnoreCase("true"));
		if (!g.i().fire) g.i().sound.fire( false );
	}
	
	private boolean ballFlammable(String value){
		return g.i().hero.flammable;
	}

	private void changeBallY(String value) {
		float change = Float.parseFloat(value);
		g.i().hero.changeBallY(change);
	}

	private void changeLocation(String value) {
		float x;
		float y;
		for (int i=0; i < g.i().mapObjects.size(); i++){
			if (value.equalsIgnoreCase(g.i().mapObjects.get(i).getName())){
				x = g.i().mapObjects.get(i).getX();
				y = g.i().mapObjects.get(i).getY();
				g.i().hero.setPosition(x, y);
				i=1000;
			}
		}
	}
	
	private void changeMyAnimation(String value) {
		g.i().hero.changeBall(value);
	}
	
	// Changes to environment
	
	private void toggleState(String value)
	{
		NP obj = getObjNamed(value);
		if (obj!=null) 
			obj.setAnimation( (obj.getAnimation().equalsIgnoreCase("on")) ? "off" : "on");
	
		
	}
	
	// Changes to sound
	private void playSoundLoop(String value) {
		if (g.i().sfx){
			switch (value){
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
	
	private void playSound(String value) {
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
	
	private void changeMusic(String value) {
		g.i().sound.BGMusic(value);
	}

	// Changes to others
	private void changeOthersCollidable(String value) {
		boolean collidable;
		String[] Values = value.split("-");
		String objectName = Values[0];
		collidable = (Values[1]=="true");
		NP obj = getObjNamed(objectName);
		if (obj!=null) 
			obj.setCollidable(collidable);
	}

	private void changeOthersAnim(String value) {
		String[] Values = value.split("-");
		NP obj = getObjNamed(Values[0]);
		if (obj!=null) 
			obj.setAnimation(Values[1]);
	}
	
	private void changeActive(String value)
	{
		String[] Values = value.split("-");
		NP obj = getObjNamed(Values[0]);
		if (obj!=null) 
			obj.setActive(Values[1]);
	}

	private NP getObjNamed(String name) {
		for (int iO=0; iO < g.i().mapObjects.size(); iO++)
			if (name.equalsIgnoreCase(  g.i().mapObjects.get(iO).getName()  ) )
					return g.i().mapObjects.get(iO);
		return null;
	}
	
	private void reduceHealth(String value)
	{
		g.i().playerHealth -= Integer.parseInt(value);
		if (g.i().playerHealth > g.i().maxHealth){
			g.i().playerHealth = g.i().maxHealth;
		}
		g.i().hero.getHurt();
	}

	// Change screens
	private void menu(String value) {
		String[] options = value.split("-");
		switch (options[0]){
		case "Main":
			game.setScreen(new ScreenMenu(game));
			break;
		case "Test":
			game.setScreen(new ScreenTest(game));
			break;
		case "gameOver":
			game.setScreen(new ScreenGameOver(game));
			break;
		case "options":
			if (options.length==1){
				game.setScreen(new ScreenOptions(game));
			} else {
				Screen screen = game.getScreen();
				game.setScreen(new ScreenOptions(game, screen));
			}
			break;
		case "credits":
			game.setScreen(new ScreenCredits(game));
			break;
		}
	}

	private void NewLevel(String Value){
		switch (Value){
			case "Main":
				game.setScreen(new ScreenMenu(game));
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

	//=========================================================================
	// Conditions
	//=========================================================================
	private boolean condition(String conditionString){
		if (conditionString == null || conditionString.equalsIgnoreCase("")) {
			return true;
		}
		String[] Values = conditionString.split(",");
		for (int i = 0; i < Values.length; i +=2){
			boolean status = TriggerConditions(Values[i], Values[i+1]);
			if (!status){
				return false;
			}
		}
		return true;
	}
	
	private boolean TriggerConditions(String type, String value){
		switch (type){
			case "ballFlammable":
				return ballFlammable(value);
			case "ballType":
				return ballType(value);
			case "animationName":
				return true;
			case "myAnimationIs":
				return AnimationIs(value);
			case "velGTE":
				return velGTE(value);
			}
		return false;
	}

	private boolean velGTE(String value) {
		if(Math.pow(Math.pow(g.i().hero.heroMover.speedXPerSecond,2)+
				Math.pow(g.i().hero.heroMover.speedYPerSecond,2),0.5) >Float.parseFloat(value))
		return true;
		else
			return false;
	}

	private boolean AnimationIs(String value) {
		String[] split = value.split("-");
		if (split.length > 0){						// Check to make sure proper format used. 
			NP obj = getObjNamed(split[0]);
			if (obj!=null)
				return  (boolean)( split[1].equalsIgnoreCase( obj.getAnimation() ) );
		}
		return false;
	}

	private boolean ballType(String value) {
		if (value.equalsIgnoreCase(g.i().currentBallType)){
			return true;
		}
		return false;
	}

}
