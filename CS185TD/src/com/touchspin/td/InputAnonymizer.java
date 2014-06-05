package com.touchspin.td;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InputAnonymizer extends Actor implements GestureListener,
		InputProcessor {

	boolean click;
	Vector2 tiltSpeed;

	/*
	 * I was thinking that the input anonymizer should only need to read in the
	 * tilt of the device and should not care about things like player position
	 * or direction, because player position is affected by the map and its
	 * collisions.
	 */
	boolean pausePressed;
	boolean attack;
	boolean crouch;
	boolean jump;
	boolean dash;

	public InputAnonymizer() {
		click = false;
		// velocity = new Vector2(0, 0);

		tiltSpeed = new Vector2(0, 0);

		// direction = new Vector2(0, 0);
		pausePressed = false;
		attack = false;
		crouch = false;
		jump = false;
		dash = false;

		setProperInputProcessor();
	}

	public void readAccel() {
		if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)) {
			tiltSpeed.y = -Gdx.input.getAccelerometerX();
			tiltSpeed.x = Gdx.input.getAccelerometerY();
		}
	}

	public void readKey() {
		if (Gdx.input.isKeyPressed(Input.Keys.Z))
			attack = true;
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
			tiltSpeed.x = -5;
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			tiltSpeed.x = 5;
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
			tiltSpeed.y = 5;
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
			tiltSpeed.y = -5;
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
			pausePressed = true;
	}

	@Override
	public void act(float delta) {
		if(g.i().controls == 'A')
			readAccel();
		if(g.i().controls == 'D')
			readKey();
	}

	@Override
	public boolean keyDown(int keycode) {
//		if (keycode == Input.Keys.Z)
//			attack = true;
		if (keycode == Input.Keys.SPACE)
			jump = true;
//		if (keycode == Input.Keys.LEFT)
//			tiltSpeed.x = -2;
//		if (keycode == Input.Keys.RIGHT)
//			tiltSpeed.x = 2;
//		if (keycode == Input.Keys.UP)
//			tiltSpeed.y = -2;
//		if (keycode == Input.Keys.DOWN)
//			tiltSpeed.y = 2;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		click = true;
		if (g.i().controls == 'A' && screenX < 100 && screenY < 100){
			pausePressed=true;
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		click = true;
		attack = true;
		return true;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if (Gdx.app.getType() == ApplicationType.Android) {
			if (velocityY < -50) {
				jump = true;
			}
		}
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void resetAll()
	{
		click = false;
		tiltSpeed = new Vector2(0, 0);
		pausePressed = false;
		attack = false;
		crouch = false;
		jump = false;
		dash = false;
		setProperInputProcessor();
		
	}
	
	private void setProperInputProcessor()
	{
		switch (g.i().controls) {
		case 'A':
			// only listen to touch screen events
			Gdx.input.setInputProcessor(new GestureDetector(this));
			break;
		case 'D':
			// only listen to keyboard events
			Gdx.input.setInputProcessor((InputProcessor)this);
			break;
		default:
			break;
		}
	}
}
