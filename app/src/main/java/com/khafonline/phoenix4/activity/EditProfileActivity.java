package com.khafonline.phoenix4.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends MasterActivity implements View.OnClickListener {
    Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_edit_profile);
        findViewById(R.id.btn_save).setOnClickListener(this);
        initializeViews();

    }

    private void initializeViews() {
        findViewById(R.id.waiting).setVisibility(View.GONE);

        profile = SharedPref.getProfile();
        ((EditText) findViewById(R.id.editTextFirstName)).setText(profile.getFirst_name());
        ((EditText) findViewById(R.id.editTextLastName)).setText(profile.getLast_name());
        ((EditText) findViewById(R.id.editTextBio)).setText(profile.getBio());
        ((EditText) findViewById(R.id.editTextAddress)).setText(profile.getAddress());
        ((EditText) findViewById(R.id.editTextEmail)).setText(profile.getEmail());
        ((EditText) findViewById(R.id.editTextMobile)).setText(profile.getMobile());

    }


    private void edit_profile() {
        findViewById(R.id.waiting).setVisibility(View.VISIBLE);

        String first_name = ((EditText) findViewById(R.id.editTextFirstName)).getText().toString();
        String last_name = ((EditText) findViewById(R.id.editTextLastName)).getText().toString();
        String bio = ((EditText) findViewById(R.id.editTextBio)).getText().toString();
        String address = ((EditText) findViewById(R.id.editTextAddress)).getText().toString();
        String email = ((EditText) findViewById(R.id.editTextEmail)).getText().toString();
        String mobile = ((EditText) findViewById(R.id.editTextMobile)).getText().toString();


        String token = SharedPref.getToken();


        findViewById(R.id.waiting).setVisibility(View.VISIBLE);
        Profile profile1 = new Profile(first_name, last_name, mobile, email, bio, address);
        RetrofitClientInstance.market().edit_profile(token, profile1, profile.getId()).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {

                findViewById(R.id.waiting).setVisibility(View.GONE);
                LeoResponse leoResponse = response.body();

                int a = 0;
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {

                findViewById(R.id.waiting).setVisibility(View.GONE);
                int a = 0;
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_save) {
            edit_profile();
        }
    }
}
