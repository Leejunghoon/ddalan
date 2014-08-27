package com.ddalan.www;

import java.util.ArrayList;

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
import android.widget.Toast;

import com.ddalan.gcm.SendPush;

public class FriendsTab extends Fragment {
	String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
	String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();

	String name;
	String number;
	boolean spreadList; // 친구 목록을 false일 때 한 번만 불러오고, 불러온 뒤에는 true로 바꾸게 설정

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

		// 친구목록 서버에 저장

		// 친구목록 그리드뷰에 데이터 바인딩 작업
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(), textArr,
				numArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 팝업메뉴.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				new SendPush(getActivity()).execute(numArr.get(position));

				Intent intent = new Intent(getActivity(), Frame_animation.class);

				startActivity(intent);
				/*
				 * Toast.makeText(getActivity(), textArr.get(position) +
				 * "에게 따란을 전송합니다.", Toast.LENGTH_SHORT).show();
				 */
			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("길게 누름, " + position + "번 선택됨.");
				String picStr = numArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", textArr.get(position));
				intent.putExtra("number", numArr.get(position));
				intent.putExtra("dialogpic", picNo);
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}// onCreateView

	// 전화번호부에서 이름, 전화번호를 가져옴(ContactsContract.CommonDataKinds 이용)
	public void getNumber(ContentResolver cr) {
		/*
		 * Cursor phones = cr.query(
		 * ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
		 * null); // use the cursor to access the contacts
		 * 
		 * name = phones .getString(phones
		 * .getColumnIndex(ContactsContract.CommonDataKinds
		 * .Phone.DISPLAY_NAME)); number = phones .getString(phones
		 * .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		 */
		textArr.add("이정훈");
		numArr.add("010-4811-0702");
		textArr.add("조현성");
		numArr.add("010-4173-6582");
		textArr.add("남기환");
		numArr.add("010-5605-3458");
		textArr.add("이슬이");
		numArr.add("010-7111-2207");
		textArr.add("김새봄");
		numArr.add("010-5113-5408");
		textArr.add("이기쁨");
		numArr.add("010-4274-0075");
		textArr.add("임새샘");
		numArr.add("010-9972-5514");
		textArr.add("곽재호");
		numArr.add("010-9525-8390");
		textArr.add("김영길");
		numArr.add("010-3586-8469");
		textArr.add("박종호");
		numArr.add("010-5294-2085");
		textArr.add("채예지");
		numArr.add("010-5474-0475");
		textArr.add("길민경");
		numArr.add("010-5541-7537");
		textArr.add("노영수");
		numArr.add("010-2912-0606");
		textArr.add("진현범");
		numArr.add("010-4860-7047");
		textArr.add("김용년");
		numArr.add("010-9976-2772");
		textArr.add("이형운");
		numArr.add("010-4119-1273");
		textArr.add("김 용");
		numArr.add("010-6600-7770");
		textArr.add("정혜림");
		numArr.add("010-7707-0725");
	}

}// class