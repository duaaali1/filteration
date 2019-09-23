package com.roacult.kero.team7.backdropapp.controler;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.dialog.MyDialogFragment;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Product> productList1 = new ArrayList<>();
    private ArrayList<Product> filterdproductList = new ArrayList<>();
    private EditText etName;
    private EditText edstorNO;
    private EditText edBuilding;
    private EditText edFloor;
    private EditText etStreet;
    private RecyclerView rvProduct;
    private  ProductAdapter productAdapter;
    private int position1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        init();
        readFromExcel();
        setupProductRv();
        addEditTextWatcher();
        filter();

    }
    private void setupProductRv() {
        rvProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        rvProduct.setItemAnimator(new DefaultItemAnimator());
        rvProduct.setNestedScrollingEnabled(false);

        productAdapter = new ProductAdapter(this, productList);

        productAdapter.setOnItemClickListner(new ProductAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position, View view) {
              /*  showMenu(view);
                position1=position;*/
            }
            @Override
            public void onLongItemClick(int position, View view) {

            }
        });
        rvProduct.setAdapter(productAdapter);
    }

    private void addEditTextWatcher() {
        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter();
                productAdapter.addAllItems(filterdproductList);
            }

            @Override
            public void afterTextChanged(Editable s) {

// productAdabter.add(filterdproductList)
            }
        };
        etName.addTextChangedListener(tw);
        edstorNO.addTextChangedListener(tw);
        edBuilding.addTextChangedListener(tw);
        edFloor.addTextChangedListener(tw);
        etStreet.addTextChangedListener(tw);

    }

    private void filter() {
        filterdproductList.clear();
        for (int i = 0; i < productList1.size(); i++) {
            filter(productList1.get(i));
        }
    }

    private void filter(Product product) {
        if ((product.getItem().toLowerCase().contains(getText(etName)) || getText(etName).equals("")) && (product.getStoreNumber().toLowerCase().equals(getText(edstorNO)) || getText(edstorNO).equals("")) && (product.getFloor().toLowerCase().equals(getText(edFloor)) || getText(edFloor).equals("")) && (product.getStreet().toLowerCase().equals(getText(etStreet)) || getText(etStreet).equals("")) && (product.getBuilding().toLowerCase().contains(getText(edBuilding)) || getText(edBuilding).equals("")))
            filterdproductList.add(product);

    }

    private String getText(EditText et) {
        return et.getText().toString().toLowerCase().trim();
    }


    private void init() {
        etName = findViewById(R.id.etName);
        edstorNO = findViewById(R.id.edstorNO);
        edBuilding = findViewById(R.id.edBuilding);
        edFloor = findViewById(R.id.edFloor);
        etStreet = findViewById(R.id.etStreet);
        rvProduct=findViewById(R.id.rvProduct);
  //      seach=findViewById(R.id.seach);
    }

    private void readFromExcel() {
        try {
            AssetManager am = getAssets();
            InputStream inputStream = am.open("android.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();

            for (int i = 1; i < rows; i++) {
                Product product =new Product(sheet.getCell(10, i).getContents(), sheet.getCell(9, i).getContents(), sheet.getCell(8, i).getContents(), sheet.getCell(7, i).getContents(), sheet.getCell(6, i).getContents(), sheet.getCell(5, i).getContents(), sheet.getCell(4, i).getContents(), sheet.getCell(3, i).getContents(), sheet.getCell(2, i).getContents(), sheet.getCell(1, i).getContents(), sheet.getCell(0, i).getContents(), null) ;
                productList.add(product);
                productList1.add(product);
            }

        } catch (Exception e) {

        }

    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }



    public void showMenu(View v ) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
              productAdapter.remove(productList1.get(position1));
               productList1.remove(productList1.get(position1));

                return true;
            case R.id.edit:
                setupDialog();
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
               productAdapter.add(product , position1);
               productList1.remove(productList1.get(position1));
               productList1.add(position1,product);


            }
        });
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

