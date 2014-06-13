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
    
    String KeyboardMouse;
	String Continue;
	String Gyro;
	String Keyboard;
	String Mouse;
	String MuteMusic;
	String MuteSFX;
	String Back;
	String Sounds;
	String Controls;
	String Language;
	String NewGame;
	String Options;
	String HighScore;
	String Credits;
	String Exit;
	String CurrentLanguage;
    
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
    	NinePatch np;
    	
    	BitmapFont bfont;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        np = new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceUp.png")), 20, 20, 20, 20);
        NinePatchDrawable npUP = new NinePatchDrawable(np);
        
        np = new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceDown.png")), 20, 20, 20, 20);
        NinePatchDrawable npDown = new NinePatchDrawable(np);
        
        np = new NinePatch(new Texture(Gdx.files.internal("img/menu/Btn9SliceChecked.png")), 20, 20, 20, 20);
        NinePatchDrawable npCheck = new NinePatchDrawable(np);
 
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
    	g.i().languageSet();
    	setTemplet();
		switch (g.i().language){
		case "en":
			CurrentLanguage = "English";
			Continue = "Continue";
			Gyro = "Gyro";
			Keyboard = "Keyboard";
			Mouse = "Mouse";
			KeyboardMouse = "Keyboard & Mouse";
			MuteMusic = "Mute Music";
			MuteSFX = "Mute SFX";
			Back = "Back";
			Sounds = "Sounds";
			Controls = "Controls";
			Language = "Language";
			NewGame = "New Game";
			Options = "Options";
			HighScore = "High Score";
			Credits = "Credits";
			Exit = "Exit";
			break;
		case "zh":
			CurrentLanguage = "\u4e2d\u6587";
			Continue = "\u7ee7\u7eed";
			Gyro = "\u9640\u87ba\u4eea";
			Keyboard = "\u952e\u76d8";
			Mouse = "\u9f20\u6807";
			KeyboardMouse = "\u9f20\u6807\u4e0e\u952e\u76d8";
			MuteMusic = "\u9759\u97f3\u80cc\u666f\u97f3\u4e50";
			MuteSFX = "\u9759\u97f3\u97f3\u6548";
			Back = "\u540e\u9000";
			Sounds = "\u97f3\u6548";
			Controls = "\u63a7\u5236";
			Language = "\u8bed\u8a00";
			NewGame = "\u65b0\u6e38\u620f";
			Options = "\u9009\u9879";
			HighScore = "\u6700\u9ad8\u5206";
			Credits = "\u5236\u4f5c\u4eba\u5458\u540d\u5355";
			Exit = "\u9000\u51fa";
			break;
		case "es":
			CurrentLanguage = "Espa\u00f1ol";
			Continue = "Contin\u00fae";
			Gyro = "giroszk\u00f3p";
			Keyboard = "Teclado";
			Mouse = "Rat\u00f3n";
			KeyboardMouse = "Teclado & Rat\u00f3n";
			MuteMusic = "Silenciar M\u00fasica";
			MuteSFX = "Silenciar SFX";
			Back = "Retorno";
			Sounds = "Sonidos";
			Controls = "Configuraci\u00f3n";
			Language = "Idioma";
			NewGame = "Nuevo juego";
			Options = "Opciones";
			HighScore = "R\u00e9cord";
			Credits = "Cr\u00e9dito";
			Exit = "Salida";
			break;
		case "vi":
			CurrentLanguage = "ti\u1ebfng Vi\u1ec7t";
			Continue = "ti\u1ebfp t\u1ee5c";
			Gyro = "con quay h\u1ed3i chuy\u1ec3n";
			Keyboard = "b\u00e0n ph\u00edm";
			Mouse = "chu\u1ed9t";
			KeyboardMouse = "chu\u1ed9t v\u00e0 b\u00e0n ph\u00edm";
			MuteMusic = "t\u1eaft nh\u1ea1c n\u1ec1n";
			MuteSFX = "t\u1eaft nh\u1ea1c ngo\u1ea1i c\u00e3nh";
			Back = "quay l\u1ea1i";
			Sounds = "\u00e2m thanh";
			Controls = "danh m\u1ee5c \u0111i\u1ec1u khi\u1ec3n";
			Language = "ng\u00f4n ng\u1eef";
			NewGame = "thi\u1ebft l\u1eadp l\u1ea1i tr\u00f2 ch\u01a1i";
			Options = "danh m\u1ee5c \u0111i\u1ec1u ch\u1ec9nh";
			HighScore = "danh m\u1ee5c \u0111i\u1ec3m";
			Credits = "c\u00f4ng tr\u1ea1ng";
			Exit = "tho\u00e1t ra";
			break;
		case "ja":
			CurrentLanguage = "\u65e5\u672c\u8a9e";
			Continue = "\u30b3\u30f3\u30c6\u30a3\u30cb\u30e5\u30fc";
			Gyro = "\u30b8\u30e3\u30a4\u30ed";
			Keyboard = "\u30ad\u30fc\u30dc\u30fc\u30c9";
			Mouse = "\u30de\u30a6\u30b9";
			KeyboardMouse = "\u30de\u30a6\u30b9&\u30ad\u30fc\u30dc\u30fc\u30c9";
			MuteMusic = "\u30df\u30e5\u30fc\u30c8Music";
			MuteSFX = "\u30df\u30e5\u30fc\u30c8SFX";
			Back = "\u30ea\u30bf\u30fc\u30f3";
			Sounds = "\u30b5\u30a6\u30f3\u30c9";
			Controls = "\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb";
			Language = "\u8a00\u8a9e";
			NewGame = "\u30cb\u30e5\u30fc\u30b2\u30fc\u30e0";
			Options = "\u30aa\u30d7\u30b7\u30e7\u30f3";
			HighScore = "\u30cf\u30a4\u30b9\u30b3\u30a2";
			Credits = "\u30af\u30ec\u30b8\u30c3\u30c8";
			Exit = "\u30a8\u30b0\u30b8\u30c3\u30c8";
			break;
		}
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