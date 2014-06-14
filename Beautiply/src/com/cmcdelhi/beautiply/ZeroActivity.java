package com.cmcdelhi.beautiply;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cmcdelhi.beautiply.background.BeautiplyAsyncTask;
import com.cmcdelhi.beautiply.background.BeautiplyMessageAyncTask;
import com.cmcdelhi.beautiply.background.BeautiplyWallPaperSetterService;
import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautifyGridHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;
import com.cmcdelhi.beautiply.utility.ZeroActivityGridHolder;

@SuppressLint("NewApi")
public class ZeroActivity extends Activity {

	TextView beautiplyTitle, goBeautiplied, goBeautipliedDescription,
			editorsPick, imsexy, trending, longpresstext;

	public static TextView promoText;

	Typeface tf, robo;
	BeautiplyUtility bu;
	ScrollView thirdlayout;
	LinearLayout firstlayout, secondlayout, forthlayout;
	ImageView imgPlay, imgsettings, imgAppShare;
	User u;
	boolean isMenuPressed = false;

	String TAG = "CMC DELHI BEAUTIPLY";

	RelativeLayout internetheader;
	public static RelativeLayout promoLayout;
	static public ImageView zeroActivityImageA, zeroActivityImageB,
			zeroActivityImageD;

	HorizontalScrollView horizontalScrollView;
	public static LinearLayout dashboard, zeroActivityImageC;

