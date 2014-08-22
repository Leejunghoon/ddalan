package com.ddalan.www;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

	// ////����//////
	SoundPool mPool;
	int mDdok;

	// ////����//////
	Vibrator mVib;

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

				// ////////////�������///////////////
				// mPool.play(mDdok, 1, 1, 0, 0, 1);
				// /////////////�������///////////////
				// mVib.vibrate(500);

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

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		
	    MenuInflater mInflater = getActivity().getMenuInflater();
	    mInflater.inflate(R.menu.pop, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
        case R.id.action_search:
        	Toast.makeText(getActivity(), "action_search Selected", Toast.LENGTH_SHORT).show();
            openSearch();
            return true;
        case R.id.action_settings:
        	Toast.makeText(getActivity(), "action_settings Selected ", Toast.LENGTH_SHORT).show();
            openSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
	}

	private void openSettings() {
		// TODO Auto-generated method stub
		
	}

	private void openSearch() {
		// TODO Auto-generated method stub
		
	}

	
	
}// class