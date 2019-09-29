package com.roacult.kero.team7.backdropapp.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

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
}
