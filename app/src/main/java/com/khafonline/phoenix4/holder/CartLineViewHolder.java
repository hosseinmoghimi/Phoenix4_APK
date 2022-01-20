package com.khafonline.phoenix4.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;

public class CartLineViewHolder extends MainViewHolder{
    private NetworkImageView productThumbnail;
    private View productWrapper;
    private TextView productTitle;
    private TextView supplierTitle;
    private TextView quantity;
    private TextView unitPrice;
    private View supplierWrapper;
    private View wrapper;
    public CartLineViewHolder(@NonNull View itemView) {
        super(itemView);
        productTitle=(TextView)itemView.findViewById(R.id.productTitle);
        productThumbnail=(NetworkImageView) itemView.findViewById(R.id.productThumbnail);
        supplierTitle=(TextView)itemView.findViewById(R.id.supplierTitle);
        unitPrice=(TextView)itemView.findViewById(R.id.unitPrice);
        quantity=(TextView)itemView.findViewById(R.id.quantity);
    }

    public NetworkImageView getProductThumbnail() {
        return productThumbnail;
    }

    public View getProductWrapper() {
        return productWrapper;
    }

    public TextView getProductTitle() {
        return productTitle;
    }

    public TextView getSupplierTitle() {
        return supplierTitle;
    }

    public TextView getQuantity() {
        return quantity;
    }

    public TextView getUnitPrice() {
        return unitPrice;
    }

    public View getSupplierWrapper() {
        return supplierWrapper;
    }

    public View getWrapper() {
        return wrapper;
    }
}
