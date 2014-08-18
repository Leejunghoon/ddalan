package com.ddalan.www;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
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
        
       progressBar = (ProgressBar)getActivity().findViewById(R.id.progressbar1);
       progressBar = (ProgressBar)getActivity().findViewById(R.id.progressbar2);
       progressBar = (ProgressBar)getActivity().findViewById(R.id.progressbar3);
       progressBar = (ProgressBar)getActivity().findViewById(R.id.progressbar4);
        
//		getActivity().findViewById(R.id.btn1).setOnClickListener(clickListener);
// 		getActivity().findViewById(R.id.btn2).setOnClickListener(clickListener);
 		
        return rootView;
    }	
  
	private View.OnClickListener clickListener = new View.OnClickListener() {
  		
  		public void onClick(View v) {
  			if(v.getId()==R.id.btn1){
  				progressBar.incrementProgressBy(5);
 			} else if(v.getId()==R.id.btn2){
 				progressBar.incrementSecondaryProgressBy(5);
  			}
 		}
 	};
}


