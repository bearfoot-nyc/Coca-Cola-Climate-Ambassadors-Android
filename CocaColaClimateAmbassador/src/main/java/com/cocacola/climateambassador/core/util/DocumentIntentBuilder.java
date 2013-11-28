package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.cocacola.climateambassador.core.CaApplication;
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

    private static final String PREFERRED_APP_PACKAGE_NAME = "com.quickoffice.android";

    @Inject protected AppPackageFileWriter mAppPackageFileWriter;
    @Inject protected Timber Log;

    private Context mContext;

    @Inject
    public DocumentIntentBuilder(Context mContext) {
        this.mContext = mContext;
    }

   public Intent createViewerIntent(Context context, Uri path, String fileName) throws PreferredAppNotInstalledException {

        if(!isPreferredAppInstalled()) {
           throw new PreferredAppNotInstalledException("QuickOffice not installed");
        }

        FileType fileType = FileType.getTypeForFilename(fileName);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(path, fileType.getMimeType());
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if(context instanceof CaApplication) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;

    }

    public boolean isPreferredAppInstalled() {

        PackageManager pm = mContext.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        boolean isInstalled = false;
        for(ApplicationInfo pkg : packages) {
            if(PREFERRED_APP_PACKAGE_NAME.equals(pkg.packageName)) {
                isInstalled = true;
                break;
            }
        }

        return isInstalled;

    }

    private void launchQuickOfficeInPlayStore() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("market://details?id=" + PREFERRED_APP_PACKAGE_NAME));
        mContext.startActivity(intent);

    }

    private boolean isActivityForIntentAvailable(Intent intent) {

        // Verify it resolves
        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);

        return activities.size() > 0;

    }

}
