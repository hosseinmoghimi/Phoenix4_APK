package com.khafonline.phoenix4.activity;

import android.os.Bundle;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.Profile;

public class ProfileActivity extends MasterActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this,R.layout.activity_profile);

        Profile profile= SharedPref.getProfile();
        if(profile==null)
            this.finish();

    }
}
