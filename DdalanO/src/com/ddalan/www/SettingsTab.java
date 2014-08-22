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
	Preference setProfile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.settingstab);

		setProfile = (Preference) findPreference("picphoto");
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
				Toast.makeText(getActivity(), "친구 목록을 동기화합니다.",
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
		System.out.println("onActivityResult");
		if(requestCode==GET_PICTURE_URI){
			System.out.println("if 1");
			setProfile.setIcon(R.drawable.man);
	}

}
}
