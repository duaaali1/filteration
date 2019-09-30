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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.MyCallback;
import com.roacult.kero.team7.backdropapp.model.Product;


public class AddDialogFragment extends DialogFragment {
    private Button img1, ivCancel;
    private MyCallback myCallback;
    private EditText edbuilding;
    private EditText edstreet;
    private EditText edstoreNumber;
    private EditText editem;
    private EditText edchinaPrice;
    private EditText edjordanianPrice;
    private EditText edpacking;
    private EditText edNotes;
    private EditText edcartonsNo;
    private EditText edtotal;


    public AddDialogFragment(MyCallback myCallback) {
        this.myCallback = myCallback;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.from(getActivity()).inflate(R.layout.daialog_add_product, container, false);
        setupCancel(rootView);
        init(rootView);
        setupWishBtn(rootView);
        return rootView;
    }

    private void init(View rootView) {
        edbuilding = rootView.findViewById(R.id.building);
        edstreet = rootView.findViewById(R.id.street);
        edstoreNumber = rootView.findViewById(R.id.storeNumber);
        editem = rootView.findViewById(R.id.item);
        edchinaPrice = rootView.findViewById(R.id.chinaPrice);
        edpacking = rootView.findViewById(R.id.packing);
        edcartonsNo = rootView.findViewById(R.id.cartonsNo);
        edNotes=rootView.findViewById(R.id.notes);
    }

    private void setupWishBtn(View rootView) {
        img1 = rootView.findViewById(R.id.save);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCallback.onSave(new Product(getText(edbuilding), null, getText(edstreet), getText(edstoreNumber), null, getText(editem), getText(edchinaPrice), null, getText(edpacking), getText(edcartonsNo), null, null , getText(edNotes)));
                getDialog().dismiss();
            }
        });
    }

    private String getText(EditText edbuilding) {
        return edbuilding.getText().toString().trim();
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