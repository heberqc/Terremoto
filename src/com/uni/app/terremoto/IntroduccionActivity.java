/****************************************
 * NombrePrograma: Terremoto
 * Descripcion: Esta aplicacion muestra los últimos terremotos ocurridos y sus datos (ubicación del epicentro, intensidad, fecha y hora, etc.)
 * Autor: Heber Quequejana
 *
 ****************************************/
package com.uni.app.terremoto;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;

/// en el SharedPreferences 'datos' se guarda el valor del check con  nombre 'show'

public class IntroduccionActivity extends Activity {

	CheckBox chck1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_introduccion);
		chck1 = (CheckBox) findViewById(R.id.checkBox1);
		SharedPreferences preferences = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		chck1.setChecked(preferences.getBoolean("show", false));
		if (chck1.isChecked()) {
			next();
		} else {

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_introduccion, menu);
		return true;
	}

	public void onClick(View v) {
		next();
	}

	private void next() {
		Intent i = new Intent(getApplicationContext(),
				ListEarthQuakeActivity.class);
		startActivity(i);
		SharedPreferences preferencias = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putBoolean("show", chck1.isChecked());
		editor.commit();
		this.finish();
	}
}
