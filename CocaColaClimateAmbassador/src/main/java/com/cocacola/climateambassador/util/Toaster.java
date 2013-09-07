package com.cocacola.climateambassador.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by realandylawton on 9/7/13.
 */
public class Toaster {

    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG);
    }

}
