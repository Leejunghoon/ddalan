package com.ddalan.www;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
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
		if(!regID.equals("") || regID != null){
			
			Log.w(TAG, "onRegistered!! " + regID);
		}
	}

	@Override
	protected void onUnregistered(Context context, String regID) {
		// TODO Auto-generated method stub
		Log.w(TAG, "onUnregistered!! " + regID);
	}
	
	public void showToast(){
		Toast.makeText(this, "RegID ��� �Ϸ�", Toast.LENGTH_LONG).show();
	}
	
	@SuppressWarnings("deprecation")
	private void showMessage(Context context, Intent intent){
		String title = intent.getStringExtra("title");
		String msg = intent.getStringExtra("msg");
		String ticker = intent.getStringExtra("ticker");
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Activity.NOTIFICATION_SERVICE);
		
//		�ش� ������ �����ϴ� �̺�Ʈ�� �ϰ���� �� �Ʒ� �ּ��� Ǯ���ּ���
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, 
				new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK), 0);
		PendingIntent pendingIntent1 = PendingIntent.getActivity(context, 0, new Intent(), 0);
		
		Notification notification = new Notification();
		notification.icon = R.drawable.push;  // Ǫ�� �޼��� ���� �̹���
		notification.tickerText = ticker;
		notification.when = System.currentTimeMillis();
		notification.vibrate = new long[] { 500, 100, 500, 100 };
		notification.sound = Uri.parse("/system/media/audio/notifications/20_Cloud.ogg");
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.setLatestEventInfo(context, title, msg, pendingIntent);
		
		notificationManager.notify(0, notification);
	}
	
	

}
