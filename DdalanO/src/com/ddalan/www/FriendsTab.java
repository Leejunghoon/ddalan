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
	boolean spreadList; // ģ�� ����� false�� �� �� ���� �ҷ�����, �ҷ��� �ڿ��� true�� �ٲٰ� ����

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ģ����� View�� ģ�� �ǿ� ����
		View friendView = inflater.inflate(R.layout.friendstab, container,
				false);
		// �ּҷϿ��� �̸��� �����ͼ� ����..
		if (spreadList == false) {
			getNumber(getActivity().getContentResolver());
			spreadList = true;

		}

		// ģ����� ������ ����

		// ģ����� �׸���信 ������ ���ε� �۾�
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(), textArr,
				numArr));
		gridView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �˾��޴�.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				new SendPush(getActivity()).execute(numArr.get(position));

				Intent intent = new Intent(getActivity(), Frame_animation.class);

				startActivity(intent);
				/*
				 * Toast.makeText(getActivity(), textArr.get(position) +
				 * "���� ������ �����մϴ�.", Toast.LENGTH_SHORT).show();
				 */
			}

		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("��� ����, " + position + "�� ���õ�.");
				String picStr = numArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
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

	// ��ȭ��ȣ�ο��� �̸�, ��ȭ��ȣ�� ������(ContactsContract.CommonDataKinds �̿�)
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
		textArr.add("������");
		numArr.add("010-4811-0702");
		textArr.add("������");
		numArr.add("010-4173-6582");
		textArr.add("����ȯ");
		numArr.add("010-5605-3458");
		textArr.add("�̽���");
		numArr.add("010-7111-2207");
		textArr.add("�����");
		numArr.add("010-5113-5408");
		textArr.add("�̱��");
		numArr.add("010-4274-0075");
		textArr.add("�ӻ���");
		numArr.add("010-9972-5514");
		textArr.add("����ȣ");
		numArr.add("010-9525-8390");
		textArr.add("�迵��");
		numArr.add("010-3586-8469");
		textArr.add("����ȣ");
		numArr.add("010-5294-2085");
		textArr.add("ä����");
		numArr.add("010-5474-0475");
		textArr.add("��ΰ�");
		numArr.add("010-5541-7537");
		textArr.add("�뿵��");
		numArr.add("010-2912-0606");
		textArr.add("������");
		numArr.add("010-4860-7047");
		textArr.add("����");
		numArr.add("010-9976-2772");
		textArr.add("������");
		numArr.add("010-4119-1273");
		textArr.add("�� ��");
		numArr.add("010-6600-7770");
		textArr.add("������");
		numArr.add("010-7707-0725");
	}

}// class