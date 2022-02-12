package com.example.market.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.market.R;
import com.example.market.adapter.MainViewPagerAdapter;
import com.example.market.adapter.SaleInEndAdapter;
import com.example.market.database.DatabaseHalper;
import com.example.market.database.MProduct;
import com.example.market.databinding.FragmentNavMainSaleBinding;
import com.example.market.product.BundleBarcode;
import com.example.market.product.ProductList;
import com.example.market.product.SaleInEnd;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainSaleFragment extends Fragment {

    public FragmentNavMainSaleBinding binding;


    private int count = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNavMainSaleBinding.inflate(getLayoutInflater());

        binding.mainFragmentGoToScuttle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fragment içine BottomSheet eklenmesi
                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(container.getContext());
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_main_scutlle);
                bottomSheetDialog.show();
                //Fragment içine BottomSheet eklenmesi (son)




                //Bottom Sheet içine RecyclerView eklenmesi
                ArrayList<SaleInEnd> saleArrayList = new ArrayList<>();
                saleArrayList.add(new SaleInEnd("yeni","eski","devam","tamam"));

                RecyclerView mRecyclerView = bottomSheetDialog.findViewById(R.id.bottomSheetRecyclerView);
                SaleInEndAdapter saleInEndAdapter = new SaleInEndAdapter(saleArrayList,container.getContext());
                mRecyclerView.setAdapter(saleInEndAdapter);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
                ////Bottom Sheet içine RecyclerView eklenmesi (son)
            }
        });

        binding.mainBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),ZXingActivity.class);
                startActivity(i);

            }
        });


        count ++;






        configureTabLayout();

        return binding.getRoot();
    }


    //Tab bar kullanımı
    private void configureTabLayout() {

        ViewPager viewPager = binding.mainFragmetViewPager;
        TabLayout tabLayout = binding.tabBarMain;

        tabLayout.setupWithViewPager(viewPager);


        MainViewPagerAdapter adapter  = new MainViewPagerAdapter(getChildFragmentManager());


        String[] category = new String[]{"Sigara", "Çikolata", "İçecekler", "Temizlik", "Mutfak"};
        ArrayList<ProductList> sigara = new ArrayList<>();
        ArrayList<ProductList> cikolata = new ArrayList<>();
        ArrayList<ProductList> icecekler = new ArrayList<>();
        ArrayList<ProductList> temizlik = new ArrayList<>();
        ArrayList<ProductList> mutfak = new ArrayList<>();
        sigara.clear();
        icecekler.clear();
        temizlik.clear();
        mutfak.clear();

        //Veri tabanında bütün verilerin çekilmesi
        DatabaseHalper db = new DatabaseHalper(getContext());
        List<MProduct> mAllList = db.allList();

        for (MProduct mProdact : mAllList){
            if (mProdact.getCatecory().equals(category[0])){
                sigara.add(new ProductList(mProdact.getProductName(), mProdact.getBarcode(), mProdact.getPrice(), 0, 0,"ana"));
            }else if (mProdact.getCatecory().equals(category[1])){
                cikolata.add(new ProductList(mProdact.getProductName(), mProdact.getBarcode(), mProdact.getPrice(), 0, 0, "ana"));
            }else if (mProdact.getCatecory().equals(category[2])){
                icecekler.add(new ProductList(mProdact.getProductName(), mProdact.getBarcode(), mProdact.getPrice(), 0, 0, "ana"));
            }else if (mProdact.getCatecory().equals(category[3])){
                temizlik.add(new ProductList(mProdact.getProductName(), mProdact.getBarcode(), mProdact.getPrice(), 0, 0, "ana"));
            }else if (mProdact.getCatecory().equals(category[4])){
                mutfak.add(new ProductList(mProdact.getProductName(), mProdact.getBarcode(), mProdact.getPrice(), 0, 0, "ana"));
            }
        }

        Fragment sigaraFragment = new MainSaleGridFragment(sigara);
        Fragment cikolataFragment = new MainSaleGridFragment(cikolata);
        Fragment iceceklerFragment = new MainSaleGridFragment(icecekler);
        Fragment temizlikFragment = new MainSaleGridFragment(temizlik);
        Fragment mutfakFragment = new MainSaleGridFragment(mutfak);

        adapter.addFrag(sigaraFragment,category[0]);
        adapter.addFrag(cikolataFragment,category[1]);
        adapter.addFrag(iceceklerFragment,category[2]);
        adapter.addFrag(temizlikFragment,category[3]);
        adapter.addFrag(mutfakFragment,category[4]);

        viewPager.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        BundleBarcode b = new BundleBarcode();
        binding.editTextTextPersonName.setText(b.getBarcode());
    }
}