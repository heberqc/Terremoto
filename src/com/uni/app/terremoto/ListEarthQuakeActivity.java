/****************************************
 * NombrePrograma: Terremoto
 * Descripcion: Esta aplicacion muestra los últimos terremotos ocurridos y sus datos (ubicación del epicentro, intensidad, fecha y hora, etc.)
 * Autor: Heber Quequejana
 *
 ****************************************/
package com.uni.app.terremoto;

import java.util.ArrayList;
import java.util.List;

import com.uni.app.terremoto.adapters.EarthQueakeAdapter;
import com.uni.app.terremoto.beans.Earthquake;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListEarthQuakeActivity extends ListActivity {

	private List<Earthquake> list;
	public static final String ACTION = "download_earthquakes";
	private MyApplication myapp;
	private BroadcastReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isOnline()) {
			startLoad();
		} else {
			Toast.makeText(getApplicationContext(), "No Connection",
					Toast.LENGTH_SHORT).show();
		}

	}

	void startLoad() {
		myapp = (MyApplication) getApplication();
		list = new ArrayList<Earthquake>();
		if (myapp.getList().size() > 0) {

			list.addAll(myapp.getList());
		} else {
			Toast.makeText(getApplicationContext(),
					getString(R.string.loading), Toast.LENGTH_SHORT).show();
			RefreshEarthaquakeList();
		}
		EarthQueakeAdapter myListAdapter = new EarthQueakeAdapter(
				getApplicationContext(), R.layout.item_list_earth_quake, list);
		setListAdapter(myListAdapter);
	}

	// Clic en items del list
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		@SuppressWarnings("unchecked")
		ArrayAdapter<Earthquake> adapter = (ArrayAdapter<Earthquake>) getListAdapter();
		Intent detalles = new Intent(getApplicationContext(),
				DetallesActivity.class);
		Earthquake earthquake = adapter.getItem(position);
		detalles.putExtra("title", earthquake.getTitle());
		detalles.putExtra("magnitude", earthquake.getMagnitude());
		detalles.putExtra("location", earthquake.getLocation());
		detalles.putExtra("depth", earthquake.getDepth());
		detalles.putExtra("latitude", earthquake.getLatitude());
		detalles.putExtra("longitude", earthquake.getLongitude());
		detalles.putExtra("date_time", earthquake.getDate_time());
		detalles.putExtra("link", earthquake.getLink());
		startActivity(detalles);
	}

	// Menús del layout
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_list_earth_quake, menu);
		return true;
	}

	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_refresh_list:
			if (isOnline()) {
				myapp.newList();
				Intent i = new Intent(getApplicationContext(),
						ListEarthQuakeActivity.class);
				startActivity(i);
				this.finish();
			} else {
				Toast.makeText(getApplicationContext(), "No Connection",
						Toast.LENGTH_SHORT).show();
			}
			return true;
		case R.id.menu_intro:
			SharedPreferences preferencias = getSharedPreferences("datos",
					Context.MODE_PRIVATE);
			Editor editor = preferencias.edit();
			editor.putBoolean("show", false);
			editor.commit();
			Intent i = new Intent(getApplicationContext(),
					IntroduccionActivity.class);
			startActivity(i);
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		return false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	protected void onResume() {
		receiver = new EarthQuakeBroadcast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION);
		registerReceiver(receiver, filter);
		super.onResume();
	}

	private void RefreshEarthaquakeList() {
		Intent intent = new Intent(getApplicationContext(),
				EarthQuakeService.class);
		intent.putExtra("action", ACTION);
		startService(intent);

	}

	// Clase EarthQuakeBroadcast
	class EarthQuakeBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ACTION)) {

				if (myapp.getList().size() > 0) {
					Toast.makeText(getApplicationContext(), "Ok",
							Toast.LENGTH_SHORT).show();
					UpdateList(myapp.getList());

				} else {
					Toast.makeText(getApplicationContext(), "Error",
							Toast.LENGTH_LONG).show();
				}

			}
		}

		private void UpdateList(List<Earthquake> myEarthQuakes) {
			@SuppressWarnings("unchecked")
			ArrayAdapter<Earthquake> adapter = (ArrayAdapter<Earthquake>) getListAdapter();
			list.addAll(myEarthQuakes);
			adapter.notifyDataSetChanged();
		}
	}

}
