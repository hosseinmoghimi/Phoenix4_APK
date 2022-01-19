package com.khafonline.phoenix4.activity;

import android.os.Bundle;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.component.MyTextView;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Product;
import com.khafonline.phoenix4.volley.VolleySingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends MasterActivity {
    public static final String ARG_PRODUCT_ID = "Arg_Product_id";
    private int productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachToBaseActivity(this, R.layout.activity_product);

        productId = getIntent().getIntExtra(ARG_PRODUCT_ID, 0);
        if (productId == 0)
            this.finish();
        getProduct();

    }

    private void getProduct() {
        RetrofitClientInstance.market().product(productId).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                if (leoResponse != null) {
                    product = leoResponse.getProduct();
                    fillProduct();
                }
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {

            }
        });
    }

    private void fillProduct() {
        ((MyTextView) findViewById(R.id.product_title)).setText(product.getTitle());
        ((MyTextView) findViewById(R.id.product_description)).setText(product.getDescription());


        NetworkImageView mNetworkImageView = ((NetworkImageView) findViewById(R.id.product_image));
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS + product.getThumbnail();
        mNetworkImageView.setImageUrl(url, mImageLoader);


    }
}
