package com.ddalan.www;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	// 탭들을 등록하고 관리하는 Activity 하나.
	// 탭들을 표현할 Fragment 들이 탭의 개수만큼
	// TabListener 하나.

	// Tab 변수 선언
	ActionBar.Tab Tab1, Tab2, Tab3, Tab4;
	Fragment fragmentTab1 = new FavoritesTab();
	Fragment fragmentTab2 = new FriendsTab();
	Fragment fragmentTab3 = new HistoryTab();
	Fragment fragmentTab4 = new SettingsTab();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();

		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#ffcd00")));

		// 액션바의 기본로고아이콘 숨기기
		actionBar.setDisplayShowHomeEnabled(true);
		// 액션바의 기본타이틀 숨기기
		actionBar.setDisplayShowTitleEnabled(true);
		// 가장 중요한 ActionBar모드를 ActionBar.NAVIGATION_MODE_TABS로 설정해주면 간단하게 탭을
		// 구현할수 있다.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// 탭 아이콘과 타이틀을 설정
		Tab1 = actionBar.newTab().setIcon(R.drawable.fav);
		Tab2 = actionBar.newTab().setIcon(R.drawable.user);
		Tab3 = actionBar.newTab().setIcon(R.drawable.history);
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
