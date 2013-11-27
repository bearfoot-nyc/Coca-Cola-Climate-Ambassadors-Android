package com.cocacola.climateambassador.test.android.core.util;

import android.content.Intent;
import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.core.util.DocumentViewerDelegate;
import com.cocacola.climateambassador.data.json.FileType;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentViewDelegateTests extends CaFileTestCase {

    @Inject protected DocumentUriBuilder mDocumentUriBuilder;
    @Inject protected DocumentViewerDelegate mDocumentViewerDelegate;
    @Inject protected Timber Log;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDocumentViewerDelegate = null;
    }

    public void testThrowsNoFileExistsException() {

        //FileType pdfType = FileType.PDF;
        //String fileName = "someImaginaryFile";
        //
        //File file = null;
        //try {
        //    file = mDocumentViewerDelegate.createFileForFileType(pdfType, fileName);
        //    fail("Expected FileNotInAppPackageException");
        //} catch (DocumentViewerDelegate.FileNotInAppPackageException e) {
        //    // Testing if exception was thrown, PASS!
        //}


    }

    public void testCreatesProperIntentForFileType()
        throws AppPackageFileWriter.PackageWriteException {

        String fileName = VALID_PDF_FILENAME;
        FileType fileType = FileType.getTypeForFilename(fileName);

        Uri path = mDocumentUriBuilder.createUriForFilename(fileName);

        Intent intent = mDocumentViewerDelegate.createViewerIntent(mContext, path, fileType);

        assertEquals(path, intent.getData());

    }

    public void testCatchesActivityNotFoundException() {

        // TODO Implement this.  Being a good TDDer

    }

    public void testDetectsQuickOfficeIsInstalled() {

        // FIXME How can you test this?  Can't uninstall/install packages totally programmatically

        //
        //boolean isQuickOfficeInstalled = mDocumentViewerDelegate.isQuickOfficeInstalled();
        //
        //assertFalse("Quick office should not be installed yet", isQuickOfficeInstalled);

    }

    public void testLaunchesDocumentInQuickOffice() throws AppPackageFileWriter.PackageWriteException {

        // FIXME Test that the Intent we use for start activity for file has the correct package name
        // FIXME We can't rely on checking if QuickOffice is there because can't install/uninstall packages

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

    private void failDueToFileNotInAppPackageException() {
        fail("FileNotInAppPackageException thrown");
    }

}
