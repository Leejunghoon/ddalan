package com.ddalan.www;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Fragment;

public class FavoritesTab extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.favoritestab, container,false);
		GridView gridView = (GridView) rootView.findViewById(R.id.GridView01);
		gridView.setAdapter(new FavoritesAdapter(rootView.getContext()));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
				
				
			}
			
		});
		return rootView;
	}

}