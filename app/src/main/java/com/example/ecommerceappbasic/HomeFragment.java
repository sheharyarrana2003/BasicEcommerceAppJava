package com.example.ecommerceappbasic;

import android.content.ClipData;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {
    RecyclerView rcItems;
    List<Items> newItems;
    MyApdater adapter;
    List<Items> filteredItems=new ArrayList<>();
    boolean search=false;
    EditText etSearch;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcItems= view.findViewById(R.id.rcItems);
        etSearch=view.findViewById(R.id.etSearch);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcItems.setLayoutManager(layoutManager);
        newItems= new ArrayList<>();

        newItems.add(new Items("Air Jordan","You have got the hops and the speed-lace up in shoes",R.drawable.air,"226$"));
        newItems.add(new Items("Zoom Freak","The forward thinking design of his latest signature",R.drawable.zoom,"236$"));
        newItems.add(new Items("KD Treys","A secure mid foot strap is suited for scoring binges",R.drawable.kd,"246$"));
        newItems.add(new Items("Kyrie 6","Bouncing cushioning is paired with soft yet supportive foam",R.drawable.pngegg,"200$"));

        adapter= new MyApdater(newItems,getContext());
        rcItems.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                filterItems(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return  view;
    }
    private void filterItems(String query){
        search=true;
        filteredItems.clear();
        if(query.isEmpty()){
            filteredItems.addAll(newItems);
        }
        else{
            filteredItems.addAll(newItems.stream().filter(item->item.getName()
                    .toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList()));
        }
        adapter.updateList(filteredItems);
    }

}