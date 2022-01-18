package com.khafonline.phoenix4.activity;

import android.os.Bundle;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.model.Product;

public class ProductActivity extends MasterActivity{
    public static final String ARG_PRODUCT_ID="Arg_Product_id";
    private int productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_product);
    }
}
