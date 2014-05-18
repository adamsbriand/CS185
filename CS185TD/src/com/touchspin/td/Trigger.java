package com.touchspin.td;

/* This class handles any triggers that the user may encounter when colliding with hidden
 * tiles.  This trigger requires the input of two string values, or one comma delimited
 * string.  The first value will indicate the action to be taken.  The second value will 
 * effect how that action will take place.  
 */

public class Trigger {
	MainGame game;
	   
	public Trigger(MainGame game, String ChangeType, String ChangeValue) {
		this.game = game;
		TriggerActions(ChangeType, ChangeValue);
	}
	
	public Trigger(String ChangeType, String ChangeValue) {
		TriggerActions(ChangeType, ChangeValue);
	}
	
	public Trigger(MainGame game, String TriggerString){
		this.game = game;
		int split = TriggerString.indexOf(',');
		String Value1 = TriggerString.substring(0,split - 1);
		String Value2 = TriggerString.substring(split + 1);
		TriggerActions(Value1, Value2);
	}
	
	public Trigger(String TriggerString){
		int split = TriggerString.indexOf(',');
		String Value1 = TriggerString.substring(0,split - 1);
		String Value2 = TriggerString.substring(split + 1);
		TriggerActions(Value1, Value2);
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
