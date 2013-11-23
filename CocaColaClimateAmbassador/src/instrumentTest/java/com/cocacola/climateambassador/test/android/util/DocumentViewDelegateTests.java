package com.cocacola.climateambassador.test.android.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.cocacola.climateambassador.AppPackageFileWriter;
import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.json.FileType;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentViewDelegateTests extends CaFileTestCase {

    @Inject protected DocumentViewerDelegate mDocumentViewerDelegate;
    @Inject protected Timber Log;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mDocumentViewerDelegate = null;
    }

    public void testGetsFileTypeForExtension() {

        String extension = "pdf";

        FileType fileType = FileType.getTypeForExtension(extension);

        assertNotNull(fileType);
        assertEquals(extension, fileType.getExtension());

    }

    public void testGetsFileTypeForFilename() {

        String fileName = VALID_PPT_FILENAME;
        FileType expectedType = FileType.PPT;

        FileType type = FileType.getTypeForFilename(fileName);

        assertNotNull(type);
        assertEquals(expectedType, type);

    }

    public void testBadExtensionHasNoFileType() {

        String extension = "badFileType";

        FileType fileType = FileType.getTypeForExtension(extension);

        assertNull(fileType);

    }

    public void testThrowsNoFileExistsException() {

        FileType pdfType = FileType.PDF;
        String fileName = "someImaginaryFile";

        File file = null;
        try {
            file = mDocumentViewerDelegate.createFileForFileType(pdfType, fileName);
            fail("Expected FileNotInAppPackageException");
        } catch (DocumentViewerDelegate.FileNotInAppPackageException e) {
            // Testing if exception was thrown, PASS!
        }


    }

    public void testGetsProperFileTypeDirectory() {

        FileType pdfType = FileType.PDF;
        String expectedDir = "/data/data/com.cocacola.climateambassador/files/docs";

        File fileTypeDir = mDocumentViewerDelegate.getFileTypeDirectory(pdfType);

        assertEquals(expectedDir, fileTypeDir.getAbsolutePath());


    }

    public void testCreatesFileForFileType() {

        FileType pdfType = FileType.PDF;
        String fileName = VALID_PDF_FILENAME;
        String expectedPath = "/data/data/com.cocacola.climateambassador/files/docs/" + VALID_PDF_FILENAME;

        File file = null;
        try {
            file = mDocumentViewerDelegate.createFileForFileType(pdfType, fileName);
            assertTrue(file.exists());
            assertEquals(expectedPath, file.getAbsolutePath());
        } catch (DocumentViewerDelegate.FileNotInAppPackageException e) {
            failDueToFileNotInAppPackageException();
        }

    }

    public void testCreatesUriForFileType() throws DocumentViewerDelegate.FileNotInAppPackageException {

        FileType pdfType = FileType.PDF;
        String fileName = VALID_PDF_FILENAME;

        File file = null;
        Uri uri = null;
        try {
            file = mDocumentViewerDelegate.createFileForFileType(pdfType, fileName);

            uri = mDocumentViewerDelegate.createUriForFile(file);

            assertNotNull(uri);

        } catch (DocumentViewerDelegate.FileNotInAppPackageException e) {
            failDueToFileNotInAppPackageException();
        }

    }

    public void testCreatesProperIntentForFileType() {

        FileType fileType = FileType.PDF;

        Uri path = mDocumentViewerDelegate.createUriForFile(getValidFile());

        Intent intent = mDocumentViewerDelegate.createViewerIntent(mContext, path, fileType);

        assertEquals(path, intent.getData());

    }

    public void testCatchesActivityNotFoundException() {

        // TODO Implement this.  Being a good TDDer

    }

    public void testDetectsQuickOfficeIsInstalled() {

        boolean isQuickOfficeInstalled = mDocumentViewerDelegate.isQuickOfficeInstalled();

        assertFalse("Quick office should not be installed yet", isQuickOfficeInstalled);

    }

    public void testLaunchesDocumentInQuickOffice()
        throws AppPackageFileWriter.FailedToWriteToPackageException {

        String fileName = VALID_PDF_FILENAME;
        String playStorePkgName = "com.android.vending";

        mDocumentViewerDelegate.startActivityForFile(mContext, fileName);

        // Get current running app
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

        // get the info from the currently running task
        List< ActivityManager.RunningTaskInfo > taskInfo = am.getRunningTasks(1);

        ComponentName componentInfo = taskInfo.get(0).topActivity;

        assertEquals("Current package is not Play Store", playStorePkgName, componentInfo.getPackageName());


    }

    private void failDueToFileNotInAppPackageException() {
        fail("FileNotInAppPackageException thrown");
    }

}
