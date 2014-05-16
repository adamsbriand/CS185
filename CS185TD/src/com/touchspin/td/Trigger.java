package com.touchspin.td;

/* This class handles any triggers that the user may encounter when colliding with hidden
 * tiles.  This trigger requires the input of two string values, or one comma delimited
 * string.  The first value will indicate the action to be taken.  The second value will 
 * effect how that action will take place.  
 */

public class Trigger {
	   
	public Trigger(String ChangeType, String ChangeValue) {
		TriggerActions(ChangeType, ChangeValue);
	}
	
	public Trigger(String TriggerString){
		int split = TriggerString.indexOf(',');
		String Value1 = TriggerString.substring(0,split - 1);
		String Value2 = TriggerString.substring(split + 1);
		TriggerActions(Value1, Value2);
	}
   
	private void TriggerActions(String Value1, String value2) {
		switch (value2){
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
		default:
		}
	}

	private void NewLevel(String Value){
		
		g.i().StartNewLevel();
	}
	
	private void switchBall(String Value){
		
	}
	
	private void changeState(String Value){
		
	}
	
	private void startAnimation(String Value){
		
	}
}
