package com.khafonline.phoenix4.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.component.DialogYesNo;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.core.SharedPref;
import com.khafonline.phoenix4.model.Profile;
import com.khafonline.phoenix4.volley.VolleySingleton;

public class ProfileActivity extends MasterActivity {
    Profile profile ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_profile);
        context = this;
        profile = SharedPref.getProfile();
        if (profile == null)
            this.finish();

        fillProfile();




        findViewById(R.id.btn_save).setOnClickListener(v -> {
            DialogYesNo dialogYesNo = new DialogYesNo(context, "ثبت تغییرات","آیا تغییرات ذخیره گردد؟");
            dialogYesNo.setYesCommand(new DialogYesNo.YesCommandCallBack() {
                @Override
                public void yesCommand() {
                    Toast.makeText(context,"ذخیره شد",Toast.LENGTH_LONG).show();
                }
            });
            dialogYesNo.show();
        });


        findViewById(R.id.btn_edit_profile).setOnClickListener(v -> {
            Intent intent=new Intent(context, EditProfileActivity.class);
            startActivity(intent);
        });



    }

    private void fillProfile() {
        ((TextView)findViewById(R.id.profile_name)).setText(profile.getName());
        ((TextView)findViewById(R.id.email)).setText(profile.getEmail());
        ((TextView)findViewById(R.id.mobile)).setText(profile.getMobile());
        ((TextView)findViewById(R.id.mobile)).setText(profile.getMobile());


        NetworkImageView mNetworkImageView = (NetworkImageView) findViewById(R.id.profileImage);
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS + profile.getImage();
        mNetworkImageView.setImageUrl(url, mImageLoader);


    }
}
