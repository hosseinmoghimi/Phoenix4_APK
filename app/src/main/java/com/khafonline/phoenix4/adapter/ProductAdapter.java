package com.khafonline.phoenix4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.activity.CategoryActivity;
import com.khafonline.phoenix4.activity.ProductActivity;
import com.khafonline.phoenix4.activity.ProductDetailActivity;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.holder.CategoryViewHolder;
import com.khafonline.phoenix4.holder.MainViewHolder;
import com.khafonline.phoenix4.holder.ProductViewHolder;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.model.Product;
import com.khafonline.phoenix4.utility.Utilities;
import com.khafonline.phoenix4.volley.VolleySingleton;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<MainViewHolder> {

    Context context;
    List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewGroup ;
        viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(viewGroup);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Product product = products.get(position);
        ProductViewHolder myHolder = (ProductViewHolder) holder;
        myHolder.getTitleView().setText(product.getTitle());
        myHolder.get_price_view().setText(Utilities.separate(product.getPrice())+" "+Constants.CURRENCY);


        NetworkImageView mNetworkImageView= myHolder.getThumbnail();
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS +product.getThumbnail();
        mNetworkImageView.setImageUrl(url, mImageLoader);

//        MaterialCardView card=(MaterialCardView)myHolder.getWrapper();
//        myHolder.getWrapper().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                card.setChecked(!card.isChecked());
//                return card.isChecked();
//            }
//        });


        myHolder.getWrapper().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.ARG_PRODUCT_ID, product.getId());
                context.startActivity(intent);

//                Toast.makeText(context,category.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
