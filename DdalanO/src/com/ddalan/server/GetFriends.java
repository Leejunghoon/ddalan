package com.ddalan.server;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import org.json.JSONObject;

import com.ddalan.www.FavoritesTab;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;


public class GetFriends extends Thread{
	String	res;
	@Override
	public void run() {
				
			
			HttpClient client = new DefaultHttpClient();
						
			
			// ��ü ���� ���� �κ�, ���� �ִ�ð� ���
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			
			// Post��ü ��
			HttpPost httpPost = new HttpPost("http://192.168.0.79:8080/getFriends.do");
			try {
				
				HttpResponse response = client.execute(httpPost);
		
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("리스폰 성공");
					res = EntityUtils.toString(resEntity, "utf-8");
					System.out.println("데이터 "+res);
				//  mHandler.sendEmptyMessage(res);	
					
				}
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	
	
	
	
}
		
		
		
