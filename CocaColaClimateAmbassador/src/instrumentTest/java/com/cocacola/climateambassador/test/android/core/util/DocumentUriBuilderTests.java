package com.cocacola.climateambassador.test.android.core.util;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
import java.io.File;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/27/13.
 */
public class DocumentUriBuilderTests extends CaFileTestCase {

    @Inject DocumentUriBuilder mDocumentUriBuilder;
    @Inject AppPackageFileWriter mPackageFileWriter;

    @Override protected void tearDown() throws Exception {
        super.tearDown();
        mPackageFileWriter = null;
    }

    public void testCreatesUriForExistingFilename() throws AppPackageFileWriter.PackageWriteException {

        String fileName = VALID_PDF_FILENAME;

        // Create the file if it doesn't exist
        createFileIfNotExists(fileName);

        Uri uri = mDocumentUriBuilder.createUriForFilename(fileName);

        assertValidUri(uri);


    }

    public void testCreatesUriForNonExistingFileName()
        throws AppPackageFileWriter.PackageWriteException {

        String fileName = VALID_PPT_FILENAME;

        // File shouldn't exist yet
        Uri uri = mDocumentUriBuilder.createUriForFilename(fileName);

        assertValidUri(uri);


    }

    private void assertValidUri(Uri uri) {

        assertNotNull(uri);
        assertEquals("Authorities not the same", DocumentUriBuilder.AUTHORITY, uri.getAuthority());

        //ContentResolver cr = mContext.getContentResolver();
        //Cursor cur = cr.query(uri, null, null, null, null);
        //if(cur != null) {
        //    cur.moveToFirst();
        //    String filePath = cur.getString(0);
        //
        //    if (filePath == null || filePath.isEmpty()) {
        //        // data not set
        //    } else if((new File(filePath)).exists()){
        //
        //        System.out.println("Tittay sprinkles!");
        //
        //    } else {
        //        // File was not found
        //        // this is binary data
        //    }
        //} else {
        //    // content Uri was invalid or some other error occurred
        //}

    }

    private void createFileIfNotExists(String filename) throws AppPackageFileWriter.PackageWriteException {
        mPackageFileWriter.writeToPkgDir(filename);
    }

}
