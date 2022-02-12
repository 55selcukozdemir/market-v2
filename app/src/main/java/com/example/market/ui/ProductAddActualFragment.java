package com.example.market.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.market.R;
import com.example.market.database.DatabaseHalper;
import com.example.market.databinding.FragmentNavProductAddActualBinding;


public class ProductAddActualFragment extends Fragment {

    public FragmentNavProductAddActualBinding binding;
    public Context context = getContext();
    private static final String TAG = "ProductAddActualFragmen";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNavProductAddActualBinding.inflate(getLayoutInflater());


        binding.productAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = binding.productNameEditText.getText().toString();
                String barcode = binding.barcodeEditText.getText().toString();
                String category = binding.categorySpinner.getSelectedItem().toString();
                Float price = Float.parseFloat(binding.priceEditText.getText().toString());
                DatabaseHalper databaseHalper = new DatabaseHalper(getContext());
                databaseHalper.addProduct(productName, barcode, category, price);
            }
        });


        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String barcode = binding.barcodeEditText.getText().toString();
                DatabaseHalper databaseHalper = new DatabaseHalper(getContext());
                databaseHalper.deleteProduct(barcode);

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.category_sql, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.categorySpinner.setAdapter(adapter);

        return binding.getRoot();
    }

}