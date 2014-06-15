package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends GameObject {

	Stage stage;
	MainGame game;
	private OrthographicCamera backGroundCamera;
	private OrthographicCamera foregroundCamera;
	private boolean soundLoaded = false;
	private int frameCount = 15;
	private Group bgg = new Group();
	private Group fgg = new Group();
	private Group playerg = new Group();
	private Group toppest = new Group();
	private char mode;
	private float initialBGCameraDifferenceY = 0;

	public GameScreen(MainGame game, String mapPath) {

		this.game = game;
		if (g.i().gameMode == 'R')
			mode = 'R';
		else
			mode = 'M';
		tiledMapWrapper = new TiledMapWrapper(mapPath);
		g.i().leAnonymizer.resetAll();
		setUpCamera();
		stage = new Stage();
		g.i().hud = new HUD(camera, tiledMapWrapper);
		loadNPs();
		g.i().hero = new Hero(camera, tiledMapWrapper);
		stage.addActor(bgg);
		stage.addActor(playerg);
		stage.addActor(g.i().hero);
		stage.addActor(fgg);
		stage.addActor(toppest);
		stage.addActor(g.i().hud);
		setCamera(g.i().hero.getX(), g.i().hero.getY());
		adjustInitialCamera();
	}

	@Override
	public void render(float delta) {
		if (g.i().leAnonymizer.pausePressed) {
			pause();
		} else {
			update();
			draw();
		}
	}

	@Override
	public void update() {
		stage.act();
		camera.update();
		if (mode == 'R') {
			backGroundCamera.update();
			foregroundCamera.update();
		}

		setCamera(g.i().hero.getX(), g.i().hero.getY());
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
		Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 0 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );	
		if (mode == 'R') {
			tiledMapWrapper.renderbackground();
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
		if (!g.i().leAnonymizer.pausePressed) {
			dispose();
		}
	}

	@Override
	public void pause() {
		game.setScreen(new ScreenOptions(this));
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		tiledMapWrapper.dispose();
	}

	private void setCamera(float x, float y) {
		if (g.i().hero.getX() >= camera.viewportWidth / 2
				&& g.i().hero.getX() + camera.viewportWidth / 2 <= tiledMapWrapper
						.getPixelWidth())
			camera.position.x = x;
		if (g.i().hero.getY() >= camera.viewportHeight / 2
				&& g.i().hero.getY() + camera.viewportHeight / 2 <= tiledMapWrapper
						.getPixelHeight())
			camera.position.y = y;
	}

	private void setBackGroundCameraView() {
		backGroundCamera.position.x = tiledMapWrapper.backgroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ backGroundCamera.viewportWidth / 2;
		backGroundCamera.position.y = initialBGCameraDifferenceY
				+ tiledMapWrapper.backgroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ backGroundCamera.viewportHeight / 2;
	}

	private void setForegroundCameraView() {
		foregroundCamera.position.x = tiledMapWrapper.foregroundfactor
				* (camera.position.x - camera.viewportWidth / 2)
				+ foregroundCamera.viewportWidth / 2;
		foregroundCamera.position.y = tiledMapWrapper.foregroundfactor
				* (camera.position.y - camera.viewportHeight / 2)
				+ foregroundCamera.viewportHeight / 2;
	}

	private void setUpCamera()

	{

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		if (mode == 'R') {
			if (tiledMapWrapper.getPixelWidth() > tiledMapWrapper
					.getPixelHeight()) {
				camera = new OrthographicCamera();
				camera.setToOrtho(false, 640, h * 640 / w);
				camera.update();

				backGroundCamera = new OrthographicCamera();
				backGroundCamera.setToOrtho(false, 640, h * 640 / w);
				backGroundCamera.update();

				foregroundCamera = new OrthographicCamera();
				foregroundCamera.setToOrtho(false, 640, h * 640 / w);
				foregroundCamera.update();
			} else {
				camera = new OrthographicCamera();
				camera.setToOrtho(false, 640, h * 640 / w);
				camera.update();

				backGroundCamera = new OrthographicCamera();
				backGroundCamera.setToOrtho(false, 640, h * 640 / w);
				backGroundCamera.update();

				foregroundCamera = new OrthographicCamera();
				foregroundCamera.setToOrtho(false, 640, h * 640 / w);
				foregroundCamera.update();
			}
		} else {
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 400, 400 * h / w);
			camera.update();
		}
	}

	private void setView() {

		if (mode == 'R') {
			tiledMapWrapper.setPlayerLayerView(camera.combined,
					camera.position.x - camera.viewportWidth - 5,
					camera.position.y - camera.viewportHeight - 5,
					camera.viewportWidth * 2 + 10,
					camera.viewportHeight * 2 + 10);
			// render the background base one the position of background camera
			setBackGroundCameraView();
			tiledMapWrapper.setBackGroundView(backGroundCamera.combined,
					backGroundCamera.position.x
							- backGroundCamera.viewportWidth - 5,
					backGroundCamera.position.y
							- backGroundCamera.viewportHeight - 5,
					backGroundCamera.viewportWidth * 2 + 10,
					backGroundCamera.viewportHeight * 2 + 10);
			setForegroundCameraView();
			tiledMapWrapper.setForegroundView(foregroundCamera.combined,
					foregroundCamera.position.x - foregroundCamera.viewportWidth
							- 5, foregroundCamera.position.y
							- foregroundCamera.viewportHeight - 5,
					foregroundCamera.viewportWidth * 2 + 10,
					foregroundCamera.viewportHeight * 2 + 10);
		} else {
			tiledMapWrapper.setPlayerLayerView(camera.combined,
					camera.position.x - camera.viewportWidth - 5,
					camera.position.y - camera.viewportHeight - 5,
					camera.viewportWidth * 2 + 10,
					camera.viewportHeight * 2 + 10);
		}
	}

	private void adjustInitialCamera() {
		initialBGCameraDifferenceY = (1 - tiledMapWrapper.backgroundfactor)
				* (camera.position.y - camera.viewportHeight / 2);
	}

	private void loadNPs() {
		NP temp;
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
		String active = "true";
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

			if(name.startsWith("Ball"))
			{
				type = name.substring(4);
				Balls tempBall = new Balls(type,startX,startY,camera,tiledMapWrapper);
				g.i().balls.add(tempBall);
				playerg.addActor(tempBall);
			}
			if (object instanceof RectangleMapObject) {
				width = (int) ((RectangleMapObject) object).getRectangle().width;
				height = (int) ((RectangleMapObject) object).getRectangle().height;
			} else if (object instanceof EllipseMapObject) {
				width = (int)((EllipseMapObject) object).getEllipse().width;
				height = (int)((EllipseMapObject) object).getEllipse().height;
			}
			else
			{
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

			if (tempProperties.get("active") != null) {
				active = (String) tempProperties.get("active");
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
			} else if (spriteSheet.equalsIgnoreCase("CornerLightOnOff.png")) {
				animRows = 2;
				animCols = 3;
			} else if (spriteSheet.equalsIgnoreCase("Campfire.png")) {
				animRows = 4;
				animCols = 3;
			} else if (spriteSheet.equalsIgnoreCase("Water.png")) {
				animRows = 5;
				animCols = 5;
			} else if (spriteSheet.equalsIgnoreCase("ExitMarker.png")) {
				animRows = 1;
				animCols = 1;
			} else if (spriteSheet.equalsIgnoreCase("Teleporter.png")) {
				animRows = 6;
				animCols = 4;
			} else if (spriteSheet.equalsIgnoreCase("AirPuff.png")) {
				animRows = 6;
				animCols = 9;
			} else if (spriteSheet.equalsIgnoreCase("JustWizard.png")) {
				animRows = 6;
				animCols = 8;
			} else if (spriteSheet.equalsIgnoreCase("Dragon.png")) {
				animRows = 10;
				animCols = 5;
			}
			else if (spriteSheet.equalsIgnoreCase("Fireball.png")) {
				animRows = 4;
				animCols = 12;
			}
			else if (spriteSheet.equalsIgnoreCase("HeartYummy.png")) {
				animRows = 5;
				animCols = 5;
			}
			else {
				animRows = 1;
				animCols = 1;
			}

			if (!spriteSheet.equalsIgnoreCase(""))
				spriteSheet = "img/spritesheet/" + spriteSheet;

			temp = new NP(startX, startY, width, height, name, type,
					conditions, action, anims, roamingRadius, spriteSheet,
					animRows, animCols, collidable, collisionParameter, camera,
					active);

			g.i().mapObjects.add(temp);

			if (spriteSheet
					.equalsIgnoreCase("img/spritesheet/TransmorgifierTop.png")
					|| spriteSheet
							.equalsIgnoreCase("img/spritesheet/transmorgifier.png")
					|| spriteSheet
							.equalsIgnoreCase("img/spritesheet/Water.png"))
				fgg.addActor(g.i().mapObjects.get(count));
			else if (spriteSheet
					.equalsIgnoreCase("img/spritesheet/CornerLightOnOff.png"))
				toppest.addActor(g.i().mapObjects.get(count));
			else
				bgg.addActor(g.i().mapObjects.get(count));
			count++;

				MapObject tempMapObject ;
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
				}
				else
				tempMapObject = new RectangleMapObject(temp.getX(),
						temp.getY(), temp.getWidth(), temp.getHeight());

			if (!temp.name.equalsIgnoreCase("dragon")) {
				tiledMapWrapper.collisionObjects.add(tempMapObject);
				g.i().npMap.put(tempMapObject, temp);
			}

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
			active = "true";
		}

	}
}
