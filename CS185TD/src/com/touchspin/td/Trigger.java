package com.touchspin.td;

import java.util.Random;

/* This class handles any triggers that the user may encounter when colliding with hidden
 * tiles.  This trigger requires the input of two string values, or one comma delimited
 * string.  The first value will indicate the action to be taken.  The second value will 
 * effect how that action will take place.  
 */

public class Trigger {
	MainGame game;
	
	
	// constructor
	public Trigger(MainGame game){
		this.game = game;
	}
	
	// Public Actions
	public void action(String ChangeType, String ChangeValue) {
		TriggerActions(ChangeType, ChangeValue);
	}
	
	public void action(String actionString){
		if (actionString==null || actionString==""){
			return;
		}
		String[] Values = actionString.split(",");
		for (int i = 0; i < Values.length; i +=2){
			TriggerActions(Values[i], Values[i+1]);
		}
	}
	
	public void action(String[] actionArray){
		if (actionArray==null || actionArray[0]==""){
			return;
		}
		for (int i = 0; i < actionArray.length; i +=2 ){
			TriggerActions(actionArray[i], actionArray[i+1]);
		}
	}
	
	
	// public Conditions
	public boolean condition(String conditionString){
		if (conditionString == null || conditionString == "") {
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
	
	public boolean condition(String[] condistionArray){
		if (condistionArray == null || condistionArray[0] == "") {
			return true;
		}
		for (int i = 0; i < condistionArray.length; i +=2){
			boolean status = TriggerConditions(condistionArray[i], condistionArray[i+1]);
			if (!status){
				return false;
			}
		}
		return true;
	}
   
	// Change others
	public void changeOthersAnim(GameThing other, String animName){
		
	}
	
    public void changeMyAnim(String animName){
    	// changes animation of object collided with
    }
    
    public void changeMyCollidable(boolean nuCollidable){
    	// changes collidability of object collided with
    }
    
    public void changeOthersCollidable(boolean nuCollidable){ // changes collidability of other object
    	
    }
    
	private void TriggerActions(String type, String value) {
		switch (type){
			case "NewLevel":
				NewLevel(value);
				break;
			case "menu":
				menu(value);
				int t = 5;
				break;
			case "changeMyAnim":
				changeMyAnimation(value);
			case "playSound":
				playSound(value);
			case "changeLogic":
				changeLogic(value);
			case "changeMusic":
				changeMusic(value);
			case "setTarget":
				setTarget(value);
			case "changeMap":
				NewLevel(value);
			case "changeLocation":
				changeLocation(value);
			case "changeBalldY":
				changeBalldY(value);
			case "playSoundLoop":
				playSoundLoop(value);
			case "changeOthersAnim":
				changeOthersAnim(value);
			case "changeOthersCollidable":
				changeOthersCollidable(value);
			case "toggleLight":
				toggleLight(value);
			case "igniteBall":
				igniteBall(value);
			default:
		}
	}
	
	private void igniteBall(String value) {
		if (value=="true"){
			g.i().fire = true;
		} else {
			g.i().fire = false;
		}
		
	}

	private void toggleLight(String value) {
		switch (value){
			case "lightSourceGlobal":
			
		}
	}

	private void changeOthersCollidable(String value) {
		String[] Values = value.split("-");
		// door1,false
	}

	private void changeOthersAnim(String value) {
		String[] Values = value.split("-");
		// fan1,on"
		// door1,open
	}

	private void playSoundLoop(String value) {
		// sndFanOn
		// sndFire
	}

	private void changeBalldY(String value) {
		// -10
		int change = Integer.parseInt(value);
	}

	private void changeLocation(String value) {
		// dest1
		// enter1
	}

	private void setTarget(String value) {
		switch (value){
		case "hero":
			
		}
	}

	private void changeMusic(String value) {
		switch (value){
			case "dragon":
			
		}
	}

	private void changeLogic(String value) {
		switch (value){
		case "startAttack":
			
		}
	}

	private void playSound(String value) {
		switch (value){
		case "sndGlassBreak":
			Random random = new Random();
			int ran = random.nextInt(2) + 1;
			if (ran == 1){
				g.i().sound.sndGlassBreak1.play();
			} else {
				g.i().sound.sndGlassBreak2.play();
			}
		case "sndLightSwitch":
			g.i().sound.sndLightSwitch.play();
		case "sndDoorOpen":
			
		case "sndSlideWhistleDown":
			g.i().sound.sndSlideWhistleDown.play(g.i().sfxLevel);
		}
	}

	private void changeMyAnimation(String value) {
		switch (value){
		case "break":
			
		}
	}

	private boolean TriggerConditions(String type, String value2){
		switch (type){
			case "ballFlammable":
				return ballFlammable(value2);
			case "ballType":
				return ballType(value2);
			case "myAnimationIs":
				return myAnimationIs(value2);
			case "onScreen":
				return onScreen(value2);
			case "animationName":
				return animationName(value2);
			case "velGTE":
				return velGTE(value2);
			}
		return false;
	}

	private boolean velGTE(String value) {
		// 3
		return false;
	}

	private boolean animationName(String value) {
		// idle
		return false;
	}

	private boolean onScreen(String value) {
		// true
		return false;
	}

	private boolean myAnimationIs(String value) {
		// on
		return false;
	}

	private boolean ballType(String value) {
		// pingPong
		// bowling
		return false;
	}

	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new ScreenMenu(game));
			int t = 7;
			break;
		}
	}

	private void NewLevel(String Value){
		switch (Value){
		case "Level1Run1":
			g.i().gameMode = 'R';
			game.setScreen(new Runner(game, "maps/Level1Runner1.tmx"));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
			g.i().sound.sndg1Loopable.play();
			break;
		case "Level1Maze1":
			g.i().gameMode = 'M';
			game.setScreen(new Maze(game, "maps/Maze1.tmx"));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
			break;
		}
	}
	
	private boolean ballFlammable(String value){
		if (value=="true"){
			return g.i().fire;
		} else {
			return !(g.i().fire);
		}
	}
	
}
