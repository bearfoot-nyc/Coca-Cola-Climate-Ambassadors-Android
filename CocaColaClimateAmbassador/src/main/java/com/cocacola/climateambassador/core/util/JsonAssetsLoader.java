package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.core.CaConstants;
import com.cocacola.climateambassador.data.json.CaseJson;
import com.cocacola.climateambassador.data.json.ModuleJson;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by realandylawton on 8/31/13.
 */

@Singleton
public class JsonAssetsLoader {

    private Context mContext;
    private Gson mGson;

    @Inject
    public JsonAssetsLoader(Context context, Gson gson) {
        mContext = context;
        mGson = gson;
    }

    public <T> T parseFromJsonFile(String fileName, Class<T> type) throws IOException {

        String json = parseAsString(fileName);

        T t = mGson.fromJson(json, type);

        return t;

    }

    private String parseAsString(String filename) throws IOException {

        AssetManager assetManager = mContext.getAssets();

        InputStream in = assetManager.open(CaConstants.JSON_ASSETS_DIRECTORY + File.separator + filename);

        int size = in.available();
        byte[] buffer = new byte[size];

        in.read(buffer);
        in.close();

        String fileAsString = new String(buffer);

        return fileAsString;

    }

}
