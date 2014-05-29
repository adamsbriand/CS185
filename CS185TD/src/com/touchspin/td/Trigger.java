package com.touchspin.td;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

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
	
	public void action(String Conditional, String Action){
		boolean go = condition(Conditional);
		if (go){
			action(Action);
		}
	}

	// public Conditions
	public boolean condition(String conditionString){
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

    // private Actions
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
			default:
		}
	}

	// Changes to the hero
	private void igniteBall(String value) {
		if (value=="true"){
			g.i().fire = true;
		} else {
			g.i().fire = false;
		}
	}
	
	private boolean ballFlammable(String value){
		if (value=="true"){
			return g.i().fire;
		} else {
			return !(g.i().fire);
		}
	}

	private void changeBalldY(String value) {
		// -10
		float change = Float.parseFloat(value);
		g.i().hero.changeBalldy(change);
	}

	private void changeLocation(String value) {
		// dest1
		// enter1
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
	
	// Changes to sound
	private void playSoundLoop(String value) {
		if (g.i().sfx){
			switch (value){
				case "sndFanOn":
					g.i().sound.sndWindBlowing.loop(g.i().sfxLevel);
					break;
				case "sndFire":
					g.i().sound.sndCampFire.loop(g.i().sfxLevel);
					break;
			}
		}
	}
	
	private void playSound(String value) {
		if (g.i().sfx){
			Random random = new Random();
			int rand;
			switch (value){
			case "sndGlassBreak":
				rand = random.nextInt(2) + 1;
				if (rand == 1){
					g.i().sound.sndGlassBreak1.play(g.i().sfxLevel);
				} else {
					g.i().sound.sndGlassBreak2.play(g.i().sfxLevel);
				}
				break;
			case "sndLightSwitch":
				g.i().sound.sndLightSwitch.play(g.i().sfxLevel);
				break;
			case "sndDoorOpen":
	
				break;
			case "sndSlideWhistleDown":
				g.i().sound.sndSlideWhistleDown.play(g.i().sfxLevel);
				break;
			case "bounce":
				rand = random.nextInt(3) + 1;
				switch (g.i().currentBallType){
					case "pingpong":
						switch (rand){
							case 1:
								g.i().sound.sndPingPongBounce1.play(g.i().sfxLevel);
								break;
							case 2:
								g.i().sound.sndPingPongBounce2.play(g.i().sfxLevel);
								break;
							case 3:
								g.i().sound.sndPingPongBounce3.play(g.i().sfxLevel);
								break;
						}
						break;
					case "":
				}
			}
		}
	}
	
	private void changeMusic(String value) {
		/*
		if (g.i().music){
			g.i().sound.sndg1Loopable.stop();
			g.i().sound.sndgScaryIntro.stop();
			g.i().sound.sndgScaryLoopable.stop();
			switch (value){
				case "dragon":
					g.i().sound.sndgScaryIntro.setVolume(g.i().musicLevel);
					g.i().sound.sndgScaryIntro.play();
					g.i().sound.sndgScaryIntro.setOnCompletionListener(
							new Music.OnCompletionListener(){
						public void onCompletion(Music music) {
							g.i().sound.sndgScaryLoopable.setVolume(g.i().musicLevel);
							g.i().sound.sndgScaryLoopable.play();
							g.i().sound.sndgScaryLoopable.setLooping(true);
						}});
					break;
				default:
					g.i().sound.sndg1Loopable.setVolume(g.i().musicLevel);
					g.i().sound.sndg1Loopable.play();
					g.i().sound.sndg1Loopable.setLooping(true);
			}
		}
		*/
		switch (value){
			case "dragon":
				g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/songScaryIntro.wav"));
				g.i().sound.bgMusic.setLooping(false);
				g.i().sound.bgMusic.setOnCompletionListener(
						new Music.OnCompletionListener(){
					public void onCompletion(Music music) {
						g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/songScaryLoopable.wav"));
						g.i().sound.bgMusic.setVolume(g.i().musicLevel);
						g.i().sound.bgMusic.play();
						g.i().sound.bgMusic.setLooping(true);
						g.i().sound.bgMusic.setOnCompletionListener(null);
					}});
				break;
			default:
				g.i().sound.bgMusic = Gdx.audio.newMusic(Gdx.files.internal("snd/song1Loopable.wav"));
				g.i().sound.bgMusic.setLooping(true);
		}
		if (g.i().music){
			g.i().sound.bgMusic.setVolume(g.i().musicLevel);
			g.i().sound.bgMusic.play();
		}
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
			if (objectName==g.i().mapObjects.get(i).getName()){
				g.i().mapObjects.get(i).setCollidable(collidable);
			}
		}
		// door1,false
	}

	private void changeOthersAnim(String value) {
		String[] Values = value.split("-");
		for (int i=0; i < g.i().mapObjects.size(); i++){
			
			if (Values[0].equalsIgnoreCase(g.i().mapObjects.get(i).getName())){
				g.i().mapObjects.get(i).setAnimation(Values[1]);
				NP test = g.i().mapObjects.get(i);
				int stop =8;
			}
		}
		// fan1,on"
		// door1,open
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

	// Change screens
	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new ScreenMenu(game));
			break;
		}
	}

	private void NewLevel(String Value){
		switch (Value){
		case "Level1Run1":
			g.i().gameMode = 'R';
			game.setScreen(new GameScreen(game, "map/Level1Runner1.tmx"));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
			break;
		case "Level1Maze1":
			g.i().gameMode = 'M';
			game.setScreen(new GameScreen(game, "map/Level1Maze1.tmx"));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
			break;
		case "Level1Dialog1":
			g.i().gameMode = 'D';
			game.setScreen(new GameDialog(game, "BeginingDialog.xml"));
		}
	}

	// Private Conditions
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
		//return DragonOnScreen // this does not exist
		return true;
	}

	private boolean myAnimationIs(String value) {
		// on
		return false;
	}

	private boolean ballType(String value) {
		if (value==g.i().currentBallType){
			return true;
		}
		return false;
	}

}