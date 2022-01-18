package com.khafonline.phoenix4.communication;

import android.provider.SyncStateContract;

import com.khafonline.phoenix4.core.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = Constants.DATA_SERVER_ADDRESS;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MarketRestService market(){
        return RetrofitClientInstance.getRetrofitInstance().create(MarketRestService.class);

    }
//    public static AuthRestService auth(){
//        return RetrofitClientInstance.getRetrofitInstance().create(AuthRestService.class);
//
//    }
}
