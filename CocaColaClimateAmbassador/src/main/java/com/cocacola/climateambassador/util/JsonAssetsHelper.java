package com.cocacola.climateambassador.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.models.Case;
import com.google.gson.Gson;

import java.io.File;
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
    private Gson mGson;

    @Inject
    public JsonAssetsHelper(Context context, Gson gson) {
        mContext = context;
        mGson = gson;
    }

    public String parseAsString(String filename) throws IOException {

        AssetManager assetManager = mContext.getAssets();

        InputStream in = assetManager.open("json" + File.separator + filename);

        int size = in.available();
        byte[] buffer = new byte[size];

        in.read(buffer);
        in.close();

        String fileAsString = new String(buffer);

        return fileAsString;

    }

    public Case parseCaseFromJsonFile(String filename) throws IOException {

        String json = parseAsString(filename);

        Case myCase = mGson.fromJson(json, Case.class);

        return myCase;

    }

}