	LinearLayout.LayoutParams lp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zero);
		
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

		// beautiply utility object
		bu = new BeautiplyUtility(getBaseContext());

		// loading the fonts
		tf = Typeface.createFromAsset(getAssets(), "fonts/amplify.ttf");
		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");

		// setting up font and title
		beautiplyTitle = (TextView) findViewById(R.id.beautiplyTitle);
		beautiplyTitle.setTypeface(tf);
		beautiplyTitle.setTextColor(Color.WHITE);

		goBeautiplied = (TextView) findViewById(R.id.goBeautiplied);
		goBeautiplied.setTypeface(tf);
		goBeautiplied.setTextColor(Color.BLACK);

		goBeautipliedDescription = (TextView) findViewById(R.id.goBeautipliedDescription);
		goBeautipliedDescription.setTypeface(robo);
		goBeautipliedDescription.setTextColor(Color.BLACK);
		firstlayout = (LinearLayout) findViewById(R.id.firstlayout);
		secondlayout = (LinearLayout) findViewById(R.id.secondlayout);
		forthlayout = (LinearLayout) findViewById(R.id.forthlayout);
		thirdlayout = (ScrollView) findViewById(R.id.thirdlayout);

		editorsPick = (TextView) findViewById(R.id.editorsPick);
		editorsPick.setTypeface(robo);
		editorsPick.setTextColor(Color.BLACK);

		imsexy = (TextView) findViewById(R.id.imsexy);
		imsexy.setTypeface(robo);
		imsexy.setTextColor(Color.BLACK);

		imgPlay = (ImageView) findViewById(R.id.imgPlay);
		imgsettings = (ImageView) findViewById(R.id.imgsettings);
		imgAppShare = (ImageView) findViewById(R.id.imgAppShare);

		trending = (TextView) findViewById(R.id.trending);
		trending.setTypeface(robo);
		trending.setTextColor(Color.BLACK);

		longpresstext = (TextView) findViewById(R.id.longpresstext);
		longpresstext.setTypeface(robo);
		longpresstext.setTextColor(Color.WHITE);

		promoText = (TextView) findViewById(R.id.promoText);
		promoText.setTypeface(robo);
		promoText.setTextColor(Color.WHITE);

		// refrencing the imageView
		zeroActivityImageA = (ImageView) findViewById(R.id.zeroActivityImageA);
		zeroActivityImageB = (ImageView) findViewById(R.id.zeroActivityImageB);
		// zeroActivityImageC = (ImageView)
		// findViewById(R.id.zeroActivityImageC);
		zeroActivityImageD = (ImageView) findViewById(R.id.zeroActivityImageD);

		zeroActivityImageC = (LinearLayout) findViewById(R.id.zeroActivityImageC);
		zeroActivityImageC.setBackgroundDrawable(getWallpaper());

		// referencing the dashboard(main layout)
		dashboard = (LinearLayout) findViewById(R.id.dashboard);
		dashboard.setBackgroundDrawable(getWallpaper());

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

			// / Setting the Play-Stop Button based on wallpaper setter service
			// running state

			if (u.isWallpaperSetterServiceRunning() == true) {
				imgPlay.setImageDrawable(getResources().getDrawable(
						R.drawable.stop));

			} else {
				imgPlay.setImageDrawable(getResources().getDrawable(
						R.drawable.play));

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
		} catch (Exception e) {
			showDialog(0);
			try {
				bu.createFirstTimeUser();
			} catch (JSONException e1) {

				e1.printStackTrace();
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

		// ///blurring
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
			dashboard.setBackground(dBlur);
		} else {
			dashboard.setBackgroundDrawable(dBlur);
		}

		zeroActivityImageA.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					firstlayout.setBackgroundColor(Color.LTGRAY);

				}
				case MotionEvent.ACTION_UP: {
					Intent i = new Intent(
							"com.cmcdelhi.beautiply.FirstActivity");
					startActivityForResult(i, 2);

				}

					break;

				default:
					break;
				}
				return false;
			}
		});
		zeroActivityImageB.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					secondlayout.setBackgroundColor(Color.LTGRAY);

				}
				case MotionEvent.ACTION_UP: {

					Intent i = new Intent(
							"com.cmcdelhi.beautiply.ForthActivity");
					new BeautiplyAsyncTask(getBaseContext()).execute(
							"SET_MAIN_SCREEN_IMAGE", "large",
							ZeroActivityGridHolder.zeroActivityImageBLink + "");
					ForthActivity.fullScreenImageUrl = ZeroActivityGridHolder.zeroActivityImageBLink;

					startActivityForResult(i, 1);

				}

					break;

				default:
					break;
				}
				return false;
			}
		});
		zeroActivityImageC.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					longpresstext
							.setText("Hold the image long \n while we get it");

				}
				case MotionEvent.ACTION_UP: {
					// longpresstext.setText("");

				}

					break;

				default:
					break;
				}
				return false;
			}
		});

		zeroActivityImageC.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				Intent i = new Intent("com.cmcdelhi.beautiply.ForthActivity");
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						ZeroActivityGridHolder.zeroActivityImageCLink + "");
				ForthActivity.fullScreenImageUrl = ZeroActivityGridHolder.zeroActivityImageCLink;

				startActivityForResult(i, 4);
				return false;
			}
		});

		zeroActivityImageD.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					forthlayout.setBackgroundColor(Color.LTGRAY);

				}
				case MotionEvent.ACTION_UP: {
					Intent i = new Intent(
							"com.cmcdelhi.beautiply.ForthActivity");
					new BeautiplyAsyncTask(getBaseContext()).execute(
							"SET_MAIN_SCREEN_IMAGE", "large",
							ZeroActivityGridHolder.zeroActivityImageDLink + "");
					ForthActivity.fullScreenImageUrl = ZeroActivityGridHolder.zeroActivityImageDLink;

					startActivityForResult(i, 3);

				}

					break;

				default:
					break;
				}
				return false;
			}
		});
		// Bitmap bmpBlured = fastblur(get, 10);

		// Drawable d = new BitmapDrawable(getResources(), bmpBlured);
		//
		// // Check if we're running on GingerBread or above
		// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
		// rl.setBackground(d);
		// } else {
		// // Call commit()
		// rl.setBackgroundDrawable(d);
		//
		// }

		// refering the internet hearder
		internetheader = (RelativeLayout) findViewById(R.id.internetheader);
		// refreing promolayout
		promoLayout = (RelativeLayout) findViewById(R.id.promoLayout);

		// refrencing horizontal scroll view
		horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
		horizontalScrollView.scrollTo(500, 0);

		// if internet is available then load haeader
		if (bu.isNetworkAvailable() == true) {
			internetheader.setVisibility(RelativeLayout.GONE);
			new BeautiplyAsyncTask(getBaseContext()).execute("SET_PROMO",
					"large", "PROMO_URL");

			new BeautiplyAsyncTask(getBaseContext()).execute(
					"SET_ZEROACTIVITY_IMAGE_A", "thumb", "NOTHING");

			new BeautiplyAsyncTask(getBaseContext()).execute(
					"SET_ZEROACTIVITY_IMAGE_C", "large", "NOTHING");

			new BeautiplyMessageAyncTask().execute("");
		} else {

			// load the stored image

		}
		imgsettings.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgsettings.setImageDrawable(getResources().getDrawable(
							R.drawable.settingsblack));

				}
				case MotionEvent.ACTION_UP: {
					Intent i = new Intent("com.cmcdelhi.beautiply.MenuActivity");
					startActivityForResult(i, 3);
				}

					break;

				default:
					break;
				}
				return false;
			}
		});

		imgPlay.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {

					break;
				}
				case MotionEvent.ACTION_UP: {

					try {
						u = bu.loadUser();
						if (u.isWallpaperSetterServiceRunning() == true) {
							Intent beautifyWallPaperSetterService = new Intent(
									getBaseContext(),
									BeautiplyWallPaperSetterService.class);
							stopService(beautifyWallPaperSetterService);
							imgPlay.setImageDrawable(getResources()
									.getDrawable(R.drawable.play));

							u.setWallpaperSetterServiceRunning(false);
							bu.saveUser(u);

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(), "Beautiply has stopped")
									.show();

						} else {
							Intent beautifyWallPaperSetterService = new Intent(
									getBaseContext(),
									BeautiplyWallPaperSetterService.class);
							startService(beautifyWallPaperSetterService);
							imgPlay.setImageDrawable(getResources()
									.getDrawable(R.drawable.stop));

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
							} else if (obtainedTimeInterval == 7 * 24 * 60 * 60
									* 1000) {
								scheduledTime = "Every Week";

							} else if (obtainedTimeInterval == -1) {
								scheduledTime = "On unlock";

							}

							u.setWallpaperSetterServiceRunning(true);
							bu.saveUser(u);

							BeautiplyToastGenerator.getBeautiplyShakeToast(
									getBaseContext(),
									"Beautiply has started and will change the wallaper at every "
											+ scheduledTime).show();

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

		imgAppShare.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent me) {

				switch (me.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgAppShare.setImageDrawable(getResources().getDrawable(
							R.drawable.shareblack));

					Intent sendIntent = new Intent();
					sendIntent.setAction(Intent.ACTION_SEND);
					sendIntent
							.putExtra(
									Intent.EXTRA_TEXT,
									"Beautiply Wallpaper Manager at : https://play.google.com/store/apps/details?id=com.cmcdelhi.beautiply");
					sendIntent.setType("text/plain");

					startActivity(sendIntent);

				}
				case MotionEvent.ACTION_UP: {

				}

					break;

				default:
					break;
				}

				return false;
			}
		});

	}

	// Resetting the Card Colors
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		firstlayout.setBackgroundResource(R.drawable.nintydeggradiantstatic);
		secondlayout
				.setBackgroundResource(R.drawable.twoseventydeggradiantstatic);
		forthlayout
				.setBackgroundResource(R.drawable.twoseventydeggradiantstatic);

		imgsettings.setImageDrawable(getResources().getDrawable(
				R.drawable.settings));

		imgAppShare.setImageDrawable(getResources().getDrawable(
				R.drawable.share));

		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher);
			builder.setTitle("Terms and Conditions");
			builder.setMessage("The images used in the application may contain explicit content.All the images supplied by the application are the copyright property of their respective owner.CMC is not resposible for any copyright issues.Information collected through this application is kept confidential and is not passed to third party organizations for marketing or promotional purposes. If you supply us with your email address you may receive occasional emails from us relating to the services we provide. If you do not wish to receive such e-mails please inform us at info@cmdelhi.com and you will not receive any future emails from us.Due to overcautious spam filters, our email might not always reach you. To ensure that our enquiries are served diligently, we send a follow-up email (ashish.arora@cmcdelhi.com) in case we do not receive a read report or a reply to our email.");
			builder.setPositiveButton("I Agree",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {

						}
					});
			return builder.create();

		default:
			break;
		}
		return super.onCreateDialog(id);
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
