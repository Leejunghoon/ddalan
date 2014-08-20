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
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;


public class AddFriend extends AsyncTask<Void, Void, Void> {
	
	ArrayList<String> textArr = new ArrayList<String>();
	ArrayList<String> numArr = new ArrayList<String>();
	String name;
	String number;
	
	Context mContext;
	
		
	public AddFriend(Context context){
		mContext = context;
	}
	
	protected Void doInBackground(Void...voids) {
		 executeClient();
		return null;
		
	}
	

		protected void onPostExecute(String result) {
			// ��� �۾��� ��ġ�� ������ �� (�޼ҵ� ���)
		}
		
		// ���� �����ϴ� �κ�
		public String executeClient() {
			
			
			getNumber(mContext.getContentResolver());	// �̸� , ��ȭ��ȣ ���ε�
			
			
			
			ArrayList<NameValuePair> post = new ArrayList<NameValuePair>();
			post.add(new BasicNameValuePair("friendsN", getNameString()));
			post.add(new BasicNameValuePair("friendsP", getPhoneString()));

			System.out.println("�̰��� ����"+getNameString());
			// ���� HttpClient ��ü ����
			HttpClient client = new DefaultHttpClient();
			
			// ��ü ���� ���� �κ�, ���� �ִ�ð� ���
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			
			// Post��ü ����
			HttpPost httpPost = new HttpPost("http://192.168.0.47:8080/addFriend.do");
			
			try {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(post,"UTF-8");
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

		
		public void getNumber(ContentResolver cr) {
			Cursor phones = cr.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
					null, null);
			// use the cursor to access the contacts
			while (phones.moveToNext()) {
				name = phones
						.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
				number = phones
						.getString(phones
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				textArr.add(name);
				numArr.add(number);
			}

		}
		
		public String getNameString(){
			   try{
			      JSONObject json = new JSONObject();
			      json.put( "name", textArr);
			      
			      return json.toString();
			   }
			   catch( Exception e ){
			      return null;
			   }
			}  
		public String getPhoneString(){
			   try{
			      JSONObject json = new JSONObject();
			      json.put( "phone", numArr );
			      
			      return json.toString();
			   }
			   catch( Exception e ){
			      return null;
			   }
			}  
		
		
		
}