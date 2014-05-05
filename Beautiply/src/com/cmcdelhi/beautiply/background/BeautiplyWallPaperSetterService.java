package com.cmcdelhi.beautiply.background;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.cmcdelhi.beautiply.FirstActivity;
import com.cmcdelhi.beautiply.R;
import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

public class BeautiplyWallPaperSetterService extends Service {
	Timer timer;
	TimerTask task;
	BeautiplyUtility bu;
	long timeInterval = 3 * 60 * 60 * 1000;
	String TAG = "CMC DELHI BEAUTIPLY";

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		bu = new BeautiplyUtility(getApplicationContext());
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_WALLPAPER", "large", "NOTHING");
			}
		};

		try {
			User u = bu.loadUser();
			timeInterval = u.getTimeIntervalToChangeWallpaper();
			u.setWallpaperSetterServiceRunning(true);
			bu.saveUser(u);

			if (timeInterval != -1) {
				timer.scheduleAtFixedRate(task, 1000, timeInterval);
			}

			

			// BeautiplyToastGenerator btgy = new BeautiplyToastGenerator(
			// getBaseContext());
			// btgy.getBeautiplyShakeToast(
			// "Beautiply has started and will change the wallaper at every "
			// + scheduledTime).show();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy() {

		try {
			User u = bu.loadUser();
			timeInterval = u.getTimeIntervalToChangeWallpaper();
			u.setWallpaperSetterServiceRunning(false);
			bu.saveUser(u);

			// Toast.makeText(getBaseContext(),
			// "BeautifyWallPaperSetterService Service Stopped",
			// Toast.LENGTH_SHORT).show();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
