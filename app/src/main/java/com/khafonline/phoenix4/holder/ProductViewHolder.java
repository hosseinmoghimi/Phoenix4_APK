package com.khafonline.phoenix4.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;

public class ProductViewHolder extends MainViewHolder{
    private TextView titleView;
    private TextView product_price_view;
    private View product_wrapper;
    private NetworkImageView product_thumbnail;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        this.product_wrapper=(View)itemView.findViewById(R.id.product_wrapper);
        this.product_price_view=(TextView)itemView.findViewById(R.id.product_price);
        this.titleView=(TextView)itemView.findViewById(R.id.product_title);
        this.product_thumbnail=(NetworkImageView) itemView.findViewById(R.id.product_thumbnail);
    }

    public TextView get_price_view() {
        return product_price_view;
    }

    public View getWrapper() {
        return product_wrapper;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public NetworkImageView getThumbnail() {
        return product_thumbnail;
    }

}
