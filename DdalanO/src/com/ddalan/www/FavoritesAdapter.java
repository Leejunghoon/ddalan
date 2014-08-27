package com.ddalan.www;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FavoritesAdapter extends BaseAdapter {

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

	public int getPhoneNumber(int position) {
		String pNo = favNumArr.get(position);
		System.out.println(pNo);
		int pStr = 0;

		String pNoStr = pNo.substring(9);
		System.out.println("pNoStr=" + pNoStr);
		if (pNoStr.length() == 4 || pNoStr.length() == 3
				|| pNoStr.length() == 2) {

			pStr = Integer.parseInt(pNoStr);
		} else {
			pStr = 6582;
		}
		return pStr;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView favFriendPic;
		TextView favFriendName;

		if (convertView == null) {
			convertView = inflater
					.inflate(R.layout.favoriteview, parent, false);
		}

		favFriendPic = (ImageView) convertView.findViewById(R.id.favface);
		favFriendName = (TextView) convertView.findViewById(R.id.favname);
		switch (getPhoneNumber(position)) {

		case 2207:
			favFriendPic.setImageResource(R.drawable.face32);
			break;
		case 75:
			favFriendPic.setImageResource(R.drawable.face10);
			break;
		case 6582:
			favFriendPic.setImageResource(R.drawable.face30);
			break;
		case 2085:
			favFriendPic.setImageResource(R.drawable.face15);
			break;
		case 702:
			favFriendPic.setImageResource(R.drawable.face21);
			break;
		case 5514:
			favFriendPic.setImageResource(R.drawable.face31);
			break;
		case 5408:
			favFriendPic.setImageResource(R.drawable.face12);
			break;
		case 8469:
			favFriendPic.setImageResource(R.drawable.face11);
			break;
		case 475:
			favFriendPic.setImageResource(R.drawable.face20);
			break;
		case 606:
			favFriendPic.setImageResource(R.drawable.face17);
			break;
		case 7047:
			favFriendPic.setImageResource(R.drawable.face13);
			break;
		case 7770:
			favFriendPic.setImageResource(R.drawable.face28);
			break;
		case 3458:
			favFriendPic.setImageResource(R.drawable.face29);
			break;

		default:
			favFriendPic.setImageResource(R.drawable.woman8);
			break;

		}

		favFriendName.setText(favNameArr.get(position));

		// imageView.setImageResource(picture[position]);
		return convertView;
	}

}
