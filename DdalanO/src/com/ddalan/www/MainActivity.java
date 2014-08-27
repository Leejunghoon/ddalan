package com.ddalan.www;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

	// 占실듸옙占쏙옙 占쏙옙占쏙옙構占�占쏙옙占싹댐옙 Activity 占싹놂옙.
	// 占실듸옙占쏙옙 표占쏙옙占쏙옙 Fragment 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙큼
	// TabListener 占싹놂옙.

	// Tab 占쏙옙占쏙옙 占쏙옙占쏙옙
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

		// 占쌓션뱄옙占쏙옙 占썩본占싸곤옙占쏙옙占쏙옙占�占쏙옙占쏙옙
		actionBar.setDisplayShowHomeEnabled(true);
		// 占쌓션뱄옙占쏙옙 占썩본타占쏙옙틀 占쏙옙占쏙옙
		actionBar.setDisplayShowTitleEnabled(true);
		// 占쏙옙占쏙옙 占쌩울옙占쏙옙 ActionBar占쏙옙躍�ActionBar.NAVIGATION_MODE_TABS占쏙옙 占쏙옙占쏙옙占쏙옙占쌍몌옙 占쏙옙占쏙옙占싹곤옙 占쏙옙占쏙옙
		// 占쏙옙占쏙옙占쌀쇽옙 占쌍댐옙.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// 占쏙옙 占쏙옙占쏙옙占쌤곤옙 타占쏙옙틀占쏙옙 占쏙옙占쏙옙
		Tab1 = actionBar.newTab().setIcon(R.drawable.fav);
		Tab2 = actionBar.newTab().setIcon(R.drawable.user);
		Tab3 = actionBar.newTab().setIcon(R.drawable.history);
		Tab4 = actionBar.newTab().setIcon(R.drawable.set);

		// 占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));
		Tab4.setTabListener(new TabListener(fragmentTab4));

		// 占쌓션바울옙 占쏙옙占쌩곤옙
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);
		actionBar.addTab(Tab4);

	}

	public void changeState(View v){
		Button btn=(Button)findViewById(R.id.change);
		if(btn.getText()=="받은 따란"){
			btn.setText("보낸 따란");	
		}else if(btn.getText()=="보낸 따란"){
			btn.setText("받은 따란");	
		}

	}
	
}
