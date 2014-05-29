package com.touchspin.td;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class GameDialog extends GameObject
{
	private XmlReader xml;
	private FileHandle script;		
	private Element root;
	private Array<Element> items;
	private DialogSnippet speakerA;
	private DialogSnippet speakerB;
	private SpriteBatch batch;
	private Texture backGround;
	private String backGroundPath;
	private String songPath;
	private int count;
	private boolean currentSpeakerisA = true;
	
	
	public GameDialog(MainGame game, String scriptPath)
	{
				
		this.xml = new XmlReader();
		this.script = new FileHandle(scriptPath);
		// get script xml
		try
		{
			this.root = this.xml.parse(script);
		}
		catch(IOException e)
		{
			
		}
		this.items = root.getChildrenByName("Snippet");	
		this.count = 0;
		this.backGround = null;
		this.songPath = null;		
	}
	
	@Override
	public void update() 
	{
		next();
		if(currentSpeakerisA) 
		{			
			if(!speakerA.getBackgroundPath().equals(backGroundPath))			
				switchBackground(speakerA.getBackgroundPath());	
				
			//if(!speakerA.getSongPath().equals(songPath))
				//switchSong(speakerA.getSongPath());		
			//speak(speakerA);
			//currentSpeakerisA = false;
		}
		else 
		{
			if(!speakerB.getBackgroundPath().equals(backGroundPath))			
				switchBackground(speakerB.getBackgroundPath());		
			
			//if(!speakerB.getSongPath().equals(songPath))
				//switchSong(speakerA.getSongPath());
			//speak(speakerB);
			//currentSpeakerisA = true;
		}		
	}
	
	private void speak(DialogSnippet speaker)
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
		
	}
	
	private void switchBackground(String path)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		backGroundPath = path;
		backGround = new Texture(Gdx.files.internal(backGroundPath));	
		batch.draw(backGround, 0, 0);
	}
	
	private void switchSong(String path)
	{
		songPath = path;
		// need to switch the song here and play it. might not need this method
	}
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void render(float delta) {
		update();
		draw();		
	}
	
	// get next snippets
	private void next()
	{
		speakerA = new DialogSnippet(items.get(count++));
		speakerB = new DialogSnippet(items.get(count++));
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
		backGround.dispose();
		
		
	}
}
