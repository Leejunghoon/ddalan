package com.ddalan.www;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class Sp extends Activity implements Runnable {

	private static final String TAG = "GCM";
	private static final String SENDER_ID = "926061344928";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getActionBar();

		// 占쌓션뱄옙 占쏙옙占�
		actionBar.hide();

		setContentView(R.layout.splash);

		(new Thread(this)).start();
	}// OnCreate

	@Override
	public void run() {
		// 3占쏙옙 占쏙옙占쏙옙占쏙옙
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		// 占쏙옙占싱억옙慣占�占쏙옙占쏙옙
		CDialog.hideLoading();

		// 첫占싸깍옙占쏙옙 & 占쏙옙占쏙옙 占쏙옙占쏙옙占�화占쏙옙 占싱듸옙 占쏙옙占쏙옙 //

		// GCM DeviceID 占쏙옙占�
		final String regId = GCMRegistrar
				.getRegistrationId(getApplicationContext());
		// 占쏙옙溝占�ID占쏙옙 占쏙옙占쏙옙占쏙옙 ID占쏙옙占쏙옙 占쏙옙占심니댐옙
		if (regId.equals("") || regId == null) {
			GCMRegistrar.register(getApplicationContext(), SENDER_ID);
			Intent intent = new Intent(this, WebViewActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			Log.w(TAG, "deviceID Registered : " + regId);
		}

		// 占쌤몌옙占쏙옙 Back占쏙옙튼占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙, 占쏙옙트占쏙옙 화占쏙옙占쏙옙占쏙옙 占쏙옙占싣울옙占쏙옙
		// 占십듸옙占쏙옙 占쏙옙트占쏙옙 화占쏙옙占쏙옙 占쏙옙占쏙옙
		finish();
	}

}
