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
	ImageView imageView;
	int position;
	String number;
	int photoFromIntent;

	public FriendDialog() {
		System.out.println("WebDialog의 디폴트 생성자 안");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frienddialog);

		Intent intent2 = getIntent();
		position = intent2.getExtras().getInt("position");
		name = intent2.getExtras().getString("name");
		number = intent2.getExtras().getString("number");
		photoFromIntent = intent2.getExtras().getInt("dialogpic");

		nameView = (TextView) findViewById(R.id.namespace);
		numberView = (TextView) findViewById(R.id.numberspace);
		nameView.setText(name);
		numberView.setText(number);

		imageView = (ImageView) findViewById(R.id.photospace);
		
		switch (photoFromIntent) {
		
		case 0:
			imageView.setImageResource(R.drawable.man5);
			break;
			
		case 1:
			imageView.setImageResource(R.drawable.man6);
			break;

		case 2:
			imageView.setImageResource(R.drawable.man7);
			break;
			
		case 3:
			imageView.setImageResource(R.drawable.man8);
			break;
			
		case 4:
			imageView.setImageResource(R.drawable.man9);
			break;
			
		case 5:
			imageView.setImageResource(R.drawable.woman5);
			break;
			
		case 6:
			imageView.setImageResource(R.drawable.woman6);
			break;
			
		case 7:
			imageView.setImageResource(R.drawable.woman7);
			break;
			
		default:
			imageView.setImageResource(R.drawable.woman8);
			break;
			
		}

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