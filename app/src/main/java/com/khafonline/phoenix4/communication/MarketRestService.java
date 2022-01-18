package com.khafonline.phoenix4.communication;

import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MarketRestService {


    String MY_ORDERS = "MY_ORDERS";


    @GET("market/apk-api/categories/")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<LeoResponse> categories(@Header("token") String token);


    @GET("market/apk-api/products/{category_id}/")
    Call<LeoResponse> list_products_of_category(@Header("token") String token,@Path("category_id") int category_id);

//    @GET("market/api/product/{pk}/")
//    Call<LeoResponse> product(@Header("token") String token,@Path("pk") int pk);

    @POST("apk-api/login/")
    Call<LeoResponse> login( @Body User user);

    @GET("authentication/apk-api/login/")
    Call<LeoResponse> getToken();


}
