package com.touchspin.td;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CS185TD";
		cfg.width = 800;
		cfg.height = 500;
		
		new LwjglApplication(new MainGame(), cfg);
	}
}
