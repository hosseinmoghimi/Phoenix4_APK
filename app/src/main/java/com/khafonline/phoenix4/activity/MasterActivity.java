package com.khafonline.phoenix4.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.volley.VolleySingleton;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MasterActivity extends AppCompatActivity {
    Activity context;
    Profile profile;
    private AppBarConfiguration mAppBarConfiguration;


    public void attachToBaseActivity(Activity activity, int layout) {
        FrameLayout frameLayout = (FrameLayout) this.findViewById(R.id.content_frame_layout);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View activityView = layoutInflater.inflate(layout, null, false);

        boolean isOk = false;
        if (activityView != null)
            if (frameLayout != null) {
                frameLayout.addView(activityView);
                //super.setTitle("سبد خرید");
                isOk = true;

//                setProfile();

            }
        if (!isOk) {
            //Core.addLog("activity_order_detail :" + " frame layout and view inflate error");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        profile = SharedPref.getProfile();
        if (profile != null)
            showProfileInDrawer(navigationView);
    }

    public void showProfileInDrawer(NavigationView navigationView ) {
        View vvv=navigationView.getHeaderView(0);
        TextView textView_name = (TextView) vvv.findViewById(R.id.profile_name);
        TextView textView_mobile = (TextView) vvv.findViewById(R.id.profile_mobile);
        textView_name.setText(profile.getName());
        textView_mobile.setText(profile.getMobile());
        NetworkImageView mNetworkImageView = (NetworkImageView) vvv.findViewById(R.id.profileImageView);
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS + profile.getImage();
        mNetworkImageView.setImageUrl(url, mImageLoader);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.master, menu);
        return true;
    }

}