package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by realandylawton on 9/7/13.
 */
public class Toaster {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int resId) {
        String text = context.getResources().getString(resId);
        toast(context, text);
    }

}
