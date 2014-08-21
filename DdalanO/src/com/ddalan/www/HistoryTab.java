package com.ddalan.www;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryTab extends Fragment {

	ListView list;
	boolean spreadList;

	// 1.data Á¦ÀÛ
	ArrayList<String> nameArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.historytab, container, false);

		if (spreadList == false) {
			getHisNumber();
			spreadList = true;
		}

		list = (ListView) rootView.findViewById(R.id.listhistory);

		// 2.Adapter ±¸¼º
		MyListAdapter MyAdapter = new MyListAdapter(getActivity(),
				R.layout.icontext, nameArr, numArr);

		// 3.AdapterView
		list.setAdapter(MyAdapter);

		return rootView;
	}

	public void getHisNumber() {

		// db¿¡¼­ Áñ°ÜÃ£±â Ä£±¸ °¡Á®¿À´Â ¸Þ¼­µå ÀÛ¼º

		nameArr.add("Â¡Â¡ÀÌ1");
		nameArr.add("Â¡Â¡ÀÌ2");
		nameArr.add("Â¡Â¡ÀÌ3");
		nameArr.add("Â¡Â¡ÀÌ4");
		nameArr.add("Â¡Â¡ÀÌ5");
		nameArr.add("Â¡Â¡ÀÌ6");

		numArr.add("¿ÀÈÄ 3:47");
		numArr.add("¿ÀÈÄ 2:14");
		numArr.add("¿ÀÀü 10:32");
		numArr.add("¿ÀÀü 8:23");
		numArr.add("2014/8/20");
		numArr.add("2014/8/19");

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

			ImageView img = (ImageView) convertView.findViewById(R.id.hisPic);
			img.setImageResource(R.drawable.woman);
			TextView txt = (TextView) convertView.findViewById(R.id.hisName);
			txt.setText(nameArr.get(position));
			TextView time = (TextView) convertView.findViewById(R.id.hisTime);
			time.setText(numArr.get(position));
			// TODO Auto-generated method stub
			return convertView;
		}
	}

}