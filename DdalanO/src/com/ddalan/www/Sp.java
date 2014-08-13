package com.ddalan.www;


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
				// 3초 딜레이
				try {
					Thread.sleep(2000);
				} catch (Exception e) {}

				// 다이얼로그 닫음
				CDialog.hideLoading();

				// 화면 이동
				Intent intent = new Intent(this, WebViewActivity.class);
				startActivity(intent);

				// 단말기 Back버튼을 눌렀을 때, 인트로 화면으로 돌아오지 않도록 인트로 화면을 종료
				finish();
			}
	
	
	}


