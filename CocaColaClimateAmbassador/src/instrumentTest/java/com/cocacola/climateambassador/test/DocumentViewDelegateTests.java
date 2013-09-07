package com.cocacola.climateambassador.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.DocumentViewerDelegate;
import com.cocacola.climateambassador.models.FileType;

import java.io.File;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class DocumentViewDelegateTests extends InstrumentationTestCase {

    @Inject
    DocumentViewerDelegate mDocumentViewerDelegate;

    protected Context mContext;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mContext = getInstrumentation().getTargetContext();

        ObjectGraph graph = ObjectGraph.create(new ClimateAmbassadorTestModule(mContext));
        graph.inject(this);
    }

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
        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";
        String expectedPath = "/data/data/com.cocacola.climateambassador/files/docs/coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";

        File file = null;
        try {
            file = mDocumentViewerDelegate.createFileForFileType(pdfType, fileName);
            assertTrue(file.exists());
            assertEquals(expectedPath, file.getAbsolutePath());
        } catch (DocumentViewerDelegate.FileNotInAppPackageException e) {
            failDueToFileNotInAppPackageException();
        }

    }

    private File getValidFile() {

        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";

        File fileTypeDir = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + FileType.PDF.getDirectory());

        File file =  new File(fileTypeDir + File.separator + fileName);

        return file;

    }

    public void testCreatesUriForFileType() throws DocumentViewerDelegate.FileNotInAppPackageException {

        FileType pdfType = FileType.PDF;
        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";

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

    private void failDueToFileNotInAppPackageException() {
        fail("FileNotInAppPackageException thrown");
    }

    public void testCreatesProperIntentForFileType() {

        FileType fileType = FileType.PDF;
        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";

        Uri path = mDocumentViewerDelegate.createUriForFile(getValidFile());

        Intent intent = mDocumentViewerDelegate.createViewerIntent(mContext, path, fileType);

        assertEquals(path, intent.getData());

    }

}
