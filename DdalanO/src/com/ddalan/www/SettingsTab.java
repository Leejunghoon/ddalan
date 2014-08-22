package com.ddalan.www;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import android.preference.RingtonePreference;
import android.widget.Toast;

public class SettingsTab extends PreferenceFragment {
	// SharedPreferences.Editor editor;
	public static final int GET_PICTURE_URI = 0;
	Uri uri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settingstab);

		Preference setProfile = (Preference) findPreference("picphoto");
		setProfile
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference preference) {

						Intent galleryIntent = new Intent(
								Intent.ACTION_PICK,
								android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(galleryIntent, GET_PICTURE_URI);
						return false;
					}
				});
		setProfile.setIcon(R.drawable.woman);

		Preference syncList = (Preference) findPreference("syncList");
		syncList.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			@Override
			public boolean onPreferenceClick(Preference preference) {
				Toast.makeText(getActivity(), "친구 목록을 동기화합니다..",
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		RingtonePreference ringtonePref = (RingtonePreference) findPreference("selectRingtone");
		ringtonePref
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {

					@Override
					public boolean onPreferenceClick(Preference preference) {

						return false;
					}
				});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode==GET_PICTURE_URI){
			if(requestCode==getActivity().RESULT_OK){
				Uri uri=data.getData();
				this.uri=uri;
			}
		}
	}

}
