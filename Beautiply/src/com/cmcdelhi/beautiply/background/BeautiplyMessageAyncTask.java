package com.cmcdelhi.beautiply.background;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.cmcdelhi.beautiply.ZeroActivity;

import android.os.AsyncTask;

public class BeautiplyMessageAyncTask extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {

		return readJSONFeed("http://1.dot.gufran1267.appspot.com/cmcbeautiplyqoute");

	}

	@Override
	protected void onPostExecute(String result) {

		try {
			JSONObject jsono = new JSONObject(result);
			String promonotification = (String) jsono.get("promonotification");
			ZeroActivity.promoText.setText(promonotification);

		} catch (JSONException e) {

		} catch (Exception e) {

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
