package com.example.ecommerceappbasic;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecommerceappbasic.Items;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyApdater extends RecyclerView.Adapter<MyApdater.MyViewHolder> {

    private List<Items> productList;
    Context context;


    public MyApdater(List<Items> productList,Context context) {
        this.productList = productList;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Items product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice());
        holder.tvDescription.setText(product.getDescription());
        holder.ivImage.setImageResource(product.getImage());
        holder.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalList.cart_products.add(new cart_items(product.getName(), product.getPrice(), product.getImage()));
                Toast.makeText(context, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void updateList(List<Items> newItems){
        productList=newItems;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        TextView tvPrice;
        ImageView ivImage;
        FloatingActionButton fabAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivImage=itemView.findViewById(R.id.ivImage);
            fabAdd=itemView.findViewById(R.id.fbAdd);
        }
    }
}
