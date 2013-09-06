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

    public static Map<FileType, String> fileTypePathMap = new HashMap<FileType, String>();
    static {
        fileTypePathMap.put(FileType.PDF, "docs");
        fileTypePathMap.put(FileType.PPT, "docs");
        fileTypePathMap.put(FileType.VIDEO, "video");
    }

    private Context mContext;

    @Inject
    public DocumentViewerDelegate(Context mContext) {
        this.mContext = mContext;
    }

    public void startActivityForFileType(Context context, FileType fileType, String fileName) {

        if(isActivityForIntentAvailable(fileType.getMimeType())) {
            Uri path = createUriFromFileName(fileType, fileName);
            Intent intent = createViewerIntent(context, path, fileType);
            context.startActivity(intent);
        }

    }

    public Uri createUriFromFileName(FileType fileType, String fileName) {

        File file = getFileForFileType(fileType, fileName);
        Uri uri = FileProvider.getUriForFile(mContext, AUTHORITY, file);

        return uri;
    }

    public File getFileForFileType(FileType fileType, String fileName) {
        File docsDir = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + fileTypePathMap.get(fileType));
        return new File(docsDir + File.separator + fileName);
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

    private boolean isActivityForIntentAvailable(String dataType) {
        // FIXME Implement this
        return true;
    }

    //    public void startPdfViewerActivity(Context context, String fileName) {
//
//        if(isActivityForIntentAvailable("application/pdf")) {
//            Uri path = createUriFromFileName(FileType.PDF, fileName);
//            Intent intent = createViewerIntent(context, path, FileType.PDF);
//            context.startActivity(intent);
//        }
//
//    }
//
//    public void startPptViewActivity(Context context, String fileName) {
//
//        if(isActivityForIntentAvailable(FileType.PPT.getMimeType())) {
//            Uri path = createUriFromFileName(FileType.PPT, fileName);
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
