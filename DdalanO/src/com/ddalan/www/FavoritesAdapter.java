package com.ddalan.www;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoritesAdapter extends BaseAdapter {

	GridView gridView;
	ArrayList<String> favNameArr = new ArrayList<String>();
	ArrayList<String> favNumArr = new ArrayList<String>();

	@SuppressWarnings("unused")
	private Context mContext;
	LayoutInflater inflater;

	public FavoritesAdapter(Context c) {
		mContext = c;
	}

	public FavoritesAdapter(Context c, ArrayList<String> text,
			ArrayList<String> num) {
		mContext = c;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		favNameArr = text;
		favNumArr = num;
	}

	@Override
	public int getCount() {
		return favNameArr.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getPhoneLastNumber(int position) {
		int lastNum = 0;
		String pNo = null;

		pNo = favNumArr.get(position).substring(
				(favNumArr.get(position).length()) - 1);
		lastNum = Integer.parseInt(pNo);
		return lastNum;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;

		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.favoriteview, parent, false);
		}

		imageView = (ImageView) convertView.findViewById(R.id.favface);
		textView = (TextView) convertView.findViewById(R.id.favname);

		switch (getPhoneLastNumber(position)) {

		case 0:
			imageView.setImageResource(R.drawable.man5);
			break;

		case 1:
			imageView.setImageResource(R.drawable.man6);
			break;

		case 2:
			imageView.setImageResource(R.drawable.man7);
			break;

		case 3:
			imageView.setImageResource(R.drawable.man8);
			break;

		case 4:
			imageView.setImageResource(R.drawable.man9);
			break;

		case 5:
			imageView.setImageResource(R.drawable.woman5);
			break;

		case 6:
			imageView.setImageResource(R.drawable.woman6);
			break;

		case 7:
			imageView.setImageResource(R.drawable.woman7);
			break;

		default:
			imageView.setImageResource(R.drawable.woman8);
			break;

		}

		textView.setText(favNameArr.get(position));

		// imageView.setImageResource(picture[position]);
		return convertView;
	}

}
