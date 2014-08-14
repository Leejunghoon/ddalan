package com.ddalan.gcm;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
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


public class SendPush extends AsyncTask<String, Void, Void> {
		
	Context mContext;
	
	public SendPush(Context context){
		mContext = context;
	}
	
	protected Void doInBackground(String... regid) {
			 executeClient(regid[0]);
			return null;
			
		}

		protected void onPostExecute(String result) {
			// ��� �۾��� ��ġ�� ������ �� (�޼ҵ� ���)
		}
		
		// ���� �����ϴ� �κ�
		public String executeClient(String regid) {

			ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
			post.add(new BasicNameValuePair("regID", regid));
			
		
			// ���� HttpClient ��ü ����
			HttpClient client = new DefaultHttpClient();
			
			// ��ü ���� ���� �κ�, ���� �ִ�ð� ���
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			
			// Post��ü ����
			HttpPost httpPost = new HttpPost("http://192.168.0.47:8080/push.do");
			
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post, "UTF-8");
				httpPost.setEntity(entity);
				client.execute(httpPost);
				return EntityUtils.getContentCharSet(entity);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
}