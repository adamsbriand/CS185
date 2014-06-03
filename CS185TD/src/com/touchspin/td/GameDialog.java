package com.touchspin.td;

import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class GameDialog extends GameObject
{
	//used to parse XML file
	private XmlReader xml;
	private FileHandle script;	
	private Element root;
	private Array<Element> items;
	
	private SpriteBatch batch;
	private BitmapFont font;				
	
	// info from XML file for a single snippet
	private String[] textArray;
	private String currentText;
	private String speakerName;
	private char position;
	private String backGroundPath;	
	private short dialogCount; //text strip to read from in the current dialog snippet
	private short snippetCount;//dialog snippet being used
	private short charCount;// used to create substrings for display text one at a time.
	private int h;//holds screen height
	private int w;//holds screen width
	private final long secondsPerChar;//how many seconds past between each char print
	private long nextPrintTime;//next time that a char will be printed
	private long finalPrintPause;
		
	private float textX = 0;
	private int textY = 0;	
	private boolean finishedWithSnippet = false;
	
	public GameDialog(MainGame game, String scriptPath)
	{				
		//parse XML file
		xml = new XmlReader();		
		script = Gdx.files.internal(scriptPath);	
		try
		{	root = xml.parse(script);	}
		catch(IOException e)
		{}		
		items = root.getChildrenByName("Snippet");	
		g.i().t.action("changeMusic," + root.getAttribute("music"));
		
		font = new BitmapFont();
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		currentText = "";
		secondsPerChar = 250;
		nextPrintTime = 0;
		backGroundPath = null;
		snippetCount = 0;
		charCount = 0;
		batch = new SpriteBatch();
		nextSnippet();		
	}
	
	@Override
	public void render(float delta) 
	{		
		update();	
		draw();		
	}
	
	@Override
	public void draw() 
	{
		Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 0 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );	
		batch.begin();
		
		//draw background
		if(!backGroundPath.equals("black"))			
		{
			Texture background = new Texture(Gdx.files.internal(backGroundPath));
			batch.draw(background, w/2 - (background.getWidth()/2), h/2 - (background.getHeight()/2));
		}
			
		font.drawMultiLine(batch, currentText, textX, textY);	
		
		batch.end();
	}	
	
	@Override
	public void update() 
	{		
		if(System.currentTimeMillis() >= nextPrintTime)
		{
			if(finishedWithSnippet)
			{
				nextSnippet();
				finishedWithSnippet = false;
			}
			nextChar();
		}
		
		//used to display text
		switch(position)
		{
			case'L':		
				textX = 50;
				textY = 50;
				break;
			case 'C':
				textX = w/2 - ((currentText.length() * font.getSpaceWidth())/2);
				textY = h/2;
				break;
			case'R':
				textX = w - 100 - ((currentText.length() * font.getSpaceWidth())/2);
				textY = h - 50;	
				break;
			case 'B':
				textX = w/2 - ((currentText.length() * font.getSpaceWidth())/2);
				textY = 50;
				break;
		}		
	}
	
	/**
	 * Get the next snippet.
	 */
	private void nextSnippet()
	{	
		speakerName = items.get(snippetCount).getAttribute("name");
		textArray = items.get(snippetCount).getAttribute("text").split(":");	
		position = items.get(snippetCount).getAttribute("position").charAt(0);		
		backGroundPath = items.get(snippetCount).getAttribute("background");		
		finalPrintPause = Integer.parseInt(items.get(snippetCount).getAttribute("finalPause"));
		currentText = speakerName + ":\n";
		dialogCount = 0;
		snippetCount++;
	}
	
	/**
	 * Adds the next char for displaying text
	 */
	private void nextChar()
	{
		nextPrintTime = System.currentTimeMillis();
		
			try
			{
				if(textArray[dialogCount].equals("C_"))// check if current text is for a command
				{
					dialogCount += 3;
					performAction(textArray[dialogCount - 2], textArray[dialogCount - 1]);
				}
				else
				{
					currentText += textArray[dialogCount].charAt(charCount++);
					if(textArray[dialogCount].charAt(charCount - 1) == ' ')
						currentText += textArray[dialogCount].charAt(charCount++);
				}
			}			
			catch(Exception e)
			{
				charCount = 0;
				if(dialogCount > textArray.length)
				{
					dialogCount = 0;
					nextPrintTime += finalPrintPause;
					finishedWithSnippet = true;
					currentText = "";					
				}
				else
					dialogCount++;				
			}
		
		nextPrintTime += secondsPerChar;
		
	}
	
	private void performAction(String command, String value)
	{
		if(command.equals("pause"))		
			nextPrintTime += Integer.parseInt(value) * 1000;
		
		else if(command.equals("changeBackground"))
		{
			backGroundPath = value;
		}
		else if(command.equals("end"))
		{
			g.i().t.action(root.get("End"));
		}
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		//backGround.dispose();		
	}
}
