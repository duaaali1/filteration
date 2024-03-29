package com.roacult.kero.team7.backdropapp.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.roacult.kero.team7.backdropapp.R;


public class CardDialogFragment extends DialogFragment {
    private String img;
    private ImageView img1, ivCancel;

    public CardDialogFragment(String img) {
        this.img = img;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.from(getActivity()).inflate(R.layout.cart_dialog, container, false);
        initImage(rootView);
        setupCancel(rootView);
        return rootView;
    }


    private void initImage(View rootView) {
        img1 = rootView.findViewById(R.id.ivImg);
        Glide.with(rootView.getContext().getApplicationContext())
                .load(img)
                .into(img1);
    }

    private void setupCancel(View rootView) {
        ivCancel = rootView.findViewById(R.id.ivCancel);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        final View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_sample_dialog, null);

        final Drawable d = new ColorDrawable(Color.BLACK);

        d.setAlpha(140);

        dialog.getWindow().setBackgroundDrawable(d);
        dialog.getWindow().setContentView(view);
        dialog.setContentView(view);
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        params.gravity = Gravity.CENTER;


        dialog.setCanceledOnTouchOutside(true);

        return dialog;
    }
}