package com.khafonline.phoenix4.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.adapter.CategoryAdapter;
import com.khafonline.phoenix4.adapter.ProductAdapter;
import com.khafonline.phoenix4.communication.RetrofitClientInstance;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.model.LeoResponse;
import com.khafonline.phoenix4.model.Product;
import com.khafonline.phoenix4.repository.CategoryRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends MasterActivity {
    public static final String ARG_CATEGORY_ID = "CATEGORY_ID";
    private int categoryId = 0;
    Category category;
    List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.attachToBaseActivity(this, R.layout.activity_category);
        context = this;
        categoryId = getIntent().getIntExtra(ARG_CATEGORY_ID, 0);

        if (categoryId > 0)

            category = CategoryRepository.select(categoryId);

        categories = CategoryRepository.selectByParentID(categoryId);
        getProducts();
        fillCategories(categories);
        if (category != null) {
            ((TextView) findViewById(R.id.category_title)).setText(category.getTitle());
        }
        Button get_category_btn = findViewById(R.id.get_category_btn);
        get_category_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCategories();
            }
        });
    }

    private void fillCategories(List<Category> categories) {

        RecyclerView rec = (RecyclerView) findViewById(R.id.category_recyclerView);
        CategoryAdapter adapter = new CategoryAdapter(context, categories);
        rec.setAdapter(adapter);
        GridLayoutManager layout_manager = new GridLayoutManager(context, 3);
        rec.setLayoutManager(layout_manager);

    }

    private void getCategories() {
        String token = "";
        RetrofitClientInstance.market().categories(token).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                if (leoResponse != null && leoResponse.getResult().equals("SUCCEED")) {


                    List<Category> categories = leoResponse.getCategories();
                    fillCategories(categories);
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


    private void getProducts() {
        String token = "";
        RetrofitClientInstance.market().list_products_of_category(token, categoryId).enqueue(new Callback<LeoResponse>() {
            @Override
            public void onResponse(Call<LeoResponse> call, Response<LeoResponse> response) {
                LeoResponse leoResponse = response.body();
                if (leoResponse != null && leoResponse.getResult().equals("SUCCEED")) {
                    if (leoResponse.getProducts().size() < 1) {
                        findViewById(R.id.product_recyclerView).setVisibility(View.INVISIBLE);
                    }
                    List<Product> products = leoResponse.getProducts();
                    fillProducts(products);
                }
            }

            @Override
            public void onFailure(Call<LeoResponse> call, Throwable t) {
                int a = 0;
            }
        });

    }

    private void fillProducts(List<Product> products) {
        RecyclerView rec = (RecyclerView) findViewById(R.id.product_recyclerView);
        ProductAdapter adapter = new ProductAdapter(context, products);
        rec.setAdapter(adapter);
        LinearLayoutManager layout_manager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        rec.setLayoutManager(layout_manager);

    }

}
