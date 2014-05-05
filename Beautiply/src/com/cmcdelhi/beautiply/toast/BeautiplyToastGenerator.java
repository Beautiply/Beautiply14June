package com.cmcdelhi.beautiply.toast;

import com.cmcdelhi.beautiply.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class BeautiplyToastGenerator {

//	public static Toast getToast(Context context, String msg) {
//
//		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//
//		View layout = inflater.inflate(R.layout.beautiplytoastlayout, null);
//		// layout.setBackgroundColor(Color.TRANSPARENT);
//
//		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
//				LayoutParams.WRAP_CONTENT);
//
//		layout.setLayoutParams(params);
//
//		TextView msgTV = (TextView) layout.findViewById(R.id.toastMessage);
//		msgTV.setText(msg);
//		// msgTV.setTextColor(Color.BLACK);
//
//		Toast toast = new Toast(context);
//		toast.setGravity(Gravity.CENTER, 0, 0);
//		toast.setDuration(Toast.LENGTH_SHORT);
//
//		toast.setView(layout);
//
//		return toast;
//	}

	public static Toast getBeautiplyShakeToast(Context context, String msg) {
		// LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View layout = inflater
				.inflate(R.layout.beautiplyshaketoastlayout, null);
		// layout.setBackgroundColor(Color.TRANSPARENT);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);

		layout.setLayoutParams(params);

		TextView msgTV = (TextView) layout.findViewById(R.id.toastMessage);
		msgTV.setText(msg);
		// msgTV.setTextColor(Color.BLACK);

		Toast toast = new Toast(context);

		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.setDuration(Toast.LENGTH_SHORT);

		toast.setView(layout);

		return toast;
	}

}
