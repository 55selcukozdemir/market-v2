package com.example.market.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.market.R;
import com.example.market.database.SaleDatabaseHalper;
import com.example.market.product.ProductList;

import java.util.ArrayList;

//Satış sayfasında ki Recycler View Grid Sayfasının Adapteri Bu şekilde hazırlandı.

public class MainGridViewAdapter extends RecyclerView.Adapter<MainGridViewAdapter.ViewHolder> {

    private ArrayList<ProductList> mArraylist;
    private Context context;
    private static final String TAG = "MainGridViewAdapter";
    private View contactView;

    public MainGridViewAdapter(ArrayList<ProductList> mArraylist, Context context) {
        this.mArraylist = mArraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater i = LayoutInflater.from(context);
        contactView = i.inflate(R.layout.recyclerview_companent_grid_view_adapter,parent,false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainGridViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.priceText.setText(mArraylist.get(position).getPrice().toString());
        holder.nameText.setText(mArraylist.get(position).getProductName());
        holder.productImage.setImageResource(mArraylist.get(position).getPictures());
        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaleDatabaseHalper db = new SaleDatabaseHalper(context);

                if(db.searchForSale(mArraylist.get(position).getBarcode()).isEmpty()){
                    db.addSale(
                            mArraylist.get(position).getProductName(),
                            mArraylist.get(position).getBarcode(),
                            mArraylist.get(position).getCategory(),
                            mArraylist.get(position).getPrice(),
                            mArraylist.get(position).getNumber());
                    Log.d(TAG, mArraylist.get(position).getProductName() + " eklendi.");
                }else{
                    db.saleUpdate(
                            mArraylist.get(position).getProductName(),
                            mArraylist.get(position).getBarcode(),
                            mArraylist.get(position).getCategory(),
                            mArraylist.get(position).getPrice(),
                            db.searchForSale(mArraylist.get(position).getBarcode()).get(0).getNumber() + 1
                    );
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView priceText, nameText;
        private ImageView productImage;
        private LayoutInflater layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            priceText = itemView.findViewById(R.id.grid_view_price_text_view);
            nameText = itemView.findViewById(R.id.grid_view_name_text_view);
            productImage = itemView.findViewById(R.id.grid_view_product_image_view);

        }
    }
}
