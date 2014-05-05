package com.cmcdelhi.beautiply.background;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

public class BeautiplyStarredWallpaperSetterService extends
		BeautiplyWallPaperSetterService {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {
			bu = new BeautiplyUtility(getApplicationContext());

			User u = bu.loadUser();

			List<String> loadedStarredUrlList = u.getUserStarredURL();

			if (!loadedStarredUrlList.isEmpty()) {

				timer = new Timer();
				task = new TimerTask() {

					@Override
					public void run() {
						User tempUser;
						try {
							tempUser = bu.loadUser();

							List<String> loadedStarredUrlList = tempUser
									.getUserStarredURL();

							String li = ""
									+ loadedStarredUrlList.get(new Random()
											.nextInt(loadedStarredUrlList
													.size() - 1));

							for (String url : loadedStarredUrlList) {
								Log.d(TAG, url);
							}

							Log.d(TAG, "HOT URL " + li);

							new BeautiplyAsyncTask(getBaseContext()).execute(
									"SET_WALLPAPER", "large", li);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				};

				u = bu.loadUser();
				timeInterval = u.getTimeIntervalToChangeWallpaper();
				u.setWallpaperSetterServiceRunning(true);
				bu.saveUser(u);

				if (timeInterval != -1) {
					timer.scheduleAtFixedRate(task, 1000, timeInterval);
				}

				long obtainedTimeInterval = u
						.getTimeIntervalToChangeWallpaper();

				String scheduledTime = " 3 Hours ";

				if (obtainedTimeInterval == 15 * 60 * 1000) {
					scheduledTime = "15 Minutes";
				} else if (obtainedTimeInterval == 30 * 60 * 1000) {
					scheduledTime = "30 Minutes";

				} else if (obtainedTimeInterval == 1 * 60 * 60 * 1000) {
					scheduledTime = "1 Hour";

				} else if (obtainedTimeInterval == 2 * 60 * 60 * 1000) {
					scheduledTime = "2 Hour";

				} else if (obtainedTimeInterval == 3 * 60 * 60 * 1000) {
					scheduledTime = "3 hour";

				} else if (obtainedTimeInterval == 6 * 60 * 60 * 1000) {
					scheduledTime = "6 Hour";

				} else if (obtainedTimeInterval == 12 * 60 * 60 * 1000) {
					scheduledTime = "12 Hour";

				} else if (obtainedTimeInterval == 24 * 60 * 60 * 1000) {
					scheduledTime = "Every Day";
				} else if (obtainedTimeInterval == 7 * 24 * 60 * 60 * 1000) {
					scheduledTime = "Every Week";

				} else if (obtainedTimeInterval == -1) {
					scheduledTime = "On unlock";

				}

				// btgy.getBeautiplyShakeToast(
				// "Only starred wallapaper will be set at every "
				// + scheduledTime).show();
			} else {

				// btgy.getBeautiplyShakeToast(
				// "Oops you haven't starred any image.Please choose your favourite image to proceed.")
				// .show();

			}

			Log.d(TAG, "Starred Wallpaper Setter Service Started ");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		// btgy = new BeautiplyToastGenerator(getBaseContext());
		// btgy.getBeautiplyShakeToast(" Starred Wallpaper Setter Service Stopped")
		// .show();
		Log.d(TAG, "Starred Wallpeper Setter Service Stopped ");
		super.onDestroy();
	}
}
