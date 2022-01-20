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
import com.khafonline.phoenix4.activity.ProductActivity;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.holder.CartLineViewHolder;
import com.khafonline.phoenix4.holder.MainViewHolder;
import com.khafonline.phoenix4.holder.ProductViewHolder;
import com.khafonline.phoenix4.model.CartLine;
import com.khafonline.phoenix4.model.Product;
import com.khafonline.phoenix4.utility.Utilities;
import com.khafonline.phoenix4.volley.VolleySingleton;

import java.util.List;

public class CartLineAdapter extends RecyclerView.Adapter<MainViewHolder> {

    Context context;
    List<CartLine> cartLines;

    public CartLineAdapter(Context context, List<CartLine> cartLines) {
        this.context = context;
        this.cartLines = cartLines;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewGroup ;
        viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.item_cart_line, parent, false);
        CartLineViewHolder cartLineViewHolder = new CartLineViewHolder(viewGroup);
        return cartLineViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final CartLine cartLine = cartLines.get(position);
        CartLineViewHolder myHolder = (CartLineViewHolder) holder;
        myHolder.getProductTitle().setText(cartLine.getShop().getProduct().getTitle());
        myHolder.getSupplierTitle().setText(cartLine.getShop().getSupplier().getTitle());
        myHolder.getQuantity().setText(cartLine.getQuantity()+" "+cartLine.getShop().getUnit_name());
        myHolder.getUnitPrice().setText(Utilities.separate(cartLine.getShop().getUnit_price())+" "+ Constants.CURRENCY);


        NetworkImageView mNetworkImageView= myHolder.getProductThumbnail();
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS +cartLine.getShop().getProduct().getThumbnail();
        mNetworkImageView.setImageUrl(url, mImageLoader);

//        MaterialCardView card=(MaterialCardView)myHolder.getWrapper();
//        myHolder.getWrapper().setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                card.setChecked(!card.isChecked());
//                return card.isChecked();
//            }
//        });


        myHolder.getProductTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.ARG_PRODUCT_ID, cartLine.getShop().getProduct().getId());
                context.startActivity(intent);

            }
        });



        myHolder.getProductThumbnail().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductActivity.class);
                intent.putExtra(ProductActivity.ARG_PRODUCT_ID, cartLine.getShop().getProduct().getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return cartLines.size();
    }
}
