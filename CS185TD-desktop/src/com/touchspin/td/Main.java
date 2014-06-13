package com.touchspin.td;

import org.lwjgl.input.Keyboard;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Ollie";
		cfg.width = 800;
		cfg.height = 500;
		cfg.addIcon("img/icons/OllieIcon512.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon256.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon144.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon128.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon96.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon72.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon64.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon48.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon32.png", Files.FileType.Internal);
		cfg.addIcon("img/icons/OllieIcon16.png", Files.FileType.Internal);
		cfg.resizable = false;
		new LwjglApplication(new MainGame(), cfg);
		Keyboard.enableRepeatEvents(true);
	}
}
