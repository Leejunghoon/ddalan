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
		// �ּҷϿ��� �̸��� �����ͼ� ����..
		getFavNumber(getActivity().getContentResolver());

		// ģ����� �׸���信 ������ ���ε� �۾�
		GridView gridView = (GridView) friendView.findViewById(R.id.grid);
		gridView.setAdapter(new FavoritesAdapter(this.getActivity(), textArr,
				numArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �佺Ʈ.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("������");
				Toast.makeText(getActivity(),
						textArr.get(position) + "���� ������ �����մϴ�",
						Toast.LENGTH_SHORT).show();

			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("��� ����, " + position + "�� ���õ�!");
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
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

		// db���� ���ã�� ģ�� �������� �޼��� �ۼ�

		textArr.add("¡¡��1");
		textArr.add("¡¡��2");
		textArr.add("¡¡��3");
		textArr.add("¡¡��4");
		textArr.add("¡¡��5");
		textArr.add("¡¡��6");

		numArr.add("0100000001");
		numArr.add("0100000002");
		numArr.add("0100000003");
		numArr.add("0100000004");
		numArr.add("0100000005");
		numArr.add("0100000006");

	}

}