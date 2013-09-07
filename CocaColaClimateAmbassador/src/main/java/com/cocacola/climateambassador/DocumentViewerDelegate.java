package com.cocacola.climateambassador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.cocacola.climateambassador.models.FileType;
import com.cocacola.climateambassador.util.Toaster;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Singleton
public class DocumentViewerDelegate {

    private static final String AUTHORITY = "com.cocacola.climateambassador.files";

    @Inject AppPackageFileWriter mAppPackageFileWriter;
    @Inject Timber Log;

    private Context mContext;

    @Inject
    public DocumentViewerDelegate(Context mContext) {
        this.mContext = mContext;
    }

    public void startActivityForFile(Context context, String fileName) throws AppPackageFileWriter.FailedToWriteToPackageException {
        FileType type = FileType.getTypeForFilename(fileName);
        startActivityForFile(context, type, fileName);
    }

    public void startActivityForFile(Context context, FileType fileType, String fileName) throws AppPackageFileWriter.FailedToWriteToPackageException {

        if(isActivityForIntentAvailable(fileType.getMimeType())) {

            Uri path = null;

            try {

                path = createUriForFileName(fileType, fileName);

            }
            catch (FileNotInAppPackageException e) {

                Toaster.toast(mContext, "FileNotInAppPackage yet: " + fileName);

                // Move the file from assets to package directory
                try {
                    mAppPackageFileWriter.writeToPkgDir(fileName, fileType);
                    path = createUriForFileName(fileType, fileName);
                }
                catch (AppPackageFileWriter.FailedToWriteToPackageException fileWriterE) {
                    Toaster.toast(mContext, "There was an error loading the file");
                } catch (FileNotInAppPackageException e1) {
                    Toaster.toast(mContext, "There was an error opening the file: " + e.getMessage());
                } finally {
                    launchAcitivtyForValidatedPath(context, path, fileType);
                }

            }

            launchAcitivtyForValidatedPath(context, path, fileType);

        }
        else {
            Toaster.toast(mContext, "No app available to show file");
        }

    }

    private void launchAcitivtyForValidatedPath(Context context, Uri path, FileType fileType) {
        Intent intent = createViewerIntent(context, path, fileType);
        context.startActivity(intent);
    }

    public Uri createUriForFileName(FileType fileType, String fileName) throws FileNotInAppPackageException {

        File file = createFileForFileType(fileType, fileName);
        Uri path = createUriForFile(file);

        return path;

    }

    public Uri createUriForFile(File file) {

        Uri uri = FileProvider.getUriForFile(mContext, AUTHORITY, file);
        return uri;

    }

    public File getFileTypeDirectory(FileType fileType) {

        // Shouldn't be responsible for creating directory, that is AppPackageFileWriter's responsibility
        File fileTypeDir = null;
        fileTypeDir = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + fileType.getDirectory());

        return fileTypeDir;

    }

    public File createFileForFileType(FileType fileType, String fileName) throws FileNotInAppPackageException {

        File file =  new File(getFileTypeDirectory(fileType), fileName);

        if(!file.exists()) {
            throw new FileNotInAppPackageException();
        }

        return file;
    }

   public Intent createViewerIntent(Context context, Uri path, FileType fileType) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, fileType.getMimeType());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if(context instanceof CaApplication) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;

    }

    public class FileNotInAppPackageException extends Exception {

    }

    private boolean isActivityForIntentAvailable(String dataType) {
        // FIXME Implement this
        return true;
    }

    //    public void startPdfViewerActivity(Context context, String fileName) {
//
//        if(isActivityForIntentAvailable("application/pdf")) {
//            Uri path = createUriForFile(FileType.PDF, fileName);
//            Intent intent = createViewerIntent(context, path, FileType.PDF);
//            context.startActivity(intent);
//        }
//
//    }
//
//    public void startPptViewActivity(Context context, String fileName) {
//
//        if(isActivityForIntentAvailable(FileType.PPT.getMimeType())) {
//            Uri path = createUriForFile(FileType.PPT, fileName);
//            Intent intent = createViewerIntent(context, path, FileType.PPT);
//            context.startActivity(intent);
//        }
//
//    }
//
//    public void startActivityForDocType(Context context, FileType fileType, String fileName) {
//
//    }


}
