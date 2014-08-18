package com.ddalan.www;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
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

public class FavoritesTab extends Fragment {
	String DISPLAY_NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
	String PHONE_NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();
	String name;
	String number;
	int photo = R.drawable.woman5;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View friendView = inflater.inflate(R.layout.favoritestab, container,
				false);
		// 주소록에서 이름만 가져와서 저장..
		getFavNumber(getActivity().getContentResolver());

		// 친구목록 그리드뷰에 데이터 바인딩 작업
		GridView gridView = (GridView) friendView.findViewById(R.id.grid);
		gridView.setAdapter(new FavoritesAdapter(this.getActivity(), textArr,
				numArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 토스트.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("으하하");
				Toast.makeText(getActivity(),
						textArr.get(position) + "에게 따란을 전송합니다",
						Toast.LENGTH_SHORT).show();

			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("길게 누름, " + position + "번 선택됨!");
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", textArr.get(position));
				intent.putExtra("number", numArr.get(position));
				intent.putExtra("photo", photo);
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}

	public void getFavNumber(ContentResolver cr) {

		// db에서 즐겨찾기 친구 가져오는 메서드 작성

		textArr.add("징징이1");
		textArr.add("징징이2");
		textArr.add("징징이3");
		textArr.add("징징이4");
		textArr.add("징징이5");
		textArr.add("징징이6");

		numArr.add("0100000001");
		numArr.add("0100000002");
		numArr.add("0100000003");
		numArr.add("0100000004");
		numArr.add("0100000005");
		numArr.add("0100000006");

	}

}