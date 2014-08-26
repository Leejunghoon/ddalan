package com.ddalan.www;

import java.util.ArrayList;
import java.util.Random;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryTab extends Fragment {

	ListView list;
	boolean spreadList;

	// 1.data 占쏙옙占쏙옙
	ArrayList<String> nameArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();

	Button recOrSent;
	Button period;
	Button resetBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.historytab, container, false);
		recOrSent=(Button)getActivity().findViewById(R.id.rOs);
		period=(Button)getActivity().findViewById(R.id.period);

		if (spreadList == false) {
			getHisNumber();
			spreadList = true;
		}

		list = (ListView) rootView.findViewById(R.id.listhistory);

		// 2.Adapter 占쏙옙占쏙옙
		MyListAdapter MyAdapter = new MyListAdapter(getActivity(),
				R.layout.icontext, nameArr, numArr);

		// 3.AdapterView
		list.setAdapter(MyAdapter);
		
		

		return rootView;
	}

	public void getHisNumber() {

		// db占쏙옙占쏙옙 占쏙옙占시ｏ옙占�친占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨쇽옙占쏙옙 占쌜쇽옙

		nameArr.add("�댁뒳�댁뿉寃��덈�媛��붿뼱��");
		nameArr.add("議고쁽�깆뿉寃��덈�媛��붿뼱��");

		numArr.add("Date : 4:10");
		numArr.add("Date : 4:11");

	}

	/*
	 * class MyItem { int icon; String name;
	 * 
	 * MyItem(int icon, String name) { this.icon = icon; this.name = name; } }
	 */
	class MyListAdapter extends BaseAdapter {

		Context mContext;
		LayoutInflater inflater;

		ArrayList<String> nameArr;
		ArrayList<String> numArr;

		int layout;

		public MyListAdapter(Context context, int layout,
				ArrayList<String> nameArr, ArrayList<String> numArr) {
			mContext = context;
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			this.layout = layout;
			this.nameArr = nameArr;
			this.numArr = numArr;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return nameArr.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return nameArr.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(layout, parent, false);
			}

			Random ran = new Random();
			int x = ran.nextInt(10);

			ImageView img = (ImageView) convertView.findViewById(R.id.hisPic);

			switch (x % 2) {
			case 0:
				img.setImageResource(R.drawable.arrowup);
				break;
			case 1:
				img.setImageResource(R.drawable.arrowdown);
				break;
			default:
				img.setImageResource(R.drawable.arrowup);

			}

			TextView txt = (TextView) convertView.findViewById(R.id.hisName);
			txt.setText(nameArr.get(position));
			TextView time = (TextView) convertView.findViewById(R.id.hisTime);
			time.setText(numArr.get(position));
			// TODO Auto-generated method stub
			return convertView;
		}
	}

}