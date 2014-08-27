package com.ddalan.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;


public class FaUpdate extends AsyncTask<String, Void, Void> {
		
	Context mContext;
	
	public FaUpdate(Context context){
		mContext = context;
	}
	
	protected Void doInBackground(String... phone) {
			 executeClient(phone[0]);
			return null;
			
		}

		protected void onPostExecute(String result) {
			// 占쏙옙占�占쌜억옙占쏙옙 占쏙옙치占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙 (占쌨소듸옙 占쏙옙占�
		}
		
		// 占쏙옙占쏙옙 占쏙옙占쏙옙求占�占싸븝옙
		public String executeClient(String num) {
			InputStream content;
			ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
			post.add(new BasicNameValuePair("phone", num));
			
		
			// 占쏙옙占쏙옙 HttpClient 占쏙옙체 占쏙옙
			HttpClient client = new DefaultHttpClient();
			
			// 占쏙옙체 占쏙옙占쏙옙 占쏙옙占쏙옙 占싸븝옙, 占쏙옙占쏙옙 占쌍댐옙챨占�占쏙옙占�
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			
			// Post
			HttpPost httpPost = new HttpPost("http://192.168.43.63:8080/FaUpdate.do");
			
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");
				httpPost.setEntity(entity);
				client.execute(httpPost);
				
				return EntityUtils.getContentCharSet(entity);
		
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
}