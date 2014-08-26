package com.ddalan.www;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class FavoritesTab extends Fragment {
	ArrayList<String> favNameArr = new ArrayList<String>();
	ArrayList<String> favNumArr = new ArrayList<String>();

	ArrayList<String> rareNameArr = new ArrayList<String>();
	ArrayList<String> rareNumArr = new ArrayList<String>();
	
	
	
	GridView favView ;
	boolean spreadList;
	static int one = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View friendView = inflater.inflate(R.layout.favoritestab, container,
				false);
		
		// �ּҷϿ��� �̸��� �����ͼ� ����..
		if (spreadList == false) {
			
			
			spreadList = true;
		}
		

		new FaF().start();
		

		// ģ����� �׸���信 ������ ���ε� �۾�
		GridView rareView = (GridView) friendView.findViewById(R.id.grid2);
		// 쓰레드 적용
	    
		rareView.setAdapter(new FavoritesAdapter(this.getActivity(),	rareNameArr, rareNumArr));
	    
	    
		rareView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �佺Ʈ.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

			
				Toast.makeText(getActivity(),
						rareNameArr.get(position) + "연락 좀 해",
						Toast.LENGTH_SHORT).show();

			}

		});

		rareView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = rareNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));

				System.out.println("선택된, " + position + "다다다!!");
				//WebDialog
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", rareNameArr.get(position));
				intent.putExtra("number", rareNumArr.get(position));
				intent.putExtra("dialogpic", picNo);
				startActivity(intent);
				return false;

			}

		});

		favView = (GridView) friendView.findViewById(R.id.grid);
		favView.setAdapter(new FavoritesAdapter(this.getActivity(), favNameArr,
				favNumArr));
		favView.setOnItemClickListener(new OnItemClickListener() {

			// ģ��(Item)�� ������ ��(Click) ������ �������� �޼ҵ� ����. ������ �佺Ʈ.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				System.out.println("������");
				Toast.makeText(getActivity(),
						favNameArr.get(position) + "선택됨",
						Toast.LENGTH_SHORT).show();

			}

		});

		favView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = favNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));

			
				// ������ ��Ƽ��Ƽ(WebDialog) ��Ƽ��Ƽ�� �����Ѵ�.
				Intent intent = new Intent(getActivity(), FriendDialog.class);
				intent.putExtra("position", position);
				intent.putExtra("name", favNameArr.get(position));
				intent.putExtra("number", favNumArr.get(position));
				intent.putExtra("dialogpic", picNo);
				startActivity(intent);
				return false;
			}

		});
		return friendView;
	}
		
	class FaF extends Thread {
	 
	    
	    @Override
	    public void run() { // 핸들러로 카운터를 보낸다.
	        
	      
	        	
	        	HttpClient client = new DefaultHttpClient();
				
				String res;
				// ��ü ���� ���� �κ�, ���� �ִ�ð� ���
				HttpParams params = client.getParams();
				HttpConnectionParams.setConnectionTimeout(params, 5000);
				HttpConnectionParams.setSoTimeout(params, 5000);
				
				
				// Post��ü ��
				HttpPost httpPost = new HttpPost("http://192.168.0.79:8080/FavFriends.do");
				try {
					
					HttpResponse response = client.execute(httpPost);
			
					HttpEntity resEntity = response.getEntity();
					if (resEntity != null) {
						System.out.println("리스폰 성공");
						res = EntityUtils.toString(resEntity, "utf-8");
						System.out.println("데이터 "+res);
					  Message msg  = new Message();
					  msg.obj = res;
					  mHandler.sendMessage(msg);	
					
					}
					
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	        	
	      
	    }
	}



	private final Handler mHandler = new Handler() { //핸들러를 통해 UI스레드에 접근한다.

	    @Override
	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        String res = (String)msg.obj;
	        System.out.println("핸들러 메세지"+ res);
	        
	        String name = res.substring(res.indexOf("{") + 8, res.indexOf("]"));
			String phone = res.substring(res.indexOf("\"fp\":\"[") + 7, res.indexOf("}")-2);
			System.out.println("이름:"+name);
			System.out.println("폰:"+phone);

			        	        
	        
	        getFav(name, phone);
	       
	        
	        
	        
	    }
	    
	};


	public void getFav(String name , String phone){
		
		
		String[] nameArr = name.split(", ");
		String[] phoneArr = phone.split(", ");
        
		for(int i = 0; i < nameArr.length; i++){
			favNameArr.add(nameArr[i]);
	        favNumArr.add(phoneArr[i]);
	        
		}
	      
	}


/*	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		favNameArr.removeAll(favNameArr);
		favNumArr.removeAll(favNumArr);
		
	}*/





	
	

}

