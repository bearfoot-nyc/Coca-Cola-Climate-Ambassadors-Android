package com.cocacola.climateambassador;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */

@Singleton
public class AppPackageFileWriter {

    @Inject
    Timber Log;

    private Context mContext;

    @Inject
    public AppPackageFileWriter(Context context) {
        mContext = context;
    }

    public void writeAssetsToPackageDir() {

        // Get path of assets
        String[] assets = getAllAssets();

        // Get access to private package dir
        File pkgDir = mContext.getFilesDir();


        // Open each file
        for(String asset : assets) {

            // Write file to private package dir
            writeToPkgDir(pkgDir, asset);

        }

    }

    private void writeToPkgDir(File pkgDir, String fileName) {

        File file = new File(pkgDir, fileName);

        try {

            BufferedOutputStream out;
            out = new BufferedOutputStream(new FileOutputStream(file));

            out.flush();
            out.close();


        } catch (IOException e) {
            e.printStackTrace();
            Log.e(e, "failed to write %s to disk", fileName);
        }

    }

    public String[] getAllAssets() {

        AssetManager assetManager = mContext.getAssets();

        String files[] = null;

        try {
            files = assetManager.list("pdf");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(e, "no assets");
            return null;
        }

        return files;

    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
