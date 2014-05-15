package com.touchspin.td;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
	
	android.graphics.PointF mBallSpd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useAccelerometer = true;
        cfg.useCompass = true;
        initialize(new MainGame(), cfg);
        
      //listener for accelerometer, use anonymous class for simplicity
      ((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
      			new SensorEventListener () {
      				
      				@Override
      				public void onSensorChanged(SensorEvent event) 
      				{
      					// set ball speed based on phone tilt (ignore Z axis)
      					g.i().accelX = event.values[1];
      					g.i().accelY = -event.values[0];
      					// timer event will redraw ball
      				}
      				
      				@Override
      				public void onAccuracyChanged(Sensor sensor, int accuracy) {} // ignore
      				},
      				((SensorManager)getSystemService(Context.SENSOR_SERVICE))
      				.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
      				SensorManager.SENSOR_DELAY_NORMAL);
    }
}