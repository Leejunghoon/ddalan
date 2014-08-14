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
		Toast.makeText(this, "RegID ��� �Ϸ�", Toast.LENGTH_LONG).show();
	}

	@SuppressWarnings("deprecation")
	private void showMessage(Context context, Intent intent) {
		String title = intent.getStringExtra("title");
		String msg = intent.getStringExtra("msg");
		String ticker = intent.getStringExtra("ticker");

		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		// �ش� ������ �����ϴ� �̺�Ʈ�� �ϰ���� �� �Ʒ� �ּ��� Ǯ���ּ��� - ���ξ�Ƽ��Ƽ�� �̵�??
		// �������Ʈ�� �� �� ����, �ϳ��� �����.

		// ù��°
		PendingIntent pendingIntent = PendingIntent.getActivity(
				getApplicationContext(), 0, intent,
				Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		// �ι�°
	//	PendingIntent pendingIntent2 = PendingIntent.getActivity(
	//			getApplicationContext(), 0, intent,
	//			Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		// ����°
		// PendingIntent pendingIntent3 = PendingIntent.getActivity(context, 0,
		// new Intent(), 0);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				GCMIntentService.this);
		builder.setSmallIcon(R.drawable.woman7);
		builder.setContentTitle("����");
		builder.setContentText(ticker + "�����κ��� �޼����� �����߾��");
		builder.setVibrate(new long[] { 50, 50, 50, 50 });
		builder.setAutoCancel(true);
		builder.setSound(Uri.parse("android.resource://"
				+ context.getPackageName() + "/" + R.raw.ddaran));
		builder.setContentIntent(pendingIntent);

		manager.notify(0, builder.build());

		// Notification notification = new Notification();

		// notification.icon = R.drawable.push; // Ǫ�� �޼��� ���� �̹���
		// notification.tickerText = ticker;
		// notification.when = System.currentTimeMillis();
		// notification.flags = Notification.FLAG_AUTO_CANCEL;
		// notification.setLatestEventInfo(context, title, msg, pendingIntent);

	}

}
