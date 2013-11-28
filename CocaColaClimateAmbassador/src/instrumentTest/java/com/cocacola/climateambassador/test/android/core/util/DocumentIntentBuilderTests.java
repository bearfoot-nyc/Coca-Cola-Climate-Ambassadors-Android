package com.cocacola.climateambassador.test.android.core.util;

import android.content.Intent;
import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentIntentBuilder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.data.json.FileType;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentIntentBuilderTests extends CaFileTestCase {

    @Inject protected DocumentUriBuilder mDocumentUriBuilder;
    @Inject protected DocumentIntentBuilder mDocumentIntentBuilder;
    @Inject protected Timber Log;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDocumentIntentBuilder = null;
    }

    public void testCreatesProperIntentForFileType()
        throws AppPackageFileWriter.PackageWriteException,
        DocumentIntentBuilder.PreferredAppNotInstalledException {

        String fileName = VALID_PDF_FILENAME;

        Uri path = mDocumentUriBuilder.createUriForFilename(fileName);

        Intent intent = mDocumentIntentBuilder.createViewerIntent(mContext, path, fileName);

        assertEquals(path, intent.getData());

    }

    public void testDetectsQuickOfficeIsInstalled() {

        // FIXME How can you test this?  Can't uninstall/install packages totally programmatically

        //
        //boolean isPreferredAppInstalled = mDocumentViewerDelegate.isPreferredAppInstalled();
        //
        //assertFalse("Quick office should not be installed yet", isPreferredAppInstalled);

    }

    public void testLaunchesDocumentInQuickOffice() throws AppPackageFileWriter.PackageWriteException {

        //String fileName = VALID_PDF_FILENAME;
        //String playStorePkgName = "com.android.vending";
        //
        //mDocumentViewerDelegate.startActivityForFile(mContext, fileName);
        //
        //// Get current running app
        //ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        //
        //// get the info from the currently running task
        //List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);
        //
        //ComponentName componentInfo = taskInfo.get(0).topActivity;
        //
        //assertEquals("Current package is not Play Store", playStorePkgName, componentInfo.getPackageName());


    }

}
