package com.uni.app.terremoto.adapters;

import java.util.List;

import com.uni.app.terremoto.R;
import com.uni.app.terremoto.beans.Earthquake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EarthQueakeAdapter extends ArrayAdapter<Earthquake> {
	private List<Earthquake> list;
	int layoutResourceId;
	// Context context;
	private LayoutInflater inflater;

	public EarthQueakeAdapter(Context context, int layoutResourceId,
			List<Earthquake> objects) {
		super(context, layoutResourceId, objects);

		this.layoutResourceId = layoutResourceId;
		this.list = objects;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = inflater.inflate(layoutResourceId, parent, false);
		TextView tvTitle = (TextView) rowView.findViewById(R.id.tvTitle);
		TextView tvMaginitude = (TextView) rowView
				.findViewById(R.id.tvMagnitude);
		TextView tvDate = (TextView) rowView.findViewById(R.id.tvDate);

		tvTitle.setText(list.get(position).getTitle());
		tvMaginitude.setText(list.get(position).getMagnitude());
		tvDate.setText(list.get(position).convertDate2HumanFormat());

		return rowView;
	}

}
