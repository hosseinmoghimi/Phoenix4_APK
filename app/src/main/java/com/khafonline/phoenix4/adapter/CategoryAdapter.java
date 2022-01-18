package com.khafonline.phoenix4.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.khafonline.phoenix4.R;
import com.khafonline.phoenix4.activity.CategoryActivity;
import com.khafonline.phoenix4.core.Constants;
import com.khafonline.phoenix4.holder.CategoryViewHolder;
import com.khafonline.phoenix4.holder.MainViewHolder;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.volley.VolleySingleton;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<MainViewHolder> {
    private List<Category> categories;
    private Context context;

    public CategoryAdapter( Context context,List<Category> categories) {
        this.categories = categories;
        this.context = context;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewGroup viewGroup ;
        viewGroup = (ViewGroup) layoutInflater.inflate(R.layout.item_category, parent, false);
        CategoryViewHolder productViewHolder = new CategoryViewHolder(viewGroup);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Category category = categories.get(position);
        CategoryViewHolder myHolder = (CategoryViewHolder) holder;
        myHolder.getTitleView().setText(category.getTitle());


        NetworkImageView mNetworkImageView= myHolder.getThumbnail();
        ImageLoader mImageLoader;
        mImageLoader = VolleySingleton.getInstance().getImageLoader();

        String url = Constants.IMAGE_SERVER_ADDRESS +category.getThumbnail();
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
                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra(CategoryActivity.ARG_CATEGORY_ID, category.getId());
                context.startActivity(intent);

//                Toast.makeText(context,category.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
