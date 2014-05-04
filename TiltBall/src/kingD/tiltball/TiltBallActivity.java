package kingD.tiltball;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;

public class TiltBallActivity extends Activity
{
	BallView mBallView = null;
	Handler RedrawHandler = new Handler(); // so redraw occurs in main thread
	Timer mTmr = null;
	TimerTask mTsk = null;
	int mScrWidth, mScrHeight, mRadius, xPadding, yPadding;
	android.graphics.PointF mBallPos, mBallSpd;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE); //hide title bar
		// set app to full screen and keep screen on
		getWindow().setFlags(0xFFFFFFFF, 
				LayoutParams.FLAG_FULLSCREEN|LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//create pointer to main screen
		final FrameLayout mainView = (android.widget.FrameLayout) findViewById(R.id.main_view);
	
		// get screen dimensions
		Display display = getWindowManager().getDefaultDisplay();
		mScrWidth = display.getWidth();
		mScrHeight = display.getHeight();
		mBallPos = new android.graphics.PointF();
		mBallSpd = new android.graphics.PointF();
		
		//create variables for ball position and speed
		mBallPos.x = mScrWidth/2;
		mBallPos.y = mScrHeight/2;
		mRadius = 30;
		xPadding = 5;
		yPadding = 5;
		mBallSpd.x = 0;
		mBallSpd.y = 0;
		
		// create initial ball
		mBallView = new BallView(this, mBallPos.x, mBallPos.y, mRadius);
		
		mainView.addView(mBallView);
		mBallView.invalidate();
		
		//listener for accelerometer, use anonymous class for simplicity
		((SensorManager)getSystemService(Context.SENSOR_SERVICE)).registerListener(
				new SensorEventListener () {
					@Override
					public void onSensorChanged(SensorEvent event) {
						// set ball speed based on phone tilt (ignore Z axis)
						mBallSpd.x = -event.values[0];
						mBallSpd.y = event.values[1];
						// timer event will redraw ball
					}
					@Override
					public void onAccuracyChanged(Sensor sensor, int accuracy) {} // ignore
				},
				((SensorManager)getSystemService(Context.SENSOR_SERVICE))
				.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
				SensorManager.SENSOR_DELAY_NORMAL);
				
	} //onCreate
	
	//listener for menu button on phone
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add("Exit"); //only one menu item
		return super.onCreateOptionsMenu(menu);
	}
	
	//listener for menu item clicked
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//Handle item selection
		if(item.getTitle() == "Exit") //user clicked exit
			finish(); //will call onPause
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onPause() //app moved to background, stop background threads
	{
		mTmr.cancel(); //kill\release timer (our only background thread)
		mTmr = null;
		mTsk = null;
		super.onPause();
	}
	
	@Override
	public void onResume() // app moved to foreground (also occurs at app startup)
	{
		// create timer to move ball to new position
		mTmr = new Timer();
		mTsk = new TimerTask() {
			public void run() {
				// move ball based on current speed
				mBallPos.x += mBallSpd.x;
				mBallPos.y += mBallSpd.y;
				
				//if ball goes to the edge of the screen it stops
				if (mBallPos.x > mScrWidth - mRadius) mBallPos.x = mScrWidth - mRadius;
				if (mBallPos.y > mScrHeight - mRadius) mBallPos.y = mScrHeight - mRadius;
				if (mBallPos.x < 0 + mRadius) mBallPos.x = 0 + mRadius;
				if (mBallPos.y < 0 + mRadius) mBallPos.y = 0 + mRadius;
				
				//update ball class instance
				mBallView.x = mBallPos.x;
				mBallView.y = mBallPos.y;
				//redraw ball. Must run in background thread to prevent thread lock.
				RedrawHandler.post(new Runnable() {
					public void run() {
						mBallView.invalidate();
					}});
			}			
		}; //TimerTask
		mTmr.schedule(mTsk, 10, 10); //start timer
		super.onResume();
	}//onResume
	
	@Override
	public void onDestroy() //main thread stopped
	{
		super.onDestroy();
		//wait for thread to exit before clearing app
		System.runFinalizersOnExit(true);
		//remove app from memory
		android.os.Process.killProcess(android.os.Process.myPid());
	}
	
	//listener for config change
	//This is called when user tilts phone enough to trigger landscape view
	//we want our app to stay in portrait view, so bypass event
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
	}
	
}//TitlBallActivity
