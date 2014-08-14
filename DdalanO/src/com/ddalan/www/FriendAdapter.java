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
	static int pic = R.drawable.man;
	static int dgirl = R.drawable.woman;
	static int defaultboy = R.drawable.man;

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
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public int getPhoneLastNumber(int position) {
		String pNo = (numArr.get(position)).substring(10);
		System.out.println(pNo);
		int lastNum = Integer.parseInt(pNo);
		return lastNum;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;

		if (convertView == null) {
			// View = friendtab.xml ViewGroup = friendtab.xml(RelativeLayout)
			convertView = inflater.inflate(R.layout.friendview, parent, false);

		}

		imageView = (ImageView) convertView.findViewById(R.id.frface);
		textView = (TextView) convertView.findViewById(R.id.frname);

		switch (getPhoneLastNumber(position)) {
		case 0:
			pic = R.drawable.man5;
			imageView.setImageResource(pic);
			//imageView.setImageResource(R.drawable.man5);

			break;
		case 1:
			pic = R.drawable.man6;
			imageView.setImageResource(pic);
		//	imageView.setImageResource(R.drawable.man6);

			break;

		case 2:
			pic = R.drawable.man7;			
			imageView.setImageResource(pic);
		//	imageView.setImageResource(R.drawable.man7);

			break;
		case 3:
			pic = R.drawable.man8;
			imageView.setImageResource(pic);
	//		imageView.setImageResource(R.drawable.man8);

			break;
		case 4:
			pic = R.drawable.man9;
			imageView.setImageResource(pic);
		//	imageView.setImageResource(R.drawable.man9);

			break;
		case 5:
			pic = R.drawable.woman5;
			imageView.setImageResource(pic);
		//	imageView.setImageResource(R.drawable.woman5);

			break;
		case 6:
			pic = R.drawable.woman6;
			imageView.setImageResource(pic);
	//		imageView.setImageResource(R.drawable.woman6);

			break;
		case 7:
			pic = R.drawable.woman7;
			imageView.setImageResource(pic);

			break;
		default:
			pic = R.drawable.woman8;
			imageView.setImageResource(pic);
		//	imageView.setImageResource(R.drawable.woman8);

			break;
		}

		textView.setText(textArr.get(position));

		return convertView;
	}

}
