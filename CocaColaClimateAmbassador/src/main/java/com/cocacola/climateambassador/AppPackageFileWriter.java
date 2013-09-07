package com.cocacola.climateambassador;

import android.content.Context;
import android.content.res.AssetManager;

import com.cocacola.climateambassador.models.FileType;

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
    private AssetManager mAssetManager;

    @Inject
    public AppPackageFileWriter(Context context) {
        mContext = context;
        mAssetManager = mContext.getAssets();
    }



    public InputStream createInputFromAsset(String directory, String fileName) throws IOException {

        BufferedInputStream in = null;
        in = new BufferedInputStream(mAssetManager.open(directory + File.separator + fileName));

        return in;

    }

    public File getPackageDir() {
        return mContext.getFilesDir();
    }

    public String createNewDirectoryString(String directory) {
        return getPackageDir() + File.separator + directory;
    }

    public File createDirectoryItNotExists(String directory) {

        File directoryFile = new File(createNewDirectoryString(directory));

        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }

        return directoryFile;

    }

    public void writeToPkgDir(String fileName, FileType fileType) throws FailedToWriteToPackageException {

        InputStream in = null;
        try {
            in = createInputFromAsset(fileType.getDirectory(), fileName);
            writeToPkgDir(in, fileType.getDirectory(), fileName);
        } catch (IOException e) {
            throw new FailedToWriteToPackageException(fileName);
        }
    }

    public void writeToPkgDir(InputStream in, String directory, String fileName) throws FailedToWriteToPackageException {

        // Create the directory if it doesn't exist
        File directoryFile = createDirectoryItNotExists(directory);

        File file = new File(directoryFile, fileName);

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
            throw new FailedToWriteToPackageException(fileName);
        }

    }

    public class FailedToWriteToPackageException extends Exception {

        private String fileName;

        public FailedToWriteToPackageException(String fileName) {
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

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

}
