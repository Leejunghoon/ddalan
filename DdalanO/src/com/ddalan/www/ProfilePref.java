package com.ddalan.www;


import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class ProfilePref extends DialogPreference 
{
    public ProfilePref(Context oContext, AttributeSet attrs)
    {
        super(oContext, attrs);
        setDialogMessage("�ĺ����̵�: \n"+"��ȭ��ȣ : 010-7111-2207\n"+"�̸��� : clararish@gmail.com");
        
    }    
    
}