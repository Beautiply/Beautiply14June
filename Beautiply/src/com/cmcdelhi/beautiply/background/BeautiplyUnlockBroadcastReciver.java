package com.cmcdelhi.beautiply.background;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

public class BeautiplyUnlockBroadcastReciver extends BroadcastReceiver {

	BeautiplyUtility bu;
	User u;

	@Override
	public void onReceive(Context context, Intent intent) {
		bu = new BeautiplyUtility(context);
		try {

			// load user
			u = bu.loadUser();
			// get if shake ON
			// then start the service

			if (u.isBeautiplyShakeOn()) {
				context.startService(new Intent(context,
						BeautiplyShakeService.class));
			}

			// /////////>>>>>>If On Unlock Prefrence is Set Then Setting
			// Wallpaper<<<<<<<<///////////

			if (u.getTimeIntervalToChangeWallpaper() == -1) {
				new BeautiplyAsyncTask(context).execute("SET_WALLPAPER",
						"large", "NOTHING");
			}
			// /////////>>>>>>Resetting Up The wallpaper<<<<<<<<///////////
			// only if beautiply is running or shake is running
			if (u.isWallpaperSetterServiceRunning() || u.isBeautiplyShakeOn()) {

				WindowManager window = (WindowManager) context
						.getSystemService(Context.WINDOW_SERVICE);
				Display display = window.getDefaultDisplay();
				int height = display.getHeight();
				int width = display.getWidth();

				if (width >= 480 || height >= 800) {

					boolean result = bu.setBeautiplyImageOnHomeScreen();
					Log.i("Cmc Delhi Beautiply", "Image Sert On Home Scree "
							+ result);
				}

			}

			Log.i("Cmc Delhi Beautiply", "Image Loaded  " + intent.getAction());
		} catch (FileNotFoundException e) {
			Log.i("Cmc Delhi Beautiply", "FNF Exception");
			e.printStackTrace();
		} catch (IOException e) {
			Log.i("Cmc Delhi Beautiply", "IO Exception");
			e.printStackTrace();
		} catch (Exception e) {
			Log.i("Cmc Delhi Beautiply", "Exception " + e.getMessage());
			e.printStackTrace();
		}

	}

}
