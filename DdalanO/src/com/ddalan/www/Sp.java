package com.ddalan.www;


import com.ddalan.gcm.GcmRegID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Sp extends Activity implements Runnable{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			setContentView(R.layout.splash);
			
						
			(new Thread(this)).start();
			
		
		
			
	}// OnCreate

	
			@Override
			public void run() {
				//5�� ������
				try {
					Thread.sleep(10000);
					
				} catch (Exception e) {}

				// ���̾�α� ����
				CDialog.hideLoading();

				// GCM�� device ID�� ��ϵ� �ִٸ� ȸ������â �н�..
				if(new GcmRegID().getID(this) != null){
					Intent intent = new Intent(getApplicationContext(),MainActivity.class );
					startActivity(intent);
				}

				// �ܸ��� Back��ư�� ������ ��, ��Ʈ�� ȭ������ ���ƿ��� �ʵ��� ��Ʈ�� ȭ���� ����
				finish();
			}
	
	
	}


