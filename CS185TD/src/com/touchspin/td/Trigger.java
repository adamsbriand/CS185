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
	
	public void action(String actionString){
		String[] Values = actionString.split(",");
		for (int i = 0; i < Values.length; i += 2){
			TriggerActions(Values[i], Values[i+1]);
		}
	}
	
	public void action(String[] actionArray){
		for (int i = 0; i < actionArray.length; i += 2){
			TriggerActions(actionArray[i], actionArray[i+1]);
		}
	}
	
	public boolean condition(String conditionString){
		String[] Values = conditionString.split(",");
		for (int i = 0; i < Values.length; i++){
			boolean status = TriggerConditions(Values[i].toString());
			if (!status){
				return false;
			}
		}
		return true;
	}
	
	public boolean condition(String[] condistionArray){
		for (int i = 0; i < condistionArray.length; i++){
			boolean status = TriggerConditions(condistionArray[i].toString());
			if (!status){
				return false;
			}
		}
		return true;
	}
   
	private void TriggerActions(String type, String value2) {
		switch (type){
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
	
	private boolean TriggerConditions(String value){
		switch (value){
		case "fire":
			return g.i().fire;
		}
		return false;
	}

	private void menu(String value) {
		switch (value){
		case "Main":
			game.setScreen(new ScreenMenu(game));
			break;
		}
	}

	private void NewLevel(String Value){
		if (Value=="Runner"){
			g.i().gameMode = 'R';
			game.setScreen(new Runner(game, "maps/Level1Runner1.tmx"));
			g.i().leAnonymizer.click = false;
			g.i().leAnonymizer.resetAll();
		}
		if (Value == "Maze"){
				g.i().gameMode = 'M';
				game.setScreen(new Maze(game, "maps/Maze1.tmx"));
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
