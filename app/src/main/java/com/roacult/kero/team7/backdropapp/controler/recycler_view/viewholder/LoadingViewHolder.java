package com.roacult.kero.team7.backdropapp.controler.recycler_view.viewholder;


import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.roacult.kero.team7.backdropapp.R;


public class LoadingViewHolder extends RecyclerView.ViewHolder {
   public ProgressBar progressBar;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        progressBar = itemView.findViewById(R.id.loadmore_progress);
    }

}





