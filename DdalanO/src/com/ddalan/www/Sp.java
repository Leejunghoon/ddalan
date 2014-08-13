package com.ddalan.www;


import com.ddalan.gcm.GcmRegID;
import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Sp extends Activity implements Runnable{

	private static final String TAG = "GCM";
	private static final String SENDER_ID = "926061344928";	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.splash);
			
			(new Thread(this)).start();
	}// OnCreate

	
			@Override
			public void run() {
				// 3�� ������
				try {
					Thread.sleep(4000);
				} catch (Exception e) {}

				// ���̾�α� ����
				CDialog.hideLoading();
				
				
					
					  //GCM DeviceID ���
				   final String regId = GCMRegistrar.getRegistrationId(getApplicationContext());
				  	//��ϵ� ID�� ������ ID���� ���ɴϴ�
				   if(regId.equals("") || regId == null){
				  		GCMRegistrar.register(getApplicationContext(), SENDER_ID);
				  		Intent intent = new Intent(this, WebViewActivity.class);
						startActivity(intent);
				  }else{
					  Intent intent = new Intent(this, MainActivity.class);
					  startActivity(intent);
					  Log.w(TAG, "deviceID Registered : " + regId);
				  }
				
				   // ù�α��� & ���� ����� ȭ�� �̵� ����
				
					
		
					
		
				
				
				// �ܸ��� Back��ư�� ������ ��, ��Ʈ�� ȭ������ ���ƿ��� �ʵ��� ��Ʈ�� ȭ���� ����
				finish();
			}
	
	
	}


