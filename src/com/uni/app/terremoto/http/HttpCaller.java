package com.uni.app.terremoto.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

public class HttpCaller {

	private static final String LOG = HttpCaller.class.getName();

	public String request(String url) {

		String json = "";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);

		try {

			Log.d(LOG, url);

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// Lectura Simple de una respuesta JSON
				InputStream instream = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(instream));
				StringBuilder total = new StringBuilder();
				String line;
				while ((line = reader.readLine()) != null) {
					total.append(line);
				}
				json = total.toString();
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return json;
	}
}
