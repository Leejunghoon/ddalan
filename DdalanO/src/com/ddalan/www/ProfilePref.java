package com.ddalan.www;


import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

public class ProfilePref extends DialogPreference 
{
    public ProfilePref(Context oContext, AttributeSet attrs)
    {
        super(oContext, attrs);
        setDialogMessage("식별아이디: \n"+"전화번호 : 010-7111-2207\n"+"이메일 : clararish@gmail.com");
        
    }    
    
}