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


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.favoriteview, parent, false);
		}
		
		imageView = (ImageView)convertView.findViewById(R.id.favface);
		textView=(TextView)convertView.findViewById(R.id.favname);
		
		imageView.setImageResource(R.drawable.woman);
		textView.setText("Â¡Â¡ÀÌ");
	
		 //imageView.setImageResource(picture[position]);
		return convertView;
	}

	
}
