package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public class ScreenCredits extends GameMenu {
	final String Creditsimage = "img/menu/Title.png";
			
	public ScreenCredits(MainGame mainGame){
		super(mainGame);
		g.i().sound.BGMusic("menu");
	}

	@Override
	void buttons() {
			NinePatchDrawable draw = new NinePatchDrawable(new NinePatch(new Texture(Gdx.files.internal(Creditsimage))));
			BitmapFont bfont = new BitmapFont();
	        bfont.scale(0);
			TextButton btn = new TextButton("", new TextButtonStyle(draw, draw, draw, bfont));
			btn.setHeight(Gdx.graphics.getHeight());
			btn.setWidth(Gdx.graphics.getWidth());
			btn.setVisible(false);
			btn.setVisible(true);
			btn.addListener(new ChangeListener() {
	        	public void changed (ChangeEvent event, Actor actor) {
	        		g.i().t.action("menu,Main");
	        	}
	        });
			stage.addActor(btn);
		}

	@Override
	void logo() {
		// TODO Auto-generated method stub
		
	}

}
