package com.roacult.kero.team7.backdropapp.controler.recycler_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.roacult.kero.team7.backdropapp.R;


public class ViewPagerFragment extends Fragment {
    private ImageView ivViewPager;
    private String img;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public ViewPagerFragment(String img) {
        this.img = img;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setImage(img);
    }

    private void setImage(String img) {
        Glide.with(ivViewPager.getContext().getApplicationContext())
                .load(img)
                .into(ivViewPager);
    }

    private void init(View view) {
        ivViewPager = view.findViewById(R.id.ivViewPager);
    }
}
