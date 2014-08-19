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

		// 액션바 숨김!
		actionBar.hide();

		setContentView(R.layout.splash);

		(new Thread(this)).start();
	}// OnCreate

	@Override
	public void run() {
		// 3초 딜레이
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		// 다이얼로그 닫음
		CDialog.hideLoading();

		// 첫로그인 & 기존 사용자 화면 이동 로직 //

		// GCM DeviceID 등록
		final String regId = GCMRegistrar
				.getRegistrationId(getApplicationContext());
		// 등록된 ID가 없으면 ID값을 얻어옵니다
		if (regId.equals("") || regId == null) {
			GCMRegistrar.register(getApplicationContext(), SENDER_ID);
			Intent intent = new Intent(this, WebViewActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			Log.w(TAG, "deviceID Registered : " + regId);
		}

		// 단말기 Back버튼을 눌렀을 때, 인트로 화면으로 돌아오지 않도록 인트로 화면을 종료
		finish();
	}

}
