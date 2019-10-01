package com.roacult.kero.team7.backdropapp.controler;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import android.os.Bundle;
import android.preference.PreferenceManager;

import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.ViewPagerFragment;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.controler.service.MyStartedServiceWithNotification;
import com.roacult.kero.team7.backdropapp.dialog.AddDialogFragment;
import com.roacult.kero.team7.backdropapp.dialog.CardDialogFragment;
import com.roacult.kero.team7.backdropapp.dialog.MoreDialogFragment;
import com.roacult.kero.team7.backdropapp.model.BaseModel;
import com.roacult.kero.team7.backdropapp.model.Product;
import com.roacult.kero.team7.backdropapp.utils.Utils;


import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<BaseModel> productList = new ArrayList<>();
    private ArrayList<Product> productList1 = new ArrayList<>();
    private ArrayList<Product> markedList1 = new ArrayList<>();
    private ArrayList<Product> filterdproductList = new ArrayList<>();
    private RecyclerView rvProduct;
    private ProductAdapter productAdapter;
    private com.github.clans.fab.FloatingActionButton fabSearch;
    private com.github.clans.fab.FloatingActionButton fabMark;
    private com.github.clans.fab.FloatingActionButton fabAdd;
    private String Name;
    private String storNO;
    private String Building;
    private String Street;
    private int position1;
    private Toolbar mTopToolbar;
    private com.github.clans.fab.FloatingActionMenu fab;
    private static MyCallback myCallback;
    private Integer nextPage = 0;
    private ArrayList arrayList;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.arabicScrean(this);
        setContentView(R.layout.search);
        mTopToolbar = findViewById(R.id.toolbar);
        mTopToolbar.setElevation(10);
        setSupportActionBar(mTopToolbar);
        init();
        setToolbar();
        getExtra();
        MyStartedServiceWithNotification.setCallBack(new MyCallback() {
            @Override
            public void onSave(Product product) {

            }

            @Override
            public void send(ArrayList<Product> productList2) {


                productList1.addAll(productList2);
                setupProductRv();
                filter();
                //  arrayList = (ArrayList) chopped(filterdproductList, 20);
                //containsName(productList1, "قش");
                fabSearch.setOnClickListener(MainActivity.this);
                fabMark.setOnClickListener(MainActivity.this);
                fabAdd.setOnClickListener(MainActivity.this);
            }

        });
        if (productList1.size() == 0)
            startService();
        MyStartedServiceWithNotification.sendList();
        // getData();


        //markedList1= (ArrayList<Product>) readList("mark");


    }

    private void startService() {
        Intent intent = new Intent(this, MyStartedServiceWithNotification.class);

        startService(intent);
    }

    private void filter() {
        filterdproductList.clear();
        for (int i = 0; i < productList1.size(); i++) {
            filter(productList1.get(i));
        }
        productAdapter.addAllItems(filterdproductList);

    }


    public static void setCallBack(MyCallback callBack) {
        myCallback = callBack;
    }

    public void setToolbar() {
        setSupportActionBar(mTopToolbar);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


    private void getExtra() {
        Intent intent = getIntent();
        Name = intent.getExtras().getString("Name");
        storNO = intent.getExtras().getString("storNO");
        Building = intent.getExtras().getString("Building");
        Street = intent.getExtras().getString("Street");
    }

    private void setupProductRv() {
        rvProduct.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvProduct.setItemAnimator(new DefaultItemAnimator());
        rvProduct.setNestedScrollingEnabled(false);
        productAdapter = new ProductAdapter(this, productList);
        productAdapter.setOnItemClickListner(new ProductAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position, View view) {
                position1 = position;
            }

            @Override
            public void onchangeMark(boolean mark) {
                //   ismark = mark;
            }


            @Override
            public void onNOClick(int position, View view) {
                showCardDialog(filterdproductList.get(position));
            }

            @Override
            public void onMenuClick(int position, View view, boolean mark) {
                position1 = position;


                setupMoreDialog(view.getVisibility() == View.VISIBLE, view, position);
                // ismark = mark;

            }

            @Override
            public void setViewPAger(ViewPager viewPager, Product product1) {
                setupViewPager(viewPager, product1);
            }


        });
        /*productAdapter.setLoaderListener(rvProduct, new PaginationCallBack() {
            @Override
            public void loadMoreItems() {
                if (nextPage != null)
                    requestUsers();
            }
        }, productAdapter);*/
        rvProduct.setAdapter(productAdapter);
    }

    private void requestUsers() {
        int totalPages = arrayList.size();
        if (nextPage < totalPages) {
            productAdapter.addAllItems((List<Product>) arrayList.get(nextPage));
            nextPage++;
        } else {
            nextPage = null;
            productAdapter.removeLoadingFooter();
        }
    }


    private void setupViewPager(ViewPager viewPager, Product product1) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if (product1.getImagesList() != null) {
            for (int i = 0; i < product1.getImagesList().size(); i++) {
                adapter.addFragment(new ViewPagerFragment(product1.getImagesList().get(i)));

            }
            viewPager.setAdapter(adapter);

            //   adapter.addFragment(new ViewPagerFragment("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"));
        }

    }




  /*  private void filter() {

        filterdproductList.clear();
        filterdproductList.addAll(Collections2.filter(productList1, filterDescription(Name, storNO, Building, Street))) ;
        productAdapter.addAllItems(filterdproductList);

       *//* for (int i = 0; i < productList1.size(); i++) {
            filter(productList1.get(i));
        }*//*
    }*/



    private void filter(Product product) {
        if ((product.getItem().toLowerCase().contains(Name) || Name.equals("")) && (product.getStoreNumber().toLowerCase().equals(storNO) || storNO.equals("")) && (product.getStreet().toLowerCase().equals(Street) || Street.equals("")) && (product.getBuilding().toLowerCase().contains(Building) || Building.equals(""))) {
            if(markedList1.contains(product))
                product.setMark(true);
            filterdproductList.add(product);
        }


    }


    private void init() {

        rvProduct = findViewById(R.id.rvProduct);
        fab = findViewById(R.id.menu_green);

        fabSearch = findViewById(R.id.fabSearch);
        fabMark = findViewById(R.id.fabMark);
        fabAdd = findViewById(R.id.fabAdd);

    }

    private void readFromExcel() {
        try {

            AssetManager am = getAssets();
            InputStream inputStream = am.open("android.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            for (int i = 1; i < rows; i++) {
                Product product = new Product(getContents(sheet, i, 0), null, getContents(sheet, i, 1), getContents(sheet, i, 2), null, getContents(sheet, i, 3), getContents(sheet, i, 4), getContents(sheet, i, 5), getContents(sheet, i, 6), getContents(sheet, i, 7), getContents(sheet, i, 8), getImageLIst(sheet, i), getContents(sheet, i, 9), getContents(sheet, i, 10));
                productList.add(product);
                productList1.add(product);
            }


        } catch (Exception e) {
            int b = 0;

        }

    }

    private String getContents(Sheet sheet, int i, int i2) {
        return sheet.getCell(i2, i).getContents().trim();
    }

    private ArrayList<String> getImageLIst(Sheet sheet, int j) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 10; i < 16; i++) {
            if (getContents(sheet, j, i).equals(""))
                continue;
            arrayList.add(getContents(sheet, j, i));
        }
        return arrayList;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabSearch:
                productAdapter.addAllItems(filterdproductList);

                break;
            case R.id.fabMark:
                ArrayList markedList2 = (ArrayList<Product>) readList("mark");
                if (markedList2 != null)
                    productAdapter.addAllItems(markedList2);
                else {
                    markedList2=new ArrayList();
                    productAdapter.addAllItems(markedList2);
                }

                break;
            case R.id.fabAdd:
                setupAddDialog();

                break;

        }
    }


    private void setupDialog() {
        final AddDialogFragment dialogFragment = new AddDialogFragment(new MyCallback() {
            @Override
            public void onSave(Product product) {
                product.setImagesList(filterdproductList.get(position1).getImagesList());
                product.setStoreImage(filterdproductList.get(position1).getStoreImage());
                productAdapter.update(product, position1);
                productList1.set(productList1.indexOf(filterdproductList.get(position1)), product);
                if (markedList1.contains(filterdproductList.get(position1)))
                    markedList1.set(markedList1.indexOf(filterdproductList.get(position1)), product);
                filterdproductList.set(position1, product);
                saveList(markedList1, "mark");
                saveList(productList1, "List");
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }

    private void setupAddDialog() {
        final AddDialogFragment dialogFragment = new AddDialogFragment(new MyCallback() {
            @Override
            public void onSave(Product product) {
                productList1.add(product);
                filterdproductList.add(product);
                saveList(productList1, "List");
                productAdapter.add(product, productList1.size() - 1);
            }


        });
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }

    private void setupMoreDialog(final boolean mark, final View view, int position) {
        final MoreDialogFragment dialogFragment = new MoreDialogFragment(new MyCallback() {
            @Override
            public void onSave(Product product) {

            }


            @Override
            public void delete() {
                productAdapter.remove(filterdproductList.get(position1));
                productList1.remove(filterdproductList.get(position1));


                saveList(productList1, "List");
                if (markedList1.contains(filterdproductList.get(position1))) {

                    markedList1.remove(filterdproductList.get(position1));
                    saveList(markedList1, "mark");
                }
                filterdproductList.remove(position1);
            }

            @Override
            public void edit() {
                setupDialog();

            }

            @Override
            public void mark() {
                if (view.getVisibility() == View.VISIBLE)
                    view.setVisibility(View.GONE);
                else
                    view.setVisibility(View.VISIBLE);
                //    myCallback.mark();
                if (view.getVisibility() == View.VISIBLE) {
                    filterdproductList.get(position1).setMark(true);
                    markedList1.add(filterdproductList.get(position1));
                } else if (markedList1.contains(filterdproductList.get(position1))) {
                    filterdproductList.get(position1).setMark(false);
                    markedList1.remove(filterdproductList.get(position1));

                }

                saveList(markedList1, "mark");

            }
        }, mark);
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }

    private void showCardDialog(Product product) {
        final CardDialogFragment dialogFragment = new CardDialogFragment(product.getStoreImage());
        dialogFragment.show(getSupportFragmentManager(), "Sample Fragment");
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private List<Product> readList(String TAG) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, "");
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    private void saveList(ArrayList<Product> List, String TAG) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(List);
        editor.putString(TAG, json);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.stopService(this);
    }
}

