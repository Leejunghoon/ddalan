package com.ddalan.www;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.ddalan.gcm.SendPush;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;



public class FriendsTab extends Fragment {
	String _ID = ContactsContract.Contacts._ID;
	String	DISPLAY_NAME =ContactsContract.Contacts.DISPLAY_NAME;
	ArrayList<String> textArr = new ArrayList<String>();	 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		//친구목록 View를 친구 탭에 붙임
		View friendView = inflater.inflate(R.layout.friendstab, container, false);
		
		// 주소록에서 이름만 가져와서 저장..
		Cursor c = getData();
		if(c.getCount() > 0){
			while(c.moveToNext()){
				String contact_id = c.getString(c.getColumnIndex(_ID));
				String name = c.getString(c.getColumnIndex(DISPLAY_NAME));
				textArr.add(name);
			}
		}
			
				
		// 친구목록 그리드뷰에 데이터 바인딩 작업 
		GridView gridView = (GridView) friendView.findViewById(R.id.GridView02);
		gridView.setAdapter(new FriendAdapter(this.getActivity(),textArr));
		
		
		gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
		gridView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.pop, menu);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {

				}
				return false;
			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {

			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final int _position = position;
				PopupMenu popup = new PopupMenu(getActivity(), view);
				MenuInflater inflater = popup.getMenuInflater();
				inflater.inflate(R.menu.pop, popup.getMenu());
				popup.show();
				popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						item.getItemId();
						switch (item.getItemId()) {
						case R.id.one:
							Toast.makeText(getActivity(),
									_position + "AAAA",
									Toast.LENGTH_SHORT).show();
							break;
						case R.id.two:
								new SendPush().execute();
							
							break;
						case R.id.three:
							Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("tel:010-3893-0942"));
							startActivity(intent);
							break;
						case R.id.four:
							Toast.makeText(getActivity(),
									_position+ "FAV Add",
									Toast.LENGTH_SHORT).show();
						
						}// switch
						return true;

					}// onMenuItemClick

				}// OnMenuItemClickListener()
				);
			}

		});
		return friendView;
	}// onCreateView
	
	
	@SuppressWarnings("unused")
	private Cursor getData(){
	
		Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
				
		StringBuffer output = new StringBuffer();
		
		ContentResolver contentResolver = getActivity().getContentResolver();
		
		return contentResolver.query(CONTENT_URI, null, null, null, null);
	}
	
	
}// class