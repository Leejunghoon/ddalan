package com.ddalan.www;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class SettingsTab extends PreferenceFragment {

	// SharedPreferences.Editor editor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settingstab);

		Preference profilePref = (Preference) findPreference("setAvatar");
		profilePref
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference preference) {

						return false;
					}
				});

		Preference syncList = (Preference) findPreference("syncList");
		syncList.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getActivity(), "친구 목록을 동기화합니다.",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

}
