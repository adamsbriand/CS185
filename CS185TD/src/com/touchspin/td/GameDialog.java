package com.touchspin.td;

import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
	
	private Sprite sprite;
	//private OrthographicCamera backGroundCamera;	
	//private DialogSnippet currentSnippet;
	private SpriteBatch batch;
	private BitmapFont font;
	//private String songPath;
	//private float pauseTime;
	//private float currentTime;			
	
	// info from XML file for a single snippet
	
	private String[] textArray;
	private String currentText;
	private String speakerName;
	private char position;
	private String backGroundPath;
	private String bubblePath;
	private String songPath;	
	private String imagePath;
	private short dialogCount; //text strip to read from in the current dialog snippet
	private short snippetCount;//dialog snippet being used
	private short charCount;// used to create substrings for display text one at a time.
	private int h;//holds screen height
	private int w;//holds screen width
	private final long secondsPerChar;//how many seconds past between each char print
	private long nextPrintTime;//next time that a char will be printed
	
	private float textX = 0;
	private int textY = 0;
	
	
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
		
		font = new BitmapFont();
		
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		currentText = "";
		secondsPerChar = 250;
		nextPrintTime = 0;
		backGroundPath = null;
		songPath = null;	
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
			batch.draw(background, 0, 0);
		}
		//draw speech bubble
		if(!bubblePath.equals("none"))
		{
			Texture speechBubble = new Texture(Gdx.files.internal("DialogImages/DlgSpeechBubble.png"));
			batch.draw(speechBubble, 0, 0);		
		}
		
		font.draw(batch, currentText, textX, textY);
		
		batch.end();
	}	
	
	@Override
	public void update() 
	{		
		if(System.currentTimeMillis() >= nextPrintTime)
			nextChar();
		
		//used to display text
		if(position == 'L')
		{
			textX = 50;
			textY = 50;
		}
		else if (position == 'C')
		{
			textX = w/2 - ((currentText.length() * font.getSpaceWidth())/2);
			textY = h/2;
		}
		else if(position == 'R')
		{
			textX = w - 50;
			textY = h - 50;
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
		songPath = items.get(snippetCount).getAttribute("music");		
		bubblePath = items.get(snippetCount).getAttribute("textBubble");
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
			currentText += textArray[dialogCount].charAt(charCount++);
			if(textArray[dialogCount].charAt(charCount - 1) == ' ')
				currentText += textArray[dialogCount].charAt(charCount++);
		}
		catch(StringIndexOutOfBoundsException e)
		{
			dialogCount += 4;// a command is next
			charCount = 0;
			performAction(textArray[dialogCount - 2], textArray[dialogCount - 1]);
		}
		nextPrintTime += secondsPerChar;
	}
	
	private void createBackGround()
	{
		float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();		
		
		Texture backGround = new Texture(Gdx.files.internal(backGroundPath));	
		TextureRegion region = new TextureRegion(backGround, 0, 0, 800, 420);
		sprite = new Sprite(region);
		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth() );
		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.setPosition(-sprite.getWidth() / 2, -sprite.getHeight() / 2);					
	}	
	
	private void performAction(String command, String value)
	{
		if(command.equals("pause"))
		{
			nextPrintTime += Integer.parseInt(value) * 1000;
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
