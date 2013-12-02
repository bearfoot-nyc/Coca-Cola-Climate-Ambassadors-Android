package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.data.json.FileType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */

@Singleton
public class AppPackageFileWriter {

    private static final String DOCUMENTS_DIRECTORY = "docs";

    public class PackageWriteException extends Exception {

        private String fileName;

        public PackageWriteException(String fileName) {
            super();
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    @Inject protected Timber Log;
    private Context mContext;

    private AssetManager mAssetManager;

    @Inject public AppPackageFileWriter(Context context) {
        mContext = context;
        mAssetManager = mContext.getAssets();
    }

    public File writeToPkgDir(String fileName) throws PackageWriteException {

        File file = null;

        InputStream in = null;
        try {
            in = createInputFromAsset(fileName);
            file = writeToPkgDir(in, fileName);
        } catch (IOException e) {
            throw new PackageWriteException(fileName);
        }

        return file;

    }

    public File writeToPkgDir(InputStream in, String fileName) throws
        PackageWriteException {

        File file = new File(getPackageDir(), fileName);

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
            throw new PackageWriteException(fileName);
        }

        return file;

    }

    public InputStream createInputFromAsset(String fileName) throws IOException {

        BufferedInputStream in = null;
        in = new BufferedInputStream(mAssetManager.open(DOCUMENTS_DIRECTORY + File.separator + fileName));

        return in;

    }

    public File getPackageDir() {
        return mContext.getFilesDir();
    }

   public File createFile(String fileName) {

       File file =  new File(getPackageDir(), fileName);

       return file;

   }

    public boolean fileExists(String fileName) {

        File file =  createFile(fileName);

        return file != null || file.exists();

    }

}
