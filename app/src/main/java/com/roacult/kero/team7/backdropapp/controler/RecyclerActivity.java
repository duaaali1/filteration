package com.roacult.kero.team7.backdropapp.controler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.dialog.AddDialogFragment;
import com.roacult.kero.team7.backdropapp.dialog.CardDialogFragment;
import com.roacult.kero.team7.backdropapp.dialog.MyDialogFragment;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.util.ArrayList;
import java.util.Locale;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Product> productList1 = new ArrayList<>();
    private ArrayList<Product> markedList1 = new ArrayList<>();
    private RecyclerView rvProduct;
    private ProductAdapter productAdapter;
    private int position1;

    private FloatingActionButton fab;
    private ImageView ivMark;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources res = this.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale("ar")); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
        setContentView(R.layout.search_product);
        init();
        setupProductRv();
        ivMark.setOnClickListener(this);
        fab.setOnClickListener(this);

    }
    private void init() {
        rvProduct = findViewById(R.id.rvProduct);

        fab = findViewById(R.id.fab);
    }

    private void setupProductRv() {
        rvProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvProduct.setItemAnimator(new DefaultItemAnimator());
        rvProduct.setNestedScrollingEnabled(false);
        productAdapter = new ProductAdapter(this, productList);
        productAdapter.setOnItemClickListner(new ProductAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position, View view) {

            }

            @Override
            public void onLongItemClick(int position, View view) {

            }

            @Override
            public void onNOClick(int position, View view) {
                showCardDialog();
            }

            @Override
            public void onMenuClick(int position, View view) {
                showMenu(view);
                position1=position ;
            }
        });
        rvProduct.setAdapter(productAdapter);
    }
    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu);
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                ///////
                productAdapter.remove(productList1.get(position1));
                productList1.remove(productList1.get(position1));
                return true;
            case R.id.edit:
                setupDialog();
                return true;
            case R.id.mark:
                //note
                markedList1.add(productList1.get(position1));
                return true;
            default:
                return false;
        }
    }
    private void setupDialog() {
        final MyDialogFragment dialogFragment = new MyDialogFragment(new MyCallback() {
            @Override
            public void onSave(Product product) {
                productAdapter.remove(productList1.get(position1));
                productAdapter.add(product, position1);
                productList1.remove(productList1.get(position1));
                productList1.add(position1, product);


            }


        });
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }
    private void setupAddDialog() {
        final AddDialogFragment dialogFragment = new AddDialogFragment(new MyCallback() {
            @Override
            public void onSave(Product product) {
                productList1.add(product);
                productAdapter.add(product, productList1.size() - 1);
            }


        });
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }
    private void showCardDialog() {
        final CardDialogFragment dialogFragment = new CardDialogFragment("data:image/jpeg;base64,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");
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.fab:
                setupAddDialog();

                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
