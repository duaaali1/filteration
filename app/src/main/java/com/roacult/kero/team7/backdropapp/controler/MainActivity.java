package com.roacult.kero.team7.backdropapp.controler;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.io.InputStream;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Product> filterdproductList = new ArrayList<>();

    private EditText etName;
    private EditText edstorNO;
    private EditText edBuilding;
    private EditText edFloor;
    private EditText etStreet;
    private RecyclerView rvProduct;
    private  ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        init();
        readFromExcel();
        setupProductRv();
        addEditTextWatcher();


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

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter();
                productAdapter.addAllItems(filterdproductList);
// productAdabter.add(filterdproductList)
            }
        };
        etName.addTextChangedListener(tw);
        edstorNO.addTextChangedListener(tw);
        edBuilding.addTextChangedListener(tw);
        edFloor.addTextChangedListener(tw);
        etStreet.addTextChangedListener(tw);
        ;
    }

    private void filter() {
        for (int i = 0; i < productList.size(); i++) {
            filter(productList.get(i));
        }
    }

    private void filter(Product product) {
        filterdproductList.clear();
        if ((product.getItem().contains(getText(etName)) || getText(etName).equals("")) && (product.getItem().equals(getText(edstorNO)) || getText(edstorNO).equals("")) && (product.getItem().equals(getText(edFloor)) || getText(edFloor).equals("")) && (product.getItem().equals(getText(etStreet)) || getText(etStreet).equals("")) && (product.getItem().contains(getText(edBuilding)) || getText(edBuilding).equals("")))
            filterdproductList.add(product);

    }

    private String getText(EditText et) {
        return et.getText().toString().trim();
    }


    private void init() {
        etName = findViewById(R.id.etName);
        edstorNO = findViewById(R.id.edstorNO);
        edBuilding = findViewById(R.id.edBuilding);
        edFloor = findViewById(R.id.edFloor);
        etStreet = findViewById(R.id.etStreet);
        rvProduct=findViewById(R.id.rvProduct);
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
            }

        } catch (Exception e) {

        }

    }
}

