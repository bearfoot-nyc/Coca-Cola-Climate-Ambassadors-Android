package com.cocacola.climateambassador;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    private File mPkgDir;
    private File mDocsDir;
    private AssetManager mAssetManager;

    private static final String DOCS_DIR = "docs";

    @Inject
    public AppPackageFileWriter(Context context) {
        mContext = context;
        mPkgDir = context.getFilesDir();
        mDocsDir = new File(mPkgDir.getAbsolutePath() + File.separator + DOCS_DIR);
        mAssetManager = context.getAssets();
    }

    public void writeAssetsToPackageDir() {

        // Get path of assets
        String[] assets = getAllAssets();

        // Open each file
        for(String asset : assets) {

            // Write file to private package dir
            writeToPkgDir(createInputFromAsset(DOCS_DIR, asset), mDocsDir, asset);

        }

    }

    private InputStream createInputFromAsset(String directory, String fileName) {

        BufferedInputStream in = null;

        try {
            in = new BufferedInputStream(mAssetManager.open(directory + File.separator + fileName));
        } catch (FileNotFoundException e) {
            Log.e(e, "No file found for %s", fileName);
        } catch (IOException e) {
           Log.e(e, "IOException");
        }

        return in;

    }

    private void writeToPkgDir(InputStream in, File directory, String fileName) {

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, fileName);

        FileOutputStream out = null;

        try {

            out = new FileOutputStream(file);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            in = null;

            out.flush();
            out.close();
            out = null;


        } catch (IOException e) {
            e.printStackTrace();
            Log.e(e, "failed to write %s to disk", fileName);
        }

    }

    public String[] getAllAssets() {

        AssetManager assetManager = mContext.getAssets();

        String files[] = null;

        try {
            files = assetManager.list("docs");
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
