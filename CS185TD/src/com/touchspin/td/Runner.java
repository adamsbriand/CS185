package com.touchspin.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Runner extends GameObject {


	 MyRunner runner;
	 Stage stage;
	 
     float w = Gdx.graphics.getWidth();
     float h = Gdx.graphics.getHeight();
     float blockHeight = h/9;
	 
		public Runner() {

			tiledMapWrapper = new TiledMapWrapper("SideScroller1.tmx");
	        
			camera = new OrthographicCamera();
	        camera.setToOrtho(false,w*tiledMapWrapper.getPixelHeight()/h,
	        		tiledMapWrapper.getPixelHeight());
	        camera.update();
	        stage = new Stage();
	        runner = new MyRunner(camera,tiledMapWrapper);
	        stage.addActor(runner);
	        
	        anonymizer = new RunnerInputAnonymizer(){

//				@Override
//				public boolean Navigate(int keycode) {
//					 if(keycode == Input.Keys.LEFT)
//						 cameraTranslate(-32,0);
//				        if(keycode == Input.Keys.RIGHT)
//				        	cameraTranslate(32,0);
//				        if(keycode == Input.Keys.UP)
//				        	cameraTranslate(0,32);
//				        if(keycode == Input.Keys.DOWN)
//				        	cameraTranslate(0,-32);
//				        if(keycode == Input.Keys.NUM_1)
//				        	tiledMapWrapper.getTiledMap().getLayers().get(0).setVisible(
//				        			!tiledMapWrapper.getTiledMap().getLayers().get(0).isVisible());
//				        if(keycode == Input.Keys.NUM_2)
//				        	tiledMapWrapper.getTiledMap().getLayers().get(1).setVisible(
//				        			!tiledMapWrapper.getTiledMap().getLayers().get(1).isVisible());
//				        return false;
//				}

				@Override
				public boolean attack() {
					runner.attack();
					return true;
				}

				@Override
				public boolean crouch() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean jump() {
					runner.jump(30*10);
					return false;
				}

				@Override
				public boolean dash() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean pause() {
					// TODO Auto-generated method stub
					return false;
				}};
				
		}
	 
	@Override
	public void render() {	
		update();
		draw();
	}

	@Override
	public void update() {
		stage.act();
		camera.update();
		cameraTranslate(runner.getDistancePerFrameX(),0);
		//render the map from 1 pixel before the left of the camera to 1 pixel after
		//the right of the map.
		tiledMapWrapper.getTiledMapRenderer().setView(camera.combined,
				camera.position.x-camera.viewportWidth-1,0,camera.viewportWidth*2+2,camera.viewportHeight);
	}

	@Override
	public void draw() {
		tiledMapWrapper.getTiledMapRenderer().render();
        stage.draw();
	}
	
	private void cameraTranslate(float x, float y)
	{
		if(runner.getX()+runner.getWidth()/2 > camera.position.x 
				&& camera.position.x+x>=camera.viewportWidth/2 
				&& camera.position.x + x + camera.viewportWidth/2 <= tiledMapWrapper.getPixelWidth())
			camera.translate(x,0);
		if(camera.position.y+y>=camera.viewportHeight/2 && camera.position.y + y + camera.viewportHeight/2 <= tiledMapWrapper.getPixelHeight())
			camera.translate(0,y);
	}

	

}
