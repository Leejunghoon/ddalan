package com.ddalan.gcm;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;


public class SendPush extends AsyncTask<Void, Void, Void> {
		protected Void doInBackground(Void... unused) {
			 executeClient();
			return null;
			
		}

		protected void onPostExecute(String result) {
			// ��� �۾��� ��ġ�� ������ �� (�޼ҵ� ���)
		}
		
		// ���� �����ϴ� �κ�
		public String executeClient() {

			// ���� HttpClient ��ü ����
			HttpClient client = new DefaultHttpClient();
			
			
			// Post��ü ����
			HttpPost httpPost = new HttpPost("http://192.168.0.79:8080/addUser.do");
			
			try {
				client.execute(httpPost);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
}