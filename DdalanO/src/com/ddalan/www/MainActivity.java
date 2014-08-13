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

	// �ǵ��� ����ϰ� �����ϴ� Activity �ϳ�.
	// �ǵ��� ǥ���� Fragment ���� ���� ������ŭ
	// TabListener �ϳ�.

	// Tab ���� ����
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
		
		// �׼ǹ��� �⺻�ΰ������ ����� _�̰��� �����ϸ� ���� �߻�
		actionBar.setDisplayShowHomeEnabled(false);
		// �׼ǹ��� �⺻Ÿ��Ʋ ����� _�̰��� �����ϸ� ���� �߻�
		actionBar.setDisplayShowTitleEnabled(false);
		// ���� �߿��� ActionBar��带 ActionBar.NAVIGATION_MODE_TABS�� �������ָ� �����ϰ� ����
		// �����Ҽ� �ִ�.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// �� �����ܰ� Ÿ��Ʋ�� ����
		Tab1 = actionBar.newTab().setIcon(R.drawable.star);
		Tab2 = actionBar.newTab().setIcon(R.drawable.user);
		Tab3 = actionBar.newTab().setIcon(R.drawable.stats);
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
