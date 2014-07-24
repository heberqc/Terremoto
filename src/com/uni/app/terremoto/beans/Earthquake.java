package com.uni.app.terremoto.beans;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import com.google.gson.Gson;
import com.google.gson.annotations.*;
import com.google.gson.reflect.TypeToken;

public class Earthquake {

	@SerializedName("title")
	private String title;
	private String magnitude;
	private String location;
	private String depth; // unidad (KM)
	private String latitude;
	private String longitude;
	private String date_time;// Formato de fecha UTC
	private String link;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMagnitude() {
		return magnitude;
	}

	public void setMagnitude(String magnitude) {
		this.magnitude = magnitude;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	/*
	 * Este metodo nos permite deserializar un String (JSON) y convertirlo a una
	 * Lista de Earthqueakes
	 */
	public List<Earthquake> getListOfEarthQuakes(String json) {

		Type listType = new TypeToken<List<Earthquake>>() {
		}.getType();

		List<Earthquake> earthquakes = new Gson().fromJson(json, listType);

		return earthquakes;
	}

	@SuppressLint("SimpleDateFormat")
	public String convertDate2HumanFormat() {
		// DATE DE EJEMPLO: 2013-07-03T19:21:46+00:00

		String date_salida = "";
		try {
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			date = formatter.parse(this.date_time);

			SimpleDateFormat df = new SimpleDateFormat();
			df.setTimeZone(TimeZone.getTimeZone("GMT-05"));

			formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			date_salida = df.format(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date_salida;
	}
}
