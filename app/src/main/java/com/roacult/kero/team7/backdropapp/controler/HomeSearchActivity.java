package com.roacult.kero.team7.backdropapp.controler;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.recycler_view.adapter.ProductAdapter;
import com.roacult.kero.team7.backdropapp.model.Product;
import com.roacult.kero.team7.backdropapp.utils.Utils;

import java.util.ArrayList;

public class HomeSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText edstorNO;
    private EditText edBuilding;
    private EditText etStreet;
    private String Name;
    private String storNO;
    private String Building;
    private String Street;
    private Toolbar toolbar;
    private Button btn_search;
    private boolean waiting;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.arabicScrean(this);
        setContentView(R.layout.activity_signin);
        init();
        toolbar.setElevation(10);

        while (Utils.readList("List" , HomeSearchActivity.this)==null){
            Toast.makeText(this , "الرجاء الانتظار حتى الانتهاء من تحميل البيانات" , Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this , "تم تحميل البيانات .. بامكانك البدئ بالبحث الان" , Toast.LENGTH_LONG).show();
        btn_search.setOnClickListener(this);


    }

    private void init() {
        etName = findViewById(R.id.etName);
        edstorNO = findViewById(R.id.edstorNO);
        edBuilding = findViewById(R.id.edBuilding);
        etStreet = findViewById(R.id.etStreet);
        btn_search = findViewById(R.id.btn_search);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_search) {
            Name = getText(etName);
            storNO = getText(edstorNO);
            Building = getText(edBuilding);
            Street = getText(etStreet);
        if (!(Name.equals("") && storNO.equals("") && Building.equals("") && Street.equals(""))) {

            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Name", Name);
            i.putExtra("storNO", storNO);
            i.putExtra("Building", Building);
            i.putExtra("Street", Street);
            startActivity(i);
            }
        else
            Toast.makeText(this,"ادخل ما تريد البحث عنه أولا",Toast.LENGTH_LONG).show();


        }

      }

    private String getText(EditText et) {
        return et.getText().toString().toLowerCase().trim();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.stopService(this);
    }
}
