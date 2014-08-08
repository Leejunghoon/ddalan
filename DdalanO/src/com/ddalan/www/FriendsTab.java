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
		// ģ����� View�� ģ�� �ǿ� ����
		View friendView = inflater.inflate(R.layout.friendstab, container,
				false);
		// �ּҷϿ��� �̸��� �����ͼ� ����..
		getNumber(getActivity().getContentResolver());

		// ģ����� �׸���信 ������ ���ε� �۾�
		imgView=(ImageView)getActivity().findViewById(R.id.frface);
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(), textArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �˾��޴�.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				new SendPush().execute();
				Toast.makeText(getActivity(),
						textArr.get(position) + "���� ������ �����մϴ�",
						Toast.LENGTH_SHORT).show();
			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("��� ����, " + position + "�� ���õ�.");
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
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