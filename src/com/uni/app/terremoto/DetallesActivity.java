/****************************************
 * NombrePrograma: Terremoto
 * Descripcion: Esta aplicacion muestra los últimos terremotos ocurridos y sus datos (ubicación del epicentro, intensidad, fecha y hora, etc.)
 * Autor: Heber Quequejana
 *
 ****************************************/
package com.uni.app.terremoto;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetallesActivity extends Activity {

	TextView tvTitle;
	TextView tvMagnitude;
	TextView tvLocation;
	TextView tvDepth;
	TextView tvLatitude;
	TextView tvLongitude;
	TextView tvDateTime;
	TextView tvLink;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalles);
		//
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvMagnitude = (TextView) findViewById(R.id.tvMagnitude);
		tvLocation = (TextView) findViewById(R.id.tvLocation);
		tvDepth = (TextView) findViewById(R.id.tvDepth);
		tvLatitude = (TextView) findViewById(R.id.tvLatitude);
		tvLongitude = (TextView) findViewById(R.id.tvLongitude);
		tvDateTime = (TextView) findViewById(R.id.tvDateTime);
		tvLink = (TextView) findViewById(R.id.tvLink);
		//
		Bundle bundle = getIntent().getExtras();
		tvTitle.setText(bundle.getString("title"));
		tvMagnitude.setText(bundle.getString("magnitude") + " "
				+ getString(R.string.richter));
		tvLocation.setText(bundle.getString("location"));
		tvDepth.setText(bundle.getString("depth") + " km");
		tvLatitude.setText(bundle.getString("latitude"));
		tvLongitude.setText(bundle.getString("longitude"));
		tvDateTime.setText(bundle.getString("date_time"));
		tvLink.setText(bundle.getString("link"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detalles, menu);
		return true;
	}

	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.btnWeb:
			Toast.makeText(getApplicationContext(), tvLink.getText(),
					Toast.LENGTH_SHORT).show();
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) tvLink
					.getText()));
			break;
		case R.id.btnLocation:
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"
					+ tvLatitude.getText() + "," + tvLongitude.getText()
					+ "?z=10"));
			PackageManager pm = getPackageManager();
			ComponentName cn = intent.resolveActivity(pm);
			if (cn == null) {
				Toast.makeText(getApplicationContext(), "No MAPS",
						Toast.LENGTH_SHORT).show();
				intent = null;
			} else {
				Toast.makeText(getApplicationContext(),
						tvLatitude.getText() + " , " + tvLongitude.getText(),
						Toast.LENGTH_SHORT).show();
			}
			break;

		}
		if (intent != null) {
			startActivity(intent);
		}
	}

}
