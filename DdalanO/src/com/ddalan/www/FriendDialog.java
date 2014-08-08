package com.ddalan.www;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FriendDialog extends Activity {

	String name;
	String phone;
	String regId;
	TextView nameView;
	TextView numberView;
	ImageView imgView;
	int position;
	String number;

	public FriendDialog() {
		System.out.println("WebDialog의 디폴트 생성자 안");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webpopup);

		Intent intent2 = getIntent();
		position = intent2.getExtras().getInt("position");
		name = intent2.getExtras().getString("name");
		number = intent2.getExtras().getString("number");

		nameView = (TextView) findViewById(R.id.namespace);
		numberView = (TextView) findViewById(R.id.numberspace);
		nameView.setText(name);
		numberView.setText(number);

		imgView = (ImageView) findViewById(R.id.photospace);
		imgView.setImageResource(R.drawable.defalut);

	}

	public void btnOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:
			Toast.makeText(FriendDialog.this, "전화걸기", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
					+ number));
			startActivity(intent);
			break;

		case R.id.btn2:
			Toast.makeText(FriendDialog.this, "가져온 포지션 값=" + position,
					Toast.LENGTH_SHORT).show();
			break;

		case R.id.btn3:
			Intent itt = new Intent(Intent.ACTION_PICK); // ACTION_PICK액션을 사용하는
															// 예
			itt.setData(Uri.parse("content://com.android.contacts/data/phones"));
			startActivityForResult(itt, 0);

			Toast.makeText(FriendDialog.this, "친구 삭제", Toast.LENGTH_SHORT).show();
			break;

		}

	}
}