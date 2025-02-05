package com.cocacola.climateambassador.test.android.core.util;

import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.data.json.FileType;
import com.cocacola.climateambassador.test.CaTestModule;
import dagger.ObjectGraph;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;

/**
 * Created by andrewlawton on 8/24/13.
 */
public class AppPackageFileWriterTests extends CaFileTestCase {

    @Inject protected AppPackageFileWriter mAppPackageFileWriter;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mContext = getInstrumentation().getTargetContext();

        ObjectGraph graph = ObjectGraph.create(new CaTestModule(mContext));
        graph.inject(this);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        mAppPackageFileWriter = null;
    }

    public void testCreatesInputFromAsset() {

        String fileName = VALID_PDF_FILENAME;
        FileType fileType = FileType.getTypeForExtension("pdf");

        InputStream in = null;
        try {
            in = mAppPackageFileWriter.createInputFromAsset(fileName);
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
            in = mAppPackageFileWriter.createInputFromAsset(fileName);
            fail("Should have thrown IO Exception");
        } catch (IOException e) {
            // Threw IO Exception as expected
        }

    }

    public void testWritesPkgToDir() {

        InputStream in = getValidInputStream();
        String fileName = getValidFileName();
        FileType fileType = getValidFileType();

        try {
            mAppPackageFileWriter.writeToPkgDir(in, fileName);
            assertFileIsInDir(fileName);
        } catch (AppPackageFileWriter.PackageWriteException e) {
            e.printStackTrace();
            fail("Threw FailedToWriteToPackageException");
        }

    }

    public void testReturnsFileAfterWritingToPkgDir() {

        InputStream in = getValidInputStream();
        String fileName = getValidFileName();
        FileType fileType = getValidFileType();

        try {
            mAppPackageFileWriter.writeToPkgDir(in, fileName);
            assertFileIsInDir(fileName);
        } catch (AppPackageFileWriter.PackageWriteException e) {
            e.printStackTrace();
            fail("Threw FailedToWriteToPackageException");
        }

    }

    public void testCreatesFile() {

        String fileName = VALID_PDF_FILENAME;
        FileType fileType = FileType.getTypeForFilename(fileName);
        String expectedPath = "/data/data/com.cocacola.climateambassador/files/docs/" + VALID_PDF_FILENAME;

        File file = mAppPackageFileWriter.createFile(fileName);

        assertTrue(file.exists());
        assertEquals(expectedPath, file.getAbsolutePath());


    }

    private InputStream getValidInputStream() {

        String fileName = VALID_PDF_FILENAME;
        FileType fileType = FileType.getTypeForExtension("pdf");

        InputStream in = null;

        try {
            in = mAppPackageFileWriter.createInputFromAsset(fileName);
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
        return VALID_PDF_FILENAME;
    }

    private FileType getValidFileType() {
        return FileType.PDF;
    }

}
