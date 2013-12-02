package com.cocacola.climateambassador.test.android.core.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import com.cocacola.climateambassador.data.DaoMaster;
import com.cocacola.climateambassador.data.Document;
import com.cocacola.climateambassador.test.data.AbsDataTests;
import java.io.File;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/27/13.
 */
public class DocumentUriBuilderTests extends AbsDataTests {

    private static final int KEY_DISPLAY_NAME = 0;
    private static final int KEY_SIZE = 1;

    @Inject DocumentUriBuilder mDocumentUriBuilder;
    @Inject AppPackageFileWriter mPackageFileWriter;
    @Inject DataSeeder mDataSeeder;

    @Override public void setUp() throws Exception {
        super.setUp();
        mDataSeeder.seed();
    }

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        mPackageFileWriter = null;
    }

    public void testCreatesUriForExistingFile() throws AppPackageFileWriter.PackageWriteException {

        for(Document document : getAllDocuments()) {
            assertCreatesUriForExistingFilename(document.getFileName());
        }

    }

    public void testCreatesUriForNonExistingFile() throws AppPackageFileWriter.PackageWriteException {

        for(Document document : getAllDocuments()) {
            assertCreatesUriForNonExistingFileName(document.getFileName());
        }

    }

    private List<Document> getAllDocuments() {

        List<Document> documentList = mDaoMaster.newSession().getDocumentDao().loadAll();

        assertNotNull(documentList);
        assertTrue("Should have more than one document", documentList.size() > 0);

        return documentList;
    }

    private void assertCreatesUriForExistingFilename(String fileName) throws AppPackageFileWriter.PackageWriteException {

        // Create the file if it doesn't exist
        createFileIfNotExists(fileName);

        Uri uri = mDocumentUriBuilder.createUriForFilename(fileName);

        assertValidUri(uri);


    }

    private void assertCreatesUriForNonExistingFileName(String fileName)

        throws AppPackageFileWriter.PackageWriteException {

        // File shouldn't exist yet
        Uri uri = mDocumentUriBuilder.createUriForFilename(fileName);

        assertValidUri(uri);


    }

    private void assertValidUri(Uri uri) {

        assertNotNull(uri);
        assertEquals("Authorities not the same", DocumentUriBuilder.AUTHORITY, uri.getAuthority());

        ContentResolver cr = mContext.getContentResolver();
        Cursor cur = cr.query(uri, null, null, null, null);

        assertNotNull("Cursor was null", cur);

        cur.moveToFirst();

        // Display Name
        String filename = cur.getString(KEY_DISPLAY_NAME);
        assertNotNull("File name was null", filename);
        assertTrue("File name was empty", !filename.isEmpty());

        // Assert file size is > 0
        Long size = cur.getLong(KEY_SIZE);
        assertTrue("File size was 0 for " + filename, size > 0);

        // Assert we can get a File pointed to filename
        File fileInPackage = mPackageFileWriter.createFile(filename);
        assertNotNull("File was null in package", fileInPackage);
        assertEquals("File should exist", true, fileInPackage.exists());

    }

    private void createFileIfNotExists(String filename) throws AppPackageFileWriter.PackageWriteException {
        mPackageFileWriter.writeToPkgDir(filename);
    }

}
