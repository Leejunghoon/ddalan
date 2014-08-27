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

public class FriendAdapter extends BaseAdapter {
	GridView gridView;

	// 텍스트 배열 선언
	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();

	@SuppressWarnings("unused")
	private Context mContext;
	LayoutInflater inflater;

	public FriendAdapter(Context c, ArrayList<String> text,
			ArrayList<String> num) {
		mContext = c;
		inflater = (LayoutInflater) c
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		textArr = text;
		numArr = num;
	}

	@Override
	public int getCount() {
		return textArr.size();
	}

	@Override
	public Object getItem(int position) {
		return textArr.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getPhoneNumber(int position) {
		String pNo = numArr.get(position);
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
		ImageView imageView;
		TextView textView;
		ImageView checkView;

		if (convertView == null) {
			// View = friendtab.xml ViewGroup = friendtab.xml(RelativeLayout)
			convertView = inflater.inflate(R.layout.friendview, parent, false);
		}

		imageView = (ImageView) convertView.findViewById(R.id.frface);
		textView = (TextView) convertView.findViewById(R.id.frname);
		checkView = (ImageView) convertView.findViewById(R.id.checkImg);
		checkView.setImageResource(R.drawable.ddalan);

		switch (getPhoneNumber(position)) {

		case 2207:
			imageView.setImageResource(R.drawable.face32);
			break;
		case 75:
			imageView.setImageResource(R.drawable.face10);
			break;
		case 6582:
			imageView.setImageResource(R.drawable.face30);
			break;
		case 2085:
			imageView.setImageResource(R.drawable.face15);
			break;
		case 702:
			imageView.setImageResource(R.drawable.face21);
			break;
		case 5514:
			imageView.setImageResource(R.drawable.face31);
			break;
		case 5408:
			imageView.setImageResource(R.drawable.face12);
			break;
		case 8469:
			imageView.setImageResource(R.drawable.face11);
			break;
		case 475:
			imageView.setImageResource(R.drawable.face20);
			break;
		case 606:
			imageView.setImageResource(R.drawable.face17);
			break;
		case 7047:
			imageView.setImageResource(R.drawable.face13);
			break;
		case 7770:
			imageView.setImageResource(R.drawable.face28);
			break;
		case 3458:
			imageView.setImageResource(R.drawable.face29);
			break;

		default:
			imageView.setImageResource(R.drawable.woman8);
			break;

		}

		textView.setText(textArr.get(position));

		return convertView;
	}
}
