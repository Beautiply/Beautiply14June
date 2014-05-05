package com.cmcdelhi.beautiply.utility;

import java.util.ArrayList;
import java.util.List;

public class User {

	private long timeIntervalToChangeWallpaper;
	private boolean isBeautiplyShakeOn = false;
	private boolean isBeautiplyStarOn = false;
	private boolean isWallpaperSetterServiceRunning = false;
	private List<String> userLikesURL = new ArrayList<String>();
	private List<String> userDisLikesURL = new ArrayList<String>();

	public boolean isWallpaperSetterServiceRunning() {
		return isWallpaperSetterServiceRunning;
	}

	public void setWallpaperSetterServiceRunning(
			boolean isWallpaperSetterServiceRunning) {
		this.isWallpaperSetterServiceRunning = isWallpaperSetterServiceRunning;
	}

	public boolean isBeautiplyStarOn() {
		return isBeautiplyStarOn;
	}

	public void setBeautiplyStarOn(boolean isBeautiplyStarOn) {
		this.isBeautiplyStarOn = isBeautiplyStarOn;
	}

	public boolean isBeautiplyShakeOn() {
		return isBeautiplyShakeOn;
	}

	public void setBeautiplyShakeOn(boolean isBeautiplyShakeOn) {
		this.isBeautiplyShakeOn = isBeautiplyShakeOn;
	}
	

	private List<String> userStarredURL = new ArrayList<String>();;

	public long getTimeIntervalToChangeWallpaper() {
		return timeIntervalToChangeWallpaper;
	}

	public void setTimeIntervalToChangeWallpaper(
			long timeIntervalToChangeWallpaper) {
		this.timeIntervalToChangeWallpaper = timeIntervalToChangeWallpaper;
	}

	public List<String> getUserStarredURL() {
		return userStarredURL;
	}

	public void setUserStarredURL(List<String> userStarredURL) {
		this.userStarredURL = userStarredURL;
	}

	public List<String> getUserLikesURL() {
		return userLikesURL;
	}

	public void setUserLikesURL(List<String> userLikesURL) {
		this.userLikesURL = userLikesURL;
	}

	public List<String> getUserDisLikesURL() {
		return userDisLikesURL;
	}

	public void setUserDisLikesURL(List<String> userDisLikesURL) {
		this.userDisLikesURL = userDisLikesURL;
	}

}
