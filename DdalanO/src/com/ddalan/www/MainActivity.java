package com.ddalan.www;

import com.ddalan.gcm.AddUser;
import com.ddalan.gcm.GcmRegID;
import com.ddalan.www.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends FragmentActivity {

	// 탭들을 등록하고 관리하는 Activity 하나.
	// 탭들을 표현할 Fragment 들이 탭의 개수만큼
	// TabListener 하나.

	// Tab 변수 선언
	ActionBar.Tab Tab1, Tab2, Tab3, Tab4;
	Fragment fragmentTab1 = new FavoritesTab();
	Fragment fragmentTab2 = new FriendsTab();
	Fragment fragmentTab3 = new StatusTab();
	Fragment fragmentTab4 = new SettingsTab();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		class Sp{}
		
		ActionBar actionBar = getActionBar();
		
		// 액션바의 기본로고아이콘 숨기기 _이것을 생략하면 오류 발생
		actionBar.setDisplayShowHomeEnabled(false);
		// 액션바의 기본타이틀 숨기기 _이것을 생략하면 오류 발생
		actionBar.setDisplayShowTitleEnabled(false);
		// 가장 중요한 ActionBar모드를 ActionBar.NAVIGATION_MODE_TABS로 설정해주면 간단하게 탭을
		// 구현할수 있다.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// 탭 아이콘과 타이틀을 설정
		Tab1 = actionBar.newTab().setIcon(R.drawable.star);
		Tab2 = actionBar.newTab().setIcon(R.drawable.user);
		Tab3 = actionBar.newTab().setIcon(R.drawable.stats);
		Tab4 = actionBar.newTab().setIcon(R.drawable.set);

		// 탭 리스너 설정
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));
		Tab4.setTabListener(new TabListener(fragmentTab4));

		// 액션바에 탭추가
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		actionBar.addTab(Tab4);

	

		
																	

	}

}
