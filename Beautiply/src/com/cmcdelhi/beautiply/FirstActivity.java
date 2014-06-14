package com.cmcdelhi.beautiply;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.cmcdelhi.beautiply.background.BeautiplyAsyncTask;
import com.cmcdelhi.beautiply.background.BeautiplyStarredWallpaperSetterService;
import com.cmcdelhi.beautiply.background.BeautiplyWallPaperSetterService;
import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautifyGridHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

@SuppressLint("NewApi")
public class FirstActivity extends Activity {
	private static final String PREF_NAME = "SwitchButtonDemo";
	private static final String PREF_THEME = "isDark";

	ScrollView rl;
	Bitmap bmpMain;
	public static LinearLayout l1;
	LinearLayout mylinearlay22, mylinearlay23, mylinearlay24;
	public static RelativeLayout internetheader;
	TextView dizzyTitle, Thirdscrtv22, Thirdscrnewtxtview111, moving,
			Thirdscrnewtxtview2, Thirdscrseprator56, Thirdscrseprator57,
			Thirdscrnewtxtview22, dizzyTitleFooter;
	RelativeLayout rl2, relativelayout49;
	RelativeLayout footer, expandFooter, header;
	ImageView imgsettings, imgRefresh;
	public static ImageView imgPlay;

	public static ImageView star5, star6, star7, star8, star9, star1, star2,
			star3, star4, star10;
	Typeface tf, robo;

	User u;

	String TAG = "CMC DELHI BEAUTIPLY";

	static int selectedImageIndex = 0;

	public static TextView imageView1textView1, imageView2textView2,
			imageView3textView3, imageView4textView4, imageView5textView5,
			imageView6textView6, imageView7textView7, imageView8textView8,
			imageView9textView9, imageView10textView10, noops, mytextview47,
			mytextview48;
	FrameLayout thirdscrframelayout1, thirdscrframelayout2,
			thirdscrframelayout3, thirdscrframelayout4, thirdscrframelayout5,
			thirdscrframelayout6, thirdscrframelayout7, thirdscrframelayout8,
			thirdscrframelayout9, thirdscrframelayout10, thirdscrframelayout11;
	ImageView Imageview45, myimageview1, imgstop, starimage1111;

	LinearLayout Thirdscrll, ThirdscrLL3, Layout4, LinearLayout44,
			myLinearlay21;
	RelativeLayout ThirdscrLL3_1, ThirdscrLL3_2, ThirdscrLL3_3, ThirdscrLL3_4,
			ThirdscrLL3_5;

	TextView ThirdScreenBeautiply, tv1, tv2, tv3, tv4, beu1, sep15, sep16,
			Thirdscrtextview3, sep17, sep18, sep1, sep2, sep3, sep4,
			ThirdscrLL3_1_1, ThirdscrLL3_1_2, ThirdscrLL3_1_3,
			ThirdscrLL3_1_4_1, ThirdscrLL3_1_5, Thirdscrnewtxtview;
	RelativeLayout myview1;

	TextView nn, mytextview1, mytextview2, mytextview3, mytextview4,
			mytextview5, Thirdscrtextview35;
	ScrollView rll;
	private SeekBar seekbar1 = null;
	// Switch switch1;
	BeautiplyUtility bul;
	Timer timer;
	TimerTask task;

	int timesBackPressed = 0;

	boolean isMenuPressed = false;
	boolean isMenuPressed1 = false;
	boolean isMenuPressed2 = false;
	boolean isMenuPressed3 = false;
	boolean isMenuPressed4 = false;
	boolean isMenuPressed5 = false;
	boolean isMenuPressed6 = false;
	boolean isMenuPressed7 = false;
	boolean isMenuPressed8 = false;
	boolean isMenuPressed9 = false;
	boolean isMenuPressed10 = false;
	boolean isMenuPressed11 = false;
	boolean isMenuPressed12 = false;
	boolean isMenuPressed13 = false;

