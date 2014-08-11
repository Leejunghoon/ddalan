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
				//5초 딜레이
				try {
					Thread.sleep(10000);
					
				} catch (Exception e) {}

				// 다이얼로그 닫음
				CDialog.hideLoading();

				// GCM에 device ID가 등록되 있다면 회원가입창 패스..
				if(new GcmRegID().getID(this) != null){
					Intent intent = new Intent(getApplicationContext(),MainActivity.class );
					startActivity(intent);
				}

				// 단말기 Back버튼을 눌렀을 때, 인트로 화면으로 돌아오지 않도록 인트로 화면을 종료
				finish();
			}
	
	
	}


