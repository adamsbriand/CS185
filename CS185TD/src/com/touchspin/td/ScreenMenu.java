package com.touchspin.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Slider.SliderStyle;
 
public class ScreenMenu implements Screen {
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    MainGame game;
    
    public ScreenMenu (MainGame MainGame){
        create();
        this.game=MainGame;
    }
 
    public ScreenMenu(){
        create();
    }
    
    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
 
        // A skin can be loaded via JSON or defined programmatically, either is fine. Using a skin is optional but strongly
        // recommended solely for the convenience of getting a texture, region, etc as a drawable, tinted drawable, etc.
        skin = new Skin();
        // Generate a 1x1 white texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(100, 100, Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
 
        skin.add("white", new Texture(pixmap));
 
        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();
        bfont.scale(1);
        skin.add("default",bfont);
 
        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
 
        textButtonStyle.font = skin.getFont("default");
 
        skin.add("default", textButtonStyle);
        
        //Slider slider = new Slider(0, 100, 1, false, );
 
        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        final TextButton textButton=new TextButton("New Runner",textButtonStyle);
        textButton.setPosition(200, 200);
        stage.addActor(textButton);
        
        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Run1");
            }
        });
        
        final TextButton textButton2=new TextButton("New Maze",textButtonStyle);
        textButton2.setPosition(500, 200);
        stage.addActor(textButton2);
        
        textButton2.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("NewLevel,Level1Maze1");
        	}
        });
    }
 
    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        Table.drawDebug(stage);
    }
 
    @Override
    public void resize (int width, int height) {
        //stage.setViewport(width, height, false);
    }
 
    @Override
    public void dispose () {
        stage.dispose();
        skin.dispose();
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
}