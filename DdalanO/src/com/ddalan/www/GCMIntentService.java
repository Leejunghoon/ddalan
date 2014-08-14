package com.ddalan.www;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	private static final String TAG = "GCM";
	private static final String SENDER_ID = "926061344928";

	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		if (intent.getAction().equals("com.google.android.c2dm.intent.RECEIVE")) {
			showMessage(context, intent);
		}

	}

	@Override
	protected void onError(Context context, String msg) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onError!! " + msg);
	}

	@Override
	protected void onRegistered(Context context, String regID) {
		// TODO Auto-generated method stub
		if (!regID.equals("") || regID != null) {

			Log.w(TAG, "onRegistered!! " + regID);
		}
	}

	@Override
	protected void onUnregistered(Context context, String regID) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onUnregistered!! " + regID);
	}

	public void showToast() {
		Toast.makeText(this, "RegID 등록 완료", Toast.LENGTH_LONG).show();
	}

	@SuppressWarnings("deprecation")
	private void showMessage(Context context, Intent intent) {
		String title = intent.getStringExtra("title");
		String msg = intent.getStringExtra("msg");
		String ticker = intent.getStringExtra("ticker");

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// 해당 어플을 실행하는 이벤트를 하고싶을 때 아래 주석을 풀어주세요 - 메인액티비티로 이동??
		// 펜딩인텐트는 셋 중 선택, 하나만 만들기.

		// 첫번째
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, intent,
				Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		// 두번째
	//	PendingIntent pendingIntent2 = PendingIntent.getActivity(
	//			getApplicationContext(), 0, intent,
	//			Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		// 세번째
		// PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0,
		// new Intent(), 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				GCMIntentService.this);
		builder.setSmallIcon(R.drawable.woman7);
		builder.setContentTitle("따란");
		builder.setContentText(ticker + "님으로부터 메세지가 도착했어요");
		builder.setVibrate(new long[] { 50, 50, 50, 50 });
		builder.setAutoCancel(true);
		builder.setSound(Uri.parse("android.resource://"
				+ context.getPackageName() + "/" + R.raw.ddaran));
		builder.setContentIntent(pendingIntent);

		manager.notify(0, builder.build());

		// Notification notification = new Notification();

		// notification.icon = R.drawable.push; // 푸쉬 메세지 전송 이미지
		// notification.tickerText = ticker;
		// notification.when = System.currentTimeMillis();
		// notification.flags = Notification.FLAG_AUTO_CANCEL;
		// notification.setLatestEventInfo(context, title, msg, pendingIntent);

	}

}
