package com.example.ecommerceappbasic;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartFragment extends Fragment {
    private RecyclerView rcCart;
    private CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rcCart = view.findViewById(R.id.rcCart);
        rcCart.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        cartAdapter = new CartAdapter(GlobalList.cart_products,getContext());
        rcCart.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        return view;
    }

}
