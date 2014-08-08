package com.ddalan.www;

import java.util.ArrayList;

import com.ddalan.gcm.SendPush;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class FriendsTab extends Fragment {
	String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
	String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();
	String name;
	String number;
	ImageView imgView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 친구목록 View를 친구 탭에 붙임
		View friendView = inflater.inflate(R.layout.friendstab, container,
				false);
		// 주소록에서 이름만 가져와서 저장..
		getNumber(getActivity().getContentResolver());

		// 친구목록 그리드뷰에 데이터 바인딩 작업
		imgView=(ImageView)getActivity().findViewById(R.id.frface);
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(), textArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 팝업메뉴.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				new SendPush().execute();
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
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", textArr.get(position));
				intent.putExtra("number", numArr.get(position));
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}// onCreateView

	public void getNumber(ContentResolver cr) {
		Cursor phones = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
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