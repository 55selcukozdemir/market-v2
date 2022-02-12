package com.example.market.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.market.R;
import com.example.market.adapter.MainGridViewAdapter;
import com.example.market.product.ProductList;

import java.util.ArrayList;

public class MainSaleGridFragment extends Fragment {


    private ArrayList<ProductList> mProductList = new ArrayList<>();

    public MainSaleGridFragment(ArrayList<ProductList> mProductList) {
        this.mProductList = mProductList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_sale_grid, container, false);

        RecyclerView mRecyclerView = view.findViewById(R.id.main_sale_grid_view);
        MainGridViewAdapter mainGridViewAdapter = new MainGridViewAdapter(mProductList,container.getContext());
        mRecyclerView.setAdapter(mainGridViewAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(),2);

        mRecyclerView.setLayoutManager(layoutManager);

        return view;
    }
}