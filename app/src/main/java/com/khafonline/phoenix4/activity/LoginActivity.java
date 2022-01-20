package com.khafonline.phoenix4.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initializeViews();


    }

    private void initializeViews() {

        findViewById(R.id.waiting).setVisibility(View.GONE);
        findViewById(R.id.cirLoginButton).setOnClickListener(this);
        ((EditText) findViewById(R.id.editTextUsername)).setText("khafonli");
        ((EditText) findViewById(R.id.editTextPassword)).setText("leonolan@2020");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cirLoginButton) {
            String username = ((EditText) findViewById(R.id.editTextUsername)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextPassword)).getText().toString();
            authenticate(username, password);
        }
    }


    private void authenticate(String username, String password) {

        findViewById(R.id.waiting).setVisibility(View.VISIBLE);
        User user = new User(username, password);
        RetrofitClientInstance.market().login(user).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {

                findViewById(R.id.waiting).setVisibility(View.GONE);
                LeoResponse leoResponse = response.body();
                Profile profile = leoResponse.getProfile();
                if (profile != null) {

                    String token = leoResponse.getToken();
                    SharedPref.setProfile(profile);
                    SharedPref.setToken(token);
                    setResult(RESULT_OK);

                    LoginActivity.this.finish();
                }
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                setResult(RESULT_CANCELED);

                findViewById(R.id.waiting).setVisibility(View.GONE);
                int a = 0;
            }
        });

    }


}
