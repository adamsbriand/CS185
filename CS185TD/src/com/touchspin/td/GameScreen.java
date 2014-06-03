package com.touchspin.td;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends GameObject {

	Stage stage;
	MainGame game;
	private OrthographicCamera backGroundCamera;
	private OrthographicCamera foregroudCamera;
	private boolean soundLoaded = false;
	private int frameCount = 15;
	private Group bgg = new Group();
	private Group fgg = new Group();
	private Group toppest = new Group();
	private char mode;

	public GameScreen(MainGame game, String mapPath) {

		this.game = game;
		if(g.i().gameMode == 'R')
			mode = 'R';
		else
			mode = 'M';
		tiledMapWrapper = new TiledMapWrapper(mapPath);
		setUpCamera();
		stage = new Stage();
		g.i().hud = new HUD(camera, tiledMapWrapper);
		loadNPs();
		g.i().hero = new Hero(camera, tiledMapWrapper);
		stage.addActor(bgg);
		stage.addActor(g.i().hero);
		stage.addActor(fgg);
		stage.addActor(toppest);
		stage.addActor(g.i().hud);
	}

	@Override
	public void render(float delta) {
		update();
		draw();
	}

	@Override
	public void update() {
		float tempX = g.i().hero.getX();
		float tempY = g.i().hero.getY();
		stage.act();
		camera.update();
		if (mode == 'R') {
			backGroundCamera.update();
			foregroudCamera.update();
		}

		cameraTranslate(g.i().hero.getX() - tempX, g.i().hero.getY() - tempY);
		setView();
		if (soundLoaded) {
			g.i().sound.sndSwitch.play(g.i().sfxLevel);
			g.i().sound.sndSwitch = null;
			soundLoaded = false;
			frameCount = 15;
		}

		if (g.i().sound.sndSwitch != null) {
			frameCount--;
			if (frameCount == 0) {
				soundLoaded = true;
			}
		}

	}

	@Override
	public void draw() {
		if (mode == 'R') {
			tiledMapWrapper.renderBackground();
			tiledMapWrapper.renderPlayerlayer();
			stage.draw();
			tiledMapWrapper.renderForeground();
		} else {
			tiledMapWrapper.renderMap();
			stage.draw();
		}
		// hudStage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		tiledMapWrapper.dispose();
	}

	/**
	 * Damian: Camera doesnt follow the user if the user rolls backwards
	 * 
	 * @param x
	 * @param y
	 */
	private void cameraTranslate(float x, float y) {
		if (g.i().hero.getX() >= camera.viewportWidth / 2
				&& g.i().hero.getX() + camera.viewportWidth / 2 <= tiledMapWrapper
						.getPixelWidth())
			camera.translate(x, 0);
		if (g.i().hero.getY() >= camera.viewportHeight / 2
				&& g.i().hero.getY() + camera.viewportHeight / 2 <= tiledMapWrapper
						.getPixelHeight())
			camera.translate(0, y);
	}

	private void setBackGroundCameraView() {
		backGroundCamera.position.x = tiledMapWrapper.backgroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ backGroundCamera.viewportWidth / 2;
		backGroundCamera.position.y = tiledMapWrapper.backgroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ backGroundCamera.viewportHeight / 2;
	}

	private void setForegroundCameraView() {
		foregroudCamera.position.x = tiledMapWrapper.foregroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ foregroudCamera.viewportWidth / 2;
		foregroudCamera.position.y = tiledMapWrapper.foregroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ foregroudCamera.viewportHeight / 2;
	}

	private void setUpCamera()

	{

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		if (mode == 'R') {
			camera = new OrthographicCamera();
			camera.setToOrtho(false, w * tiledMapWrapper.getPixelHeight() / h,
					tiledMapWrapper.getPixelHeight());
			camera.update();

			backGroundCamera = new OrthographicCamera();
			backGroundCamera.setToOrtho(false,
					w * tiledMapWrapper.getPixelHeight() / h,
					tiledMapWrapper.getPixelHeight());
			backGroundCamera.update();

			foregroudCamera = new OrthographicCamera();
			foregroudCamera.setToOrtho(false,
					w * tiledMapWrapper.getPixelHeight() / h,
					tiledMapWrapper.getPixelHeight());
			foregroudCamera.update();
		} else {
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 320, 320 * h / w);
			camera.update();
		}
	}

	private void setView() {

		if (mode == 'R') {
			tiledMapWrapper.setPlayerLayerView(camera.combined,
					camera.position.x - camera.viewportWidth - 1, -1,
					camera.viewportWidth * 2 + 2, camera.viewportHeight + 2);
			// render the background base one the position of background camera
			setBackGroundCameraView();
			tiledMapWrapper.setBackGroundView(backGroundCamera.combined,
					backGroundCamera.position.x
							- backGroundCamera.viewportWidth - 1, -1,
					backGroundCamera.viewportWidth * 2 + 2,
					backGroundCamera.viewportHeight + 2);
			setForegroundCameraView();
			tiledMapWrapper.setForegroundView(foregroudCamera.combined,
					foregroudCamera.position.x - foregroudCamera.viewportWidth
							- 1, -1, foregroudCamera.viewportWidth * 2 + 2,
					foregroudCamera.viewportHeight + 2);
		} else {
			tiledMapWrapper.setPlayerLayerView(camera.combined,
					camera.position.x - camera.viewportWidth - 5,
					camera.position.y - camera.viewportHeight - 5,
					camera.viewportWidth * 2 + 10,
					camera.viewportHeight * 2 + 10);
		}
	}

	private void loadNPs() {
		NP temp;
		Stack<NP> tempLightOnOff = new Stack<NP>();
		int startX = 0;
		int startY = 0;
		int width = 0;
		int height = 0;
		String name = "";
		String type = "";
		String conditions = "";
		String action = "";
		String anims = "";
		int roamingRadius = 0;
		String spriteSheet = "";
		String collisionParameter = "";
		int animRows = 0;
		int animCols = 0;
		boolean collidable = false;
		int count = 0;
		MapProperties tempProperties;

		g.i().mapObjects.clear();
		g.i().npMap.clear();

		for (MapObject object : tiledMapWrapper.npObjects) {

			name = object.getName();

			tempProperties = object.getProperties();

			if (tempProperties.get("x") != null) {
				startX = MathUtils.round((float) tempProperties.get("x"));
			}

			if (tempProperties.get("y") != null) {
				startY = MathUtils.round((float) tempProperties.get("y"));
			}

			if (object instanceof RectangleMapObject) {
				width = (int) ((RectangleMapObject) object).getRectangle().width;
				height = (int) ((RectangleMapObject) object).getRectangle().height;
			} else {
				width = 32;
				height = 32;
			}

			if (tempProperties.get("type") != null) {
				type = (String) tempProperties.get("type");
			}

			if (tempProperties.get("conditions") != null) {
				conditions = (String) tempProperties.get("conditions");
			}

			if (tempProperties.get("actions") != null) {
				action = (String) tempProperties.get("actions");
			}

			if (tempProperties.get("animations") != null) {
				anims = (String) tempProperties.get("animations");
			}

			if (tempProperties.get("roamingRadius") != null) {
				roamingRadius = Integer.parseInt((String) tempProperties
						.get("roamingRadius"));
			}

			if (tempProperties.get("spriteSheet") != null) {
				spriteSheet = (String) tempProperties.get("spriteSheet");
			}

			if (tempProperties.get("collidable") != null) {
				collidable = Boolean.parseBoolean((String) tempProperties
						.get("collidable"));
			}

			if (tempProperties.get("collisionParams") != null) {
				collisionParameter = (String) tempProperties
						.get("collisionParams");
			}

			if (spriteSheet.equalsIgnoreCase("transmorgifier.png")) {
				animRows = 5;
				animCols = 5;
			} else if (spriteSheet.equalsIgnoreCase("GlassBreak.png")) {
				animRows = 5;
				animCols = 5;
			} else if (spriteSheet.equalsIgnoreCase("LightSwitch.png")) {
				animRows = 1;
				animCols = 2;
			} else if (spriteSheet.equalsIgnoreCase("FlameWall.png")) {
				animRows = 12;
				animCols = 4;
			} else if (spriteSheet.equalsIgnoreCase("doorOpen.png")) {
				animRows = 6;
				animCols = 4;
			} else if (spriteSheet.equalsIgnoreCase("TransmorgifierTop.png")) {
				animRows = 5;
				animCols = 5;
			} else if (spriteSheet.equalsIgnoreCase("Fan.png")) {
				animRows = 3;
				animCols = 4;
			} else if (spriteSheet.equalsIgnoreCase("LightOnOff.png")) {
				animRows = 1;
				animCols = 2;
			} else if (spriteSheet.equalsIgnoreCase("Campfire.png")) {
				animRows = 4;
				animCols = 3;
			}

			if (!spriteSheet.equalsIgnoreCase(""))
				spriteSheet = "img/spritesheet/" + spriteSheet;

			temp = new NP(startX, startY, width, height, name, type,
					conditions, action, anims, roamingRadius, spriteSheet,
					animRows, animCols, collidable, collisionParameter, camera);

			g.i().mapObjects.add(temp);

			if (spriteSheet
					.equalsIgnoreCase("img/spritesheet/TransmorgifierTop.png")
					|| spriteSheet
							.equalsIgnoreCase("img/spritesheet/transmorgifier.png"))
				fgg.addActor(g.i().mapObjects.get(count));
			else if (spriteSheet
					.equalsIgnoreCase("img/spritesheet/LightOnOff.png"))
				toppest.addActor(g.i().mapObjects.get(count));
			else
				bgg.addActor(g.i().mapObjects.get(count));
			count++;

			MapObject tempMapObject;
			if (!temp.collisionParameter.equalsIgnoreCase("")) {
				String[] tempCP = temp.collisionParameter.split(",");
				if (tempCP.length == 4) {
					tempMapObject = new RectangleMapObject(temp.getX()
							+ Float.parseFloat(tempCP[0]), temp.getY()
							+ Float.parseFloat(tempCP[1]),
							Float.parseFloat(tempCP[2]),
							Float.parseFloat(tempCP[3]));
					tiledMapWrapper.collisionObjects.add(tempMapObject);
				} else
					tempMapObject = new RectangleMapObject(temp.getX(),
							temp.getY(), temp.getWidth(), temp.getHeight());
			} else
				tempMapObject = new RectangleMapObject(temp.getX(),
						temp.getY(), temp.getWidth(), temp.getHeight());

			tiledMapWrapper.collisionObjects.add(tempMapObject);
			g.i().npMap.put(tempMapObject, temp);

			startX = 0;
			startY = 0;
			width = 0;
			height = 0;
			name = "";
			type = "";
			conditions = "";
			action = "";
			anims = "";
			roamingRadius = 0;
			spriteSheet = "";
			collisionParameter = "";
			animRows = 0;
			animCols = 0;
			collidable = false;
		}

	}
}
