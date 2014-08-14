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
	
	
	private Context mContext;
	LayoutInflater inflater;
	
	
	public FriendAdapter(Context c,ArrayList<String> text) {
		mContext = c;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		textArr = text;
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


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		TextView textView;
	
		
		if(convertView == null){
			//View = friendtab.xml 	ViewGroup = friendtab.xml(RelativeLayout)
			convertView = inflater.inflate(R.layout.friendview, parent,false);
		
		}
			
			imageView = (ImageView) convertView.findViewById(R.id.frface);
			textView = (TextView) convertView.findViewById(R.id.frname);

					
			
			imageView.setImageResource(R.drawable.woman7);

			textView.setText(textArr.get(position));
		
			
			
			return convertView;
	}


}

