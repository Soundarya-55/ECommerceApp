package com.example.ecommerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommerceapp.R;
import com.example.ecommerceapp.activities.DetailedActivity;
import com.example.ecommerceapp.models.PopularProductModel1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopularProductAdapter1 extends RecyclerView.Adapter<PopularProductAdapter1.ViewHolder> {

    private Context context;
    private List<PopularProductModel1> popularProductModel1List;


    public PopularProductAdapter1(Context context, List<PopularProductModel1> popularProductModel1List) {
        this.context = context;
        this.popularProductModel1List = popularProductModel1List;
    }





    @NonNull
    @Override
    public PopularProductAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item1, parent, false));
    }




    @Override
    public void onBindViewHolder(@NonNull PopularProductAdapter1.ViewHolder holder, int position) {
        PopularProductModel1 product = popularProductModel1List.get(position);

        Glide.with(context).load(product.getImg_url()).into(holder.imageView);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detailed", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularProductModel1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView price, name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.all_img1);
            name = itemView.findViewById(R.id.all_product_name1);
            price = itemView.findViewById(R.id.all_price1);
        }
    }

}
