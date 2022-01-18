package com.khafonline.phoenix4.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.repository.CategoryRepository;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends MasterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_product_detail);


    }

    private void get_categories() {




    }
}
