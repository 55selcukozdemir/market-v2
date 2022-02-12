package com.example.market.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


import com.example.market.databinding.FragmentNavSellingBinding;


public class SellingFragment extends Fragment {

    public FragmentNavSellingBinding binding;

    public LinearLayout linearLayout;
    public Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNavSellingBinding.inflate(getLayoutInflater());



        return binding.getRoot();
    }

}