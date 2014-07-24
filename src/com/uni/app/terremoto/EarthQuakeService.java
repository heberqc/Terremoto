package com.uni.app.terremoto;

import java.util.List;

import com.uni.app.terremoto.beans.Earthquake;
import com.uni.app.terremoto.http.HttpCaller;

import android.app.IntentService;
import android.content.Intent;
//import android.widget.Toast;

public class EarthQuakeService extends IntentService {
	private String url = "http://earthquake-report.com/feeds/recent-eq?json";

	public EarthQuakeService() {
		super("My EartQuakeService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		// Extras
		String action = intent.getStringExtra("action");

		HttpCaller httpCaller = new HttpCaller();
		String respuesta = httpCaller.request(url);
		/*if (respuesta.equals("")){
			Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
		}*/
		List<Earthquake> lista = new Earthquake()
				.getListOfEarthQuakes(respuesta);

		MyApplication myapp = (MyApplication) getApplication();
		myapp.setList(lista);

		// Broadcasts
		Intent broadCastIntent = new Intent();
		broadCastIntent.setAction(action);
		this.sendBroadcast(broadCastIntent);

	}

}
