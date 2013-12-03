package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.data.json.FileType;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;

/**
 * Created by andrewlawton on 8/21/13.
 */

@Singleton
public class DocumentIntentBuilder {

    public class PreferredAppNotInstalledException extends Exception {
        public PreferredAppNotInstalledException(String detailMessage) {
            super(detailMessage);
        }
    }

    @Inject protected DocumentUriBuilder mUriBuilder;
    @Inject protected Timber Log;

    private Context mContext;

    @Inject
    public DocumentIntentBuilder(Context mContext) {
        this.mContext = mContext;
    }

   public Intent createViewerIntent(Context context, Uri path, String fileName) throws PreferredAppNotInstalledException {

        if(!PreferredAppHelper.isPreferredAppInstalled(context)) {
           throw new PreferredAppNotInstalledException("QuickOffice not installed");
        }

        FileType fileType = FileType.getTypeForFilename(fileName);
        String mimeType = fileType.getMimeType();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(path);
        intent.setType(mimeType);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        return intent;

    }

    public Intent createViewerIntent(Context context, Document document)
        throws PreferredAppNotInstalledException, AppPackageFileWriter.PackageWriteException {

        Uri path = mUriBuilder.createUriForFilename(context, document.getFileName());

        return createViewerIntent(context, path, document.getFileName());

    }

    private boolean isActivityForIntentAvailable(Intent intent) {

        // Verify it resolves
        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);

        return activities.size() > 0;

    }

}
