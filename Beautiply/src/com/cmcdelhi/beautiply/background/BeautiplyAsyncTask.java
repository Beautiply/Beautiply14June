package com.cmcdelhi.beautiply.background;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cmcdelhi.beautiply.FirstActivity;
import com.cmcdelhi.beautiply.ForthActivity;
import com.cmcdelhi.beautiply.R;
import com.cmcdelhi.beautiply.ForthActivity.FullScreenHider;
import com.cmcdelhi.beautiply.ZeroActivity;
import com.cmcdelhi.beautiply.utility.BeautifyGridHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyHolder;
import com.cmcdelhi.beautiply.utility.BeautiplyUtility;
import com.cmcdelhi.beautiply.utility.ZeroActivityGridHolder;

@SuppressLint("NewApi")
public class BeautiplyAsyncTask extends AsyncTask<String, Void, String> {

	Context context;
	Bitmap beautyImage;
	String instructionKey;
	String firstLink, secondLink, thirdLink, imSexyLink, editorsPickLink,
			trendingLink;
	String imageSize;
	String currentUrl;
	String providedUrl;
	ProgressBar pb;

	BeautiplyUtility bu;

	public BeautiplyAsyncTask(Context context) {
		this.context = context;
		bu = new BeautiplyUtility(context);
	}

	@Override
	protected String doInBackground(String... params) {
		// / Log.i("GUFRAN Beautiply ", "Inside BeautiplyAsyncTask doInBack");
		instructionKey = params[0];
		imageSize = params[1];
		providedUrl = params[2];

		for (int i = 0; i < params.length; i++) {
			String string = params[i];
			// Log.i("GUFRAN BEAUTIFY ASYNC TASK", i + string);
		}

		// Log.i("GUFRAN BEAUTIF BAHAR ", instructionKey);
		// Log.i("GUFRAN BEAUTIF BAHAR ", imageSize);
		// Log.i("GUFRAN BEAUTIF BAHAR ", providedUrl);

		return "http://1-dot-gufran1267.appspot.com/cmcbeautiply";
	}

	@Override
	protected void onPostExecute(String result) {
		// Log.i("GUFRAN Beautiply ", "Inside BeautiplyAsyncTask OnPost");
		if (providedUrl.equals("NOTHING")) {
			new URLGrabberAsyncTask().execute(result);
		} else if (providedUrl.equals("PROMO_URL")) {
			new URLGrabberAsyncTask().execute(result);
		} else {
			new ImageGrabberAsyncTask().execute(providedUrl, secondLink,
					thirdLink, editorsPickLink, imSexyLink, trendingLink);
		}

	}

