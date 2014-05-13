package com.touchspin.td;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;

public class InputAnonymizer implements GestureListener, InputProcessor {

//	private boolean click;
//	private Vector2 velocity;
//	private Vector2 direction;
//	private boolean pausePressed;
//	private boolean attack;
//	private boolean crouch;
//	private boolean jump;
//	private boolean dash;
	
	boolean click;
	Vector2 velocity;
	Vector2 direction;
	boolean pausePressed;
	boolean attack;
	boolean crouch;
	boolean jump;
	boolean dash;


	public InputAnonymizer() {
		click = false;
		velocity = new Vector2(0,0);
		direction = new Vector2(0,0);
		pausePressed = false;
		attack = false;
		crouch = false;
		jump = false;
		dash = false;
	}

//	public boolean isClick() {
//		return click;
//	}
//
//	public Vector2 getVelocity() {
//		return velocity;
//	}
//
//	public Vector2 getDirection() {
//		return direction;
//	}
//
//	public boolean pauseIsPressed() {
//		return pausePressed;
//	}
//
//	public boolean isAttck() {
//		return attack;
//	}
//
//	public boolean isCrouch() {
//		return crouch;
//	}
//
//	public boolean isJump() {
//		return jump;
//	}
//
//	public boolean isDash() {
//		return dash;
//	}

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
		// TODO Auto-generated method stub
		return false;
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
