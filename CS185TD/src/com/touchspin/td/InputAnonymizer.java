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

public class InputAnonymizer extends Actor implements  GestureListener, InputProcessor {

	boolean click;
	//Vector2 velocity;
	
	/*
	 * I was thinking that the input anonymizer should only need to
	 * read in the tilt of the device and should not care about
	 * things like player position or direction, because player position is
	 * affected by the map and its collisions. 
	 */
	Vector2 tiltSpeed;
	//Vector2 playerPosition;
	//Vector2 direction;
	boolean pausePressed;
	boolean attack;
	boolean crouch;
	boolean jump;
	boolean dash;

	public InputAnonymizer() {
		click = false;
		//velocity = new Vector2(0, 0);
		
		tiltSpeed = new Vector2(0,0);
		//playerPosition = new Vector2(0,0);
		
		//direction = new Vector2(0, 0);
		pausePressed = false;
		attack = false;
		crouch = false;
		jump = false;
		dash = false;
		
		switch (Gdx.app.getType())
		{
			case Android:
				//only listen to touch screen events
				Gdx.input.setInputProcessor(new GestureDetector(this));
			case Desktop:
				//only listen to keyboard events
				Gdx.input.setInputProcessor(this);
		default:
			break;
		}
	}

	public void readAccel()
	{
		if( Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer))
		{
			tiltSpeed.x = Gdx.input.getAccelerometerX();						
			tiltSpeed.y = Gdx.input.getAccelerometerY();
		}
	}
	@Override
	public void act(float delta) {
		
		readAccel();
			/*direction.x = Gdx.input.getAccelerometerX();
			direction.y = Gdx.input.getAccelerometerY();
			
			playerSpeed.y = Gdx.input.getAccelerometerY();
			playerSpeed.x = Gdx.input.getAccelerometerX();
			
			playerPosition.x += playerSpeed.x;
			playerPosition.y += playerSpeed.y;
			
			velocity.x += Gdx.input.getAccelerometerX();
			velocity.y += Gdx.input.getAccelerometerY();*/	
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.Z)
			attack = true;
		else if (keycode == Input.Keys.SPACE)
			jump = true;

		return true;
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
}
