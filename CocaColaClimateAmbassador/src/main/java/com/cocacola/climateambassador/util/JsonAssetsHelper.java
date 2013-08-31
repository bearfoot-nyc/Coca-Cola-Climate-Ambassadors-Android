package com.cocacola.climateambassador.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by realandylawton on 8/31/13.
 */

@Singleton
public class JsonAssetsHelper {

    private Context mContext;

    @Inject
    public JsonAssetsHelper() { }

    public static String parseAsString(Context context, String filename) throws IOException {

        InputStream in = context.getAssets().open(filename);

        int size = in.available();
        byte[] buffer = new byte[size];

        in.read(buffer);
        in.close();

        String fileAsString = new String(buffer);

        return fileAsString;

    }

}
