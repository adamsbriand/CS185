package com.touchspin.td;

public class NP extends GameThing {

	String name;
	String type;
	Mover npMover;
	String conditional;
	String action;
	
	public NP(int startX, int startY, int width, int height, String name, 
			String type, String conditional, String action)
	{
		setX(startX);
		setY(startY);
		setWidth(width);
		setHeight(height);
		this.name = name;
		this.type = type;
		this.conditional = conditional;
		this.action = action;
		
		if(type == "exampleNPC")
			npMover = new MoverPhysics();
		else if(type == "exampleNPO")
			npMover = new MoverAI();
		
	}
}
