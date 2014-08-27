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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;

import com.ddalan.gcm.SendPush;

public class FavoritesTab extends Fragment {
	ArrayList<String> favNameArr = new ArrayList<String>();
	ArrayList<String> favNumArr = new ArrayList<String>();

	ArrayList<String> rareNameArr = new ArrayList<String>();
	ArrayList<String> rareNumArr = new ArrayList<String>();

	GridView favView;
	boolean spreadList;
	static int one = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View friendView = inflater.inflate(R.layout.favoritestab, container,
				false);

		// �좎뙇�뚮줉�몄삕�좎룞���좎떛紐뚯삕�좎룞���좎룞�쇿뜝�숈삕�좎떢�쎌삕 �좎룞�쇿뜝�숈삕..

		new FaF().start();

		// 移쒎뜝�숈삕�좎룞�쇿뜝占썲뜝�볥챿�쇿뜝�숈삕略놂옙�좎룞�쇿뜝�숈삕�좎룞���좎룞�쇿뜝�몃벝���좎뙗�듭삕
		GridView rareView = (GridView) friendView.findViewById(R.id.grid2);
		// 占쎄퀡�낉옙占쏙옙怨몄뒠

		rareView.setAdapter(new FavoritesAdapter(this.getActivity(),
				rareNameArr, rareNumArr));

		rareView.setOnItemClickListener(new OnItemClickListener() {

			// 移쒎뜝�숈삕(Item)�좎룞���좎룞�쇿뜝�숈삕�좎룞���좎룞��Click)
			// �좎룞�쇿뜝�숈삕�좎룞���좎룞�쇿뜝�숈삕�좎룞�쇿뜝�숈삕 �좎뙣�뚮벝��
			// �좎룞�쇿뜝�숈삕. �좎룞�쇿뜝�숈삕�좎룞���좎뜲�ㅽ듃.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				new SendPush(getActivity()).execute(rareNumArr.get(position));
				Intent intent = new Intent(getActivity(), Frame_animation.class);

				startActivity(intent);

			}

		});

		rareView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = rareNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));

				// WebDialog
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

			// 移쒎뜝�숈삕(Item)�좎룞���좎룞�쇿뜝�숈삕�좎룞���좎룞��Click)
			// �좎룞�쇿뜝�숈삕�좎룞���좎룞�쇿뜝�숈삕�좎룞�쇿뜝�숈삕 �좎뙣�뚮벝��
			// �좎룞�쇿뜝�숈삕. �좎룞�쇿뜝�숈삕�좎룞���좎뜲�ㅽ듃.
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				new SendPush(getActivity()).execute(favNumArr.get(position));
				Intent intent = new Intent(getActivity(), Frame_animation.class);

				startActivity(intent);
			}

		});

		favView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {

				String picStr = favNumArr.get(position);
				int picNo = Integer.parseInt(picStr.substring(picStr.length() - 1));

				// �좎룞�쇿뜝�숈삕�좎룞���좎룞�숉떚�좎룞�숉떚(WebDialog)
				// �좎룞�숉떚�좎룞�숉떚�좎룞���좎룞�쇿뜝�숈삕�좎떬�먯삕.
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
		public void run() { // 占쎈챶諭억옙�以�燁삳똻�ワ옙怨뺧옙 癰귣�沅�옙占�

			HttpClient client = new DefaultHttpClient();

			String res;
			// �좎룞�숈껜 �좎룞�쇿뜝�숈삕 �좎룞�쇿뜝�숈삕 �좎떥釉앹삕, �좎룞�쇿뜝�숈삕 �좎뙇�먯삕梨ⓨ뜝占썲뜝�숈삕�좑옙
			HttpParams params = client.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);

			// Post�좎룞�숈껜 �좎룞��
			HttpPost httpPost = new HttpPost(
					"http://192.168.43.63:8080/FavFriends.do");
			try {

				HttpResponse response = client.execute(httpPost);

				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("�귐딅뮞占쏙옙占쎄퉫��");
					res = EntityUtils.toString(resEntity, "utf-8");
					System.out.println("占쎄퀣�좑옙占� + res");
					Message msg = new Message();
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

	private final Handler mHandler = new Handler() { // 占쎈챶諭억옙�占�占쎈벏鍮�UI占썬끇�낉옙�뽯퓠
														// 占쎈쵌�먲옙�뺣뼄.

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String res = (String) msg.obj;
			System.out.println("占쎈챶諭억옙占쏙쭖遺욧쉭筌욑옙+ res");

			String name = res.substring(res.indexOf("{") + 8, res.indexOf("]"));
			String phone = res.substring(res.indexOf("\"fp\":\"[") + 7,
					res.indexOf("}") - 2);

			getFav(name, phone);

		}

	};

	public void getFav(String name, String phone) {

		String[] nameArr = name.split(", ");
		String[] phoneArr = phone.split(", ");

		if (favNameArr.isEmpty()) {
			for (int i = 0; i < nameArr.length; i++) {
				favNameArr.add(nameArr[i]);
				favNumArr.add(phoneArr[i]);
			}
			rareNameArr.add("임새샘");
			rareNameArr.add("김영길");
			rareNameArr.add("박종호");

			rareNumArr.add("01099725514");
			rareNumArr.add("01035868469");
			rareNumArr.add("01052942085");
		} else if (!favNameArr.isEmpty()) {
			favNameArr.removeAll(favNameArr);
			favNumArr.removeAll(favNumArr);
			rareNameArr.removeAll(rareNameArr);
			rareNumArr.removeAll(rareNumArr);
			rareNameArr.add("임새샘");
			rareNameArr.add("김영길");
			rareNameArr.add("박종호");

			rareNumArr.add("01099725514");
			rareNumArr.add("01035868469");
			rareNumArr.add("01052942085");

			for (int i = 0; i < nameArr.length; i++) {
				favNameArr.add(nameArr[i]);
				favNumArr.add(phoneArr[i]);
			}

		}

	}

}
