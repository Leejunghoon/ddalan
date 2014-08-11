package com.ddalan.www;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ddalan.gcm.AddUser;
import com.ddalan.gcm.GcmRegID;

public class WebViewActivity extends Activity {

	String[] user =new String[4];
	private WebView webView;
	public Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webveiw);
		mContext = this.getApplicationContext();
	  
		//내 이메일 정보 가져오기
		Account[] accounts = AccountManager.get(mContext).getAccountsByType("com.google");
	    String[] addresses = new String[accounts.length];
	    for (int i = 0; i < accounts.length; i++) { 
	        addresses[i] = accounts[i].name;
	    }
	    //내 전화번호 가져오기
	    TelephonyManager systemService = (TelephonyManager)getSystemService    (Context.TELEPHONY_SERVICE);
	    String myNumber = systemService.getLine1Number();
	
	    Log.w("test", "phone!!!!!!"+myNumber +"address!!!!!"+addresses[0]);
	    
	    String INFO_URL;
		
		INFO_URL ="http://192.168.0.79:8080/index.jsp";
		
		webView = (WebView) findViewById(R.id.webView1);
		//안드로드 웹뷰에 컨텐츠 사이즈가 안맞는 문제
		//webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		//webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	    webView.getSettings().setJavaScriptEnabled(true);
	    webView.setVerticalScrollBarEnabled(false);
	    webView.setHorizontalScrollBarEnabled(false);
	    webView.setWebViewClient(new MyWebClient());
	    webView.loadUrl(INFO_URL);
	    
	
	
		user[0] = new GcmRegID().getID(mContext); //regid
		user[1] =addresses[0]; // email
		user[2] = myNumber; // phone
	
		
	}
	
	class MyWebClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if(url.startsWith("app")){
					
				new AddUser(mContext).execute(user[0],user[1],user[2]); //서버에 유저 정보 보내기
				
				Intent intent = new Intent(mContext.getApplicationContext(),MainActivity.class );
				startActivity(intent);
				return true;
			}
			else{
				view.loadUrl(url);
				return true;
			}
		}
	}

}
