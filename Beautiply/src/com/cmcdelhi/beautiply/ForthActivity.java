package com.cmcdelhi.beautiply;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmcdelhi.beautiply.SimpleGestureFilter.SimpleGestureListener;
import com.cmcdelhi.beautiply.background.BeautiplyAsyncTask;
import com.cmcdelhi.beautiply.background.InternetCheckerService;
import com.cmcdelhi.beautiply.toast.BeautiplyToastGenerator;
import com.cmcdelhi.beautiply.utility.BeautifyGridHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.User;

@SuppressLint("NewApi")
public class ForthActivity extends Activity implements OnTouchListener,
		SimpleGestureListener {
	private SimpleGestureFilter detector;
	Matrix drawMatrix;
	float lastFocusX;
	float lastFocusY;
	Typeface tf, robo;
	LinearLayout ll;
	static RelativeLayout header, footer;
	TextView dizzyTitle, imageView1textView1, noops;
	// ScrollView r1;
	public static Bitmap fullScreenImageBitmap;
	public static RelativeLayout internetheader;
	ImageView imgratinggood, imgratingbad;

	ImageView imgdownload, imgset, imgshare, imgback, imgnext, imgprevious;
	public static ProgressBar pb;
	public static ImageView bigBeauty;
	boolean fullScreenState;
	public static String fullScreenImageUrl = "";
	BeautiplyUtility beautiplyUtility;
	WallpaperManager wm;

	User u;
	Bitmap bmpMain;
	int currentImageIndex;

	// //////////Initializers for Zoom in Zoom Out//////////////////
	private static final String TAG = "Touch";
	@SuppressWarnings("unused")
	private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;

	// These matrices will be used to scale points of the image
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();

	// The 3 states (events) which the user is trying to perform
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;

	// these PointF objects are used to record the point(s) the user is touching
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;

	// ////////////////////////

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forth);
		detector = new SimpleGestureFilter(this, this);
		// // starting internet checker service
		// Intent internetChekerService = new Intent(getBaseContext(),
		// InternetCheckerService.class);
		// startService(internetChekerService);

		beautiplyUtility = new BeautiplyUtility(getBaseContext());

		currentImageIndex = FirstActivity.selectedImageIndex;

		// loading the user object
		try {
			u = beautiplyUtility.loadUser();

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			Toast.makeText(getBaseContext(),
					"First Time User .Creating configutaion", Toast.LENGTH_LONG)
					.show();
			try {
				beautiplyUtility.createFirstTimeUser();
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		robo = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
		noops = (TextView) findViewById(R.id.noops);
		noops.setTypeface(robo);
		internetheader = (RelativeLayout) findViewById(R.id.internetheader);

		pb = (ProgressBar) findViewById(R.id.progressBar1);
		tf = Typeface.createFromAsset(getAssets(), "fonts/amplify.ttf");
		ll = (LinearLayout) findViewById(R.id.ll);
		header = (RelativeLayout) findViewById(R.id.header);
		// r1 = (ScrollView) findViewById(R.id.myMainContainer);
		footer = (RelativeLayout) findViewById(R.id.footer);
		dizzyTitle = (TextView) findViewById(R.id.dizzyTitle);
		dizzyTitle.setTypeface(tf);
		dizzyTitle.setTextColor(Color.WHITE);
		imgratingbad = (ImageView) findViewById(R.id.imageViewratingbad);
		imgratinggood = (ImageView) findViewById(R.id.imageViewratinggood);
		imgdownload = (ImageView) findViewById(R.id.imgdownload);
		imgback = (ImageView) findViewById(R.id.back);
		imgnext = (ImageView) findViewById(R.id.imgnext);
		imgprevious = (ImageView) findViewById(R.id.imgprevious);
		imgset = (ImageView) findViewById(R.id.imgset);
		imgshare = (ImageView) findViewById(R.id.imgshare);
		bigBeauty = (ImageView) findViewById(R.id.bigBeauty);
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		pb.setVisibility(ProgressBar.VISIBLE);
		// header.setVisibility(RelativeLayout.GONE);
		footer.setVisibility(RelativeLayout.GONE);
		// imgratinggood.setVisibility(ImageView.INVISIBLE);
		// imgratingbad.setVisibility(ImageView.INVISIBLE);

		// checking for internet
		if (beautiplyUtility.isNetworkAvailable() == true) {
			internetheader.setVisibility(RelativeLayout.GONE);
		} else {
			imgshare.setEnabled(false);
			imgratingbad.setEnabled(false);
			imgratinggood.setEnabled(false);
			imgdownload.setEnabled(false);
			imgnext.setEnabled(false);
			imgprevious.setEnabled(false);
			imgset.setEnabled(false);

		}

		bigBeauty.setOnTouchListener(this);

		imgshare.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgshare.setImageDrawable(getResources().getDrawable(
							R.drawable.shareblack));
					break;
				}
				case MotionEvent.ACTION_UP: {

					imgshare.setImageDrawable(getResources().getDrawable(
							R.drawable.share));

					// Saving the File to SD Card and then sharing it

					FileOutputStream fileOutputStream = null;
					File path = Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
					File file = new File(path, "BeautiplyFullScreen.jpg");
					try {
						fileOutputStream = new FileOutputStream(file);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					BufferedOutputStream bos = new BufferedOutputStream(
							fileOutputStream);
					fullScreenImageBitmap
							.compress(CompressFormat.JPEG, 90, bos);
					try {
						bos.flush();
						bos.close();
						fileOutputStream.flush();
						fileOutputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

					Uri imageUri = Uri.parse("file://" + file.getAbsolutePath());

					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND);
					shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
					shareIntent.setType("image/jpeg");
					startActivity(Intent.createChooser(shareIntent,
							"Share Beautiply Image"));

					break;
				}
				}
				return true;
			}
		});

		// Imgnext is used for showing next images

		imgnext.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgnext.setImageDrawable(getResources().getDrawable(
							R.drawable.imgnextblack));
					break;
				}
				case MotionEvent.ACTION_UP: {
					pb.setVisibility(ProgressBar.VISIBLE);
					imgratingbad.setImageDrawable(getResources().getDrawable(
							R.drawable.ratingbad));
					imgratinggood.setImageDrawable(getResources().getDrawable(
							R.drawable.ratinggood));

					if (currentImageIndex <= 8) {
						currentImageIndex++;
					} else {
						currentImageIndex = 0;
					}

					switch (currentImageIndex) {
					case 0:
						currentImageIndex = 0;
						fullScreenImageUrl = BeautifyGridHolder.urlFirst;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFirst + "");
						break;
					case 1:
						currentImageIndex = 1;
						fullScreenImageUrl = BeautifyGridHolder.urlSecond;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSecond + "");
						break;
					case 2:
						currentImageIndex = 2;
						fullScreenImageUrl = BeautifyGridHolder.urlThird;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlThird + "");
					case 3:
						currentImageIndex = 3;
						fullScreenImageUrl = BeautifyGridHolder.urlFourth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFourth + "");
						break;
					case 4:
						currentImageIndex = 4;
						fullScreenImageUrl = BeautifyGridHolder.urlFifth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFifth + "");
						break;
					case 5:
						currentImageIndex = 5;
						fullScreenImageUrl = BeautifyGridHolder.urlSixth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSixth + "");
						break;
					case 6:
						currentImageIndex = 6;
						fullScreenImageUrl = BeautifyGridHolder.urlSeventh;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSeventh + "");
						break;
					case 7:
						currentImageIndex = 7;
						fullScreenImageUrl = BeautifyGridHolder.urlEight;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlEight + "");
						break;
					case 8:
						currentImageIndex = 8;
						fullScreenImageUrl = BeautifyGridHolder.urlNinth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlNinth + "");
						break;
					case 9:
						currentImageIndex = 9;
						fullScreenImageUrl = BeautifyGridHolder.urlTenth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlTenth + "");

						break;

					default:
						break;
					}

					// setting the like or dislike state of the thump up or
					// down
					// based on
					// user likeUrlList or disLikeUrlList
					try {
						u = beautiplyUtility.loadUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (String likeUrl : u.getUserLikesURL()) {

						if (likeUrl.equals(fullScreenImageUrl)) {
							imgratinggood.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratinggoodblack));
						}

					}

					for (String dislikeUrl : u.getUserDisLikesURL()) {

						if (dislikeUrl.equals(fullScreenImageUrl)) {
							imgratingbad.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratingbadblack));
						}

					}
					imgnext.setImageDrawable(getResources().getDrawable(
							R.drawable.imgnext));
					break;
				}
				}
				return true;
			}
		});

		// Imgprevious is used for showing previous image
		imgprevious.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgprevious.setImageDrawable(getResources().getDrawable(
							R.drawable.imgpreviousblack));
					break;
				}
				case MotionEvent.ACTION_UP: {
					pb.setVisibility(ProgressBar.VISIBLE);
					imgratingbad.setImageDrawable(getResources().getDrawable(
							R.drawable.ratingbad));
					imgratinggood.setImageDrawable(getResources().getDrawable(
							R.drawable.ratinggood));

					if (currentImageIndex >= 0) {
						currentImageIndex--;
					} else {
						currentImageIndex = 9;
					}

					switch (currentImageIndex) {
					case 0:
						currentImageIndex = 0;
						fullScreenImageUrl = BeautifyGridHolder.urlFirst;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFirst + "");
						break;
					case 1:
						currentImageIndex = 1;
						fullScreenImageUrl = BeautifyGridHolder.urlSecond;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSecond + "");
						break;
					case 2:
						currentImageIndex = 2;
						fullScreenImageUrl = BeautifyGridHolder.urlThird;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlThird + "");
					case 3:
						currentImageIndex = 3;
						fullScreenImageUrl = BeautifyGridHolder.urlFourth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFourth + "");
						break;
					case 4:
						currentImageIndex = 4;
						fullScreenImageUrl = BeautifyGridHolder.urlFifth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlFifth + "");
						break;
					case 5:
						currentImageIndex = 5;
						fullScreenImageUrl = BeautifyGridHolder.urlSixth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSixth + "");
						break;
					case 6:
						currentImageIndex = 6;
						fullScreenImageUrl = BeautifyGridHolder.urlSeventh;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlSeventh + "");
						break;
					case 7:
						currentImageIndex = 7;
						fullScreenImageUrl = BeautifyGridHolder.urlEight;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlEight + "");
						break;
					case 8:
						currentImageIndex = 8;
						fullScreenImageUrl = BeautifyGridHolder.urlNinth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlNinth + "");
						break;
					case 9:
						currentImageIndex = 9;
						fullScreenImageUrl = BeautifyGridHolder.urlTenth;
						new BeautiplyAsyncTask(getBaseContext()).execute(
								"SET_MAIN_SCREEN_IMAGE", "large",
								BeautifyGridHolder.urlTenth + "");

						break;

					default:
						break;
					}

					// setting the like or dislike state of the thump up or down
					// based on
					// user likeUrlList or disLikeUrlList

					imgprevious.setImageDrawable(getResources().getDrawable(
							R.drawable.imgprevious));

					// setting the like or dislike state of the thump up or
					// down
					// based on
					// user likeUrlList or disLikeUrlList
					try {
						u = beautiplyUtility.loadUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					for (String likeUrl : u.getUserLikesURL()) {

						if (likeUrl.equals(fullScreenImageUrl)) {
							imgratinggood.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratinggoodblack));
						}

					}

					for (String dislikeUrl : u.getUserDisLikesURL()) {

						if (dislikeUrl.equals(fullScreenImageUrl)) {
							imgratingbad.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratingbadblack));
						}

					}

					break;
				}
				}
				return true;
			}
		});

		// Imgback is used to go the previous screen
		imgback.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgback.setImageDrawable(getResources().getDrawable(
							R.drawable.back));
					break;
				}
				case MotionEvent.ACTION_UP: {

					imgback.setImageDrawable(getResources().getDrawable(
							R.drawable.backblack));
					finish();
					overridePendingTransition(R.anim.push_right_in,
							R.anim.push_right_out);

					break;
				}
				}
				return true;
			}
		});

		// Imgdownload is used for downloading images in memory card
		imgdownload.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgdownload.setImageDrawable(getResources().getDrawable(
							R.drawable.downloadblack));
					Toast.makeText(getBaseContext(), "Downloading image..",
							Toast.LENGTH_SHORT).show();
					break;
				}
				case MotionEvent.ACTION_UP: {

					boolean result = beautiplyUtility
							.downloadAndSaveBitmap(fullScreenImageBitmap);
					sendBroadcast(new Intent(
							Intent.ACTION_MEDIA_MOUNTED,
							Uri.parse("file://sdcard/Beautiply"
									+ Environment.getExternalStorageDirectory())));
					createNotification();
					if (result == true) {
						// sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
						// Uri.parse("file://" +
						// Environment.getExternalStorageDirectory())));
					}

					imgdownload.setImageDrawable(getResources().getDrawable(
							R.drawable.download));

					break;
				}
				}
				return true;
			}
		});

		// Imgset is used for setting up the current wallpaper

		imgset.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgset.setImageDrawable(getResources().getDrawable(
							R.drawable.wallblack));
					break;
				}
				case MotionEvent.ACTION_UP: {

					imgset.setImageDrawable(getResources().getDrawable(
							R.drawable.wall));

					boolean result = beautiplyUtility.setBitmapAsWallpaper(
							fullScreenImageBitmap, fullScreenImageUrl);

					if (result == true) {
						// Toast.makeText(getBaseContext(),
						// "Wallpaper changed  and saved ",
						// Toast.LENGTH_LONG).show();
						BeautiplyToastGenerator.getBeautiplyShakeToast(
								getBaseContext(), "Beautiplied !").show();
						// Bitmap bmpBlured = fastblur(bmpMain, 10);
						// Drawable d = new BitmapDrawable(getResources(),
						// bmpBlured);
						// ll.setBackground(d);
						// blurme(View v):

						// ssewtting the new wallpaper as background
						// r1.setBackgroundColor(Color.argb(200, 255, 255,
						// 255));
						footer.setBackgroundColor(Color.argb(50, 0, 0, 0));
						header.setBackgroundColor(Color.argb(50, 0, 0, 0));
						Bitmap bmpBlured = null;

						bmpBlured = fastblur(fullScreenImageBitmap, 17);

						Drawable dBlur = new BitmapDrawable(getResources(),
								bmpBlured);

						// Check if we're running on GingerBread or above
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							ll.setBackground(dBlur);
							if (FirstActivity.l1 != null) {
								FirstActivity.l1.setBackground(dBlur);
							}

						} else {
							ll.setBackgroundDrawable(dBlur);
							if (FirstActivity.l1 != null) {
								FirstActivity.l1.setBackgroundDrawable(dBlur);
							}

						}

					}

					break;
				}
				}
				return true;
			}
		});

		// Imgratingbad is used to dislike the image and it wont appear again

		imgratingbad.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgratingbad.setImageDrawable(getResources().getDrawable(
							R.drawable.ratingbadblack));
					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserDisLikesURL().contains(fullScreenImageUrl)) {

							u.getUserDisLikesURL().remove(fullScreenImageUrl);
							beautiplyUtility.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Dislike List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"url removed from dislike list");

							imgratingbad.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratingbad));

						} else {
							u.getUserDisLikesURL().add(fullScreenImageUrl);
							beautiplyUtility.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Saved --  DISLIKED Dislike List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"url saved in dislike list");

							imgratingbad.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratingbadblack));

							if (u.getUserLikesURL()
									.contains(fullScreenImageUrl)) {
								u.getUserLikesURL().remove(fullScreenImageUrl);
								beautiplyUtility.saveUser(u);
								imgratinggood.setImageDrawable(getResources()
										.getDrawable(R.drawable.ratinggood));
							}
						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});
		// Imgratinggood is used to like the image

		imgratinggood.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				switch (arg1.getAction()) {
				case MotionEvent.ACTION_DOWN: {
					imgratinggood.setImageDrawable(getResources().getDrawable(
							R.drawable.ratinggoodblack));
					break;
				}
				case MotionEvent.ACTION_UP: {

					try {

						if (u.getUserLikesURL().contains(fullScreenImageUrl)) {

							u.getUserLikesURL().remove(fullScreenImageUrl);
							beautiplyUtility.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Removed from Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"url removed from dislike list");

							imgratinggood.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratinggood));

						} else {
							u.getUserLikesURL().add(fullScreenImageUrl);
							beautiplyUtility.saveUser(u);

							// Toast.makeText(getBaseContext(),
							// "Url Saved --  LIKED Like List",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply",
									"url saved in dislike list");

							imgratinggood.setImageDrawable(getResources()
									.getDrawable(R.drawable.ratinggoodblack));

							if (u.getUserDisLikesURL().contains(
									fullScreenImageUrl)) {
								u.getUserDisLikesURL().remove(
										fullScreenImageUrl);
								beautiplyUtility.saveUser(u);
								imgratingbad.setImageDrawable(getResources()
										.getDrawable(R.drawable.ratingbad));
							}

						}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					break;
				}
				}
				return true;
			}
		});

		// setting the like or dislike state of the thump up or down based on
		// user likeUrlList or disLikeUrlList

		for (String likeUrl : u.getUserLikesURL()) {

			if (likeUrl.equals(fullScreenImageUrl)) {
				imgratinggood.setImageDrawable(getResources().getDrawable(
						R.drawable.ratinggoodblack));
			}

		}

		for (String dislikeUrl : u.getUserDisLikesURL()) {

			if (dislikeUrl.equals(fullScreenImageUrl)) {
				imgratingbad.setImageDrawable(getResources().getDrawable(
						R.drawable.ratingbadblack));
			}

		}

		// r1.setBackgroundColor(Color.argb(200, 255, 255, 255));
		footer.setBackgroundColor(Color.argb(50, 0, 0, 0));
		header.setBackgroundColor(Color.argb(50, 0, 0, 0));
		Bitmap bmpBlured = null;

		try {
			bmpBlured = fastblur(beautiplyUtility.loadBeautiplyHolder()
					.getCurrentWallpaper(), 17);
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

	public void blurme(View v) {

		Bitmap bmpBlured = fastblur(bmpMain, 10);
		Drawable d = new BitmapDrawable(getResources(), bmpBlured);

		// Check if we're running on GingerBread or above
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
			ll.setBackground(d);
		} else {
			// Call commit()
			ll.setBackgroundDrawable(d);

		}

	}

	public class FullScreenHider extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {

			try {
				Thread.sleep(1500);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			// header.setVisibility(RelativeLayout.VISIBLE);
			footer.setVisibility(RelativeLayout.VISIBLE);
			// imgratinggood.setVisibility(ImageView.VISIBLE);
			// imgratingbad.setVisibility(ImageView.VISIBLE);
			super.onPostExecute(result);
		}

	}

	// // Pressing the Back button//////////////////////>>>>>>>>>>>>

	@Override
	public void onBackPressed() {

		finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
		super.onBackPressed();
	}

	// ///////////For Image bigBeauty Zoom in Zoom out///////////////////

	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// ImageView view = (ImageView) v;
	// view.setScaleType(ImageView.ScaleType.MATRIX);
	// float scale;
	//
	// dumpEvent(event);
	// // Handle touch events here...
	//
	// switch (event.getAction() & MotionEvent.ACTION_MASK) {
	// case MotionEvent.ACTION_DOWN: // first finger down only
	// savedMatrix.set(matrix);
	// start.set(event.getX(), event.getY());
	// Log.d(TAG, "mode=DRAG"); // write to LogCat
	// mode = DRAG;
	// break;
	//
	// case MotionEvent.ACTION_UP: // first finger lifted
	//
	// case MotionEvent.ACTION_POINTER_UP: // second finger lifted
	//
	// mode = NONE;
	// Log.d(TAG, "mode=NONE");
	// break;
	//
	// case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down
	//
	// oldDist = spacing(event);
	// Log.d(TAG, "oldDist=" + oldDist);
	// if (oldDist > 5f) {
	// savedMatrix.set(matrix);
	// midPoint(mid, event);
	// mode = ZOOM;
	// Log.d(TAG, "mode=ZOOM");
	// }
	// break;
	//
	// case MotionEvent.ACTION_MOVE:
	//
	// if (mode == DRAG) {
	// matrix.set(savedMatrix);
	// matrix.postTranslate(event.getX() - start.x, event.getY()
	// - start.y); // create the transformation in the matrix
	// // of points
	// } else if (mode == ZOOM) {
	// // pinch zooming
	// float newDist = spacing(event);
	// Log.d(TAG, "newDist=" + newDist);
	// if (newDist > 5f) {
	// matrix.set(savedMatrix);
	// scale = newDist / oldDist; // setting the scaling of the
	// // matrix...if scale > 1 means
	// // zoom in...if scale < 1 means
	// // zoom out
	// matrix.postScale(scale, scale, mid.x, mid.y);
	// }
	// }
	// break;
	// }
	//
	// view.setImageMatrix(matrix); // display the transformation on screen
	//
	// return true; // indicate event was handled
	// }
	//
	// /*
	// *
	// --------------------------------------------------------------------------
	// * Method: spacing Parameters: MotionEvent Returns: float Description:
	// * checks the spacing between the two fingers on touch
	// * ----------------------------------------------------
	// */
	//
	// private float spacing(MotionEvent event) {
	// float x = event.getX(0) - event.getX(1);
	// float y = event.getY(0) - event.getY(1);
	// return FloatMath.sqrt(x * x + y * y);
	// }
	//
	// @Override
	// protected void onPause() {
	// // // starting internet checker service
	// // Intent internetChekerService = new Intent(getBaseContext(),
	// // InternetCheckerService.class);
	// // stopService(internetChekerService);
	// super.onPause();
	// }
	//
	// /*
	// *
	// --------------------------------------------------------------------------
	// * Method: midPoint Parameters: PointF object, MotionEvent Returns: void
	// * Description: calculates the midpoint between the two fingers
	// * ------------------------------------------------------------
	// */
	//
	// private void midPoint(PointF point, MotionEvent event) {
	// float x = event.getX(0) + event.getX(1);
	// float y = event.getY(0) + event.getY(1);
	// point.set(x / 2, y / 2);
	// }
	//
	// /** Show an event in the LogCat view, for debugging */
	// private void dumpEvent(MotionEvent event) {
	// String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
	// "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
	// StringBuilder sb = new StringBuilder();
	// int action = event.getAction();
	// int actionCode = action & MotionEvent.ACTION_MASK;
	// sb.append("event ACTION_").append(names[actionCode]);
	//
	// if (actionCode == MotionEvent.ACTION_POINTER_DOWN
	// || actionCode == MotionEvent.ACTION_POINTER_UP) {
	// sb.append("(pid ").append(
	// action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
	// sb.append(")");
	// }
	//
	// sb.append("[");
	// for (int i = 0; i < event.getPointerCount(); i++) {
	// sb.append("#").append(i);
	// sb.append("(pid ").append(event.getPointerId(i));
	// sb.append(")=").append((int) event.getX(i));
	// sb.append(",").append((int) event.getY(i));
	// if (i + 1 < event.getPointerCount())
	// sb.append(";");
	// }
	//
	// sb.append("]");
	// Log.d("Touch Events ---------", sb.toString());
	// }
	private void createNotification() {
		String a = beautiplyUtility.hello();
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int unique_id = 1;

		Intent nintent = new Intent(a);
		nintent.setType("image/*");
		nintent.setAction(Intent.ACTION_PICK);

		// nintent.setClass(this, FirstActivity.class);
		PendingIntent pin = PendingIntent.getActivity(getApplicationContext(),
				0, nintent, 0);
		String title = "Beautiply";
		String body = "Image saved";
		Notification n = new Notification(R.drawable.ic_launcher, body,
				System.currentTimeMillis());
		n.contentIntent = pin;
		n.setLatestEventInfo(getApplicationContext(), title, body, pin);

		n.defaults = Notification.DEFAULT_ALL;
		nm.notify(unique_id, n);

	}

	// @Override
	// public boolean onTouch(View v, MotionEvent event) {
	// ImageView view = (ImageView) v;
	//
	// // Dump touch event to log
	// dumpEvent(event);
	//
	// // Handle touch events here...
	// switch (event.getAction() & MotionEvent.ACTION_MASK) {
	// case MotionEvent.ACTION_DOWN:
	// savedMatrix.set(matrix);
	// start.set(event.getX(), event.getY());
	// Log.d(TAG, "mode=DRAG");
	// mode = DRAG;
	// break;
	// case MotionEvent.ACTION_POINTER_DOWN:
	// oldDist = spacing(event);
	// Log.d(TAG, "oldDist=" + oldDist);
	// if (oldDist > 10f) {
	// savedMatrix.set(matrix);
	// midPoint(mid, event);
	// mode = ZOOM;
	// Log.d(TAG, "mode=ZOOM");
	// }
	// break;
	// case MotionEvent.ACTION_UP:
	// case MotionEvent.ACTION_POINTER_UP:
	// mode = NONE;
	// Log.d(TAG, "mode=NONE");
	// break;
	// case MotionEvent.ACTION_MOVE:
	// if (mode == DRAG) {
	// // ...
	// matrix.set(savedMatrix);
	// matrix.postTranslate(event.getX() - start.x,
	// event.getY() - start.y);
	// }
	// else if (mode == ZOOM) {
	// float newDist = spacing(event);
	// Log.d(TAG, "newDist=" + newDist);
	// if (newDist > 10f) {
	// matrix.set(savedMatrix);
	// float scale = newDist / oldDist;
	// matrix.postScale(scale, scale, mid.x, mid.y);
	// }
	// }
	// break;
	// }
	//
	// view.setImageMatrix(matrix);
	// return true; // indicate event was handled
	// }
	//
	// /** Show an event in the LogCat view, for debugging */
	// private void dumpEvent(MotionEvent event) {
	// String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
	// "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
	// StringBuilder sb = new StringBuilder();
	// int action = event.getAction();
	// int actionCode = action & MotionEvent.ACTION_MASK;
	// sb.append("event ACTION_").append(names[actionCode]);
	// if (actionCode == MotionEvent.ACTION_POINTER_DOWN
	// || actionCode == MotionEvent.ACTION_POINTER_UP) {
	// sb.append("(pid ").append(
	// action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
	// sb.append(")");
	// }
	// sb.append("[");
	// for (int i = 0; i < event.getPointerCount(); i++) {
	// sb.append("#").append(i);
	// sb.append("(pid ").append(event.getPointerId(i));
	// sb.append(")=").append((int) event.getX(i));
	// sb.append(",").append((int) event.getY(i));
	// if (i + 1 < event.getPointerCount())
	// sb.append(";");
	// }
	// sb.append("]");
	// Log.d(TAG, sb.toString());
	// }
	//
	// /** Determine the space between the first two fingers */
	// private float spacing(MotionEvent event) {
	// float x = event.getX(0) - event.getX(1);
	// float y = event.getY(0) - event.getY(1);
	// return FloatMath.sqrt(x * x + y * y);
	// }
	//
	// /** Calculate the mid point of the first two fingers */
	// private void midPoint(PointF point, MotionEvent event) {
	// float x = event.getX(0) + event.getX(1);
	// float y = event.getY(0) + event.getY(1);
	// point.set(x / 2, y / 2);
	// }

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {
		String str = "";

		switch (direction) {

		case SimpleGestureFilter.SWIPE_RIGHT:
			str = "Swipe Right";
			pb.setVisibility(ProgressBar.VISIBLE);
			imgratingbad.setImageDrawable(getResources().getDrawable(
					R.drawable.ratingbad));
			imgratinggood.setImageDrawable(getResources().getDrawable(
					R.drawable.ratinggood));
			if (currentImageIndex >= 0) {
				currentImageIndex--;
			} else {
				currentImageIndex = 9;
			}

			switch (currentImageIndex) {
			case 0:
				currentImageIndex = 0;
				fullScreenImageUrl = BeautifyGridHolder.urlFirst;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFirst + "");

				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);

				break;
			case 1:
				currentImageIndex = 1;
				fullScreenImageUrl = BeautifyGridHolder.urlSecond;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSecond + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 2:
				currentImageIndex = 2;
				fullScreenImageUrl = BeautifyGridHolder.urlThird;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlThird + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
			case 3:
				currentImageIndex = 3;
				fullScreenImageUrl = BeautifyGridHolder.urlFourth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFourth + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 4:
				currentImageIndex = 4;
				fullScreenImageUrl = BeautifyGridHolder.urlFifth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFifth + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 5:
				currentImageIndex = 5;
				fullScreenImageUrl = BeautifyGridHolder.urlSixth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSixth + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 6:
				currentImageIndex = 6;
				fullScreenImageUrl = BeautifyGridHolder.urlSeventh;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSeventh + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 7:
				currentImageIndex = 7;
				fullScreenImageUrl = BeautifyGridHolder.urlEight;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlEight + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 8:
				currentImageIndex = 8;
				fullScreenImageUrl = BeautifyGridHolder.urlNinth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlNinth + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);
				break;
			case 9:
				currentImageIndex = 9;
				fullScreenImageUrl = BeautifyGridHolder.urlTenth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlTenth + "");
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_right_out);

				break;

			default:
				break;

			}
			break;

		case SimpleGestureFilter.SWIPE_LEFT:
			str = "Swipe Left";
			pb.setVisibility(ProgressBar.VISIBLE);
			imgratingbad.setImageDrawable(getResources().getDrawable(
					R.drawable.ratingbad));
			imgratinggood.setImageDrawable(getResources().getDrawable(
					R.drawable.ratinggood));
			if (currentImageIndex <= 8) {
				currentImageIndex++;
			} else {
				currentImageIndex = 0;
			}

			switch (currentImageIndex) {
			case 0:
				currentImageIndex = 0;
				fullScreenImageUrl = BeautifyGridHolder.urlFirst;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFirst + "");

				break;
			case 1:
				currentImageIndex = 1;
				fullScreenImageUrl = BeautifyGridHolder.urlSecond;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSecond + "");
				break;
			case 2:
				currentImageIndex = 2;
				fullScreenImageUrl = BeautifyGridHolder.urlThird;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlThird + "");
			case 3:
				currentImageIndex = 3;
				fullScreenImageUrl = BeautifyGridHolder.urlFourth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFourth + "");
				break;
			case 4:
				currentImageIndex = 4;
				fullScreenImageUrl = BeautifyGridHolder.urlFifth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlFifth + "");
				break;
			case 5:
				currentImageIndex = 5;
				fullScreenImageUrl = BeautifyGridHolder.urlSixth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSixth + "");
				break;
			case 6:
				currentImageIndex = 6;
				fullScreenImageUrl = BeautifyGridHolder.urlSeventh;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlSeventh + "");
				break;
			case 7:
				currentImageIndex = 7;
				fullScreenImageUrl = BeautifyGridHolder.urlEight;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlEight + "");
				break;
			case 8:
				currentImageIndex = 8;
				fullScreenImageUrl = BeautifyGridHolder.urlNinth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlNinth + "");
				break;
			case 9:
				currentImageIndex = 9;
				fullScreenImageUrl = BeautifyGridHolder.urlTenth;
				new BeautiplyAsyncTask(getBaseContext()).execute(
						"SET_MAIN_SCREEN_IMAGE", "large",
						BeautifyGridHolder.urlTenth + "");

				break;

			default:
				break;
			}

			break;
		// case SimpleGestureFilter.SWIPE_DOWN : str = "";
		// break;
		// case SimpleGestureFilter.SWIPE_UP : str = "";
		// break;

		}
		Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onDoubleTap() {

		// Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

}
