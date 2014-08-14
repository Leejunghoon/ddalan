package com.ddalan.gcm;

import android.content.Context;
import android.util.Log;

import com.google.android.gcm.GCMRegistrar;

public class GcmRegID {
	private static final String TAG = "GCM";
	private static final String SENDER_ID = "926061344928";	
	
	public String getID(Context context){
		
		  //GCM DeviceID ���
	    final String regId = GCMRegistrar.getRegistrationId(context);
	  	//��ϵ� ID�� ������ ID���� ���ɴϴ�
	   if(regId.equals("") || regId == null){
	  		GCMRegistrar.register(context, SENDER_ID);
	  }else{
	  		Log.w(TAG, "deviceID Registered : " + regId);
	  	}
	  	return regId;
	  	  	
	}
	
	

}