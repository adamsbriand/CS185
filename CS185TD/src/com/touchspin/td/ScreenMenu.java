package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.graphics.g2d.NinePatch;
 
public class ScreenMenu extends GameObject {
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    Texture texture;
    MainGame game;
    Sprite sprite;
    NinePatch np;
    TextButton textButton;
    TextButton textButton2;
    
    public ScreenMenu (MainGame MainGame){
        create();
        setBG();
        this.game=MainGame;
    }
 
    public ScreenMenu(){
        create();
    }
    
    private void setBG(){
    	float w = Gdx.graphics.getWidth();
	    float h = Gdx.graphics.getHeight();
	    camera = new OrthographicCamera();
	    texture = new Texture(Gdx.files.internal("img/menu/ScreenBG.png"));
	    sprite = new Sprite(texture);
	    sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
	    sprite.setBounds(0, 0, w, h);
    }
    
    public void create(){
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        Texture buttonTexture = new Texture(Gdx.files.internal("img/menu/Btn9SliceDown.png"));
        np = new NinePatch(buttonTexture);
        np.setLeftWidth(20);
        np.setRightWidth(20);
        np.setMiddleWidth(20);
        np.setTopHeight(20);
        np.setBottomHeight(20);
        np.setMiddleHeight(20);
        
        NinePatchDrawable npDraw = new NinePatchDrawable(np);
 
        // Store the default libgdx font under the name "default".
        BitmapFont bfont=new BitmapFont();
        bfont.scale(1);
        
        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButtonStyle textButtonStyle = new TextButtonStyle(npDraw,  npDraw, npDraw, bfont);
        
        //Slider slider = new Slider(0, 100, 1, false, );
 
        // Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
        textButton=new TextButton("New Game",textButtonStyle);
        textButton.setPosition(200, 200);
        stage.addActor(textButton);
        
        textButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
            	g.i().t.action("NewLevel,Level1Dialog1");
            }
        });
        
        textButton2=new TextButton("Test Menu",textButtonStyle);
        textButton2.setPosition(500, 200);
        stage.addActor(textButton2);
        
        
        
        textButton2.addListener(new ChangeListener() {
        	public void changed (ChangeEvent event, Actor actor) {
        		g.i().t.action("menu,Test");
        	}
        });
    }
 
    public void render (float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        sprite.draw(batch);
        //np.draw(batch, textButton.getOriginX(), textButton.getOriginY(), textButton.getWidth(), textButton.getHeight());
        //np.draw(batch, textButton2.getOriginX(), textButton2.getOriginY(), textButton2.getWidth(), textButton2.getHeight());
        batch.end();
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
}