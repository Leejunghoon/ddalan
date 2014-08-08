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
			// 모두 작업을 마치고 실행할 일 (메소드 등등)
		}
		
		// 실제 전송하는 부분
		public String executeClient() {

			// 연결 HttpClient 객체 생성
			HttpClient client = new DefaultHttpClient();
			
			
			// Post객체 생성
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