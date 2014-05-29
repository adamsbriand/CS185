package com.touchspin.td;

import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Dialog Snippet for speakers. mainly used for traversing through the text.
 * @author KingD777
 *
 */
public class DialogSnippet 
{
	private int textPoint; //next strip to read from
	private String[] textArray;
	private String speakerName;
	private char position;
	private String backGroundPath;
	private String songPath;	
	private String imagePath;
		
	public DialogSnippet(Element item)
	{		
		this.speakerName = item.getAttribute("name");
		this.textArray = item.getAttribute("text").split(":");	
		this.position = item.getAttribute("position").charAt(0);
		this.backGroundPath = item.getAttribute("background");
		this.songPath = item.getAttribute("song");
		this.textPoint = 0;			
	}
	
	public String getName()
	{	return speakerName;	}	
	
	public char getPosition()
	{ return position; 	}
	
	public String getBackgroundPath()
	{ return backGroundPath;	}
	
	public String getSongPath()
	{	return songPath;	}
	
	public String getImagePath()
	{ return imagePath;	}
	
	public String getNextStrip()
	{	
		if(textArray[textPoint] == "C_")
			return null; 
		else
			return textArray[textPoint++];
	}	
	
	public String[] getNextCommand()
	{				
		if(textArray[textPoint] == "C_")
		{
			textPoint += 3; //increment
			return new String[] { textArray[textPoint - 2],textArray[textPoint - 1] };
		}
		else
			return null;
	}
}
