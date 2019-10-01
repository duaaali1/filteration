package com.roacult.kero.team7.backdropapp.controler.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.IBinder;

import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roacult.kero.team7.backdropapp.R;
import com.roacult.kero.team7.backdropapp.controler.MainActivity;
import com.roacult.kero.team7.backdropapp.controler.MyCallback;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

public class MyStartedServiceWithNotification extends Service {
    public static final String CHANNEL_ID = "exampleServiceChannel";
    private ArrayList<Product> productList = new ArrayList<>();
    private static ArrayList<Product>  productList1 = new ArrayList<>();
    private  static MyCallback  callback;

    public ArrayList<Product> getProductList1() {
        return productList1;
    }
    public  static void   sendList(){
        callback.send(productList1);
    }
    public void setProductList1(ArrayList<Product> productList1) {
        this.productList1 = productList1;
    }

    public static void setCallBack(MyCallback callBack) {
       callback = callBack;
   }

    public class MyThreadClass implements Runnable {
    private int service_id;

        public MyThreadClass(int service_id) {
            this.service_id = service_id;

        }


        @Override
        public void run() {
            synchronized (this) {
                getData() ;



            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Example Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);


        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("شخشير")
                .setContentText("تطبيق شخشير يعمل الان")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);

        Thread thread = new Thread(new MyThreadClass(startId));
        thread.start();
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void getData() {
        ArrayList List = (ArrayList) readList("List");
        if (List == null) {
            readFromExcel();
            saveList(productList1, "List");

        } else
            productList1= List;
    }
    private void readFromExcel() {
        try {

            AssetManager am = getAssets();
            InputStream inputStream = am.open("android.xls");
            Workbook workbook = Workbook.getWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            for (int i = 1; i < rows; i++) {
                Product  product = new Product(getContents(sheet, i, 0), null, getContents(sheet, i, 1), getContents(sheet, i, 2), null, getContents(sheet, i, 3), getContents(sheet, i, 4), getContents(sheet, i, 5), getContents(sheet, i, 6), getContents(sheet, i, 7), getContents(sheet, i, 8), getImageLIst(sheet, i), getContents(sheet, i, 9),getContents(sheet, i, 10));
                productList.add(product);
                productList1.add(product);
            }


        } catch (Exception e) {
            int b=0;
        }

    }
    private void saveList(ArrayList<Product> List, String TAG) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(List);
        editor.putString(TAG, json);
        editor.apply();
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

    private List<Product> readList(String TAG) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, "");
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
    private String getContents(Sheet sheet, int i, int i2) {
        return sheet.getCell(i2, i).getContents().trim();
    }

}
