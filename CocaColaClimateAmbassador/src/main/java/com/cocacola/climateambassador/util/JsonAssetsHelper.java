package com.cocacola.climateambassador.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.models.Case;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by realandylawton on 8/31/13.
 */

public class JsonAssetsHelper {

    public static String parseAsString(Context context, String filename) throws IOException {

        AssetManager assetManager = context.getAssets();

        InputStream in = assetManager.open("json" + File.separator + filename);

        int size = in.available();
        byte[] buffer = new byte[size];

        in.read(buffer);
        in.close();

        String fileAsString = new String(buffer);

        return fileAsString;

    }

    public static Case parseCaseFromJsonFile(Context context, String filename) throws IOException {

        String json = parseAsString(context, filename);

        Case myCase = new Case();

        return myCase;

    }

}
