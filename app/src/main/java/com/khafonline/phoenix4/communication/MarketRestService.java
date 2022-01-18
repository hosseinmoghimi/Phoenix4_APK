package com.khafonline.phoenix4.communication;

import com.khafonline.phoenix4.model.LeoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface MarketRestService {


    String MY_ORDERS = "MY_ORDERS";


    //@Headers("action:" + MY_ORDERS)
    @GET("market/apk-api/categories/")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<LeoResponse> categories(@Header("token") String token);

//
//    @GET("market/api/products/{category_id}/")
//    Call<LeoResponse> list_products_of_category(@Header("token") String token,@Path("category_id") int category_id);
//
//    @GET("market/api/product/{pk}/")
//    Call<LeoResponse> product(@Header("token") String token,@Path("pk") int pk);

//    @POST("market/api/add_to_cart/{pk}/")
//    Call<LeoResponse> product(@Header("token") String token,@Path("pk") int pk);


}