	private class URLGrabberAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			// Log.i("GUFRAN Beautiply ",
			// "Inside URLGrabberAsyncTask doInBack");
			return readJSONFeed(urls[0]);
		}

		@Override
		protected void onPostExecute(String result) {

			// Log.i("GUFRAN Beautiply ", "Inside URLGrabberAsyncTask  onPost");

			try {
				JSONObject jsono = new JSONObject(result);
				firstLink = (String) jsono.get("link1");
				secondLink = (String) jsono.get("link2");
				thirdLink = (String) jsono.get("link3");
				imSexyLink = (String) jsono.get("imSexy");
				editorsPickLink = (String) jsono.get("editorsPick");
				trendingLink = (String) jsono.get("trending");

				currentUrl = firstLink;

				if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_B")) {
					currentUrl = editorsPickLink;
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_C")) {
					currentUrl = imSexyLink;
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_D")) {
					currentUrl = trendingLink;
				} else {
					currentUrl = firstLink;
				}

				if (imageSize.equals("thumb")) {
					if (firstLink.contains("large")) {
						firstLink = firstLink.replaceAll("large", "thumb");
					}
					if (secondLink.contains("large")) {
						secondLink = secondLink.replaceAll("large", "thumb");
					}

					if (thirdLink.contains("large")) {
						thirdLink = thirdLink.replaceAll("large", "thumb");
					}
					if (imSexyLink.contains("large")) {
						imSexyLink = imSexyLink.replaceAll("large", "thumb");
					}
					if (editorsPickLink.contains("large")) {
						editorsPickLink = editorsPickLink.replaceAll("large",
								"thumb");
					}
					if (trendingLink.contains("large")) {
						trendingLink = trendingLink
								.replaceAll("large", "thumb");
					}

				}

				// secondLink.replaceAll("large", "thumb");
				// thirdLink.replaceAll("large", "thumb");

				// if (providedUrl.equals("NOTHING")) {

				// dont'ta take link 3 ie thirdLink
				new ImageGrabberAsyncTask().execute(firstLink, secondLink,
						thirdLink, editorsPickLink, imSexyLink, trendingLink);

				// Log.i("INSIDE URL GRABBER NOTHING ", providedUrl);
				//
				// } else {
				// new ImageGrabberAsyncTask().execute(providedUrl,
				// secondLink, thirdLink);
				//
				// Log.i("GUFRAN BEAUTIF BAHAR NOT NOTHING", providedUrl);
				// }

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String readJSONFeed(String URL) {
			StringBuilder stringBuilder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(URL);

			try {

				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						stringBuilder.append(line);
					}
				} else {

				}

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return stringBuilder.toString();
		}
	}

	private class ImageGrabberAsyncTask extends AsyncTask<String, Void, String> {

		Bitmap bmp = null;

		@Override
		protected String doInBackground(String... arr) {
			// Log.i("GUFRAN Beautiply ",
			// "Inside ImageGrabberAsyncTask  doBack");

			try {

				URL image_url = null;

				if (instructionKey.equals("SET_PROMO")) {
					image_url = new URL(arr[1]);
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_A")) {
					image_url = new URL(arr[0]);
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_B")) {
					image_url = new URL(arr[3]);
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_C")) {
					image_url = new URL(arr[4]);
				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_D")) {
					image_url = new URL(arr[5]);
				} else {

					image_url = new URL(arr[0]);
				}

				if (image_url == null) {
					// ll.setBackground(gd);
				} else {
					bmp = BitmapFactory.decodeStream(image_url.openConnection()
							.getInputStream());
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			beautyImage = bmp;

			// Log.i("GUFRAN Beautiply ",
			// "Inside ImageGrabberAsyncTask  doPost");

			// check for null beauty image
			if (beautyImage != null) {

				if (instructionKey.equals("DOWNLOAD")) {
					try {
						bu = new BeautiplyUtility(context);
						BeautiplyHolder bh = new BeautiplyHolder();
						bh.setUrl(firstLink);

						bh.setCurrentWallpaper(beautyImage);

						boolean res = bu.saveBeautiplyHolder(bh);

						if (res == true) {
							// Toast.makeText(context, "Image Downloaded ",
							// Toast.LENGTH_LONG).show();
							Log.i("Cmc Delhi Beautiply", "Image downloaded");
						}

					} catch (FileNotFoundException e) {
						// Toast.makeText(context, "FNF Exception ",
						// Toast.LENGTH_LONG).show();
						Log.i("Cmc Delhi Beautiply",
								"Fnf Exception" + e.getMessage());

					} catch (IOException e) {
						// Toast.makeText(context, "IO Exception ",
						// Toast.LENGTH_LONG).show();
						Log.i("Cmc Delhi Beautiply",
								"IO Exception" + e.getMessage());
					} catch (JSONException e) {
						// Toast.makeText(context, "JSON Exception  ",
						// Toast.LENGTH_LONG).show();
						Log.i("Cmc Delhi Beautiply", "Json Exception");

					}
				} else if (instructionKey.equals("SET_WALLPAPER")) {
					WindowManager window = (WindowManager) context
							.getSystemService(Context.WINDOW_SERVICE);
					Display display = window.getDefaultDisplay();
					int height = display.getHeight();
					int width = display.getWidth();
					WallpaperManager wm = WallpaperManager.getInstance(context);
					try {
						if (height > width) {
							if (beautyImage.getHeight() > beautyImage
									.getWidth()) {
								wm.suggestDesiredDimensions(width, height);
								wm.setBitmap(beautyImage);

								// releasing the Shake Lock of Beautiply Shake
								BeautiplyShakeService.isShakeLocked = false;

								Log.i("Cmc Delhi Beautiply", "Wallpaper set");

								// / for downloading and saving the downloaded
								// wallpaper

								bu = new BeautiplyUtility(context);
								BeautiplyHolder bh = new BeautiplyHolder();
								bh.setUrl(firstLink);
								bh.setCurrentWallpaper(beautyImage);

								boolean res = bu.saveBeautiplyHolder(bh);

								if (res == true) {
									// Toast.makeText(context,
									// "Image Downloaded ",
									// Toast.LENGTH_LONG).show();
									Log.i("Cmc Delhi Beautiply",
											"image downloaded");
								}

							} else {
								new BeautiplyAsyncTask(context).execute(
										"SET_WALLPAPER", "large", "NOTHING");
							}
						} else {
							if (beautyImage.getHeight() < beautyImage
									.getWidth()) {
								wm.suggestDesiredDimensions(width, height);
								wm.setBitmap(beautyImage);
								// Toast.makeText(context, "Wallpaper set",
								// Toast.LENGTH_SHORT).show();
								Log.i("Cmc Delhi Beautiply", " wallpaper set");
								// / for downloading and saving the downloaded
								// wallpaper

								bu = new BeautiplyUtility(context);
								BeautiplyHolder bh = new BeautiplyHolder();
								bh.setUrl(firstLink);
								bh.setCurrentWallpaper(beautyImage);

								boolean res = bu.saveBeautiplyHolder(bh);

								if (res == true) {
									// Toast.makeText(context,
									// "Image Downloaded ",
									// Toast.LENGTH_LONG).show();
									Log.i("Cmc Delhi Beautiply",
											"Image downloaded");
								}
							} else {
								new BeautiplyAsyncTask(context).execute(
										"SET_WALLPAPER", "large", "NOTHING");
							}
						}

					} catch (FileNotFoundException e) {
						// Toast.makeText(context, "FNF Exception ",
						// Toast.LENGTH_LONG)
						// .show();
						Log.i("Cmc Delhi Beautiply",
								"FNF Exception" + e.getMessage());
					} catch (IOException e) {
						Log.e("GUFRAN BEAUTIPLY",
								"Cannot set image as wallpaper"
										+ e.getMessage(), e);
					} catch (JSONException e) {
						// Toast.makeText(context, "JSON Exception  ",
						// Toast.LENGTH_LONG).show();
						Log.i("Cmc Delhi Beautiply",
								"Json Exception" + e.getMessage());
					}
				} else if (instructionKey.equals("DOWNLOAD_CACHE_WALLPAPER")) {

					try {

						bu = new BeautiplyUtility(context);
						BeautiplyHolder bh = new BeautiplyHolder();
						bh.setUrl(firstLink);
						bh.setCurrentWallpaper(beautyImage);

						boolean res = bu.saveBeautiplyHolder(bh);

						if (res == true) {
							Log.i("Cmc Delhi Beautiply",
									"Cache Image downloaded");
						}

					} catch (FileNotFoundException e) {

						Log.i("Cmc Delhi Beautiply",
								"FNF Exception" + e.getMessage());
					} catch (IOException e) {
						Log.e("GUFRAN BEAUTIPLY",
								"Cannot set image as wallpaper"
										+ e.getMessage(), e);
					} catch (JSONException e) {
						Log.i("Cmc Delhi Beautiply",
								"Json Exception" + e.getMessage());
					}
				} else if (instructionKey.equals("SET_PROMO")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					ZeroActivity.promoLayout.setBackgroundDrawable(d);

				} else if (instructionKey.equals("SET_MAIN_SCREEN_IMAGE")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					ForthActivity.bigBeauty.setImageDrawable(d);
					ForthActivity.pb.setVisibility(ProgressBar.INVISIBLE);

					ForthActivity.fullScreenImageBitmap = beautyImage;

					new ForthActivity().new FullScreenHider().execute("");

					// Toast.makeText(context, "Img Set ",
					// Toast.LENGTH_SHORT).show();
					Log.i("Cmc Delhi Beautiply", "img set");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_1")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img1.setBackground(d);
					} else {
						FirstActivity.img1.setBackgroundDrawable(d);
					}

					FirstActivity.imageView1textView1
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star1.setVisibility(ImageView.VISIBLE);

					BeautiplyUtility.imageGridBuffer[0] = beautyImage;

					BeautifyGridHolder.urlFirst = currentUrl;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star1.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_2", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_2")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img2.setBackground(d);
					} else {
						FirstActivity.img2.setBackgroundDrawable(d);
					}
					FirstActivity.imageView2textView2
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star2.setVisibility(ImageView.VISIBLE);

					BeautiplyUtility.imageGridBuffer[1] = beautyImage;

					BeautifyGridHolder.urlSecond = currentUrl;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star2.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_3", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_3")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img3.setBackground(d);
					} else {
						FirstActivity.img3.setBackgroundDrawable(d);
					}
					FirstActivity.imageView3textView3
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star3.setVisibility(ImageView.VISIBLE);
					BeautiplyUtility.imageGridBuffer[2] = beautyImage;

					BeautifyGridHolder.urlThird = currentUrl;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star3.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_4", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_4")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img4.setBackground(d);
					} else {
						FirstActivity.img4.setBackgroundDrawable(d);
					}
					FirstActivity.imageView4textView4
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star4.setVisibility(ImageView.VISIBLE);

					BeautiplyUtility.imageGridBuffer[3] = beautyImage;

					BeautifyGridHolder.urlFourth = currentUrl;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star4.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_5", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_5")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					BeautiplyUtility.imageGridBuffer[4] = beautyImage;

					BeautifyGridHolder.urlFifth = currentUrl;

					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img5.setBackground(d);
					} else {
						FirstActivity.img5.setBackgroundDrawable(d);
					}
					FirstActivity.imageView5textView5
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star5.setVisibility(ImageView.VISIBLE);

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star5.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_6", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_6")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img6.setBackground(d);
					} else {
						FirstActivity.img6.setBackgroundDrawable(d);
					}
					FirstActivity.imageView6textView6
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star6.setVisibility(ImageView.VISIBLE);

					BeautifyGridHolder.urlSixth = currentUrl;

					BeautiplyUtility.imageGridBuffer[5] = beautyImage;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star6.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_7", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_7")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img7.setBackground(d);
					} else {
						FirstActivity.img7.setBackgroundDrawable(d);
					}
					FirstActivity.imageView7textView7
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star7.setVisibility(ImageView.VISIBLE);

					BeautifyGridHolder.urlSeventh = currentUrl;

					BeautiplyUtility.imageGridBuffer[6] = beautyImage;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star7.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_8", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_8")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img8.setBackground(d);
					} else {
						FirstActivity.img8.setBackgroundDrawable(d);
					}
					FirstActivity.imageView8textView8
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star8.setVisibility(ImageView.VISIBLE);

					BeautiplyUtility.imageGridBuffer[7] = beautyImage;

					BeautifyGridHolder.urlEight = currentUrl;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star8.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_9", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_9")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img9.setBackground(d);
					} else {
						FirstActivity.img9.setBackgroundDrawable(d);
					}
					FirstActivity.imageView9textView9
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star9.setVisibility(ImageView.VISIBLE);

					BeautifyGridHolder.urlNinth = currentUrl;

					BeautiplyUtility.imageGridBuffer[8] = beautyImage;

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star9.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_IMAGE_GRID_OBJECT_10", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_IMAGE_GRID_OBJECT_10")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);

					BeautiplyUtility.imageGridBuffer[9] = beautyImage;

					BeautifyGridHolder.urlTenth = currentUrl;

					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						FirstActivity.img10.setBackground(d);
					} else {
						FirstActivity.img10.setBackgroundDrawable(d);
					}
					FirstActivity.imageView10textView10
							.setVisibility(TextView.VISIBLE);
					FirstActivity.star10.setVisibility(ImageView.VISIBLE);

					// setting the star status if the user has already stared
					// that
					// image
					try {
						if (bu.loadUser().getUserStarredURL()
								.contains(currentUrl)) {
							FirstActivity.star10.setImageDrawable(context
									.getResources().getDrawable(
											R.drawable.starwhite));
						}
					} catch (IOException e) {

						e.printStackTrace();
					} catch (JSONException e) {

						e.printStackTrace();
					}

					try {
						boolean res = new BeautiplyUtility(context)
								.saveImageGrid(BeautiplyUtility.imageGridBuffer);
						if (res = true) {
							// Toast.makeText(context, "Grid saved ",
							// Toast.LENGTH_LONG)
							// .show();
							Log.i("Cmc Delhi Beautiply", "Grid saved");
						}

					} catch (IOException e) {
						// Toast.makeText(context, "IO Exception ",
						// Toast.LENGTH_LONG)
						// .show();
						Log.i("Cmc Delhi Beautiply",
								"IO Exception" + e.getMessage());
					}

				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_A")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						ZeroActivity.zeroActivityImageA.setBackground(d);
					} else {
						ZeroActivity.zeroActivityImageA
								.setBackgroundDrawable(d);
					}

					ZeroActivityGridHolder.zeroActivityImageALink = currentUrl;

					new BeautiplyAsyncTask(context).execute(
							"SET_ZEROACTIVITY_IMAGE_B", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_B")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						ZeroActivity.zeroActivityImageB.setBackground(d);
					} else {
						ZeroActivity.zeroActivityImageB
								.setBackgroundDrawable(d);
					}

					ZeroActivityGridHolder.zeroActivityImageBLink = currentUrl;

					// new BeautiplyAsyncTask(context).execute(
					// "SET_ZEROACTIVITY_IMAGE_C", "thumb", "NOTHING");

				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_C")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						ZeroActivity.zeroActivityImageC.setBackground(d);
					} else {
						ZeroActivity.zeroActivityImageC
								.setBackgroundDrawable(d);
					}

					new BeautiplyAsyncTask(context).execute(
							"SET_ZEROACTIVITY_IMAGE_D", "thumb", "NOTHING");

					ZeroActivityGridHolder.zeroActivityImageCLink = currentUrl;

				} else if (instructionKey.equals("SET_ZEROACTIVITY_IMAGE_D")) {
					Drawable d = new BitmapDrawable(context.getResources(),
							beautyImage);
					// Check if we're running on GingerBread or above
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
						ZeroActivity.zeroActivityImageD.setBackground(d);
					} else {
						ZeroActivity.zeroActivityImageD
								.setBackgroundDrawable(d);
					}

					ZeroActivityGridHolder.zeroActivityImageDLink = currentUrl;

				}

			}

		}
	}

}
