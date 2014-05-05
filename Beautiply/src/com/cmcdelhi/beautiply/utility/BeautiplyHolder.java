package com.cmcdelhi.beautiply.utility;

import java.util.Date;

import android.graphics.Bitmap;

public class BeautiplyHolder {

	private String url;
	private Bitmap currentWallpaper;
	private String currentWallpaperName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Bitmap getCurrentWallpaper() {
		return currentWallpaper;
	}

	public void setCurrentWallpaper(Bitmap currentWallpaper) {
		this.currentWallpaper = currentWallpaper;
	}

	public String getCurrentWallpaperName() {
		return currentWallpaperName;
	}

	public void setCurrentWallpaperName(String currentWallpaperName) {
		this.currentWallpaperName = currentWallpaperName;
	}

}
