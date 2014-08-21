package com.ddalan.www;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryTab extends Fragment {
	
  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.historytab, container, false);    

        ListView list = (ListView)rootView.findViewById(R.id.listView1); 
         
        // 1.data 제작          
        String str[] = { 
                "이정훈","조현성","남기환","이슬이"
                }; 

        // 2.Adapter 구성 
        ArrayAdapter<String> adapter = new ArrayAdapter<String> 

                (getActivity(),
                android.R.layout.simple_list_item_1
                , str); 

        // 3.AdapterView         
        list.setAdapter(adapter); 

        return rootView;
    }
}