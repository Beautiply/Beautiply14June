package com.cmcdelhi.beautiply.background;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

public class BeautiplyBootBroadcastReciever extends BroadcastReceiver {

	BeautiplyUtility bu;

	@Override
	public void onReceive(Context context, Intent arg1) {
		bu = new BeautiplyUtility(context);

		try {

			User u = bu.loadUser();

			if (u.isBeautiplyShakeOn()) {
				context.startService(new Intent(context,
						BeautiplyShakeService.class));
			}

			if (u.isWallpaperSetterServiceRunning()) {
				context.startService(new Intent(context,
						BeautiplyShakeService.class));
			}

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
