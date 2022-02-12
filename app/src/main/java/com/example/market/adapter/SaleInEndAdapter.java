package com.example.market.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.market.R;
import com.example.market.product.SaleInEnd;

import java.util.ArrayList;

public class SaleInEndAdapter extends RecyclerView.Adapter<SaleInEndAdapter.ViewHolder>{

    private ArrayList<SaleInEnd> mArraylist;
    private Context context;
    private static final String TAG = "SaleInEndAdapter";

    public SaleInEndAdapter(ArrayList<SaleInEnd> mArraylist, Context context) {
        this.mArraylist = mArraylist;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recyclerview_companent_sale_in_end,parent,false);
        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.number.setText(mArraylist.get(position).getSaleInEndNumber());
        holder.totalPriceText.setText(mArraylist.get(position).getSaleInEndTotalPrice());
        holder.priceText.setText(mArraylist.get(position).getSaleInEndPrices());
        holder.productText.setText(mArraylist.get(position).getSaleInEndProduct());

    }

    @Override
    public int getItemCount() {
        return mArraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productText, priceText, totalPriceText, number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productText = itemView.findViewById(R.id.sale_in_end_product_text);
            priceText = itemView.findViewById(R.id.sale_in_end_prices_text);
            totalPriceText = itemView.findViewById(R.id.sale_in_end_total_price_text);
            number = itemView.findViewById(R.id.sale_in_end_number);


        }
    }
}
