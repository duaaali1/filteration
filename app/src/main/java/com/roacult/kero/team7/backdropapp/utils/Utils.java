package com.roacult.kero.team7.backdropapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.roacult.kero.team7.backdropapp.controler.service.MyStartedServiceWithNotification;
import com.roacult.kero.team7.backdropapp.model.Product;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;

public class Utils {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void arabicScrean(Activity activity) {
        Resources res = activity.getResources();
        // Change locale settings in the app.

        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale("ar")); // API 17+ only.
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }
    public static List<Product> readList(String TAG , Activity activity) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(TAG, "");
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
    public static void stopService(Activity activity) {
        Intent intent = new Intent(activity, MyStartedServiceWithNotification.class);
        activity.stopService(intent);
    }

}
