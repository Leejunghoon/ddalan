package com.ddalan.www;


import android.app.Activity;
import android.os.Bundle;

import android.preference.PreferenceFragment;


public class SettingsTab extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settingstab);		
	}
	
public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsTab())
                .commit();
	    }
	}	
}

