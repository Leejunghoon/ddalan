package com.ddalan.www;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

	// �ǵ��� ����ϰ� ���ϴ� Activity �ϳ�.
	// �ǵ��� ǥ���� Fragment ���� ���� ������ŭ
	// TabListener �ϳ�.

	// Tab ���� ����
	ActionBar.Tab Tab1, Tab2, Tab3, Tab4;
	Fragment fragmentTab1 = new FriendsTab();
	Fragment fragmentTab2 = new FavoritesTab();
	Fragment fragmentTab3 = new HistoryTab();
	Fragment fragmentTab4 = new SettingsTab();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();

		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#ffcd00")));

		// �׼ǹ��� �⺻�ΰ������ ����
		actionBar.setDisplayShowHomeEnabled(true);
		// �׼ǹ��� �⺻Ÿ��Ʋ ����
		actionBar.setDisplayShowTitleEnabled(true);
		// ���� �߿��� ActionBar��带 ActionBar.NAVIGATION_MODE_TABS�� �������ָ� �����ϰ� ����
		// �����Ҽ� �ִ�.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// �� �����ܰ� Ÿ��Ʋ�� ����
		Tab1 = actionBar.newTab().setIcon(R.drawable.fav);
		Tab2 = actionBar.newTab().setIcon(R.drawable.user);
		Tab3 = actionBar.newTab().setIcon(R.drawable.history);
		Tab4 = actionBar.newTab().setIcon(R.drawable.set);

		// �� ������ ����
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));
		Tab4.setTabListener(new TabListener(fragmentTab4));

		// �׼ǹٿ� ���߰�
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		actionBar.addTab(Tab4);

	}

}
