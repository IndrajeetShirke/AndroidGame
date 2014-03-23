package com.example.game;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SensorListener implements SensorEventListener {

	static SensorListener instance;
	MainScene scene;

	public static SensorListener getSharedInstance() {
		if (instance == null)
			instance = new SensorListener();
		return instance;
	}

	private SensorListener() {
		instance = this;
		scene = (MainScene) MainActivity.getSharedInstance().mCurrentScene;
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized (this) {
			switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				// Log.v("Jimvaders",
				// "SensorListener onSensorChanged() accelerometerSpeedX = "+event.values[1]);
				scene.accelerometerSpeedY = event.values[0];
				break;
			default:
				break;
			}
		}

	}

}
