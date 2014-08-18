package com.ddalan.www;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class StatusTab extends Fragment {
	
	ProgressBar progressBar;
	
  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statustab, container, false);
        
        progressBar = (ProgressBar)getActivity().findViewById(R.id.progressbar);
  
        return rootView;
    }	
}
