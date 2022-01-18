package com.khafonline.phoenix4.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;

public class CategoryViewHolder extends MainViewHolder {

    private TextView titleView;
    private View category_wrapper;
    private NetworkImageView category_thumbnail;
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);

        this.category_wrapper=(View)itemView.findViewById(R.id.category_wrapper);
        this.titleView=(TextView)itemView.findViewById(R.id.category_title);
        this.category_thumbnail=(NetworkImageView) itemView.findViewById(R.id.category_thumbnail);
    }

    public View getWrapper() {
        return category_wrapper;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public NetworkImageView getThumbnail() {
        return category_thumbnail;
    }
}
