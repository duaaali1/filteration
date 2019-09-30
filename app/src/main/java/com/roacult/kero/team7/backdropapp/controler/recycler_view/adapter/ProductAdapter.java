package com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.viewholder.LoadingViewHolder;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.viewholder.ProductViewHolder;
import com.roacult.kero.team7.backdropapp.model.BaseModel;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * HOME ITEM PRODUCT ADAPTER
 */

public class ProductAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Product> productList;
    private static final int LOADING = 0;
    private static final int ITEM = 1;
    private boolean isLoadingAdded = false;
    private ProductAdapter.OnItemClickListner mlistner;

    public interface OnItemClickListner {
        void onItemClick(int position, View view);

        void onchangeMark(boolean mark);

        void onNOClick(int position, View view);

        void onMenuClick(int position, View view , boolean mark );

        void setViewPAger(ViewPager viewPager, Product product1);
    }

    public void setOnItemClickListner(ProductAdapter.OnItemClickListner listner) {
        mlistner = listner;
    }

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.new_row, parent, false);
                return new ProductViewHolder(itemView, mlistner);

            case LOADING:
                View viewLoading = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_loadmore, parent, false);
                return new LoadingViewHolder(viewLoading);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseModel model = productList.get(position);
        switch (getItemViewType(position)) {
            case ITEM:
                ProductViewHolder productViewHolder = (ProductViewHolder) holder;
                productViewHolder.setData((Product) model);
                break;
            case LOADING:
                LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                break;

        }
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }





    public void add(Product model , int position) {
        productList.add(model);
        notifyItemInserted(position);
    }public void remove(Product model) {
        productList.remove(model);
        this.notifyDataSetChanged();

    }public void update(Product model , int position) {
        productList.set(position, model);
        this.notifyItemChanged(position);

    }

    public void addAllItems(List<Product> list) {
        this.productList.clear();
        this.productList.addAll(list);
        this.notifyDataSetChanged();

    }

    public int getItemViewType(int position) {
        return (position == productList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }
}
