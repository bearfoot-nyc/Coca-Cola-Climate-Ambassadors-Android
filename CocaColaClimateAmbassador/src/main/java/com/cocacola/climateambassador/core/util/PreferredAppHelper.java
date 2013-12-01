package com.cocacola.climateambassador.core.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.List;

/**
 * Created by realandylawton on 12/1/13.
 */
public class PreferredAppHelper {

    private static final String PREFERRED_APP_PACKAGE_NAME = "com.quickoffice.android";

    public static boolean isPreferredAppInstalled(Context context) {

        PackageManager pm = context.getPackageManager();
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

    public static void launchPreferredAppInPlayStore(Context context) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("market://details?id=" + PREFERRED_APP_PACKAGE_NAME));
        context.startActivity(intent);

    }
}
