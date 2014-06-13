package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
 
public abstract class GameMenu extends GameObject {
    Skin skin;
    Stage stage;
    SpriteBatch batch;
    MainGame game;
    Sprite bg;
    Sprite logo;
    TextButtonStyle textButtonStyle;
    
    public GameMenu (MainGame MainGame){
    	setBG();
        setTemplet();
        buttons();
        logo();
        this.game=MainGame;
    }
    
    abstract void buttons();
    abstract void logo();
    
    public GameMenu (){
        setBG();
    }
    
    void setBG(){
	    camera = new OrthographicCamera();
	    bg = new Sprite(new Texture(Gdx.files.internal("img/menu/ScreenBG.png")));
	    bg.setOrigin(bg.getWidth() / 2, bg.getHeight() / 2);
	    bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    private void setTemplet(){
    	
    	BitmapFont bfont;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        NinePatchDrawable npUP = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceUp.png")), 20, 20, 20, 20));
        NinePatchDrawable npDown = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceDown.png")), 20, 20, 20, 20));
        NinePatchDrawable npCheck = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceChecked.png")), 20, 20, 20, 20));
 
        bfont = new BitmapFont(g.i().font);
        if (Gdx.app.getType().toString() == "Desktop"){
        	bfont.scale(1);
        } else {
        	bfont.scale(3);
        }
        bfont.setColor(Color.WHITE);
        new TextButtonStyle();
        textButtonStyle = new TextButtonStyle(npUP,  npDown, npCheck, bfont);
        textButtonStyle.pressedOffsetX = 3;
        textButtonStyle.pressedOffsetY = -3;
    }
    
    protected void setLogo(FileHandle logoImg){
    	if (logoImg == null) return;
    	logo = new Sprite(new Texture(logoImg));
    	logo.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
    	float x = (float)((Gdx.graphics.getWidth() - logo.getWidth()) / 2) ;
    	float y = (float)(Gdx.graphics.getHeight() / 4 * 3 - logo.getHeight() / 2);
    	logo.setPosition(x, y);
    	
    }
 
    public void render (float delta) {
    	update();
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        batch.begin();
        bg.draw(batch);
        if (logo != null) logo.draw(batch);
        batch.end();
        stage.draw();
        Table.drawDebug(stage);
    }
    
    public void language(){
    	setTemplet();
	}
 
    @Override
    public void resize (int width, int height) {

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