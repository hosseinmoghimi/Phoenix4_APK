package com.khafonline.phoenix4.activity;

import android.os.Bundle;

import com.khafonline.phoenix4.R;

public class CartActivity  extends MasterActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_cart);
    }
}
