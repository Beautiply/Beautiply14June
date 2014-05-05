package com.cmcdelhi.beautiply.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class BeautiplyConnectivityBroadcastReciever extends BroadcastReceiver {

	public void onReceive(final Context context, final Intent intent) {

		if (intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
			NetworkInfo networkInfo = intent
					.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (networkInfo.isConnected()) {
				// Wifi is connected now jaldi se download the wallpaper
				Log.d("BeautiplyConnectivityBroadcastReciever  ",
						"Wifi is connected: " + String.valueOf(networkInfo));
				//
				// ////downloading fresh cached wallpaper for next time
				new BeautiplyAsyncTask(context).execute(
						"DOWNLOAD_CACHE_WALLPAPER", "large", "NOTHING");
				new BeautiplyAsyncTask(context).execute(
						"DOWNLOAD_CACHE_WALLPAPER", "large", "NOTHING");
				new BeautiplyAsyncTask(context).execute(
						"DOWNLOAD_CACHE_WALLPAPER", "large", "NOTHING");
				new BeautiplyAsyncTask(context).execute(
						"DOWNLOAD_CACHE_WALLPAPER", "large", "NOTHING");
				new BeautiplyAsyncTask(context).execute(
						"DOWNLOAD_CACHE_WALLPAPER", "large", "NOTHING");

			}

		} else if (intent.getAction().equals(
				ConnectivityManager.CONNECTIVITY_ACTION)) {

			NetworkInfo networkInfo = intent
					.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
					&& !networkInfo.isConnected()) {
				// Wifi is disconnected
				// //////////NOW Doing Nothing
				Log.d("BeautiplyConnectivityBroadcastReciever  ",
						"Wifi is disconnected: " + String.valueOf(networkInfo));
			}
		}
	}

}
