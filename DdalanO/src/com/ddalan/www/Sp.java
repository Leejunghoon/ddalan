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
				// 3�� ������
				try {
					Thread.sleep(2000);
				} catch (Exception e) {}

				// ���̾�α� ����
				CDialog.hideLoading();

				// ȭ�� �̵�
				Intent intent = new Intent(this, WebViewActivity.class);
				startActivity(intent);

				// �ܸ��� Back��ư�� ������ ��, ��Ʈ�� ȭ������ ���ƿ��� �ʵ��� ��Ʈ�� ȭ���� ����
				finish();
			}
	
	
	}


