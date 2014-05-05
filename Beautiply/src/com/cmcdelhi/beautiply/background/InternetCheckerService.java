package com.cmcdelhi.beautiply.background;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RelativeLayout;

import com.cmcdelhi.beautiply.FirstActivity;
import com.cmcdelhi.beautiply.ForthActivity;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;

public class InternetCheckerService extends Service {
	BeautiplyUtility bu;
	Timer timer;
	TimerTask task;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		bu = new BeautiplyUtility(getBaseContext());
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run() {
				if (bu.isNetworkAvailable() == true) {

					if (FirstActivity.internetheader != null) {
						FirstActivity.internetheader
								.setVisibility(RelativeLayout.GONE);
					}
					if (ForthActivity.internetheader != null) {
						ForthActivity.internetheader
								.setVisibility(RelativeLayout.GONE);
					}

				} else {
					if (FirstActivity.internetheader != null) {
						FirstActivity.internetheader
								.setVisibility(RelativeLayout.VISIBLE);
					}
					if (ForthActivity.internetheader != null) {
						ForthActivity.internetheader
								.setVisibility(RelativeLayout.VISIBLE);
					}

				}

			}
		};

		timer.scheduleAtFixedRate(task, 100, 5000);

		return START_STICKY;
	}

}
