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

		// �׼ǹ� ���!
		actionBar.hide();

		setContentView(R.layout.splash);

		(new Thread(this)).start();
	}// OnCreate

	@Override
	public void run() {
		// 3�� ������
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}

		// ���̾�α� ����
		CDialog.hideLoading();

		// ù�α��� & ���� ����� ȭ�� �̵� ���� //
		
		// GCM DeviceID ���
		final String regId = GCMRegistrar
				.getRegistrationId(getApplicationContext());
		// ��ϵ� ID�� ������ ID���� ���ɴϴ�
		if (regId.equals("") || regId == null) {
			GCMRegistrar.register(getApplicationContext(), SENDER_ID);
			Intent intent = new Intent(this, WebViewActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			Log.w(TAG, "deviceID Registered : " + regId);
		}

		// �ܸ��� Back��ư�� ������ ��, ��Ʈ�� ȭ������ ���ƿ��� �ʵ��� ��Ʈ�� ȭ���� ����
		finish();
	}

}
