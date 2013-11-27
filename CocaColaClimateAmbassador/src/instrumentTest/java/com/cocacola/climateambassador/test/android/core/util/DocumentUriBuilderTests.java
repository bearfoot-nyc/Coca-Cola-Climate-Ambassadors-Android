package com.cocacola.climateambassador.test.android.core.util;

import android.net.Uri;
import com.cocacola.climateambassador.core.util.AppPackageFileWriter;
import com.cocacola.climateambassador.core.util.DocumentUriBuilder;
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

    }

    private void createFileIfNotExists(String filename) throws AppPackageFileWriter.PackageWriteException {
        mPackageFileWriter.writeToPkgDir(filename);
    }

}