	public static FrameLayout img1;
	public static FrameLayout img2;
	public static FrameLayout img3;
	public static FrameLayout img4;
	public static FrameLayout img5;
	public static FrameLayout img6;
	public static FrameLayout img7;
	public static FrameLayout img8;
	public static FrameLayout img9;
	public static FrameLayout img10;
	BeautiplyUtility bu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// SharedPreferences preferences = this.getSharedPreferences(PREF_NAME,
		// 0);
		// boolean isDark = preferences.getBoolean(PREF_THEME, false);
		//
		// // set the theme according to the setting
		// if (isDark)
		// this.setTheme(R.style.AppThemeDark);
		// else
		// this.setTheme(R.style.AppThemeLight);
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_first);
		// View.OnClickListener switchDemoClickListener = new
		// View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// Switch switchButton = (Switch) v;
		// if (switchButton.isChecked())
		// Toast.makeText(FirstActivity.this,
		// switchButton.getTextLeft(), Toast.LENGTH_SHORT)
		// .show();
		// else
		// Toast.makeText(FirstActivity.this,
		// switchButton.getTextRight(), Toast.LENGTH_SHORT)
		// .show();
		// }
		// };
		//
		// ((Switch) this.findViewById(R.id.btn_on_off))
		// .setOnClickListener(switchDemoClickListener);
		// ((Switch)
		// this.findViewById(R.id.btn_left_right)).setOnClickListener(switchDemoClickListener);
		// ((Switch)
		// this.findViewById(R.id.btn_cheer)).setOnClickListener(switchDemoClickListener);
		// // ((Switch)
		// this.findViewById(R.id.btn_checked_unchecked)).setOnClickListener(switchDemoClickListener);
		//
		// ((Switch)
		// this.findViewById(R.id.btn_checked_unchecked)).setOnCheckedChangeListener(new
		// CompoundButton.OnCheckedChangeListener()
		// {
		//
		// @Override
		// public void onCheckedChanged(CompoundButton buttonView, boolean
		// isChecked)
		// {
		// if (isChecked)
		// Toast.makeText(FirstActivity.this, ((Switch)
		// buttonView).getTextLeft(), Toast.LENGTH_SHORT).show();
		// else
		// Toast.makeText(FirstActivity.this, ((Switch)
		// buttonView).getTextRight(), Toast.LENGTH_SHORT).show();
		// }
		// });
		// switch1 = (Switch) findViewById(R.id.switch1);
		seekbar1 = (SeekBar) findViewById(R.id.seekBar1);
		mytextview47 = (TextView) findViewById(R.id.mytextview47);
		mytextview48 = (TextView) findViewById(R.id.mytextview48);
		starimage1111 = (ImageView) findViewById(R.id.starimage1111);
		expandFooter = (RelativeLayout) findViewById(R.id.expandFooter);
		moving = (TextView) findViewById(R.id.moving);
		dizzyTitleFooter = (TextView) findViewById(R.id.dizzyTitleFooter);
		Thirdscrtextview35 = (TextView) findViewById(R.id.Thirdscrtextview35);
		Thirdscrnewtxtview2 = (TextView) findViewById(R.id.Thirdscrnewtxtview2);
		Thirdscrnewtxtview22 = (TextView) findViewById(R.id.Thirdscrnewtxtview22);
		tf = Typeface.createFromAsset(getAssets(), "fonts/amplify.ttf");
		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");

		moving.setTypeface(robo);
		dizzyTitleFooter.setTypeface(tf);
		Thirdscrtextview35.setTypeface(robo);
		Thirdscrnewtxtview2.setTypeface(robo);
		Thirdscrnewtxtview22.setTypeface(robo);
		Thirdscrseprator56 = (TextView) findViewById(R.id.Thirdscrseprator56);
		Thirdscrseprator56.setBackgroundColor(Color.argb(75, 255, 255, 255));
		Thirdscrseprator57 = (TextView) findViewById(R.id.Thirdscrseprator57);
		Thirdscrseprator57.setBackgroundColor(Color.argb(75, 255, 255, 255));
		bu = new BeautiplyUtility(getBaseContext());

		Thirdscrtv22 = (TextView) findViewById(R.id.Thirdscrtv22);
		Thirdscrtv22.setVisibility(TextView.GONE);
		Thirdscrtv22.setTypeface(tf);
		Thirdscrnewtxtview111 = (TextView) findViewById(R.id.Thirdscrnewtxtview111);
		Thirdscrnewtxtview111.setTypeface(robo);

		beu1 = (TextView) findViewById(R.id.Thirdscrtv2);
		beu1.setVisibility(TextView.GONE);
		beu1.setTypeface(tf);

		imgstop = (ImageView) findViewById(R.id.imgPlay);
		star1 = (ImageView) findViewById(R.id.star1);
		star2 = (ImageView) findViewById(R.id.star2);
		star3 = (ImageView) findViewById(R.id.star3);
		star4 = (ImageView) findViewById(R.id.star4);
		star5 = (ImageView) findViewById(R.id.star5);
		star6 = (ImageView) findViewById(R.id.star6);
		star7 = (ImageView) findViewById(R.id.star7);
		star8 = (ImageView) findViewById(R.id.star8);
		star9 = (ImageView) findViewById(R.id.star9);
		star10 = (ImageView) findViewById(R.id.star10);

		star1.setVisibility(ImageView.INVISIBLE);
		star2.setVisibility(ImageView.INVISIBLE);
		star3.setVisibility(ImageView.INVISIBLE);
		star4.setVisibility(ImageView.INVISIBLE);
		star5.setVisibility(ImageView.INVISIBLE);
		star6.setVisibility(ImageView.INVISIBLE);
		star7.setVisibility(ImageView.INVISIBLE);
		star8.setVisibility(ImageView.INVISIBLE);
		star9.setVisibility(ImageView.INVISIBLE);
		star10.setVisibility(ImageView.INVISIBLE);

		img1 = (FrameLayout) findViewById(R.id.frame1);
		img2 = (FrameLayout) findViewById(R.id.frame2);
		img3 = (FrameLayout) findViewById(R.id.frame3);
		img4 = (FrameLayout) findViewById(R.id.frame4);
		img5 = (FrameLayout) findViewById(R.id.frame5);
		img6 = (FrameLayout) findViewById(R.id.frame6);
		img7 = (FrameLayout) findViewById(R.id.frame7);
		img8 = (FrameLayout) findViewById(R.id.frame8);
		img9 = (FrameLayout) findViewById(R.id.frame9);
		img10 = (FrameLayout) findViewById(R.id.frame10);

		internetheader = (RelativeLayout) findViewById(R.id.internetheader);
		imageView1textView1 = (TextView) findViewById(R.id.imageView1textView1);
		imageView1textView1.setTypeface(tf);
		imageView1textView1.setTextColor(Color.WHITE);
		imageView2textView2 = (TextView) findViewById(R.id.imageView2textView2);
		imageView2textView2.setTypeface(tf);
		imageView2textView2.setTextColor(Color.WHITE);
		imageView3textView3 = (TextView) findViewById(R.id.imageView3textView3);
		imageView3textView3.setTypeface(tf);
		imageView3textView3.setTextColor(Color.WHITE);
		imageView4textView4 = (TextView) findViewById(R.id.imageView4textView4);
		imageView4textView4.setTypeface(tf);
		imageView4textView4.setTextColor(Color.WHITE);
		imageView5textView5 = (TextView) findViewById(R.id.imageView5textView5);
		imageView5textView5.setTypeface(tf);
		imageView5textView5.setTextColor(Color.WHITE);
		imageView6textView6 = (TextView) findViewById(R.id.imageView6textView6);
		imageView6textView6.setTypeface(tf);
		imageView6textView6.setTextColor(Color.WHITE);
		imageView7textView7 = (TextView) findViewById(R.id.imageView7textView7);
		imageView7textView7.setTypeface(tf);
		imageView7textView7.setTextColor(Color.WHITE);
		imageView8textView8 = (TextView) findViewById(R.id.imageView8textView8);
		imageView8textView8.setTypeface(tf);
		imageView8textView8.setTextColor(Color.WHITE);
		imageView9textView9 = (TextView) findViewById(R.id.imageView9textView9);
		imageView9textView9.setTypeface(tf);
		imageView9textView9.setTextColor(Color.WHITE);
		imageView10textView10 = (TextView) findViewById(R.id.imageView10textView10);
		imageView10textView10.setTypeface(tf);
		imageView10textView10.setTextColor(Color.WHITE);
		mytextview47.setTypeface(robo);
		mytextview48.setTypeface(robo);

		imageView1textView1.setVisibility(TextView.INVISIBLE);
		imageView2textView2.setVisibility(TextView.INVISIBLE);
		imageView3textView3.setVisibility(TextView.INVISIBLE);
		imageView4textView4.setVisibility(TextView.INVISIBLE);
		imageView5textView5.setVisibility(TextView.INVISIBLE);
		imageView6textView6.setVisibility(TextView.INVISIBLE);
		imageView7textView7.setVisibility(TextView.INVISIBLE);
		imageView8textView8.setVisibility(TextView.INVISIBLE);
		imageView9textView9.setVisibility(TextView.INVISIBLE);
		imageView10textView10.setVisibility(TextView.INVISIBLE);
		noops = (TextView) findViewById(R.id.noops);
		noops.setTypeface(robo);
		imgRefresh = (ImageView) findViewById(R.id.imgRefresh);
		imgPlay = (ImageView) findViewById(R.id.imgPlay);
		imgsettings = (ImageView) findViewById(R.id.imgsettings);
		dizzyTitle = (TextView) findViewById(R.id.dizzyTitle);
		dizzyTitle.setTypeface(tf);
		dizzyTitle.setTextColor(Color.WHITE);
		rl = (ScrollView) findViewById(R.id.myMainContainer);
		Drawable d = new BitmapDrawable(getResources(), bmpMain);
		l1 = (LinearLayout) findViewById(R.id.ll);
		rl.setBackgroundColor(Color.argb(200, 255, 255, 255));
		internetheader = (RelativeLayout) findViewById(R.id.internetheader);
		// internetheader.setBackgroundColor(Color.argb(50, 0, 0, 0));
		footer = (RelativeLayout) findViewById(R.id.footer);
		// footer.setBackgroundColor(Color.argb(50, 0, 0, 0));
		header = (RelativeLayout) findViewById(R.id.header);
		header.setBackgroundColor(Color.argb(50, 0, 0, 0));

		// // starting internet checker service
		// Intent internetChekerService = new Intent(getBaseContext(),
		// InternetCheckerService.class);
		// startService(internetChekerService);

		if (bu.isNetworkAvailable() == true) {
			new BeautiplyAsyncTask(getBaseContext()).execute(
					"SET_IMAGE_GRID_OBJECT_1", "thumb", "NOTHING");
			internetheader.setVisibility(RelativeLayout.GONE);

			new BeautiplyAsyncTask(getBaseContext()).execute("SET_PROMO",
					"large", "PROMO_URL");

		} else {
			Bitmap[] loadedImageGrid = null;
			try {
				loadedImageGrid = bu.loadImageGrid();

				// Check if we're running on GingerBread or above
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
					d = new BitmapDrawable(getResources(), loadedImageGrid[0]);
					img1.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[1]);
					img2.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[2]);
					img3.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[3]);
					img4.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[4]);
					img5.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[5]);
					img6.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[6]);
					img7.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[7]);
					img8.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[8]);
					img9.setBackground(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[9]);
					img10.setBackground(d);
					// if not
				} else {
					// Call the old wala drawable method
					d = new BitmapDrawable(getResources(), loadedImageGrid[0]);
					img1.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[1]);
					img2.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[2]);
					img3.setBackgroundDrawable(d);

					d = new BitmapDrawable(getResources(), loadedImageGrid[3]);
					img4.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[4]);
					img5.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[5]);
					img6.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[6]);
					img7.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[7]);
					img8.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[8]);
					img9.setBackgroundDrawable(d);
					d = new BitmapDrawable(getResources(), loadedImageGrid[9]);
					img10.setBackgroundDrawable(d);
				}

				img1.setEnabled(false);
				img2.setEnabled(false);
				img3.setEnabled(false);
				img4.setEnabled(false);
				img5.setEnabled(false);
				img6.setEnabled(false);
				img7.setEnabled(false);
				img8.setEnabled(false);
				img9.setEnabled(false);
				img10.setEnabled(false);
				star1.setVisibility(ImageView.GONE);
				star2.setVisibility(ImageView.GONE);
				star3.setVisibility(ImageView.GONE);
				star4.setVisibility(ImageView.GONE);
				star5.setVisibility(ImageView.GONE);
				star6.setVisibility(ImageView.GONE);
				star7.setVisibility(ImageView.GONE);
				star8.setVisibility(ImageView.GONE);
				star9.setVisibility(ImageView.GONE);
				star10.setVisibility(ImageView.GONE);

			} catch (IOException e) {
				// Toast.makeText(getBaseContext(), "IO Exception ",
				// Toast.LENGTH_LONG).show();
				Log.i("GUFRAN", "IO Exception " + e.getMessage());
			}
		}

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

			// / Setting the Play-Stop Button based on wallpaper setter service
			// running state

			// if (u.isWallpaperSetterServiceRunning() == true) {
			// imgPlay.setImageDrawable(getResources().getDrawable(
			// R.drawable.stop));
			//
			// } else {
			// imgPlay.setImageDrawable(getResources().getDrawable(
			// R.drawable.play));
			//
			// }

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
				// switch1.setChecked(true);

			} else {
				Log.d(TAG, "Swutch Ckjec UnCHECKED HAI ");
				// switch1.setChecked(false);
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
		}

		// rl.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View arg0, MotionEvent arg1) {
		// switch (arg1.getAction()) {
		// case MotionEvent.ACTION_DOWN: {
		//
		// break;
		// }
		// case MotionEvent.ACTION_UP: {
		// if (isMenuPressed12 == false) {
		// isMenuPressed12 = true;
		//
		// expandFooter.setVisibility(RelativeLayout.VISIBLE);
		//
		// ObjectAnimator gridMover = ObjectAnimator.ofFloat(
		// promoLayout, "translationY", 0f, -1000f);
		// gridMover.setDuration(1000);
		//
		// AnimatorSet animatorSetGrid = new AnimatorSet();
		// animatorSetGrid.play(gridMover);
		//
		// animatorSetGrid.start();
		// promoLayout.setVisibility(RelativeLayout.GONE);
		//
		// ObjectAnimator menuMover = ObjectAnimator.ofFloat(rl,
		// "translationY", 1000f, 0f);
		// menuMover.setDuration(1000);
		//
		// AnimatorSet animatorSetMenu = new AnimatorSet();
		// animatorSetMenu.play(menuMover);
		//
		// animatorSetMenu.start();
		// }
		//
		// break;
		//
		// }
		//
		// default:
		// break;
		// }
		// return false;
		// }
		// });

		// expandFooter.setOnTouchListener(new OnTouchListener() {
		//
		// @Override
		// public boolean onTouch(View arg0, MotionEvent arg1) {
		// switch (arg1.getAction()) {
		// case MotionEvent.ACTION_DOWN: {
		//
		// isMenuPressed12 = false;
		// expandFooter.setVisibility(RelativeLayout.GONE);
		// promoLayout.setVisibility(RelativeLayout.VISIBLE);
		// ObjectAnimator gridMover = ObjectAnimator.ofFloat(
		// promoLayout, "translationY", -1000f, 0f);
		// gridMover.setDuration(1000);
		//
		// AnimatorSet animatorSetGrid = new AnimatorSet();
		// animatorSetGrid.play(gridMover);
		//
		// animatorSetGrid.start();
		// }
		//
		// break;
		//
		// default:
		// break;
		// }
		// return false;
		// }
		// });

		// starimage1111 is used for showing only starred images
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
		// // Imgsettings is used to open the settings layout
		// imgsettings.setOnTouchListener(new OnTouchListener() {
		// @Override
		// public boolean onTouch(View arg0, MotionEvent arg1) {
		// switch (arg1.getAction()) {
		// case MotionEvent.ACTION_DOWN: {
		// imgsettings.setImageDrawable(getResources().getDrawable(
		// R.drawable.settingsblack));
		// break;
		// }
		// case MotionEvent.ACTION_UP: {
		//
		// if (isMenuPressed == false) {
		//
		// isMenuPressed = true;
		//
		// rll.setVisibility(RelativeLayout.VISIBLE);
		// // rl.setVisibility(RelativeLayout.GONE);
		//
		// // Check if we're running on GingerBread or above
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		// // This will move the images grid up
		// ObjectAnimator gridMover = ObjectAnimator.ofFloat(
		// rl, "translationY", 0f, -1000f);
		// gridMover.setDuration(1500);
		// // ObjectAnimator fadeIn =
		// // ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetGrid = new AnimatorSet();
		// animatorSetGrid.play(gridMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetGrid.start();
		//
		// // This will move the menu up
		// ObjectAnimator menuMover = ObjectAnimator.ofFloat(
		// rll, "translationY", 1000f, 0f);
		// menuMover.setDuration(1500);
		// // ObjectAnimator fadeIn =
		// // ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetMenu = new AnimatorSet();
		// animatorSetMenu.play(menuMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetMenu.start();
		// } else {
		// // Call commit()
		// rl.setVisibility(RelativeLayout.GONE);
		// rll.setVisibility(RelativeLayout.VISIBLE);
		//
		// }
		//
		// imgsettings.setImageDrawable(getResources()
		// .getDrawable(R.drawable.settingsblack));
		//
		// } else {
		//
		// isMenuPressed = false;
		// // rl.setVisibility(RelativeLayout.GONE);
		//
		// // Check if we're running on GingerBread or above
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		// // This will move the images grid up
		// ObjectAnimator gridMover = ObjectAnimator.ofFloat(
		// rl, "translationY", -1000f, 0f);
		// gridMover.setDuration(1500);
		// // ObjectAnimator fadeIn =
		// // ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetGrid = new AnimatorSet();
		// animatorSetGrid.play(gridMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetGrid.start();
		//
		// // This will move the menu up
		// ObjectAnimator menuMover = ObjectAnimator.ofFloat(
		// rll, "translationY", 0f, 1000f);
		// menuMover.setDuration(1500);
		// // ObjectAnimator fadeIn =
		// // ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetMenu = new AnimatorSet();
		// animatorSetMenu.play(menuMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetMenu.start();
		// } else {
		// // Call commit()
		// rl.setVisibility(RelativeLayout.VISIBLE);
		// rll.setVisibility(RelativeLayout.GONE);
		//
		// }
		//
		// imgsettings.setImageDrawable(getResources()
		// .getDrawable(R.drawable.settings));
		//
		// }
		//
		// break;
		// }
		// }
		// return true;
		// }
		// });

		// // / Imgrefresh is used for refreshing wallpapers and setting up new
		// // wallpaper
		imgRefresh.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgRefresh.setImageDrawable(getResources().getDrawable(
							R.drawable.refreshblack));
					break;
				}
				case MotionEvent.ACTION_UP: {

					imgRefresh.setImageDrawable(getResources().getDrawable(
							R.drawable.refresh));

					// clearing all star status
					star1.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star2.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star3.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star4.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star5.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star6.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star7.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star8.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star9.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));
					star10.setImageDrawable(getResources().getDrawable(
							R.drawable.staremptyblack));

					if (bu.isNetworkAvailable() == true) {

						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_IMAGE_GRID_OBJECT_1", "thumb", "NOTHING");

						float dest = 720;
						// Check if we're running on Honeycomb
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
							if (imgRefresh.getRotation() == 720) {
								imgRefresh.setImageDrawable(getResources()
										.getDrawable(R.drawable.refresh));
								System.out.println(imgRefresh.getAlpha());
								dest = 0;
							}

							if (imgRefresh.getRotation() == 0) {
								imgRefresh.setImageDrawable(getResources()
										.getDrawable(R.drawable.refresh));
							}
							ObjectAnimator animation1 = ObjectAnimator.ofFloat(
									imgRefresh, "rotation", dest);
							animation1.setDuration(1000);
							animation1.start();
						}
					}

					break;
				}
				}
				return true;
			}
		});
		star1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlFirst)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlFirst);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Image Removed from Liked List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star1 removed from likelist urlFirst");

							star1.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlFirst);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Image Saved In LIKED Liked List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star1 added in likelist urlFirst");

							star1.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star2.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlSecond)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlSecond);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star2 removed from likelist urlSecond");

							star2.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlSecond);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star2 saved in likelist urlSecond");

							star2.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star3.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlThird)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlThird);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star3 removed from likelist urlThird");

							star3.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlThird);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star3 saved in likelist urlThird");

							star3.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star4.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlFourth)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlFourth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star4 removed from likelist urlFourth");

							star4.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlFourth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star4 saved in likelist urlFourth");

							star4.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star5.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlFifth)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlFifth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star5 removed from likelist urlFifth");

							star5.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlFifth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star5 saved in likelist urlFifth");

							star5.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star6.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlSixth)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlSixth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star6 removed from likelist urlsixth");
							star6.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlSixth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star6 saved in likelist urlsixth");

							star6.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star7.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlSeventh)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlSeventh);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star7 removed from likelist urlseventh");

							star7.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlSeventh);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star7 saved in likelist urlseventh");

							star7.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star8.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlEight)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlEight);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star8 removed from likelist urleight");

							star8.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlEight);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star8 saved in likelist urleight");

							star8.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star9.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlNinth)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlNinth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star9 removed from likelist urlninth");

							star9.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlNinth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star1 addedin  likelist urlninth");

							star9.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		star10.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserStarredURL().contains(
								BeautifyGridHolder.urlTenth)) {

							u.getUserStarredURL().remove(
									BeautifyGridHolder.urlTenth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star10 removed from likelist urlten");

							star10.setImageDrawable(getResources().getDrawable(
									R.drawable.staremptyblack));
						} else {
							u.getUserStarredURL().add(
									BeautifyGridHolder.urlTenth);
							bu.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// " Star Saved LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"star1 saved in likelist urltenth");

							star10.setImageDrawable(getResources().getDrawable(
									R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});

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
			l1.setBackground(dBlur);
		} else {
			l1.setBackgroundDrawable(dBlur);
		}

		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
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
		rll = (ScrollView) findViewById(R.id.ThirdscrScrollview);

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

	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (isMenuPressed == false) {

			isMenuPressed = true;

			rll.setVisibility(RelativeLayout.VISIBLE);
			// rl.setVisibility(RelativeLayout.GONE);

			// Check if we're running on GingerBread or above
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				// This will move the images grid up
				ObjectAnimator gridMover = ObjectAnimator.ofFloat(rl,
						"translationY", 0f, -1000f);
				gridMover.setDuration(1500);
				// ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
				// "alpha", 0f, 1f);
				// fadeIn.setDuration(2000);
				AnimatorSet animatorSetGrid = new AnimatorSet();
				animatorSetGrid.play(gridMover);

				// animatorSet.play(mover).with(fadeIn).after(fadeOut);
				animatorSetGrid.start();

				// This will move the menu up
				ObjectAnimator menuMover = ObjectAnimator.ofFloat(rll,
						"translationY", 1000f, 0f);
				menuMover.setDuration(1500);
				// ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
				// "alpha", 0f, 1f);
				// fadeIn.setDuration(2000);
				AnimatorSet animatorSetMenu = new AnimatorSet();
				animatorSetMenu.play(menuMover);

				// animatorSet.play(mover).with(fadeIn).after(fadeOut);
				animatorSetMenu.start();
			} else {
				// Call commit()

			}

			imgsettings.setImageDrawable(getResources().getDrawable(
					R.drawable.settingsblack));

		} else {

			isMenuPressed = false;
			// rl.setVisibility(RelativeLayout.GONE);

			// Check if we're running on GingerBread or above
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				// This will move the images grid up
				ObjectAnimator gridMover = ObjectAnimator.ofFloat(rl,
						"translationY", -1000f, 0f);
				gridMover.setDuration(1500);
				// ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
				// "alpha", 0f, 1f);
				// fadeIn.setDuration(2000);
				AnimatorSet animatorSetGrid = new AnimatorSet();
				animatorSetGrid.play(gridMover);

				// animatorSet.play(mover).with(fadeIn).after(fadeOut);
				animatorSetGrid.start();

				// This will move the menu up
				ObjectAnimator menuMover = ObjectAnimator.ofFloat(rll,
						"translationY", 0f, 1000f);
				menuMover.setDuration(1500);
				// ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
				// "alpha", 0f, 1f);
				// fadeIn.setDuration(2000);
				AnimatorSet animatorSetMenu = new AnimatorSet();
				animatorSetMenu.play(menuMover);

				// animatorSet.play(mover).with(fadeIn).after(fadeOut);
				animatorSetMenu.start();
			} else {
				// Call commit()

			}

			imgsettings.setImageDrawable(getResources().getDrawable(
					R.drawable.settings));

		}

		return super.onMenuOpened(featureId, menu);

	}

	@Override
	public void onBackPressed() {

		finish();

		// if (isMenuPressed == true) {
		// isMenuPressed = false;
		//
		// // rl.setVisibility(RelativeLayout.GONE);
		//
		// // Check if we're running on GingerBread or above
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		// // This will move the images grid up
		// ObjectAnimator gridMover = ObjectAnimator.ofFloat(rl,
		// "translationY", -1000f, 0f);
		// gridMover.setDuration(1500);
		// // ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetGrid = new AnimatorSet();
		// animatorSetGrid.play(gridMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetGrid.start();
		//
		// // This will move the menu up
		// ObjectAnimator menuMover = ObjectAnimator.ofFloat(rll,
		// "translationY", 0f, 1000f);
		// menuMover.setDuration(1500);
		// // ObjectAnimator fadeIn = ObjectAnimator.ofFloat(rll,
		// // "alpha", 0f, 1f);
		// // fadeIn.setDuration(2000);
		// AnimatorSet animatorSetMenu = new AnimatorSet();
		// animatorSetMenu.play(menuMover);
		//
		// // animatorSet.play(mover).with(fadeIn).after(fadeOut);
		// animatorSetMenu.start();
		//
		// } else {
		// // Call commit()
		//
		// }
		//
		// imgsettings.setImageDrawable(getResources().getDrawable(
		// R.drawable.settings));
		//
		// } else {
		// super.onBackPressed();
		// }

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

	public void blurMe(View v) {
		Bitmap bmpBlured = fastblur(bmpMain, 10);
		Drawable d = new BitmapDrawable(getResources(), bmpBlured);

		// Check if we're running on GingerBread or above
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			rl.setBackground(d);
		} else {
			// Call commit()
			rl.setBackgroundDrawable(d);

		}

	}

	public void openkaro(View v) {
		selectedImageIndex = 0;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlFirst

				+ "");

		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlFirst;

		startActivity(i);

		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

		// Toast.makeText(getBaseContext(),
		// "URL is  " + BeautifyGridHolder.urlFirst, Toast.LENGTH_LONG)
		// .show();
		// Toast.makeText(getBaseContext(),
		// "URL is  " + BeautifyGridHolder.urlFifth, Toast.LENGTH_LONG)
		// .show();
		// Toast.makeText(getBaseContext(),
		// "URL is  " + BeautifyGridHolder.urlTenth, Toast.LENGTH_LONG)
		// .show();

	}

	public void openkaro2(View v) {
		selectedImageIndex = 1;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlSecond
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlSecond;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro3(View v) {
		selectedImageIndex = 2;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlThird
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlThird;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro4(View v) {
		selectedImageIndex = 3;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlFourth
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlFourth;

		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro5(View v) {
		selectedImageIndex = 4;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlFifth
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlFifth;

		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro6(View v) {
		selectedImageIndex = 5;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlSixth
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlSixth;
		startActivity(i);
	}

	public void openkaro7(View v) {
		selectedImageIndex = 6;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlSeventh
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlSeventh;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro8(View v) {
		selectedImageIndex = 7;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlEight
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlEight;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro9(View v) {
		selectedImageIndex = 8;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlNinth
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlNinth;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void openkaro10(View v) {
		selectedImageIndex = 9;
		Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
		new BeautiplyAsyncTask(getBaseContext()).execute(
				"SET_MAIN_SCREEN_IMAGE", "large", BeautifyGridHolder.urlTenth
						+ "");
		ForthActivity.fullScreenImageUrl = BeautifyGridHolder.urlTenth;
		startActivity(i);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	// public void onWork(View v) {
	//
	// // CMCNotificationGenerator cng = new CMCNotificationGenerator(
	// // getApplicationContext());
	// //
	// // PendingIntent pi = PendingIntent.getActivity(
	// // getApplicationContext(),
	// // 0,
	// // new Intent(android.content.Intent.ACTION_VIEW, Uri
	// // .parse("http://www.cmcdelhi.com")), 0);
	// //
	// // cng.generateNotification("Happy Diwali",
	// // "CMC Delhi Wishes you a warm and prosperous diwali", pi, 0,
	// // true, true);
	//
	// BeautiplyToastGenerator btg = new BeautiplyToastGenerator(this);
	// // btg.getToast("Apple is REd").show();
	// btg.getBeautiplyShakeToast("Apple is red ").show();
	// }

	// switch1 is used for starting and stopping shake service
	// public void onSwitchClicked(View view) {
	// switch (view.getId()) {
	// case R.id.switch1:
	//
	// if (switch1.isChecked()) {
	//
	// try {
	// u = bu.loadUser();
	// u.setBeautiplyShakeOn(true);
	// bu.saveUser(u);
	// startService(new Intent(getBaseContext(),
	// BeautiplyShakeService.class));
	// BeautiplyToastGenerator btgyl = new BeautiplyToastGenerator(
	// getBaseContext());
	// btgyl.getBeautiplyShakeToast("Beautiply shake is on")
	// .show();
	//
	// } catch (JSONException e) {
	//
	// e.printStackTrace();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	//
	// } else {
	//
	// // switch1.setChecked(false);
	// try {
	// u = bu.loadUser();
	// u.setBeautiplyShakeOn(false);
	// bu.saveUser(u);
	// stopService(new Intent(getBaseContext(),
	// BeautiplyShakeService.class));
	//
	// BeautiplyToastGenerator btgyl = new BeautiplyToastGenerator(
	// getBaseContext());
	// btgyl.getBeautiplyShakeToast("Beautiply shake is off")
	// .show();
	//
	// } catch (JSONException e) {
	//
	// e.printStackTrace();
	// } catch (IOException e) {
	//
	// e.printStackTrace();
	// }
	//
	// super.onDestroy();
	// }
	// break;
	//
	// }
	// }

	@Override
	protected void onPause() {
		// // starting internet checker service
		// Intent internetChekerService = new Intent(getBaseContext(),
		// InternetCheckerService.class);
		// stopService(internetChekerService);
		super.onPause();
	}

	@Override
	protected void onStart() {
		WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
		Display dp = wm.getDefaultDisplay();
		int height = dp.getHeight();
		int width = dp.getWidth();
		// Toast.makeText(getBaseContext(), "The height is " + height,
		// Toast.LENGTH_SHORT).show();
		// Toast.makeText(getBaseContext(), "The width is " + width,
		// Toast.LENGTH_SHORT).show();
		Log.i("Cmc Delhi Beautiply", "Height is");
		Log.i("Cmc Delhi Beautiply", "Weight is");

		super.onStart();
	}

}
