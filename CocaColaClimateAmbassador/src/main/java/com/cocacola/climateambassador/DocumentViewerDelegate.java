package com.cocacola.climateambassador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Singleton
public class DocumentViewerDelegate {

    private Context mContext;

    private static final String DOCUMENT_PATH = "res/raw/";

    private static final String PDF_TYPE = "application/pdf";

    @Inject
    public DocumentViewerDelegate(Context mContext) {
        this.mContext = mContext;
    }

    public void startPdfViewerActivity(String fileName) {
        startPdfViewerActivity(fileName, mContext);
    }

    public void startPdfViewerActivity(String fileName, Context context) {

        if(isActivityForIntentAvailable("application/pdf")) {
            Uri path = createUriFromFileName(fileName);
            Intent intent = createViewerIntent(context, path, PDF_TYPE);

            context.startActivity(intent);
        }

    }

    private Uri createUriFromFileName(String fileName) {
        File pdfFile = new File(DOCUMENT_PATH + fileName);
        return Uri.fromFile(pdfFile);
    }

    private Intent createViewerIntent(Context context, Uri path, String docType) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, docType);

        if(context instanceof CaApplication) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;

    }

    private boolean isActivityForIntentAvailable(String dataType) {
        return true;
    }

}
