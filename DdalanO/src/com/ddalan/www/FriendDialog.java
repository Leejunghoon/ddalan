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
	String[] phoneArr;
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

		System.out.println(phone);
		int pStr = 0;

		String pNoStr = phone.substring(7);
		System.out.println("pNoStr에 들어있는 값은 = " + pNoStr);
		if (pNoStr.length() == 4 || pNoStr.length() == 3
				|| pNoStr.length() == 2) {

			pStr = Integer.parseInt(pNoStr);
		} else {
			pStr = 6582;
		}

		switch (pStr) {

		case 2207:
			imageView.setImageResource(R.drawable.face32);
			break;
		case 75:
			imageView.setImageResource(R.drawable.face10);
			break;
		case 6582:
			imageView.setImageResource(R.drawable.face30);
			break;
		case 2085:
			imageView.setImageResource(R.drawable.face15);
			break;
		case 702:
			imageView.setImageResource(R.drawable.face21);
			break;
		case 5514:
			imageView.setImageResource(R.drawable.face31);
			break;
		case 5408:
			imageView.setImageResource(R.drawable.face12);
			break;
		case 8469:
			imageView.setImageResource(R.drawable.face11);
			break;
		case 475:
			imageView.setImageResource(R.drawable.face20);
			break;
		case 606:
			imageView.setImageResource(R.drawable.face17);
			break;
		case 7047:
			imageView.setImageResource(R.drawable.face13);
			break;
		case 7770:
			imageView.setImageResource(R.drawable.face28);
			break;
		case 3458:
			imageView.setImageResource(R.drawable.face29);
			break;

		default:
			imageView.setImageResource(R.drawable.man6);
			break;

		}

	}

	public void btnOnClick(View view) {
		switch (view.getId()) {
		case R.id.btn1:
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"
					+ phone));
			startActivity(intent);
			break;

		case R.id.btn2:
			new FaUpdate(this).execute(phone);
			Toast.makeText(FriendDialog.this, "즐겨찾기에 추가되었습니다.",
					Toast.LENGTH_SHORT).show();

			break;

		case R.id.btn3:
			Intent itt = new Intent(Intent.ACTION_PICK); // ACTION_PICK占쌓쇽옙占쏙옙
															// 占쏙옙占쏙옙求占�
															// 占쏙옙
			itt.setData(Uri.parse("content://com.android.contacts/data/phones"));
			startActivityForResult(itt, 0);

			Toast.makeText(FriendDialog.this, "삭제되었습니다.", Toast.LENGTH_SHORT)
					.show();
			break;

		}
	}
}