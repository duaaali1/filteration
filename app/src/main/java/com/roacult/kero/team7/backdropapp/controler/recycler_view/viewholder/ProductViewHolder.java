package com.roacult.kero.team7.backdropapp.controler.recycler_view.viewholder;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.model.Product;


public class ProductViewHolder extends RecyclerView.ViewHolder {
    private TextView tvbuilding;

    private TextView tvstreet;
    private TextView tvstoreNumber;
    private TextView tvitemNo;
    private TextView tvitem;
    private TextView tvchinaPrice;
    private TextView tvjordanianPrice;
    private TextView tvpacking;
    private TextView tvcartonsNo;
    private TextView tvtotal;
    private ImageView imagesList;

    private TextView llstorNo;
    private LinearLayout image;

    public ProductViewHolder(@NonNull View itemView, final ProductAdapter.OnItemClickListner mlistener) {
        super(itemView);
        init(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onItemClick(position, view);
                    }
                }
            }
        });
        llstorNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onNOClick(position, view);
                    }
                }
            }
        });tvstoreNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onNOClick(position, view);
                    }
                }
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mlistener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mlistener.onMenuClick(position, view);
                    }
                }
            }
        });
    }

    private void init(View view) {
        tvbuilding = view.findViewById(R.id.tvbuilding);
        tvstreet = view.findViewById(R.id.tvstreet);
        tvstoreNumber = view.findViewById(R.id.tvstoreNumber);
        tvitem = view.findViewById(R.id.tvitem);
        tvchinaPrice = view.findViewById(R.id.tvchinaPrice);
        tvjordanianPrice = view.findViewById(R.id.tvjordanianPrce);
        tvpacking = view.findViewById(R.id.tvpacking);
        tvcartonsNo = view.findViewById(R.id.tvcartonsNo);
        tvtotal = view.findViewById(R.id.tvtotal);
        llstorNo = view.findViewById(R.id.llstorNo);
        image = view.findViewById(R.id.more);
        //imagesList=view.findViewById(R.i);


    }

    public void setData(Product product) {
        tvbuilding.setText(product.getBuilding());

        tvstreet.setText(product.getStreet());
        tvstoreNumber.setText(product.getStoreNumber());
        tvitem.setText(product.getItem());
        tvchinaPrice.setText(product.getChinaPrice());
        tvjordanianPrice.setText(product.getJordanianPrice());
        tvpacking.setText(product.getPacking());
        tvcartonsNo.setText(product.getCartonsNo());
        tvtotal.setText(product.getTotal());
    }
}




