package com.ddalan.www;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class FavoritesAdapter extends BaseAdapter {
	
	private Context mContext;

	public FavoritesAdapter(Context c) {
		mContext = c;
	}

	@Override
	public int getCount() {
		return 9;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if(convertView == null){
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(85,85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		}else{
			imageView = (ImageView) convertView;
		}
	
		 //imageView.setImageResource(picture[position]);
		return imageView;
	}

	
}
