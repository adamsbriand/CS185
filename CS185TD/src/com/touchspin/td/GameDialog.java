package com.touchspin.td;

public class GameDialog extends GameObject {

	public GameDialog(){
		anonymizer = new DialogInputAnonymizer(){

			@Override
			public boolean click() {
				// TODO Auto-generated method stub
				return false;
			}
			
		};
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}

}
