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
		// �ּҷϿ��� �̸��� �����ͼ� ����..
		if (spreadList == false) {
			getFavNumber(getActivity().getContentResolver());
			getRareNumber();
			spreadList = true;
		}

		// ģ����� �׸���信 ������ ���ε� �۾�
		GridView rareView = (GridView) friendView.findViewById(R.id.grid2);
		rareView.setAdapter(new FavoritesAdapter(this.getActivity(), rareNameArr, rareNumArr));
		rareView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �佺Ʈ.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("������");
				Toast.makeText(getActivity(),
						rareNameArr.get(position) + "���� ������ �����մϴ�",
						Toast.LENGTH_SHORT).show();

			}

		});

		rareView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = rareNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				
				System.out.println("��� ����, " + position + "�� ���õ�!!");
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
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

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �佺Ʈ.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("������");
				Toast.makeText(getActivity(),
						favNameArr.get(position) + "���� ������ �����մϴ�",
						Toast.LENGTH_SHORT).show();

			}

		});

		favView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = favNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				
				System.out.println("��� ����, " + position + "�� ���õ�!!");
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
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

		// db���� ���ã�� ģ�� �������� �޼��� �ۼ�

		favNameArr.add("¡¡��1");
		favNameArr.add("¡¡��2");
		favNameArr.add("¡¡��3");
		favNameArr.add("¡¡��4");
		favNameArr.add("¡¡��5");
		favNameArr.add("¡¡��6");

		favNumArr.add("0100000001");
		favNumArr.add("0100000002");
		favNumArr.add("0100000003");
		favNumArr.add("0100000004");
		favNumArr.add("0100000005");
		favNumArr.add("0100000006");

	}
	
	public void getRareNumber(){
		
		rareNameArr.add("��������1");
		rareNameArr.add("��������2");
		rareNameArr.add("��������3");
		
		rareNumArr.add("82821");
		rareNumArr.add("82822");
		rareNumArr.add("82823");
	}

}