package com.cocacola.climateambassador.test;

import android.content.Context;
import android.test.InstrumentationTestCase;

import com.cocacola.climateambassador.AppPackageFileWriter;
import com.cocacola.climateambassador.models.FileType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class AppPackageFileWriterTests extends InstrumentationTestCase {

    @Inject
    AppPackageFileWriter mAppPackageFileWriter;

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
        mAppPackageFileWriter = null;
    }

    public void testCreatesInputFromAsset() {

        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";
        FileType fileType = FileType.getTypeForExtension("pdf");

        InputStream in = null;
        try {
            in = mAppPackageFileWriter.createInputFromAsset(fileType.getDirectory(), fileName);
            assertNotNull(in);
        } catch (IOException e) {
           fail(e.getMessage());
        }

    }

    public void testBadFileInputThrowsException() {

        String fileName = "foobar.pdf";
        FileType fileType = FileType.getTypeForExtension("pdf");

        InputStream in = null;
        try {
            in = mAppPackageFileWriter.createInputFromAsset(fileType.getDirectory(), fileName);
            fail("Should have thrown IO Exception");
        } catch (IOException e) {
            // Threw IO Exception as expected
        }

    }

    public void testCreatesDirectoryIfNotExists() {

        File directory = mAppPackageFileWriter.createDirectoryItNotExists(FileType.PDF.getDirectory());

        String expectedPath = "/data/data/com.cocacola.climateambassador/files/docs";

        assertNotNull(directory);
        assertEquals(expectedPath, directory.getAbsolutePath());


    }

    private InputStream getValidInputStream() {

        String fileName = "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";
        FileType fileType = FileType.getTypeForExtension("pdf");

        InputStream in = null;

        try {
            in = mAppPackageFileWriter.createInputFromAsset(fileType.getDirectory(), fileName);
            assertNotNull(in);
        } catch (IOException e) {
            fail(e.getMessage());
        }

        return in;

    }

    private String[] getAllFilesInDocsDir() {

        File dir = new File(mAppPackageFileWriter.getPackageDir() + File.separator + FileType.PDF.getDirectory());

        String[] files = dir.list();

        return files;

    }

    private void assertFileIsInDir(String fileName) {

        boolean fileExists = false;

        for(String file : getAllFilesInDocsDir()) {
            if(file.equals(fileName)) {
                fileExists = true;
                break;
            }
        }

        assertEquals(fileExists, true);

    }

    private String getValidFileName() {
        return "coca-cola-Business-Case-for-Good-Fertilizer-Use-in-Citrus.pdf";
    }

    private FileType getValidFileType() {
        return FileType.PDF;
    }

    public void testWritesPkgToDir() {

        InputStream in = getValidInputStream();
        String fileName = getValidFileName();
        FileType fileType = getValidFileType();

        try {
            mAppPackageFileWriter.writeToPkgDir(in, fileType.getDirectory(), fileName);
            assertFileIsInDir(fileName);
        } catch (AppPackageFileWriter.FailedToWriteToPackageException e) {
            e.printStackTrace();
            fail("Threw FailedToWriteToPackageException");
        }

    }

}
