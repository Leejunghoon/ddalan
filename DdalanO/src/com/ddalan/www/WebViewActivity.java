package com.ddalan.www;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ddalan.gcm.GcmRegID;

public class WebViewActivity extends Activity {

	
	private WebView webView;
	public Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webveiw);
		mContext = this.getApplicationContext();
		
		String INFO_URL;
		
		INFO_URL ="http://192.168.0.79:8080/index.jsp";
		
		webView = (WebView) findViewById(R.id.webView1);
		//�ȵ�ε� ���信 ������ ����� �ȸ´� ����
		//webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
		//webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
	    webView.getSettings().setJavaScriptEnabled(true);
	    webView.setVerticalScrollBarEnabled(false);
	    webView.setHorizontalScrollBarEnabled(false);
	    webView.setWebViewClient(new MyWebClient());
	    webView.loadUrl(INFO_URL);
	    
		new GcmRegID().getID(mContext);  // RegID ��� Ȯ�� �� �߱޹ޱ� 
	    
	}
	
	class MyWebClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if(url.startsWith("app")){
				
			
				
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
