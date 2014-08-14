package com.ddalan.www;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.ddalan.gcm.GcmRegID;
import com.ddalan.gcm.SendPush;

public class FriendsTab extends Fragment {
	String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
	String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();
	String name;
	String number;
	boolean spreadList; // 친구 목록을 false일 때 한 번만 불러오고, 불러온 뒤에는 true로 바꾸게 설정

	// ////사운드//////
	SoundPool mPool;
	int mDdok;

	// ////진동//////
	Vibrator mVib;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 친구목록 View를 친구 탭에 붙임
		View friendView = inflater.inflate(R.layout.friendstab, container,
				false);
		// 주소록에서 이름만 가져와서 저장..
		if (spreadList == false) {
			getNumber(getActivity().getContentResolver());
			spreadList = true;

		}

		// ////////////////////사운드//////////////////////////
		mPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		mDdok = mPool.load(getActivity(), R.raw.ddaran, 1);

		// /////////////////////진동//////////////////////////
		mVib = (Vibrator) getActivity().getSystemService(
				Context.VIBRATOR_SERVICE);

		// 친구목록 서버에 저장

		// 친구목록 그리드뷰에 데이터 바인딩 작업
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(), textArr, numArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 팝업메뉴.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				new SendPush(getActivity()).execute(new GcmRegID()
						.getID(getActivity()));

				// ////////////사운드재생///////////////
				// mPool.play(mDdok, 1, 1, 0, 0, 1);
				// /////////////진동재생///////////////
				// mVib.vibrate(500);

				String numBer = numArr.get(position);

				Toast.makeText(getActivity(),
						textArr.get(position) + "에게 따란을 전송합니다",
						Toast.LENGTH_SHORT).show();
			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("길게 누름, " + position + "번 선택됨.");
				String picStr = numArr.get(position).substring(10);
				int picNo = Integer.parseInt(picStr);
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", textArr.get(position));
				intent.putExtra("number", numArr.get(position));
				intent.putExtra("dialogpic", FriendAdapter.pic);
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}// onCreateView

	// 전화번호부에서 이름, 전화번호를 가져옴(ContactsContract.CommonDataKinds 이용)
	public void getNumber(ContentResolver cr) {
		Cursor phones = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		// use the cursor to access the contacts
		while (phones.moveToNext()) {
			name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			number = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			textArr.add(name);
			numArr.add(number);
		}

	}

}// class