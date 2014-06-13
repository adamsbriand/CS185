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

/**
 * Class used to display all in game dialog and control instructions
 * @author KingD777
 *
 */
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
	private String lastPath;
		
	private short dialogCount; //text strip to read from in the current dialog snippet
	private short snippetCount;//dialog snippet being used
	private short charCount;// used to create substrings for display text one at a time.
	private int h;//holds screen height
	private int w;//holds screen width
	private final long secondsPerChar;//how many seconds past between each char print
	private long nextPrintTime;//next time that a char will be printed
	private Texture background;	
	private float textX = 0;
	private int textY = 0;	
	private boolean finishedWithSnippet;
	private int skipTextPauseTime;
	private long finalPauseForSkip;
	private boolean skipable;
	private int textPadding;
	private boolean canBreakText;
	private boolean masterSkip;
	
	/**
	 * Constructor
	 * @param game
	 * @param scriptPath
	 */
	public GameDialog(MainGame game, String scriptPath)
	{			
		Element languageRoot = null;
		g.i().leAnonymizer.resetAll();
		
		//parse XML file
		xml = new XmlReader();		
		script = Gdx.files.internal(scriptPath);	
		try
		{	root = xml.parse(script);	}
		catch(IOException e)
		{}			
		
		// get dialog based on current language settings
		languageRoot = root.getChildByName("dialog_" + g.i().language);	
		
		//get list of dialog snippet
		items = languageRoot.getChildrenByName("Snippet");
			
		//check if this dialog has its own music to play
		if(!root.getAttribute("music").equals(""))
			g.i().sound.BGMusic(root.getAttribute("music"));
		
		//initialize variables
		font = new BitmapFont(g.i().font);
		font.scale(Gdx.graphics.getDensity());
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		currentText = "";
		secondsPerChar = 150;
		finalPauseForSkip = 0;
		nextPrintTime = 0;
		backGroundPath = null;
		snippetCount = 0;
		charCount = 0;
		batch = new SpriteBatch();
		lastPath = null;
		finishedWithSnippet = false;
		skipTextPauseTime = 2000;
		skipable = true;
		canBreakText = true;
		textPadding = 50;
		masterSkip = false;
		
		nextSnippet();			
	}// end of constructor
	
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
			if(!backGroundPath.equals(lastPath))
			{
				lastPath = backGroundPath;
				background = new Texture(Gdx.files.internal(backGroundPath));
			}
			batch.draw(background, w/2 - (background.getWidth()/2), h/2 - (background.getHeight()/2));
		}			
		
		font.drawMultiLine(batch, currentText, textX, textY);				
		
		batch.end();
	}	
	
	/**
	 * Display all the text in this snippet at once.
	 */
	private void displayAllText()
	{
		currentText = speakerName + '\n';
		for(int count = 0; count <textArray.length; count++)
		{
			if(textArray[count].equals("C_"))	
			{
				performAction(textArray[count + 1],textArray[count + 2],true);
				count += 2;		
			}
			else				
				for(int innerCount = 0; innerCount < textArray[count].length(); innerCount++)
				{
					currentText += textArray[count].charAt(innerCount);
					if(font.getBounds(currentText).width + textPadding > w && textArray[count].charAt(innerCount) != ' '&& 
						canBreakText)					
						breakText();
				}
		}		
		nextPrintTime += finalPauseForSkip + skipTextPauseTime;
		finishedWithSnippet = true;				
	}
	
	/**
	 * inserts a new line right before the last character in a text.
	 */
	private void breakText()
	{
		
		if(g.i().language == "en" || g.i().language == "es" || g.i().language == "fr")
		{
			int count = 0;
			String lastWord = "";			
			
			for(count = currentText.length() - 1; currentText.charAt(count) != ' '; count--)			
				lastWord += currentText.charAt(count);	
						
			currentText = currentText.substring(0, count) + '\n';			
			
			for(int counter = lastWord.length() - 1; counter >= 0; counter--)			
				currentText += lastWord.charAt(counter);			
		}//end of if English statement		
		else
		{
			currentText += '\n';
		}
		canBreakText = false;
		
	}// end of breakText()
	
	@Override
	public void update() 
	{		
		if(masterSkip)
		{
			if(g.i().leAnonymizer.pausePressed)
			{
				g.i().leAnonymizer.pausePressed = false;
				performAction("end", "null", false);
			}
			if(g.i().leAnonymizer.click)
			{
				g.i().leAnonymizer.click = false;				
				nextSnippet();
			}
		}
		
		if(System.currentTimeMillis() >= nextPrintTime)
		{
			nextPrintTime = System.currentTimeMillis();
				
			if(g.i().leAnonymizer.pausePressed)
			{
				g.i().leAnonymizer.pausePressed = false;
				performAction("end", "null", false);
			}
			
			if(finishedWithSnippet)
			{				
				nextSnippet();
				finishedWithSnippet = false;				
			}	
			else 
			{
				if(g.i().leAnonymizer.click && skipable)
				{
					g.i().leAnonymizer.click = false;
					skipable = false;
					displayAllText();
				}
				else				
					nextChar();				
			}			
		}//end of outer if statement			
	}//end of update method
	
	/**
	 * Get the next snippet in this dialog sequence.
	 */
	private void nextSnippet()
	{		
		canBreakText = true;
		try
		{
			speakerName = items.get(snippetCount).getAttribute("name") + ':';
			textArray = items.get(snippetCount).getAttribute("text").split(":");	
			position = items.get(snippetCount).getAttribute("position").charAt(0);		
			backGroundPath = items.get(snippetCount).getAttribute("background");		
			currentText = speakerName + '\n';
			finalPauseForSkip = Integer.parseInt(items.get(snippetCount).getAttribute("finalPause")) * 100;
		}
		catch(IndexOutOfBoundsException e)
		{
			performAction("end", "null",false);	
		}
		charCount = 0;
		dialogCount = 0;
		snippetCount++;
		
		//set text position
		switch(position)
		{
			case'L':		
				textX = textPadding;
				textY = 70 + (int)(font.getCapHeight() * 2);
				break;
			case 'T':
				textX = textPadding;
				textY = h - (int)(font.getCapHeight() * 2);
				break;
			
			case 'C':
				textX = w/2 - ((currentText.length() * font.getSpaceWidth())/2);
				textY = h/2;
				break;
			case'R':
				textX = w - textPadding - ((getSnippetTextLength() * font.getSpaceWidth()));
				textY = h - 60 - (int)(font.getCapHeight() * 2);	
				break;
			case 'B':
				textX = w/2 - ((currentText.length() * font.getSpaceWidth())/2);
				textY = 60 + (int)(font.getCapHeight() * 2);
				break;
		}	
	}// end of next snippet method
	
	/**
	 * get total characters to display in current snippet
	 * @return
	 */
	private int getSnippetTextLength()
	{
		int length = 0;
		
		for(int count = 0; count < textArray.length; count++)
		{
			if(textArray[count].equals("C_"))
			{
				count += 2;
				continue;
			}
			length += textArray[count].length();
		}
		return length;
	}
	
	/**
	 * Adds the next char for displaying text
	 */
	private void nextChar()
	{
		skipable = true;
		g.i().leAnonymizer.click = false;
		try
		{
			if(textArray[dialogCount].equals("C_"))// check if current text is for a command
			{
				dialogCount += 3;
				performAction(textArray[dialogCount - 2], textArray[dialogCount - 1], false);
			}
			else
			{
				currentText += textArray[dialogCount].charAt(charCount++);
				if(textArray[dialogCount].charAt(charCount - 1) == ' ')
					currentText += textArray[dialogCount].charAt(charCount++);			
				if(font.getBounds(currentText).width + textPadding> w && 
						textArray[dialogCount].charAt(charCount) != ' ' && canBreakText)					
					breakText();
			}
		}			
		catch(Exception e)
		{
			charCount = 0;
			if(dialogCount > textArray.length)
			{
				nextPrintTime += finalPauseForSkip;
				finishedWithSnippet = true;					
			}
			else
				dialogCount++;				
		}		
		nextPrintTime += secondsPerChar;		
	}
		
	/**
	 * perform action commands that are embedded within the snippet text
	 * @param command
	 * @param value
	 * @param skipPauses
	 */
	private void performAction(String command, String value, boolean skipPauses)
	{
		switch(command)
		{
		case "pause":		
			if(!skipPauses)
			{				
				nextPrintTime += Integer.parseInt(value) * 100;
			}
			break;
		
		case "changeBackground":		
			backGroundPath = value;
			break;
		
		case "end":		
			g.i().t.action(root.get("End"));
			break;
		
		case "instaPrint":	
			if(!skipPauses)
				currentText += textArray[dialogCount++];		
		case "masterSkip":
			if(value.equals("true"))
				masterSkip = true;
			else
				masterSkip = false;
			break;
		default :				
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
