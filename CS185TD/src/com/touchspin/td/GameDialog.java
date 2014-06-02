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
	
	private int snippetCount;		
	
	// info from XML file for a single snippet
	private int dialogCount; //next strip to read from
	private String[] textArray;
	private String speakerName;
	private char position;
	private String backGroundPath;
	private String songPath;	
	private String imagePath;
	
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
		
		backGroundPath = null;
		songPath = null;	
		snippetCount = 0;
		batch = new SpriteBatch();
		next();		
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
		Texture background = new Texture(Gdx.files.internal(backGroundPath));
		Texture speechBubble = new Texture(Gdx.files.internal("DialogImages/DlgSpeechBubble.png"));
		Skin mySkin = new Skin();
		
		
		batch.begin();
		
		//draw background
		batch.draw(background, 0, 0);
		
		//draw speech bubble
		batch.draw(speechBubble, 0, 0);		
		
		//used to display text
		font.draw(batch, "sup people", 50, 50);
		
		batch.end();
	}	
	
	@Override
	public void update() 
	{		
		
	}
	
	/**
	 * Get the next snippet.
	 */
	private void next()
	{	
		speakerName = items.get(snippetCount).getAttribute("name");
		textArray = items.get(snippetCount).getAttribute("text").split(":");	
		position = items.get(snippetCount).getAttribute("position").charAt(0);		
		backGroundPath = items.get(snippetCount).getAttribute("background");		
		songPath = items.get(snippetCount).getAttribute("music");		
		dialogCount = 0;
		snippetCount++;
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
	
	/*private void speak(DialogSnippet speaker)
	{
		Texture speakerImage = new Texture(Gdx.files.internal(speaker.getImagePath()));
		float x, y;
		if(speaker.getPosition() == 'L')
		{
			x = 0;
			y = backGround.getHeight();
		}
		else
		{
			x = backGround.getWidth() - speakerImage.getWidth();
			y = backGround.getHeight();
		}
		
	}*/
	
	
	
	/*private void switchSong(String path)
	{
		songPath = path;
		// need to switch the song here and play it. might not need this method
	}*/
	
	
	
	
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
