package com.khafonline.phoenix4.activity;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.adapter.CartLineAdapter;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.CartLine;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends MasterActivity {
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_cart);
        fillCartLines();

    }

    private void fillCartLines() {
        String token = SharedPref.getToken();
        RetrofitClientInstance.market().cart(token).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                List<CartLine> cartLines = leoResponse.getCartLines();

                RecyclerView rc = ((RecyclerView) findViewById(R.id.recyclerView));
                rc.setAdapter(new CartLineAdapter(context, cartLines));
                rc.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                int a = 0;

            }
        });
    }
}
