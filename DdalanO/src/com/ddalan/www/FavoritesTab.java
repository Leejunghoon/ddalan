package com.ddalan.www;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class FavoritesTab extends Fragment {
	ArrayList<String> favNameArr = new ArrayList<String>();
	ArrayList<String> favNumArr = new ArrayList<String>();
	
	ArrayList<String> rareNameArr = new ArrayList<String>();
	ArrayList<String> rareNumArr = new ArrayList<String>();
	boolean spreadList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View friendView = inflater.inflate(R.layout.favoritestab, container,
				false);
		// 주소록에서 이름만 가져와서 저장..
		if (spreadList == false) {
			getFavNumber(getActivity().getContentResolver());
			getRareNumber();
			spreadList = true;
		}

		// 친구목록 그리드뷰에 데이터 바인딩 작업
		GridView rareView = (GridView) friendView.findViewById(R.id.grid2);
		rareView.setAdapter(new FavoritesAdapter(this.getActivity(), rareNameArr, rareNumArr));
		rareView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 토스트.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("으하하");
				Toast.makeText(getActivity(),
						rareNameArr.get(position) + "에게 따란을 전송합니다",
						Toast.LENGTH_SHORT).show();

			}

		});

		rareView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = rareNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				
				System.out.println("길게 누름, " + position + "번 선택됨!!");
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", rareNameArr.get(position));
				intent.putExtra("number", rareNumArr.get(position));
				intent.putExtra("dialogpic", picNo);
				startActivity(intent);
				return false;
				
				

			}

		});
		
		GridView favView = (GridView) friendView.findViewById(R.id.grid);
		favView.setAdapter(new FavoritesAdapter(this.getActivity(), favNameArr,
				favNumArr));
		favView.setOnItemClickListener(new OnItemClickListener() {

			// 친구(Item)를 눌렀을 때(Click) 무엇을 실행할지 메소드 구현. 지금은 토스트.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("으하하");
				Toast.makeText(getActivity(),
						favNameArr.get(position) + "에게 따란을 전송합니다",
						Toast.LENGTH_SHORT).show();

			}

		});

		favView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = favNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				
				System.out.println("길게 누름, " + position + "번 선택됨!!");
				// 투명한 액티비티(WebDialog) 액티비티를 시작한다.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", favNameArr.get(position));
				intent.putExtra("number", favNumArr.get(position));
				intent.putExtra("dialogpic", picNo);
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}

	public void getFavNumber(ContentResolver cr) {

		// db에서 즐겨찾기 친구 가져오는 메서드 작성

		favNameArr.add("징징이1");
		favNameArr.add("징징이2");
		favNameArr.add("징징이3");
		favNameArr.add("징징이4");
		favNameArr.add("징징이5");
		favNameArr.add("징징이6");

		favNumArr.add("0100000001");
		favNumArr.add("0100000002");
		favNumArr.add("0100000003");
		favNumArr.add("0100000004");
		favNumArr.add("0100000005");
		favNumArr.add("0100000006");

	}
	
	public void getRareNumber(){
		
		rareNameArr.add("연락좀해1");
		rareNameArr.add("연락좀해2");
		rareNameArr.add("연락좀해3");
		
		rareNumArr.add("82821");
		rareNumArr.add("82822");
		rareNumArr.add("82823");
	}

}