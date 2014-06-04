package com.touchspin.td;

/* This class handles any triggers that the user may encounter when colliding with hidden
 * tiles.  This trigger requires the input of two string values, or one comma delimited
 * string.  The first value will indicate the action to be taken.  The second value will 
 * effect how that action will take place.  
 */

public class Trigger {
	private final static String L1R1 = "map/Level1Runner1.tmx";
	private final static String L1R2 = "";
	private final static String L1M1 = "map/Level1Maze1.tmx";
	private final static String L1M2 = "map/Level1Maze2.tmx";
	private final static String L1D1 = "scripts/BeginingDialog.xml";
	private final static String L1D2 = "";
	
	MainGame game;

	// constructor
	public Trigger(MainGame game){
		this.game = game;
	}
	
	//=========================================================================
	// Public Actions
	//=========================================================================
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
		boolean go = condition(Conditional);
		if (go){
			action(Action);
		}
	}

	//=========================================================================
    // private Actions
	//=========================================================================
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
			case "changeLogic":
				changeLogic(value);
				break;
			case "changeMusic":
				changeMusic(value);
				break;
			case "setTarget":
				setTarget(value);
				break;
			case "changeMap":
				NewLevel(value);
				break;
			case "changeLocation":
				changeLocation(value);
				break;
			case "changeBalldY":
				changeBalldY(value);
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
			case "toggleLight":
				toggleLight(value);
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
			default:
		}
	}

	// Changes to the hero
	private void igniteBall(String value) {
		if (value.equalsIgnoreCase("true")){
			g.i().fire = true;
		} else {
			g.i().fire = false;
			if(!g.i().fire)
			{
				g.i().sound.fire(false);
			}
		}
	}
	
	private boolean ballFlammable(String value){
		if (value.equalsIgnoreCase("true")){
			return g.i().hero.flammable;
		} else {
			return g.i().hero.flammable;
		}
	}

	private void changeBalldY(String value) {
		// -10
		float change = Float.parseFloat(value);
		g.i().hero.changeBalldy(change);
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
	private void toggleLight(String value) {
		switch (value){
			case "lightSourceGlobal":
				
				break;
		}
	}
	
	private void toggleState(String value)
	{
		String currentAnim;
		for (int i=0; i < g.i().mapObjects.size(); i++){
			if (value.equalsIgnoreCase(g.i().mapObjects.get(i).getName())){
				currentAnim = g.i().mapObjects.get(i).getAnimation();
				if(currentAnim.equalsIgnoreCase("off"))
					g.i().mapObjects.get(i).setAnimation("on");
				else
					g.i().mapObjects.get(i).setAnimation("off");
			}
		}
		
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
		if (Values[1]=="true"){
			collidable = true;
		} else {
			collidable = false;
		}
		for (int i=0; i < g.i().mapObjects.size(); i++){
			if (objectName.equals(g.i().mapObjects.get(i).getName())){
				g.i().mapObjects.get(i).setCollidable(collidable);
			}
		}
	}

	private void changeOthersAnim(String value) {
		String[] Values = value.split("-");
		for (int i=0; i < g.i().mapObjects.size(); i++){
			if (Values[0].equalsIgnoreCase(g.i().mapObjects.get(i).getName())){
				g.i().mapObjects.get(i).setAnimation(Values[1]);
			}
		}
	}

	private void setTarget(String value) {
		switch (value){
			case "hero":
				
				break;
		}
	}

	private void changeLogic(String value) {
		switch (value){
		case "startAttack":

			break;
		}
	}
	
	private void reduceHealth(String value)
	{
		g.i().playerHealth -= Integer.parseInt(value);
		g.i().hero.getHurt();
	}

	// Change screens
	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new ScreenMenu(game));
			break;
		case "Test":
			game.setScreen(new ScreenTest(game));
			break;
		case "gameOver":
			game.setScreen(new ScreenGameOver(game));
			break;
		}
	}

	private void NewLevel(String Value){
		switch (Value){
			case "Level1Run1":
				g.i().gameMode = 'R';
				game.setScreen(new GameScreen(game, L1R1));
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Maze1":
				g.i().gameMode = 'M';
				game.setScreen(new GameScreen(game, L1M1));
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Maze2":
				g.i().gameMode = 'M';
				game.setScreen(new GameScreen(game, L1M2));
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
				break;
			case "Level1Dialog1":
				g.i().gameMode = 'D';
				game.setScreen(new GameDialog(game, L1D1));
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
			case "myAnimationIs":
				return AnimationIs(value);
			case "onScreen":
				return onScreen(value);
			case "animationName":
				return animationName(value);
			case "velGTE":
				return velGTE(value);
			}
		return false;
	}

	private boolean velGTE(String value) {
		// 3
		return true;
	}

	private boolean animationName(String value) {
		// idle
		return true;
	}

	private boolean onScreen(String value) {
		//return DragonOnScreen // this does not exist
		return true;
	}

	private boolean AnimationIs(String value) {
		String[] split = value.split("-");
		if (split.length > 0){						// Check to make sure proper format used. 
			for (int i = 0; i < g.i().mapObjects.size(); i++){
				if (split[0].equalsIgnoreCase(g.i().mapObjects.get(i).name)){
					if (split[1].equalsIgnoreCase(g.i().mapObjects.get(i).getAnimation())){
						return true;
					}
				}
			}
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