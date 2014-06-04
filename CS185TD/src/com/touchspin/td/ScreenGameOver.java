package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

public class ScreenGameOver extends GameMenu{
	
	public ScreenGameOver(MainGame MainGame){
		super(MainGame);
	}

	void buttons() {
	}

	@Override
	void logo() {
		super.setLogo(Gdx.files.internal("img/menu/LogoGameOver.png"));
	}
	
	public void update() {
		if (TimeUtils.millis()>(g.i().timeStartGame+7000)){
			g.i().t.action("menu,Main");
		}
	}

}