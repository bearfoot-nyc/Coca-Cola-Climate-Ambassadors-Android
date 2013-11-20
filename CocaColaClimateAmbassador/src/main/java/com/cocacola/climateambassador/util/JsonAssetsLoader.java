package com.cocacola.climateambassador.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.CaConstants;
import com.cocacola.climateambassador.json.CaseJson;
import com.cocacola.climateambassador.json.ModuleJson;
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

    // TODO Genercize this: T<Model> parseClassFromJsonfile
    public CaseJson parseCaseFromJsonFile(String filename) throws IOException, JsonSyntaxException {

        String json = parseAsString(filename);

        CaseJson myCase = mGson.fromJson(json, CaseJson.class);

        return myCase;

    }

    public ModuleJson parseModuleFromJsonFile(String filename) throws IOException, JsonSyntaxException {

        String json = parseAsString(filename);

        ModuleJson myModule = mGson.fromJson(json, ModuleJson.class);

        return myModule;

    }

}
