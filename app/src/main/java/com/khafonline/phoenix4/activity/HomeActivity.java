package com.khafonline.phoenix4.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.khafonline.phoenix4.R;

public class HomeActivity extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;


        ((Button)findViewById(R.id.show_category_button)).setOnClickListener(view -> {
            Intent intent=new Intent(context,CategoryActivity.class);
            startActivity(intent);
        });
    }
}
