package com.khafonline.phoenix4.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.model.User;
import com.khafonline.phoenix4.repository.CategoryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;


        ((Button) findViewById(R.id.show_category_button)).setOnClickListener(view -> {
            Intent intent = new Intent(context, CategoryActivity.class);
            startActivity(intent);
        });
        ((Button) findViewById(R.id.get_profile_button)).setOnClickListener(view -> {
            Profile profile = SharedPref.getProfile();
            String a = "sss";
        });

        ((Button) findViewById(R.id.get_categories_button)).setOnClickListener(view -> {
            getCategories();
        });

        Button btn = (Button) findViewById(R.id.login_button);
        Profile profile = SharedPref.getProfile();
        if (profile != null) {
            btn.setText(profile.getName());
            startActivity(new Intent(context, CategoryActivity.class));
        }
        btn.setOnClickListener(view -> {
            authenticate();

        });

        int a = 0;


    }

    private void getCategories() {
        String token = "";
        RetrofitClientInstance.market().categories(token).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                if (leoResponse != null && leoResponse.getResult().equals("SUCCEED")) {


                    List<Category> categories = leoResponse.getCategories();
                    if (categories.size() > 0)
                        CategoryRepository.deleteAll();
                    CategoryRepository.insertList(categories);

                    List<Category> aaa = CategoryRepository.selectAll();
                    int aaddfa = 0;
                }
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                int a = 0;
            }
        });

    }


    private void authenticate() {

        String username = "khafonli";
        String password = "leonolan@2020";
        User user = new User(username, password);
        RetrofitClientInstance.market().login(user).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                Profile profile = leoResponse.getProfile();
                String token = leoResponse.getToken();
                SharedPref.setProfile(profile);
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                int a = 0;
            }
        });

    }
}
