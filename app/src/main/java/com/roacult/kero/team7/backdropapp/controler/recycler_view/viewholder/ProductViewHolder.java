package com.roacult.kero.team7.backdropapp.controler.recycler_view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.roacult.kero.team7.backdropapp.R;

import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.model.Product;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    private TextView tvbuilding;
    private TextView tvstreet;
    private TextView tvstoreNumber;
    private TextView tvitem;
    private TextView tvchinaPrice;
    private TextView tvjordanianPrice;
    private TextView tvpacking;
    private TextView tvcartonsNo;
    private TextView tvtotal;
    private ImageView markImageView;
    private TextView llstorNo;
    private LinearLayout image;
    private ViewPager viewPager;
    private TextView tvNotes;

    private boolean mark = false;
    private ProductAdapter.OnItemClickListner listner;

    public ProductViewHolder(@NonNull final View itemView, final ProductAdapter.OnItemClickListner mlistener) {
        super(itemView);
        init(itemView);
        this.listner = mlistener;


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
        });

        tvstoreNumber.setOnClickListener(new View.OnClickListener() {
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
                        mlistener.onMenuClick(position, markImageView, mark);
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
        markImageView = view.findViewById(R.id.mark);
        viewPager = view.findViewById(R.id.viewpager);
        tvNotes = view.findViewById(R.id.tvNotes);


    }

    public void setData(Product product1) {
        tvbuilding.setText(product1.getBuilding());
        tvstreet.setText(product1.getStreet());
        tvstoreNumber.setText(product1.getStoreNumber());
        tvitem.setText(product1.getItem());
        tvchinaPrice.setText(product1.getChinaPrice());
        tvjordanianPrice.setText(product1.getJordanianPrice());
        tvpacking.setText(product1.getPacking());
        tvcartonsNo.setText(product1.getCartonsNo());
        tvtotal.setText(product1.getTotal());
        tvNotes.setText(product1.getNotes());
        listner.setViewPAger(viewPager, product1);
        // setupViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        if (product1.getMark())
            markImageView.setVisibility(View.VISIBLE);
        else
            markImageView.setVisibility(View.GONE);
    }
}




