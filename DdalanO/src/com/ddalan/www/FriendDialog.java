package com.ddalan.www;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ddalan.server.FaUpdate;

public class FriendDialog extends Activity {

	String name;
	String phone;
	String regId;
	TextView nameView;
	TextView numberView;
	ImageView imageView;
	int position;
	int photoFromIntent;

	public FriendDialog() {
	
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frienddialog);

		Intent intent2 = getIntent();
		position = intent2.getExtras().getInt("position");
		name = intent2.getExtras().getString("name");
		phone = intent2.getExtras().getString("number");
		photoFromIntent = intent2.getExtras().getInt("dialogpic");

		nameView = (TextView) findViewById(R.id.namespace);
		numberView = (TextView) findViewById(R.id.numberspace);
		nameView.setText(name);
		numberView.setText(phone);

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
			Toast.makeText(FriendDialog.this, "��ȭ�ɱ�", Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
					+ phone));
			startActivity(intent);
			break;

		case R.id.btn2:
			new FaUpdate(this).execute(phone);
			Toast.makeText(FriendDialog.this, "즐겨찾기 추가되었습니다.", Toast.LENGTH_SHORT).show();
			
			break;

		case R.id.btn3:
			Intent itt = new Intent(Intent.ACTION_PICK); // ACTION_PICK�׼��� ����ϴ�
															// ��
			itt.setData(Uri.parse("content://com.android.contacts/data/phones"));
			startActivityForResult(itt, 0);

			Toast.makeText(FriendDialog.this, "ģ�� ����", Toast.LENGTH_SHORT).show();
			break;

		}
	}
}