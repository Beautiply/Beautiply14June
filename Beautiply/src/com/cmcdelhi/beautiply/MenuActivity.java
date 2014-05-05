package com.cmcdelhi.beautiply;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;

import com.cmcdelhi.beautiply.background.BeautiplyShakeService;
import com.cmcdelhi.beautiply.background.BeautiplyStarredWallpaperSetterService;
import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MenuActivity extends Activity {
	LinearLayout ll;
	Typeface tf, robo;
	BeautiplyUtility bu;

	LinearLayout mylinearlay22, mylinearlay23, mylinearlay24;
	User u;
	String TAG = "CMC DELHI BEAUTIPLY";
	RelativeLayout rl2, relativelayout49;
	ImageView starimage1111;
	private static final String PREF_NAME = "SwitchButtonDemo";
	private static final String PREF_THEME = "isDark";

	RelativeLayout header;
	TextView dizzyTitle, Thirdscrtv22, Thirdscrnewtxtview111, moving,
			Thirdscrnewtxtview2, Thirdscrseprator56, Thirdscrseprator57,
			Thirdscrnewtxtview22, dizzyTitleFooter, Thirdscrtv2;
	ScrollView ThirdscrScrollview;
	public static TextView imageView1textView1, imageView2textView2,
			imageView3textView3, imageView4textView4, imageView5textView5,
			imageView6textView6, imageView7textView7, imageView8textView8,
			imageView9textView9, imageView10textView10, noops, mytextview47,
			mytextview48;
	FrameLayout thirdscrframelayout1, thirdscrframelayout2,
			thirdscrframelayout3, thirdscrframelayout4, thirdscrframelayout5,
			thirdscrframelayout6, thirdscrframelayout7, thirdscrframelayout8,
			thirdscrframelayout9, thirdscrframelayout10, thirdscrframelayout11;
	LinearLayout Thirdscrll, ThirdscrLL3, Layout4, LinearLayout44,
			myLinearlay21;
	RelativeLayout ThirdscrLL3_1, ThirdscrLL3_2, ThirdscrLL3_3, ThirdscrLL3_4,
			ThirdscrLL3_5;

	TextView ThirdScreenBeautiply, tv1, tv2, tv3, tv4, beu1, sep15, sep16,
			Thirdscrtextview3, sep17, sep18, sep1, sep2, sep3, sep4,
			ThirdscrLL3_1_1, ThirdscrLL3_1_2, ThirdscrLL3_1_3,
			ThirdscrLL3_1_4_1, ThirdscrLL3_1_5, Thirdscrnewtxtview;
	RelativeLayout myview1;
	private SeekBar seekbar1 = null;
	SeekBar shakeSeekBar;
	public boolean isMenuPressed11 = false;

	TextView nn, mytextview1, mytextview2, mytextview3, mytextview4,
			mytextview5, Thirdscrtextview35;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences preferences = this.getSharedPreferences(PREF_NAME, 0);
		boolean isDark = preferences.getBoolean(PREF_THEME, false);

		bu = new BeautiplyUtility(getBaseContext());

		// set the theme according to the setting
		if (isDark)
			this.setTheme(R.style.AppThemeDark);
		else
			this.setTheme(R.style.AppThemeLight);

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);

		shakeSeekBar = (SeekBar) findViewById(R.id.shakeSeekBar);

		// View.OnClickListener switchDemoClickListener = new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Switch switchButton = (Switch) v;
		// if (switchButton.isChecked()) {
		// Toast.makeText(MenuActivity.this,
		// switchButton.getTextLeft(), Toast.LENGTH_SHORT)
		// .show();
		//
		// try {
		// u = bu.loadUser();
		// u.setBeautiplyShakeOn(true);
		// bu.saveUser(u);
		//
		// Toast.makeText(MenuActivity.this,
		// "After bu.saveUser with " + true,
		// Toast.LENGTH_SHORT).show();
		//
		// // startService(new Intent(getBaseContext(),
		// // BeautiplyShakeService.class));
		// // BeautiplyToastGenerator btgyl = new
		// // BeautiplyToastGenerator(
		// // getBaseContext());
		// // btgyl.getBeautiplyShakeToast("Beautiply shake is on")
		// // .show();
		//
		// } catch (JSONException e) {
		//
		// e.printStackTrace();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		// }
		//
		// } else
		// Toast.makeText(MenuActivity.this,
		// switchButton.getTextRight(), Toast.LENGTH_SHORT)
		// .show();
		// try {
		// u = bu.loadUser();
		// u.setBeautiplyShakeOn(false);
		// bu.saveUser(u);
		// Toast.makeText(MenuActivity.this,
		// switchButton.getTextLeft(), Toast.LENGTH_SHORT)
		// .show();
		//
		// Toast.makeText(MenuActivity.this,
		// "After bu.saveUser with Shake " + false,
		// Toast.LENGTH_SHORT).show();
		//
		// // stopService(new Intent(getBaseContext(),
		// // BeautiplyShakeService.class));
		//
		// // BeautiplyToastGenerator btgyl = new
		// // BeautiplyToastGenerator(
		// // getBaseContext());
		// // btgyl.getBeautiplyShakeToast("Beautiply shake is off")
		// // .show();
		//
		// } catch (JSONException e) {
		//
		// e.printStackTrace();
		// } catch (IOException e) {
		//
		// e.printStackTrace();
		//
		// }
		//
		// }
		//
		// };
		//
		// // ((Switch) this.findViewById(R.id.btn_on_off))
		// // .setOnClickListener(switchDemoClickListener);
		// //
		// // beautiply utility object
		// bu = new BeautiplyUtility(getBaseContext());

		tf = Typeface.createFromAsset(getAssets(), "fonts/amplify.ttf");
		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
		dizzyTitle = (TextView) findViewById(R.id.dizzyTitle);
		dizzyTitle.setTypeface(tf);
		dizzyTitle.setTextColor(Color.WHITE);
		Thirdscrtv2 = (TextView) findViewById(R.id.Thirdscrtv2);
		Thirdscrtv2.setTypeface(tf);
		Thirdscrnewtxtview2 = (TextView) findViewById(R.id.Thirdscrnewtxtview2);
		Thirdscrnewtxtview2.setTypeface(robo);
		Thirdscrnewtxtview22 = (TextView) findViewById(R.id.Thirdscrnewtxtview22);
		Thirdscrnewtxtview22.setTypeface(robo);
		Thirdscrtextview35 = (TextView) findViewById(R.id.Thirdscrtextview35);
		Thirdscrtextview35.setTypeface(robo);
		Thirdscrtv22 = (TextView) findViewById(R.id.Thirdscrtv22);
		Thirdscrtv22.setTypeface(tf);
		moving = (TextView) findViewById(R.id.moving);
		moving.setTypeface(robo);
		Thirdscrnewtxtview111 = (TextView) findViewById(R.id.Thirdscrnewtxtview111);
		Thirdscrnewtxtview111.setTypeface(robo);
		header = (RelativeLayout) findViewById(R.id.header);
		mytextview47 = (TextView) findViewById(R.id.mytextview47);

		starimage1111 = (ImageView) findViewById(R.id.starimage1111);
		mytextview48 = (TextView) findViewById(R.id.mytextview48);
		ThirdscrScrollview = (ScrollView) findViewById(R.id.ThirdscrScrollview);

		// ///////////Loading User and
		// Configurations////////////>>>>>>>>>>>>>>>>>>>

		try {

			Log.d(TAG, "BEFOre Load user");
			u = bu.loadUser();

			Log.d(TAG,
					"User Time Interval  "
							+ u.getTimeIntervalToChangeWallpaper());
			Log.d(TAG, "IS BEAUTIPLY SHAKE ON  " + u.isBeautiplyShakeOn());
			Log.d(TAG, "IS BEAUTIPLY STAR ON  " + u.isBeautiplyStarOn());
			Log.d(TAG,
					"IS SERVICE RUNNING  "
							+ u.isWallpaperSetterServiceRunning());

			// obtaining the progress value and setting it on seek bar
			long obtainedTimeInterval = u.getTimeIntervalToChangeWallpaper();

			if (obtainedTimeInterval == 15 * 60 * 1000) {
				seekbar1.setProgress(5);
				mytextview47.setText("15");
				mytextview48.setText("Minutes");
			} else if (obtainedTimeInterval == 30 * 60 * 1000) {
				seekbar1.setProgress(15);
				mytextview47.setText("30");
				mytextview48.setText("Minutes");

			} else if (obtainedTimeInterval == 1 * 60 * 60 * 1000) {
				seekbar1.setProgress(25);
				mytextview47.setText("1");
				mytextview48.setText("Hour");

			} else if (obtainedTimeInterval == 2 * 60 * 60 * 1000) {
				seekbar1.setProgress(35);
				mytextview47.setText("2");
				mytextview48.setText("Hour");

			} else if (obtainedTimeInterval == 3 * 60 * 60 * 1000) {
				seekbar1.setProgress(45);
				mytextview47.setText("3");
				mytextview48.setText("Hour");

			} else if (obtainedTimeInterval == 6 * 60 * 60 * 1000) {
				seekbar1.setProgress(55);
				mytextview47.setText("6");
				mytextview48.setText("Hour");

			} else if (obtainedTimeInterval == 12 * 60 * 60 * 1000) {
				seekbar1.setProgress(65);
				mytextview47.setText("12");
				mytextview48.setText("Hour");

			} else if (obtainedTimeInterval == 24 * 60 * 60 * 1000) {
				seekbar1.setProgress(75);
				mytextview47.setText("Every");
				mytextview48.setText("Day");

			} else if (obtainedTimeInterval == 7 * 24 * 60 * 60 * 1000) {
				seekbar1.setProgress(85);
				mytextview47.setText("Every");
				mytextview48.setText("Week");

			} else if (obtainedTimeInterval == -1) {
				seekbar1.setProgress(95);
				mytextview47.setText("On");
				mytextview48.setText("Unlock");

			}

			// /setting the Star On or Off User State
			u = bu.loadUser();
			if (u.isBeautiplyStarOn()) {
				starimage1111.setImageDrawable(getResources().getDrawable(
						R.drawable.starbigwhite));
			} else {
				starimage1111.setImageDrawable(getResources().getDrawable(
						R.drawable.starbigempty));
			}

			// setting the Shake On or Off State
			u = bu.loadUser();
			if (u.isBeautiplyShakeOn()) {
				Log.d(TAG, "Swutch Ckjec CHECKED HAI ");
				shakeSeekBar.setProgress(80);

			} else {
				Log.d(TAG, "Swutch Ckjec UnCHECKED HAI ");
				shakeSeekBar.setProgress(20);
			}

		} catch (JSONException e1) {
			Toast.makeText(getBaseContext(),
					"First Time User .Creating configutaion", Toast.LENGTH_LONG)
					.show();
			try {
				bu.createFirstTimeUser();

			} catch (JSONException e2) {

				e1.printStackTrace();
			} catch (IOException e2) {

				e1.printStackTrace();
			}
			e1.printStackTrace();
		} catch (FileNotFoundException e) {

			showDialog(0);

			Thirdscrtv22.setVisibility(TextView.VISIBLE);
			beu1.setVisibility(TextView.VISIBLE);

			try {
				bu.createFirstTimeUser();
			} catch (JSONException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		} catch (IOException e1) {
			Toast.makeText(getBaseContext(),
					"First Time User .Creating configutaion", Toast.LENGTH_LONG)
					.show();
			try {
				bu.createFirstTimeUser();
			} catch (JSONException e2) {

				e1.printStackTrace();
			} catch (IOException e2) {

				e1.printStackTrace();
			}
			e1.printStackTrace();
		} catch (Exception e1) {
			Toast.makeText(getBaseContext(),
					"First Time User .Creating configutaion", Toast.LENGTH_LONG)
					.show();
			try {
				bu.createFirstTimeUser();
			} catch (JSONException e2) {

				e1.printStackTrace();
			} catch (IOException e2) {

				e1.printStackTrace();
			}
			e1.printStackTrace();
		}

		ThirdscrScrollview.setBackgroundColor(Color.argb(50, 0, 0, 0));
		header.setBackgroundColor(Color.argb(50, 0, 0, 0));

		bu = new BeautiplyUtility(getBaseContext());

		ll = (LinearLayout) findViewById(R.id.ll);
		Bitmap bmpBlured = null;
		try {
			bmpBlured = fastblur(
					bu.loadBeautiplyHolder().getCurrentWallpaper(), 17);
		} catch (ParseException e1) {
			bmpBlured = fastblur(((BitmapDrawable) getWallpaper()).getBitmap(),
					17);
			e1.printStackTrace();
		} catch (IOException e1) {
			bmpBlured = fastblur(((BitmapDrawable) getWallpaper()).getBitmap(),
					17);
			e1.printStackTrace();
		} catch (JSONException e1) {
			bmpBlured = fastblur(((BitmapDrawable) getWallpaper()).getBitmap(),
					17);
			e1.printStackTrace();
		} catch (Exception e1) {
			bmpBlured = fastblur(((BitmapDrawable) getWallpaper()).getBitmap(),
					17);
			e1.printStackTrace();
		}
		Drawable dBlur = new BitmapDrawable(getResources(), bmpBlured);
		// Check if we're running on GingerBread or above
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			ll.setBackground(dBlur);
		} else {
			ll.setBackgroundDrawable(dBlur);
		}

		mytextview1 = (TextView) findViewById(R.id.mytextview1);
		mytextview1.setTypeface(robo);
		mytextview2 = (TextView) findViewById(R.id.mytextview2);
		mytextview2.setTypeface(robo);
		mytextview3 = (TextView) findViewById(R.id.mytextview3);
		mytextview3.setTypeface(robo);
		mytextview4 = (TextView) findViewById(R.id.mytextview4);
		mytextview4.setTypeface(robo);
		mytextview5 = (TextView) findViewById(R.id.mytextview5);
		mytextview5.setTypeface(robo);
		// myimageview1=(ImageView) findViewById(R.id.myimageview1) ;
		ThirdscrLL3 = (LinearLayout) findViewById(R.id.ThirdscrLL3);
		ThirdscrLL3.setBackgroundColor(Color.argb(50, 0, 0, 0));
		myview1 = (RelativeLayout) findViewById(R.id.myview1);
		myview1.setBackgroundColor(Color.argb(50, 0, 0, 0));
		myLinearlay21 = (LinearLayout) findViewById(R.id.myLinearlay21);
		myLinearlay21.setBackgroundColor(Color.argb(50, 0, 0, 0));
		mylinearlay22 = (LinearLayout) findViewById(R.id.mylinearlay22);
		mylinearlay22.setBackgroundColor(Color.argb(50, 0, 0, 0));
		mylinearlay23 = (LinearLayout) findViewById(R.id.mylinearlay23);
		mylinearlay23.setBackgroundColor(Color.argb(50, 0, 0, 0));
		mylinearlay24 = (LinearLayout) findViewById(R.id.mylinearlay24);
		mylinearlay24.setBackgroundColor(Color.argb(50, 0, 0, 0));

		Layout4 = (LinearLayout) findViewById(R.id.LinearLayout4);
		Layout4.setBackgroundColor(Color.argb(50, 0, 0, 0));
		LinearLayout44 = (LinearLayout) findViewById(R.id.LinearLayout44);
		LinearLayout44.setBackgroundColor(Color.argb(50, 0, 0, 0));
		relativelayout49 = (RelativeLayout) findViewById(R.id.relativelayout49);
		relativelayout49.setBackgroundColor(Color.argb(50, 0, 0, 0));

		thirdscrframelayout1 = (FrameLayout) findViewById(R.id.thirdscrframelayout1);
		thirdscrframelayout2 = (FrameLayout) findViewById(R.id.thirdscrframelayout2);
		thirdscrframelayout3 = (FrameLayout) findViewById(R.id.thirdscrframelayout3);
		thirdscrframelayout4 = (FrameLayout) findViewById(R.id.thirdscrframelayout4);
		thirdscrframelayout5 = (FrameLayout) findViewById(R.id.thirdscrframelayout5);
		thirdscrframelayout6 = (FrameLayout) findViewById(R.id.thirdscrframelayout6);
		thirdscrframelayout7 = (FrameLayout) findViewById(R.id.thirdscrframelayout7);
		thirdscrframelayout8 = (FrameLayout) findViewById(R.id.thirdscrframelayout8);
		thirdscrframelayout9 = (FrameLayout) findViewById(R.id.thirdscrframelayout9);
		thirdscrframelayout10 = (FrameLayout) findViewById(R.id.thirdscrframelayout10);
		// thirdscrframelayout11=(FrameLayout)
		// findViewById(R.id.thirdscrframelayout11);
		// Imageview45=(ImageView) findViewById(R.id.Imageview45);

		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		// Thirdscrtextview3 = (TextView) findViewById(R.id.Thirdscrtextview3);
		// Thirdscrtextview3.setTypeface(robo);
		Thirdscrnewtxtview = (TextView) findViewById(R.id.Thirdscrnewtxtview);
		Thirdscrnewtxtview.setTypeface(robo);
		// sp5.setBackgroundColor(Color.argb(75, 255, 255, 255));

		// TextView sep3 = (TextView) findViewById(R.id.Thirdscrseprator3);
		// sep3.setBackgroundColor(Color.argb(75, 255, 255, 255));
		TextView sep4 = (TextView) findViewById(R.id.Thirdscrseprator4);
		sep4.setBackgroundColor(Color.argb(75, 255, 255, 255));
		TextView sep15 = (TextView) findViewById(R.id.Thirdscrseprator5);
		sep15.setBackgroundColor(Color.argb(75, 255, 255, 255));
		TextView sep16 = (TextView) findViewById(R.id.Thirdscrseprator6);
		sep16.setBackgroundColor(Color.argb(75, 255, 255, 255));
		TextView sep17 = (TextView) findViewById(R.id.Thirdscrseprator7);
		sep17.setBackgroundColor(Color.argb(75, 255, 255, 255));
		TextView sep18 = (TextView) findViewById(R.id.Thirdscrseprator8);
		sep18.setBackgroundColor(Color.argb(75, 255, 255, 255));
		ThirdscrLL3_1_1 = (TextView) findViewById(R.id.ThirdscrLL3_1_1);
		ThirdscrLL3_1_1.setTypeface(tf);
		ThirdscrLL3_1_2 = (TextView) findViewById(R.id.ThirdscrLL3_1_2);
		ThirdscrLL3_1_2.setTypeface(tf);
		ThirdscrLL3_1_3 = (TextView) findViewById(R.id.ThirdscrLL3_1_3);
		ThirdscrLL3_1_3.setTypeface(tf);
		ThirdscrLL3_1_4_1 = (TextView) findViewById(R.id.ThirdscrLL3_1_4_1);
		ThirdscrLL3_1_4_1.setTypeface(tf);
		ThirdscrLL3_1_5 = (TextView) findViewById(R.id.ThirdscrLL3_1_5);
		ThirdscrLL3_1_5.setTypeface(tf);
		ThirdscrLL3_1 = (RelativeLayout) findViewById(R.id.ThirdscrLL3_1);
		ThirdscrLL3_2 = (RelativeLayout) findViewById(R.id.ThirdscrLL3_2);
		ThirdscrLL3_3 = (RelativeLayout) findViewById(R.id.ThirdscrLL3_3);
		ThirdscrLL3_4 = (RelativeLayout) findViewById(R.id.ThirdscrLL3_4);
		ThirdscrLL3_5 = (RelativeLayout) findViewById(R.id.ThirdscrLL3_5);

		ThirdscrLL3_1_5.setTypeface(tf);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {

			ThirdscrLL3_1_1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int lines = mytextview1.getMaxLines();
					if (lines <= 0) {
						ThirdscrLL3_1_1.setTextColor(Color.BLACK);
						mytextview1.setMaxLines(Integer.MAX_VALUE);

					} else {
						ThirdscrLL3_1_1.setTextColor(Color.WHITE);
						mytextview1.setMaxLines(0);

					}

				}
			});
			ThirdscrLL3_1_2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int lines = mytextview2.getMaxLines();
					if (lines <= 0) {
						ThirdscrLL3_1_2.setTextColor(Color.BLACK);
						mytextview2.setMaxLines(Integer.MAX_VALUE);

					} else {
						ThirdscrLL3_1_2.setTextColor(Color.WHITE);
						mytextview2.setMaxLines(0);

					}

				}
			});
			ThirdscrLL3_1_3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int lines = mytextview3.getMaxLines();
					if (lines <= 0) {
						ThirdscrLL3_1_3.setTextColor(Color.BLACK);
						mytextview3.setMaxLines(Integer.MAX_VALUE);

					} else {
						mytextview3.setMaxLines(0);
						ThirdscrLL3_1_3.setTextColor(Color.WHITE);

					}

				}
			});

			ThirdscrLL3_1_4_1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int lines = mytextview4.getMaxLines();
					if (lines <= 0) {
						ThirdscrLL3_1_4_1.setTextColor(Color.BLACK);
						mytextview4.setMaxLines(Integer.MAX_VALUE);

					} else {
						mytextview4.setMaxLines(0);
						ThirdscrLL3_1_4_1.setTextColor(Color.WHITE);

					}

				}
			});
			ThirdscrLL3_1_5.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					int lines = mytextview5.getMaxLines();
					if (lines <= 0) {
						ThirdscrLL3_1_5.setTextColor(Color.BLACK);
						mytextview5.setMaxLines(Integer.MAX_VALUE);

					} else {
						mytextview5.setMaxLines(0);
						ThirdscrLL3_1_5.setTextColor(Color.WHITE);

					}

				}
			});

		} else {

			mytextview1.setMaxLines(Integer.MAX_VALUE);
			mytextview2.setMaxLines(Integer.MAX_VALUE);
			mytextview3.setMaxLines(Integer.MAX_VALUE);
			mytextview4.setMaxLines(Integer.MAX_VALUE);
			mytextview5.setMaxLines(Integer.MAX_VALUE);

		}

		// // seekbar1 is used to schedule time interval to change wallpaper

		seekbar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progressChanged = 0;

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				if (progressChanged >= 1 && progressChanged < 10) {

					BeautiplyToastGenerator
							.getBeautiplyShakeToast(getBaseContext(),
									"Setting up wallpaper may cause intense battery and data usage.")
							.show();
				}
				if (progressChanged > 10 && progressChanged < 20) {

					BeautiplyToastGenerator
							.getBeautiplyShakeToast(getBaseContext(),
									"Setting up wallpaper may cause intense battery and data usage.")
							.show();
				}

				// "Time set is :"+progressChanged, Toast.LENGTH_SHORT).show();

				// Thirdscrtextview3.setText(progressChanged);

				// Intent beautifyWallPaperSetterService2 = new Intent(
				// getBaseContext(), BeautifyWallPaperSetterService.class);
				// stopService(beautifyWallPaperSetterService2);
				//
				// Intent beautifyWallPaperSetterService = new Intent(
				// getBaseContext(), BeautifyWallPaperSetterService.class);
				// startService(beautifyWallPaperSetterService);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progressChanged = progress;
				// if (progress == 0) {
				// // Thirdscrtextview3.setText("Time set is:");
				// //mytextview47.setText("O");
				// // mytextview48.setText("Seconds");
				// thirdscrframelayout1.setVisibility(View.INVISIBLE);
				// thirdscrframelayout2.setVisibility(View.INVISIBLE);
				// thirdscrframelayout3.setVisibility(View.INVISIBLE);
				// thirdscrframelayout4.setVisibility(View.INVISIBLE);
				// thirdscrframelayout5.setVisibility(View.INVISIBLE);
				// thirdscrframelayout6.setVisibility(View.INVISIBLE);
				// thirdscrframelayout7.setVisibility(View.INVISIBLE);
				// thirdscrframelayout8.setVisibility(View.INVISIBLE);
				// thirdscrframelayout9.setVisibility(View.INVISIBLE);
				// thirdscrframelayout10.setVisibility(View.INVISIBLE);
				// // thirdscrframelayout11.setVisibility(View.INVISIBLE);
				// }

				if (progress >= 0 && progress < 10) {

					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("15");
					mytextview48.setText("Minutes");

					// Thirdscrtextview3.setVisibility(GONE);

					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);

					thirdscrframelayout1.setVisibility(View.VISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);

					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(15 * 60 * 1000);
						bu.saveUser(u);

						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);

					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
					// Toast.makeText(getBaseContext(),
					// "Wallpaper will be set at every 15 minutes. ",
					// Toast.LENGTH_SHORT).show();
				}
				if (progress > 10 && progress < 20) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("30");
					mytextview48.setText("Minutes");

					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.VISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(30 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
					// Toast.makeText(getBaseContext(),
					// "Wallpaper will be set at every 30 minutes. ",
					// Toast.LENGTH_SHORT).show();
				}
				if (progress > 20 && progress < 30) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("1");
					mytextview48.setText("hour");

					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.VISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(1 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 30 && progress < 40) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("2");
					mytextview48.setText("hour");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.VISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(2 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 40 && progress < 50) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("3");
					mytextview48.setText("hour");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.VISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(3 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 50 && progress < 60) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("6");
					mytextview48.setText("hour");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.VISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(6 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 60 && progress < 70) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("12");
					mytextview48.setText("Hour");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.VISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(12 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 70 && progress < 80) {
					// Thirdscrtextview3.setText("Time set is");
					mytextview47.setText("Every");
					mytextview48.setText("Day");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.VISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(24 * 60 * 60 * 1000);
						bu.saveUser(u);
						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				}
				if (progress > 80 && progress < 90) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("Every");
					mytextview48.setText("Week");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.VISIBLE);
					thirdscrframelayout10.setVisibility(View.INVISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time

					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(7 * 24 * 60 * 60
								* 1000);

						Log.d(TAG,
								"User Time Interval  "
										+ u.getTimeIntervalToChangeWallpaper());
						Log.d(TAG,
								"IS BEAUTIPLY SHAKE ON  "
										+ u.isBeautiplyShakeOn());
						Log.d(TAG,
								"IS BEAUTIPLY STAR ON  "
										+ u.isBeautiplyStarOn());
						Log.d(TAG,
								"IS SERVICE RUNNING  "
										+ u.isWallpaperSetterServiceRunning());

						bu.saveUser(u);

						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}
				}
				if (progress > 90 && progress <= 100) {
					// Thirdscrtextview3.setText("Time set is:");
					mytextview47.setText("On");
					mytextview48.setText("Unlock");
					// Intent beautifyWallPaperSetterService2 = new Intent(
					// getBaseContext(),
					// BeautifyWallPaperSetterService.class);
					// stopService(beautifyWallPaperSetterService2);
					// thirdscrframelayout10.setBackground(R.drawable.bri);
					thirdscrframelayout1.setVisibility(View.INVISIBLE);
					thirdscrframelayout2.setVisibility(View.INVISIBLE);
					thirdscrframelayout3.setVisibility(View.INVISIBLE);
					thirdscrframelayout4.setVisibility(View.INVISIBLE);
					thirdscrframelayout5.setVisibility(View.INVISIBLE);
					thirdscrframelayout6.setVisibility(View.INVISIBLE);
					thirdscrframelayout7.setVisibility(View.INVISIBLE);
					thirdscrframelayout8.setVisibility(View.INVISIBLE);
					thirdscrframelayout9.setVisibility(View.INVISIBLE);
					thirdscrframelayout10.setVisibility(View.VISIBLE);
					// thirdscrframelayout11.setVisibility(View.INVISIBLE);

					// saving the user time
					// -1 means the Wall paper shuld change on every unlock
					try {
						u = bu.loadUser();
						u.setTimeIntervalToChangeWallpaper(-1);

						Log.d(TAG,
								"User Time Interval  "
										+ u.getTimeIntervalToChangeWallpaper());
						Log.d(TAG,
								"IS BEAUTIPLY SHAKE ON  "
										+ u.isBeautiplyShakeOn());
						Log.d(TAG,
								"IS BEAUTIPLY STAR ON  "
										+ u.isBeautiplyStarOn());
						Log.d(TAG,
								"IS SERVICE RUNNING  "
										+ u.isWallpaperSetterServiceRunning());

						bu.saveUser(u);

						// Intent beautifyWallPaperSetterService = new Intent(
						// getBaseContext(),
						// BeautifyWallPaperSetterService.class);
						// startService(beautifyWallPaperSetterService);
					} catch (JSONException e) {

						e.printStackTrace();
					} catch (IOException e) {

						e.printStackTrace();
					}

				} else {
				}

			}
		});

		tf = Typeface.createFromAsset(getAssets(), "fonts/amplify.ttf");

		starimage1111.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					if (isMenuPressed11 == false) {
						isMenuPressed11 = true;

					} else if (isMenuPressed11 == true) {
						isMenuPressed11 = false;

						try {
							u = bu.loadUser();
							if (u.isBeautiplyStarOn() == false) {

								u.setBeautiplyStarOn(true);
								bu.saveUser(u);
								starimage1111.setImageDrawable(getResources()
										.getDrawable(R.drawable.starbigwhite));

								startService(new Intent(
										getBaseContext(),
										BeautiplyStarredWallpaperSetterService.class));

								BeautiplyToastGenerator.getBeautiplyShakeToast(
										getBaseContext(),
										"Setting only starred wallapaper")
										.show();

							} else {
								u.setBeautiplyStarOn(false);
								bu.saveUser(u);

								stopService(new Intent(
										getBaseContext(),
										BeautiplyStarredWallpaperSetterService.class));

								starimage1111.setImageDrawable(getResources()
										.getDrawable(R.drawable.starbigempty));

								BeautiplyToastGenerator
										.getBeautiplyShakeToast(
												getBaseContext(),
												"Setting  starred wallapaper is now off")
										.show();

								Log.i(TAG, "star empty false saved");
							}
						} catch (Exception e) {

						}

					}

					break;
				}
				}
				return true;
			}
		});

		shakeSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {

				if (progress >= 0 && progress <= 50) {

					shakeSeekBar.setProgress(20);

					if (progress == 20) {

						try {
							u = bu.loadUser();
							u.setBeautiplyShakeOn(false);
							bu.saveUser(u);

							stopService(new Intent(getBaseContext(),
									BeautiplyShakeService.class));

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(), "Beautiply Shake OFF")
									.show();

						} catch (JSONException e) {

							e.printStackTrace();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}

				} else {
					shakeSeekBar.setProgress(80);

					if (progress == 80) {

						try {
							u = bu.loadUser();
							u.setBeautiplyShakeOn(true);
							bu.saveUser(u);

							startService(new Intent(getBaseContext(),
									BeautiplyShakeService.class));

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(), "Beautiply Shake ON")
									.show();

						} catch (JSONException e) {

							e.printStackTrace();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}

				}

			}
		});

	}

	public Bitmap fastblur(Bitmap sentBitmap, int radius) {

		// Stack Blur v1.0 from
		// http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
		//
		// Java Author: Mario Klingemann <mario at quasimondo.com>
		// http://incubator.quasimondo.com
		// created Feburary 29, 2004
		// Android port : Yahel Bouaziz <yahel at kayenko.com>
		// http://www.kayenko.com
		// ported april 5th, 2012

		// This is a compromise between Gaussian Blur and Box blur
		// It creates much better looking blurs than Box Blur, but is
		// 7x faster than my Gaussian Blur implementation.
		//
		// I called it Stack Blur because this describes best how this
		// filter works internally: it creates a kind of moving stack
		// of colors whilst scanning through the image. Thereby it
		// just has to add one new block of color to the right side
		// of the stack and remove the leftmost color. The remaining
		// colors on the topmost layer of the stack are either added on
		// or reduced by one, depending on if they are on the right or
		// on the left side of the stack.
		//
		// If you are using this algorithm in your code please add
		// the following line:
		//
		// Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

		if (radius < 1) {
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];
		Log.e("pix", w + " " + h + " " + pix.length);
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++) {

				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				// Preserve alpha channel: ( 0xff000000 & pix[yi] )
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
						| (dv[gsum] << 8) | dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}

		Log.e("pix", w + " " + h + " " + pix.length);
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);

		return (bitmap);
	}

}
