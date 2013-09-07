package com.cocacola.climateambassador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.cocacola.climateambassador.models.FileType;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Singleton
public class DocumentViewerDelegate {

    private static final String AUTHORITY = "com.cocacola.climateambassador.foobar";

    @Inject AppPackageFileWriter mAppPackageFileWriter;

    private Context mContext;

    @Inject
    public DocumentViewerDelegate(Context mContext) {
        this.mContext = mContext;
    }

    public void startActivityForFileType(Context context, FileType fileType, String fileName) {

        if(isActivityForIntentAvailable(fileType.getMimeType())) {

            File file = null;
            Uri path = null;

            try {
                file = createFileForFileType(fileType, fileName);
                path = createUriForFile(file);
            } catch (FileNotInAppPackageException e) {
                file = mAppPackageFileWriter.moveFileToPackageDir(fileType, fileName);
                path = createUriForFile(file);
            } finally {
                Intent intent = createViewerIntent(context, path, fileType);
                context.startActivity(intent);
            }
        }

    }

    public Uri createUriForFile(File file) {

        Uri uri = FileProvider.getUriForFile(mContext, AUTHORITY, file);
        return uri;

    }

    public File createFileForFileType(FileType fileType, String fileName) throws FileNotInAppPackageException {

        File fileTypeDir = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + fileType.getDirectory());

        File file =  new File(fileTypeDir + File.separator + fileName);

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
