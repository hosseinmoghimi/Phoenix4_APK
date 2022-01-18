package com.khafonline.phoenix4.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.model.LeoResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends MasterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_product_detail);




        ((Button)findViewById(R.id.btn)).setOnClickListener(view -> {
          get_categories();
        });





    }

    private void get_categories() {

        String token = "";
        RetrofitClientInstance.market().categories(token).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse=response.body();
                List<Category> categories=leoResponse.getCategories();
                int ddd=categories.size();

            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                int a = 0;
            }
        });


    }
}
