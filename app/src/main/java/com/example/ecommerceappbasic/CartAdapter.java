package com.example.ecommerceappbasic;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private List<cart_items> products;
    Context context;

    public CartAdapter(List<cart_items> cartItems,Context context)
    {
        this.products = cartItems;
        this.context=context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewCart = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart, parent, false);
        return new CartViewHolder(viewCart);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        cart_items cart = products.get(position);
        holder.tvNameCart.setText(cart.getName());
        holder.tvPriceCart.setText(cart.getPrice());
        holder.ivImageCart.setImageResource(cart.getImage());
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder deleteDialog=new AlertDialog.Builder(context);
                deleteDialog.setTitle("Confirmation! ");
                deleteDialog.setMessage("Do you want to delete item? ");
                deleteDialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        products.remove(cart);
                        notifyDataSetChanged();
                    }
                });
                deleteDialog.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                deleteDialog.show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCart;
        TextView tvPriceCart;
        ImageView ivImageCart,ivDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameCart = itemView.findViewById(R.id.tvNameCart);
            tvPriceCart = itemView.findViewById(R.id.tvPriceCart);
            ivImageCart = itemView.findViewById(R.id.ivImageCart);
            ivDelete=itemView.findViewById(R.id.ivDelete);
        }
    }
}
