package com.cmcdelhi.beautiply.background;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;

import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautiplyHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;

public class BeautiplyShakeService extends Service implements
		SensorEventListener {
	SensorManager sensorManager;
	Sensor accelerometer;
	BeautiplyUtility bul;
	Timer timer;
	TimerTask task;
	BroadcastReceiver screenOffStateReciever;
	String TAG = "CMC DELHI BEAUTIPLY";
	static boolean isShakeLocked = false;

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		if (x > 18 || x < -18 || z > 18 || z < -18 || y > 18 || y < -18) {

			bul = new BeautiplyUtility(getApplicationContext());

			// checking whether at least one beautiply image exist or not
			if (bul.checkBeautiplyImagesExixtence()) {

				// Bitmap randomWallPaperFromCache = bul
				// .getRandomBeautiplyWallpaperFromCache();

				BeautiplyHolder randomHolder = bul
						.getRandomBeautiplyHolderFromCache();

				if (randomHolder != null) {

					if (bul.isNetworkAvailable() == true) {

						if (isShakeLocked == false) {
							// ////downloading fresh wallpaper for next time
							new BeautiplyAsyncTask(getBaseContext()).execute(
									"SET_WALLPAPER", "large", "NOTHING");
							isShakeLocked = true;

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(),
									"Setting Up New Wallpaper").show();

						} else {
							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(),
									"Please Wait.Downloading In Progress!")
									.show();
						}
					} else {

						bul.setBeautiplyHolderAsWallpaper(randomHolder,
								"NO_URL");
						BeautiplyToastGenerator
								.getBeautiplyShakeToast(getBaseContext(),
										"Internet Not Available .Beautiplying with stored wallpaper ")
								.show();
					}

				} else {

					if (bul.isNetworkAvailable() == true) {

						if (isShakeLocked == false) {
							// ////downloading fresh wallpaper for next time
							new BeautiplyAsyncTask(getBaseContext()).execute(
									"SET_WALLPAPER", "large", "NOTHING");

							isShakeLocked = true;

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(),
									"Setting Up New Wallpaper").show();
						} else {
							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(),
									"Please Wait.Downloading In Progress!")
									.show();
						}

					} else {
						BeautiplyToastGenerator.getBeautiplyShakeToast(
								getBaseContext(), "Internet Not Available.")
								.show();
					}
				}
			} else {

				if (bul.isNetworkAvailable() == true) {

					// ////downloading fresh wallpaper for next time

					if (isShakeLocked == false) {
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_WALLPAPER", "large", "NOTHING");

						isShakeLocked = true;

						BeautiplyToastGenerator.getBeautiplyShakeToast(
								getBaseContext(),
								"Setting Up New Wallpaper For You").show();
					} else {
						BeautiplyToastGenerator.getBeautiplyShakeToast(
								getBaseContext(),
								"Please Wait.Downloading In Progress!").show();
					}

				} else {
					BeautiplyToastGenerator.getBeautiplyShakeToast(
							getBaseContext(), "Internet Not Available.").show();
				}

			}

		}

	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Log.i(TAG, "Beautiply Shake Service Started ");

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		accelerometer = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sensorManager.registerListener(this, accelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
		bul = new BeautiplyUtility(getBaseContext());

		// reciver to recive when the phone screen is off
		screenOffStateReciever = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				Log.i(TAG, "Inside Lock Reciver" + intent.getAction());
				unregisterReceiver(screenOffStateReciever);
				stopSelf();

			}
		};

		final IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(screenOffStateReciever, filter);

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "Beautiply Shake Service Stopped ");
		sensorManager.unregisterListener(this);

		super.onDestroy();
	}

}
