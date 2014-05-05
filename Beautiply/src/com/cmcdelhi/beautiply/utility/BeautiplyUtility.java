package com.cmcdelhi.beautiply.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class BeautiplyUtility {

	Context context;

	public static Bitmap[] imageGridBuffer = new Bitmap[10];
	String TAG = "CMC DELHI BEAUTIPLY";

	public BeautiplyUtility(Context context) {
		super();
		this.context = context;

	}

	public boolean saveUser(User u) throws JSONException, IOException {
		JSONObject jsono = new JSONObject();
		jsono.put("WALLPAPER_CHANGE_TIME_INTERVAL",
				u.getTimeIntervalToChangeWallpaper());
		jsono.put("IS_BEAUTIPLY_SHAKE_ON", u.isBeautiplyShakeOn());
		jsono.put("IS_BEAUTIPLY_STAR_ON", u.isBeautiplyStarOn());
		jsono.put("IS_WALLPAPERSERVICE_RUNNING",
				u.isWallpaperSetterServiceRunning());

		JSONArray userLikesArray = new JSONArray();
		for (int i = 0; i < u.getUserLikesURL().size(); i++) {
			userLikesArray.put(u.getUserLikesURL().get(i));
		}
		jsono.put("USER_LIKES", userLikesArray);

		JSONArray userDisLikesArray = new JSONArray();
		for (int i = 0; i < u.getUserDisLikesURL().size(); i++) {
			userDisLikesArray.put(u.getUserDisLikesURL().get(i));
		}
		jsono.put("USER_DISLIKES", userDisLikesArray);

		JSONArray userStarredArray = new JSONArray();
		for (int i = 0; i < u.getUserStarredURL().size(); i++) {
			userStarredArray.put(u.getUserStarredURL().get(i));
		}
		jsono.put("USER_STARRED", userStarredArray);

		FileOutputStream fos = context.openFileOutput("User.json",
				context.MODE_PRIVATE);

		OutputStreamWriter osw = new OutputStreamWriter(fos);

		osw.write(jsono.toString());
		osw.flush();

		fos.flush();
		osw.close();
		fos.close();

		Log.d(TAG, " Inside BU  USER SAVED WITH Values ----->>>>> ");
		Log.d(TAG,
				"User Time Interval  " + u.getTimeIntervalToChangeWallpaper());
		Log.d(TAG, "IS BEAUTIPLY SHAKE ON  " + u.isBeautiplyShakeOn());
		Log.d(TAG, "IS BEAUTIPLY STAR ON  " + u.isBeautiplyStarOn());
		Log.d(TAG, "IS SERVICE RUNNING  " + u.isWallpaperSetterServiceRunning());

		return true;
	}

	public User loadUser() throws IOException, JSONException {

		FileInputStream fin = context.openFileInput("User.json");
		String jsonLoadedString = "";
		int i = 0;
		while ((i = fin.read()) != -1) {
			jsonLoadedString += (char) i;
		}

		JSONObject jsonObject = new JSONObject(jsonLoadedString);

		User u = new User();
		u.setTimeIntervalToChangeWallpaper(jsonObject
				.getInt("WALLPAPER_CHANGE_TIME_INTERVAL"));

		u.setBeautiplyShakeOn(jsonObject.getBoolean("IS_BEAUTIPLY_SHAKE_ON"));
		// Log.d(TAG,
		// "Obtained From JSON File Shake value "
		// + jsonObject.getBoolean("IS_BEAUTIPLY_SHAKE_ON"));
		u.setBeautiplyStarOn(jsonObject.getBoolean("IS_BEAUTIPLY_STAR_ON"));
		// Log.d(TAG,
		// "Obtained From JSON File Star value "
		// + jsonObject.getBoolean("IS_BEAUTIPLY_STAR_ON"));
		u.setWallpaperSetterServiceRunning(jsonObject
				.getBoolean("IS_WALLPAPERSERVICE_RUNNING"));
		// Log.d(TAG, "Obtained From JSON File Wallpaper Service value "
		// + jsonObject.getBoolean("IS_WALLPAPERSERVICE_RUNNING"));

		JSONArray loadedLikesArr = jsonObject.getJSONArray("USER_LIKES");
		List<String> ul = new ArrayList<String>();
		for (int j = 0; j < loadedLikesArr.length(); j++) {
			ul.add(loadedLikesArr.getString(j));
		}

		u.setUserLikesURL(ul);

		JSONArray loadedDisLikesArr = jsonObject.getJSONArray("USER_DISLIKES");
		List<String> dl = new ArrayList<String>();
		for (int j = 0; j < loadedDisLikesArr.length(); j++) {
			dl.add(loadedDisLikesArr.getString(j));
		}

		u.setUserDisLikesURL(dl);

		JSONArray loadedStarredArr = jsonObject.getJSONArray("USER_STARRED");
		List<String> sl = new ArrayList<String>();
		for (int j = 0; j < loadedStarredArr.length(); j++) {
			sl.add(loadedStarredArr.getString(j));
		}

		u.setUserStarredURL(sl);

		Log.d(TAG, " Inside BU  USER LOADED WITH Values ----->>>>> ");
		Log.d(TAG,
				"User Time Interval  " + u.getTimeIntervalToChangeWallpaper());
		Log.d(TAG, "IS BEAUTIPLY SHAKE ON  " + u.isBeautiplyShakeOn());
		Log.d(TAG, "IS BEAUTIPLY STAR ON  " + u.isBeautiplyStarOn());
		Log.d(TAG, "IS SERVICE RUNNING  " + u.isWallpaperSetterServiceRunning());

		return u;
	}

	public boolean saveBeautiplyHolder(BeautiplyHolder bph) throws IOException,
			JSONException {

		// setting the name of wallpaper
		String wallpaperName = beautiplyFileNameGenerator();

		JSONObject jsono = new JSONObject();
		jsono.put("URL", bph.getUrl());

		if (!bph.getUrl().equals("NO_URL")) {
			jsono.put("FILE_NAME", wallpaperName);
		} else {
			jsono.put("FILE_NAME", bph.getCurrentWallpaperName());
		}

		FileOutputStream fos = context.openFileOutput("BeautiplyHolder.json",
				context.MODE_PRIVATE);

		OutputStreamWriter osw = new OutputStreamWriter(fos);

		osw.write(jsono.toString());
		osw.flush();

		fos.flush();
		osw.close();
		fos.close();

		// /saving the image
		if (!bph.getUrl().equals("NO_URL")) {
			FileOutputStream fosImg = context.openFileOutput(wallpaperName,
					context.MODE_PRIVATE);
			bph.getCurrentWallpaper().compress(Bitmap.CompressFormat.JPEG, 90,
					fosImg);
			fosImg.flush();
			fosImg.close();
		}

		return true;

	}

	public BeautiplyHolder loadBeautiplyHolder() throws ParseException,
			IOException, JSONException {

		FileInputStream fin = context.openFileInput("BeautiplyHolder.json");
		String jsonLoadedString = "";
		int i = 0;
		while ((i = fin.read()) != -1) {
			jsonLoadedString += (char) i;
		}

		JSONObject jsonObject = new JSONObject(jsonLoadedString);

		BeautiplyHolder bph = new BeautiplyHolder();

		bph.setUrl(jsonObject.getString("URL"));
		bph.setCurrentWallpaperName(jsonObject.getString("FILE_NAME"));

		FileInputStream finImg = context.openFileInput(jsonObject
				.getString("FILE_NAME"));

		Bitmap imgBmp = BitmapFactory.decodeStream(finImg);

		bph.setCurrentWallpaper(imgBmp);

		return bph;
	}

	// / this generates a random file name from beautiply0.png to beautiply9.png
	private String beautiplyFileNameGenerator() {
		// /randomvalue between 0 to 9
		String name = "beautiply" + new Random().nextInt(9) + ".png";

		return name;
	}

	public boolean checkBeautiplyImagesExixtence() {

		try {
			FileInputStream fis0 = context.openFileInput("beautiply0.png");
			return true;
		} catch (FileNotFoundException e) {
			try {
				FileInputStream fis0 = context.openFileInput("beautiply1.png");
				return true;
			} catch (FileNotFoundException e1) {
				try {
					FileInputStream fis0 = context
							.openFileInput("beautiply2.png");
					return true;

				} catch (FileNotFoundException e2) {
					try {
						FileInputStream fis0 = context
								.openFileInput("beautiply3.png");
						return true;
					} catch (FileNotFoundException e3) {
						try {
							FileInputStream fis0 = context
									.openFileInput("beautiply4.png");
							return true;
						} catch (FileNotFoundException e4) {
							try {
								FileInputStream fis0 = context
										.openFileInput("beautiply5.png");
								return true;
							} catch (FileNotFoundException e5) {

								try {
									FileInputStream fis0 = context
											.openFileInput("beautiply6.png");
									return true;
								} catch (FileNotFoundException e6) {
									try {
										FileInputStream fis0 = context
												.openFileInput("beautiply7.png");
										return true;
									} catch (FileNotFoundException e7) {
										try {
											FileInputStream fis0 = context
													.openFileInput("beautiply8.png");
											return true;
										} catch (FileNotFoundException e8) {
											try {
												FileInputStream fis0 = context
														.openFileInput("beautiply9.png");
												return true;
											} catch (FileNotFoundException e9) {
												return false;
											}
										}

									}

								}
							}

						}
					}

				}

			}

		}
	}

	// this returns a randomly generated name of file that exists.
	private String beautiplyFileNameReferer() {

		if (checkBeautiplyImagesExixtence()) {
			try {
				String fname = beautiplyFileNameGenerator();
				FileInputStream fis = context.openFileInput(fname);
				return fname;
			} catch (FileNotFoundException e4) {
				return beautiplyFileNameReferer();
			}
		} else {
			return "";
		}

	}

	public BeautiplyHolder getRandomBeautiplyHolderFromCache() {

		BeautiplyHolder bh = new BeautiplyHolder();

		// Random File Name
		String fileName = beautiplyFileNameReferer();
		// setting it in bh
		bh.setCurrentWallpaperName(fileName);
		bh.setUrl("NO_URL");

		FileInputStream finImg;

		try {
			finImg = context.openFileInput(fileName);
			Bitmap imgBmp = BitmapFactory.decodeStream(finImg);

			bh.setCurrentWallpaper(imgBmp);

			return bh;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	// this is used on onlock screen to reload and adjust the walpaper
	public boolean setBeautiplyImageOnHomeScreen() {

		try {
			FileInputStream fin = context.openFileInput(loadBeautiplyHolder()
					.getCurrentWallpaperName());
			Bitmap loadedBmp = BitmapFactory.decodeStream(fin);
			WindowManager window = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = window.getDefaultDisplay();
			int height = display.getHeight();
			int width = display.getWidth();
			WallpaperManager wm = WallpaperManager.getInstance(context);
			wm.suggestDesiredDimensions(width, height);
			wm.setBitmap(loadedBmp);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}

	public boolean saveImageGrid(Bitmap[] arr) throws IOException {

		int count = 1;

		FileOutputStream fosImg = null;
		for (Bitmap im : arr) {
			// /saving the grid images

			fosImg = context.openFileOutput("gridImage" + count + ".png",
					context.MODE_PRIVATE);
			if (im != null) {
				im.compress(Bitmap.CompressFormat.PNG, 90, fosImg);
			}
			// fosImg.flush();

			count++;
		}
		fosImg.close();

		return true;
	}

	public Bitmap[] loadImageGrid() throws FileNotFoundException {

		Bitmap[] loadedGrid = new Bitmap[10];

		FileInputStream finImg;
		for (int i = 0; i < 10; i++) {
			finImg = context.openFileInput("gridImage" + (i + 1) + ".png");
			Bitmap imgBmp = BitmapFactory.decodeStream(finImg);
			loadedGrid[i] = imgBmp;
		}

		return loadedGrid;
	}

	public boolean createFirstTimeUser() throws JSONException, IOException {
		User u = new User();
		JSONObject jsono = new JSONObject();
		jsono.put("WALLPAPER_CHANGE_TIME_INTERVAL", 1 * 60 * 60 * 1000);

		jsono.put("IS_BEAUTIPLY_SHAKE_ON", false);
		jsono.put("IS_BEAUTIPLY_STAR_ON", false);
		jsono.put("IS_WALLPAPERSERVICE_RUNNING", false);

		JSONArray userLikesArray = new JSONArray();
		userLikesArray.put("http://data1.whicdn.com/images/79973974/large.jpg");

		jsono.put("USER_LIKES", userLikesArray);

		JSONArray userDisLikesArray = new JSONArray();
		userDisLikesArray
				.put("http://data1.whicdn.com/images/79628966/large.jpg");
		userDisLikesArray
				.put("http://data1.whicdn.com/images/79964038/large.jpg");

		jsono.put("USER_DISLIKES", userDisLikesArray);

		JSONArray userStarredArray = new JSONArray();
		userStarredArray
				.put("http://data1.whicdn.com/images/79973974/large.jpg");
		userStarredArray
				.put("http://data2.whicdn.com/images/79632138/large.jpg");

		jsono.put("USER_STARRED", userStarredArray);

		FileOutputStream fos = context.openFileOutput("User.json",
				context.MODE_PRIVATE);

		OutputStreamWriter osw = new OutputStreamWriter(fos);

		osw.write(jsono.toString());
		osw.flush();

		fos.flush();
		osw.close();
		fos.close();

		return true;
	}

	public boolean setBitmapAsWallpaper(Bitmap beautyImage, String beautyUrl) {

		boolean res = false;

		WindowManager window = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = window.getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();
		WallpaperManager wm = WallpaperManager.getInstance(context);
		try {

			wm.suggestDesiredDimensions(width, height);
			wm.setBitmap(beautyImage);

			if (!beautyUrl.equals("NO_URL")) {
				BeautiplyHolder bh = new BeautiplyHolder();
				bh.setUrl(beautyUrl);
				bh.setCurrentWallpaper(beautyImage);

				res = saveBeautiplyHolder(bh);
			} else {
				// BeautiplyHolder bh = new BeautiplyHolder();
				// bh.setUrl(beautyUrl);
				// bh.setCurrentWallpaper(beautyImage);
				// // changing the name in main file
				// bh.setCurrentWallpaperName();
				// res = saveBeautiplyHolder(bh);

			}

		} catch (FileNotFoundException e) {
			// Toast.makeText(context, "FNF Exception ",
			// Toast.LENGTH_LONG).show();
			Log.i("Cmc Delhi Beautiply", "Fnf Exception" + e.getMessage());
		} catch (IOException e) {
			Log.e("GUFRAN BEAUTIPLY", "Cannot set image as wallpaper", e);
		} catch (JSONException e) {
			// Toast.makeText(context, "JSON Exception  ", Toast.LENGTH_LONG)
			// .show();
			Log.i("Cmc Delhi Beautiply", "Json Exception" + e.getMessage());
		}

		return res;

	}

	public boolean setBeautiplyHolderAsWallpaper(BeautiplyHolder bh,
			String beautyUrl) {

		boolean res = false;

		WindowManager window = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = window.getDefaultDisplay();
		int height = display.getHeight();
		int width = display.getWidth();
		WallpaperManager wm = WallpaperManager.getInstance(context);
		try {

			wm.suggestDesiredDimensions(width, height);
			wm.setBitmap(bh.getCurrentWallpaper());

			res = saveBeautiplyHolder(bh);

		} catch (FileNotFoundException e) {
			// Toast.makeText(context, "FNF Exception ",
			// Toast.LENGTH_LONG).show();
			Log.i("Cmc Delhi Beautiply", "Fnf Exception" + e.getMessage());
		} catch (IOException e) {
			Log.e("GUFRAN BEAUTIPLY", "Cannot set image as wallpaper", e);
		} catch (JSONException e) {
			// Toast.makeText(context, "JSON Exception  ", Toast.LENGTH_LONG)
			// .show();
			Log.i("Cmc Delhi Beautiply", "Json Exception" + e.getMessage());
		}

		return res;

	}

	public boolean downloadAndSaveBitmap(Bitmap beautyImage) {

		// Saving the File to SD Card and then sharing it

		// FileOutputStream fileOutputStream = null;
		// File path = Environment
		// .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		// Calendar c = Calendar.getInstance();
		// String imageName = "BeautiplyImage" + c.get(Calendar.DATE) + "_"
		// + c.get(Calendar.HOUR) + "_" + c.get(Calendar.MINUTE) + "_"
		// + c.get(Calendar.SECOND) + ".jpg";
		//
		// try {
		//
		// File file = new File(path, imageName);
		// fileOutputStream = new FileOutputStream(file);
		//
		// BufferedOutputStream bos = new BufferedOutputStream(
		// fileOutputStream);
		// beautyImage.compress(CompressFormat.JPEG, 90, bos);
		//
		// bos.flush();
		// bos.close();
		// fileOutputStream.flush();
		// fileOutputStream.close();
		//
		// return true;
		//
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/Beautiply");
		myDir.mkdirs();
		Calendar c = Calendar.getInstance();
		String imageName = "BeautiplyImage" + c.get(Calendar.DATE) + "_"
				+ c.get(Calendar.HOUR) + "_" + c.get(Calendar.MINUTE) + "_"
				+ c.get(Calendar.SECOND) + ".jpg";
		File file = new File(myDir, imageName);
		hello();

		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			beautyImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public String hello() {
		String baseDir = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String fileName = "imageName.jpg";
		String path = baseDir + "/Beautiply/" + fileName;
		return path;

	}

}
