package com.touchspin.td;

/* This class handles any triggers that the user may encounter when colliding with hidden
 * tiles.  This trigger requires the input of two string values, or one comma delimited
 * string.  The first value will indicate the action to be taken.  The second value will 
 * effect how that action will take place.  
 */

public class Trigger {
	MainGame game;
	
	public Trigger(MainGame game){
		this.game = game;
	}
	   
	public void action(String ChangeType, String ChangeValue) {
		TriggerActions(ChangeType, ChangeValue);
	}
	
	public void action(String TriggerString){
		String[] Values = splitText(TriggerString);
		TriggerActions(Values[0], Values[1]);
	}
	
	private String[] splitText(String value){
		int split = value.indexOf(',');
		String[] ValueArray = new String[2]; 
		ValueArray[0] = value.substring(0,split - 1);
		ValueArray[1] = value.substring(split + 1);
		return ValueArray;
	}
   
	private void TriggerActions(String value1, String value2) {
		switch (value1){
		case "NewLevel":
			NewLevel(value2);
			break;
		case "switchBall":
			switchBall(value2);
			break;
		case "changeState":
			changeState(value2);
			break;
		case "startAnimation":
			startAnimation(value2);
			break;
		case "menu":
			menu(value2);
			break;
		default:
		}
	}

	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new Menu(game));
			break;
		}
	}

	private void NewLevel(String Value){
		if (Value=="Runner"){
			g.i().gameMode = 0;
			game.setScreen(new Runner(game));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
		}
		if (Value == "Maze"){
				g.i().gameMode = 1;
				game.setScreen(new Maze(game));
				g.i().leAnonymizer.click = false;
				g.i().leAnonymizer.resetAll();
		}
	}
	
	private void switchBall(String Value){
		
	}
	
	private void changeState(String Value){
		
	}
	
	private void startAnimation(String Value){
		
	}
}
