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
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.MyCallback;


public class MoreDialogFragment extends DialogFragment implements View.OnClickListener {
    private Button ivCancel;
    private TextView tvDelete;
    private TextView tvMark;
    private TextView tvEdit;

    private MyCallback myCallback;
    private boolean mark;

    public MoreDialogFragment(MyCallback myCallback, boolean mark) {
        this.myCallback = myCallback;
        this.mark = mark;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView;
        if (!mark) {
            rootView = inflater.from(getActivity()).inflate(R.layout.dialog, container, false);
        } else {
            rootView = inflater.from(getActivity()).inflate(R.layout.delete_mark_dialog, container, false);
        }

        init(rootView);
        setupCancel(rootView);
        return rootView;
    }

    private void init(View rootView) {
        tvDelete = rootView.findViewById(R.id.tvDelete);
        tvMark = rootView.findViewById(R.id.tvMark);
        tvEdit = rootView.findViewById(R.id.tvEdit);
        tvDelete.setOnClickListener(this);
        tvMark.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
    }

 /*   @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                productAdapter.remove(filterdproductList.get(position1));
                productList1.remove(filterdproductList.get(position1));
                saveList(productList1, "List");
                return true;
            case R.id.edit:
                setupDialog();
                return true;
            case R.id.mark:
                ///mark visible
                markedList1.add(productList1.get(position1));
                saveList(markedList1 , "mark");
                return true;
            default:
                return false;
        }
    }*/

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvDelete:
                myCallback.delete();
                dismiss();
                break;
            case R.id.tvEdit:
                myCallback.edit();
                dismiss();
                break;
            case R.id.tvMark:
                myCallback.mark();
                dismiss();
                break;

        }
    }


}