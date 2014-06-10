package com.touchspin.td;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MoverAI extends MoverPhysics {

	private NP np;
	private RectangleMapObject collisionObject;
	private final float movingAcceleration;
	private final float movingDeceleration;
	private Vector2 path;
	private boolean active = false;

	public MoverAI(NP np) {
		movingAcceleration = 10;
		movingDeceleration = 25;
		path = new Vector2(0, 0);
		this.np = np;
		String[] tempCP = np.collisionParameter.split(",");
		if (tempCP.length == 4) {
			collisionObject = new RectangleMapObject(np.getX()
					+ Float.parseFloat(tempCP[0]), np.getY()
					+ Float.parseFloat(tempCP[1]), Float.parseFloat(tempCP[2]),
					Float.parseFloat(tempCP[3]));
			g.i().hud.tiledMapWrapper.collisionObjects.add(collisionObject);
			g.i().npMap.put(collisionObject, np);
		}
	}

	@Override
	public void move(GameThing gameThing) {

		if (!active) {
			path.x = g.i().hero.getX() + g.i().hero.getWidth() / 2 - np.getX()
					- np.getWidth() / 2;
			path.y = g.i().hero.getY() + g.i().hero.getHeight() / 2 - np.getY()
					- np.getHeight() / 2;
			if (path.len() < 320)
				active = true;
		} else {
			// Save the previous position
			previousX = this.np.getX();
			previousY = this.np.getY();
			// Try to move
			AIMove();

			this.np.setX(np.getX() + Gdx.graphics.getDeltaTime()
					* speedXPerSecond);
			this.np.setY(np.getY() + Gdx.graphics.getDeltaTime()
					* speedYPerSecond);
			collisionObject.getRectangle().setCenter(
					np.getX() + np.getWidth() / 2,
					np.getY() + np.getHeight() / 2);
		}

	}

	public void AIMove() {
		path.x = g.i().hero.getX() + g.i().hero.getWidth() / 2 - np.getX()
				- np.getWidth() / 2;
		path.y = g.i().hero.getY() + g.i().hero.getHeight() / 2 - np.getY()
				- np.getHeight() / 2;

		if (path.x * accelerationX < 0) {
			accelerationX = movingDeceleration
					* MathUtils.cos(path.getAngleRad());
		} else {
			accelerationX = movingAcceleration
					* MathUtils.cos(path.getAngleRad());
		}
		if (path.y * accelerationY < 0) {
			accelerationY = movingDeceleration
					* MathUtils.sin(path.getAngleRad());
		} else {
			accelerationY = movingAcceleration
					* MathUtils.sin(path.getAngleRad());
		}

		fractionFactor = 0.003f;
		speedXPerSecond *= (1 - fractionFactor);
		speedYPerSecond *= (1 - fractionFactor);

		speedXPerSecond += accelerationX;
		speedYPerSecond += accelerationY;
	}
}
