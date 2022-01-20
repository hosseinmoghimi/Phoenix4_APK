package com.khafonline.phoenix4.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.khafonline.phoenix4.utility.MyNotification;
import com.khafonline.phoenix4.utility.MyNotification.MyActionIntent;

import java.util.ArrayList;
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


        ((Button) findViewById(R.id.home_notification_btn)).setOnClickListener(view -> {
            String title = "اعلان فونیکس";
            String text = "متن اعلان فونیکس";
            Intent intentHome = new Intent(context, CategoryActivity.class);
            List<MyActionIntent> actionIntents = new ArrayList<>();
            actionIntents.add(new MyActionIntent(new Intent(context, ProfileActivity.class), context.getString(R.string.profile) ,R.drawable.ic_menu_gallery));
            actionIntents.add(new MyActionIntent(new Intent(context, CartActivity.class), context.getString(R.string.cart), R.drawable.ic_menu_gallery));
            MyNotification.notify(title, text, intentHome, actionIntents, R.drawable.ic_menu_share);
        });

        ((Button) findViewById(R.id.show_category_button)).setOnClickListener(view -> {
            Intent intent = new Intent(context, CategoryActivity.class);
            startActivity(intent);
        });



        ((Button) findViewById(R.id.show_setting_button)).setOnClickListener(view -> {
            Intent intent = new Intent(context, SettingActivity.class);
            startActivity(intent);
        });


        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            ((Button) findViewById(R.id.login_btn)).setText("LOGIN OK");
                        }

                        else {
                            // There are no request codes
                            Intent data = result.getData();
                            ((Button) findViewById(R.id.login_btn)).setText("LOGIN Cancel");
                        }

                    }



                });

        ((Button) findViewById(R.id.login_btn)).setOnClickListener(view -> {
            Intent intent = new Intent(context, LoginActivity.class);
            someActivityResultLauncher.launch(intent);

        });


        ((Button) findViewById(R.id.get_profile_button)).setOnClickListener(view -> {
            Profile profile = SharedPref.getProfile();
            String a = "sss";
        });

        ((Button) findViewById(R.id.get_categories_button)).setOnClickListener(view -> {
            getCategories();
        });

         Profile profile = SharedPref.getProfile();
        if (profile != null) {
            startActivity(new Intent(context, CategoryActivity.class));
        }
        if (profile == null) {
            startActivity(new Intent(context, CartActivity.class));
        }



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


   }
