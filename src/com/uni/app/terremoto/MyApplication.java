package com.uni.app.terremoto;

import java.util.ArrayList;
import java.util.List;

import com.uni.app.terremoto.beans.Earthquake;

import android.app.Application;

public class MyApplication extends Application {

	private List<Earthquake> list = new ArrayList<Earthquake>();

	public List<Earthquake> getList() {
		return list;
	}

	public void setList(List<Earthquake> list) {
		this.list = list;
	}

	public void newList(){
		list = new ArrayList<Earthquake>();
	}
	
}
